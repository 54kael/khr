<template>
  <div>
    <el-form
      :rules="rules"
      ref="loginForm"
      v-loading="loading"
      element-loading-text="正在登录..."
      element-loading-spinner="el-icon-loading"
      element-loading-background="rgba(0, 0, 0, 0.8)"
      :model="loginForm"
      class="loginContainer"
    >
      <h3 class="loginTitle">系统登录</h3>
      <el-form-item prop="username">
        <el-input
          size="normal"
          type="text"
          v-model="loginForm.username"
          auto-complete="off"
          placeholder="请输入用户名"
        ></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input
          size="normal"
          type="password"
          v-model="loginForm.password"
          auto-complete="off"
          placeholder="请输入密码"
        ></el-input>
      </el-form-item>
      <el-checkbox size="normal" class="loginRemember" v-model="checked">记住密码</el-checkbox>
      <el-button size="normal" type="primary" style="width: 100%;" @click="submitLogin">登录</el-button>
    </el-form>
  </div>
</template>

<script>
const Base64 = require("js-base64").Base64;
export default {
  name: "Login",
  data() {
    return {
      checked: false,
      loading: false,
      loginForm: {
        username: "",
        password: "",
      },
      rules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
        ],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
      },
    };
  },
  methods: {
    submitLogin() {
      this.savePassword();

      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          this.loading = true;
          this.$postRequest("/hr/login", this.loginForm).then((resp) => {
              console.log(resp);
            if (resp.code == "00000") {
                console.log(resp.code);
              this.loading = false;
              localStorage.setItem("token", resp.data.token);
              this.$store.commit("initRoutes", []);
              let path = this.$route.query.redirect;
              this.$router.replace(
                path == "/" || path == undefined ? "/home" : path
              );
            } else {
              this.loading = false;
              this.$message.error(resp.message);
            }
          });
        } else {
          return false;
        }
      });
    },

    getCookie(key) {
      if (document.cookie.length > 0) {
        let start = document.cookie.indexOf(key + "=");
        if (start !== -1) {
          start = start + key.length + 1;
          var end = document.cookie.indexOf(";", start);
          if (end === -1) end = document.cookie.length;
          return unescape(document.cookie.substring(start, end));
        }
      }
      return "";
    },

    setCookie(cName, value, expiredays) {
      let exdate = new Date();
      exdate.setDate(exdate.getDate() + expiredays);
      document.cookie =
        cName +
        "=" +
        decodeURIComponent(value) +
        (expiredays == null ? "" : ";expires=" + exdate.toGMTString());
    },

    savePassword() {
      if (this.checked) {
        this.setCookie("username", this.loginForm.username);
        let passWord = Base64.encode(this.loginForm.password);
        this.setCookie("password", passWord);
        this.setCookie("checked", true);
      } else {
        this.setCookie("checked", false);
        this.setCookie("username", "");
        this.setCookie("password", "");
      }
    },
  },
  mounted() {
    if (this.getCookie("checked") == "true") {
      this.checked = true;
    } else {
      this.checked = false;
    }
    this.loginForm.username = this.getCookie("username");
    this.loginForm.password = Base64.decode(this.getCookie("password"));
  },
};
</script>

<style>
.loginContainer {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 180px auto;
  width: 350px;
  padding: 15px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.loginTitle {
  margin: 15px auto 20px auto;
  text-align: center;
  color: #505458;
}

.loginRemember {
  text-align: left;
  margin: 0px 0px 15px 0px;
}
.el-form-item__content {
  display: flex;
  align-items: center;
}
</style>
