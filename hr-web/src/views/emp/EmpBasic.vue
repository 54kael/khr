<template>
  <div>
    <div style="display:flex; justify-content:flex-end">
      <el-button type="success" @click="exportData" icon="el-icon-download">导出数据</el-button>
      <el-button type="primary" icon="el-icon-plus" @click="showAddEmpView">添加用户</el-button>
    </div>
    <div style="margin-top:10px">
      <el-card class="box-card">
        <table style="line-height:40px">
          <tr>
            <td>民族:</td>
            <td>
              <el-select v-model="searchCondition.nation" clearable placeholder="请选择">
                <el-option
                  v-for="nation in nations"
                  :key="nation.id"
                  :label="nation.name"
                  :value="nation.name"
                ></el-option>
              </el-select>
            </td>
            <td style="width:30px"></td>
            <td>政治面貌:</td>
            <td>
              <el-select v-model="searchCondition.politic" clearable placeholder="请选择">
                <el-option
                  v-for="item in politics"
                  :key="item.id"
                  :label="item.name"
                  :value="item.name"
                ></el-option>
              </el-select>
            </td>
            <td style="width:30px"></td>
            <td>职位:</td>
            <td>
              <el-select v-model="searchCondition.posId" clearable placeholder="请选择">
                <el-option
                  v-for="item in positions"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                ></el-option>
              </el-select>
            </td>
            <td style="width:30px"></td>
            <td>入职日期:</td>
            <td>
              <el-date-picker
                value-format="yyyy-MM-dd"
                v-model="searchCondition.date"
                type="daterange"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
              ></el-date-picker>
            </td>
          </tr>
          <tr>
            <td>姓名:</td>
            <td>
              <el-input clearable v-model="searchCondition.name" placeholder="姓名"></el-input>
            </td>
            <td style="width:30px"></td>
            <td>所属部门:</td>
            <td>
              <el-cascader
                v-model="searchCondition.departmentId"
                :show-all-levels="false"
                :options="departments"
                :props="defaultProps"
                clearable
              ></el-cascader>
            </td>
            <td style="width:30px"></td>
            <td>职称:</td>
            <td>
              <el-select v-model="searchCondition.jobLevelId" clearable placeholder="请选择">
                <el-option
                  v-for="item in jobLevels"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                ></el-option>
              </el-select>
            </td>
            <td style="width:30px"></td>
            <td>聘用形式:</td>
            <td>
              <el-radio v-model="searchCondition.engageForm" label="劳动合同">劳动合同</el-radio>
              <el-radio v-model="searchCondition.engageForm" label="劳务合同">劳务合同</el-radio>
              <el-button size="mini" type="primary" @click="search">搜索</el-button>
            </td>
          </tr>
        </table>
        <el-divider></el-divider>
        <el-table
          height="510"
          v-loading="loading"
          element-loading-text="正在加载..."
          element-loading-spinner="el-icon-loading"
          element-loading-background="rgba(0, 0, 0, 0.8)"
          stripe
          :data="employees"
          border
          style="width: 100%;"
        >
          <el-table-column align="center" prop="workId" label="工号" width="100" />
          <el-table-column align="center" prop="name" label="姓名" width="100" />
          <el-table-column align="center" prop="gender" label="性别" width="60" />
          <el-table-column align="center" prop="birthday" label="出生日期" width="100" />
          <el-table-column align="center" prop="department" label="所属部门" width="100" />
          <el-table-column align="center" prop="pos" label="职位" width="100" />
          <el-table-column align="center" prop="jobLevel" label="职称" width="100" />
          <el-table-column align="center" prop="phone" label="电话号码" width="100" />
          <el-table-column align="center" prop="email" label="电子邮件" width="160" />
          <el-table-column align="center" prop="tiptopDegree" label="最高学历" width="80" />
          <el-table-column align="center" prop="wedlock" label="婚姻状况" width="80" />
          <el-table-column align="center" prop="nation" label="民族" width="80" />
          <el-table-column align="center" prop="nativePlace" label="籍贯" width="100" />
          <el-table-column align="center" prop="politic" label="政治面貌" width="100" />
          <el-table-column align="center" prop="address" label="联系地址" width="200" />
          <el-table-column align="center" prop="engageForm" label="聘用形式" width="100" />
          <el-table-column align="center" prop="beginDate" label="入职日期" width="100" />
          <el-table-column align="center" prop="conversionTime" label="转正日期" width="100" />
          <el-table-column align="center" prop="beginContract" label="合同起始日期" width="100" />
          <el-table-column align="center" prop="endContract" label="合同截至日期" width="100" />
          <el-table-column align="center" prop="contractTerm" label="合同期限" width="70" />
          <el-table-column align="center" prop="idCard" label="身份证" width="160" />
          <el-table-column fixed="right" label="操作" width="100">
            <template slot-scope="scope">
              <el-button @click="showUpdateEmp(scope.row)" type="text" size="small">编辑</el-button>
              <el-button type="text" size="small">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          background
          @current-change="getEmployeeByPage(currentPage,findCondition)"
          :current-page.sync="currentPage"
          :page-size="10"
          layout="total, prev, pager, next"
          :total="total"
        ></el-pagination>
      </el-card>
    </div>

    <el-dialog
      :close-on-click-modal="false"
      :show-close="false"
      :title="title"
      :visible.sync="dialogVisible"
      width="50%"
      :before-close="handleClose"
    >
      <el-form
        label-position="right"
        :model="emp"
        :rules="rules"
        ref="emp"
        :inline="true"
        label-width="120px"
      >
        <el-form-item label="姓名" prop="name">
          <el-input v-model="emp.name"></el-input>
        </el-form-item>
        <el-form-item label="工号" v-if="title=='更改员工信息'">
          <el-input v-model="emp.workId" disabled></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-radio v-model="emp.gender" label="男">男</el-radio>
          <el-radio v-model="emp.gender" label="女">女</el-radio>
        </el-form-item>
        <el-form-item label="出生日期" prop="birthday">
          <el-date-picker
            value-format="yyyy-MM-dd"
            v-model="emp.birthday"
            type="date"
            placeholder="选择日期"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="婚姻状况" prop="wedlock">
          <el-radio v-model="emp.wedlock" label="未婚">未婚</el-radio>
          <el-radio v-model="emp.wedlock" label="已婚">已婚</el-radio>
          <el-radio v-model="emp.wedlock" label="离异">离异</el-radio>
        </el-form-item>
        <el-form-item label="身份证" prop="idCard">
          <el-input v-model="emp.idCard"></el-input>
        </el-form-item>
        <el-form-item label="政治面貌" prop="politic">
          <el-select v-model="emp.politic" clearable placeholder="请选择">
            <el-option
              v-for="item in politics"
              :key="item.id"
              :label="item.name"
              :value="item.name"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="民族" prop="nation">
          <el-select v-model="emp.nation" clearable placeholder="请选择">
            <el-option
              v-for="nation in nations"
              :key="nation.id"
              :label="nation.name"
              :value="nation.name"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="籍贯" prop="nativePlace">
          <el-input v-model="emp.nativePlace" placeholder="籍贯"></el-input>
        </el-form-item>
        <el-form-item label="电子邮箱" prop="email">
          <el-input v-model="emp.email"></el-input>
        </el-form-item>
        <el-form-item label="联系地址" prop="address">
          <el-input v-model="emp.address"></el-input>
        </el-form-item>
        <el-form-item label="职位" prop="posId">
          <el-select v-model="emp.posId" clearable placeholder="请选择">
            <el-option v-for="item in positions" :key="item.id" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="职称" prop="jobLevelId">
          <el-select v-model="emp.jobLevelId" clearable placeholder="请选择">
            <el-option v-for="item in jobLevels" :key="item.id" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="部门" prop="departmentId">
          <el-cascader
            v-model="emp.departmentId"
            :show-all-levels="false"
            :options="departments"
            :props="defaultProps"
            clearable
          ></el-cascader>
        </el-form-item>
        <el-form-item label="电话号码" prop="phone">
          <el-input v-model="emp.phone"></el-input>
        </el-form-item>
        <el-form-item label="学历" prop="tiptopDegree">
          <el-select v-model="emp.tiptopDegree" clearable placeholder="请选择">
            <el-option
              v-for="(item,index) in tiptopDegrees"
              :key="index"
              :label="item"
              :value="item"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="毕业院校" prop="school">
          <el-input v-model="emp.school"></el-input>
        </el-form-item>
        <el-form-item label="专业名称" prop="specialty">
          <el-input v-model="emp.specialty"></el-input>
        </el-form-item>
        <el-form-item label="入职日期" prop="beginDate">
          <el-date-picker
            value-format="yyyy-MM-dd"
            v-model="emp.beginDate"
            type="date"
            placeholder="选择日期"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="合同起始日期" prop="beginContract">
          <el-date-picker
            value-format="yyyy-MM-dd"
            v-model="emp.beginContract"
            type="date"
            placeholder="选择日期"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="合同结束日期" prop="endContract">
          <el-date-picker
            value-format="yyyy-MM-dd"
            v-model="emp.endContract"
            type="date"
            placeholder="选择日期"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="聘用形式" prop="engageForm">
          <el-radio v-model="emp.engageForm" label="劳动合同">劳动合同</el-radio>
          <el-radio v-model="emp.engageForm" label="劳务合同">劳务合同</el-radio>
        </el-form-item>
        <el-form-item label="账号" prop="username">
          <el-input v-model="emp.username"></el-input>
        </el-form-item>
        <el-form-item label="角色" prop="roles">
          <el-select v-model="emp.roleIds" multiple placeholder="请选择">
            <el-option
              v-for="item in roles"
              :key="item.id"
              :label="item.nameZh"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="doSaveEmp('emp')">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
export default {
  data() {
    return {
      rules: {
        name: [{ required: true, message: "请输入用户名", trigger: "blur" }],
        gender: [{ required: true, message: "请输入性别", trigger: "blur" }],
        birthday: [
          { required: true, message: "请输入出生日期", trigger: "blur" },
        ],
        idCard: [
          { required: true, message: "请输入身份证号码", trigger: "blur" },
          {
            pattern: /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/,
            message: "身份证号码格式不正确",
            trigger: "blur",
          },
        ],
        wedlock: [
          { required: true, message: "请输入婚姻状况", trigger: "blur" },
        ],
        nation: [{ required: true, message: "请选择民族", trigger: "blur" }],
        nativePlace: [
          { required: true, message: "请输入籍贯", trigger: "blur" },
        ],
        politic: [
          { required: true, message: "请选择政治面貌", trigger: "blur" },
        ],
        email: [
          { required: true, message: "请输入邮箱地址", trigger: "blur" },
          {
            type: "email",
            message: "邮箱格式不正确",
            trigger: "blur",
          },
        ],
        phone: [{ required: true, message: "请输入电话号码", trigger: "blur" }],
        address: [
          { required: true, message: "请输入员工地址", trigger: "blur" },
        ],
        departmentId: [
          { required: true, message: "请输入部门名称", trigger: "blur" },
        ],
        jobLevelId: [
          { required: true, message: "请输入职称", trigger: "blur" },
        ],
        posId: [{ required: true, message: "请输入职位", trigger: "blur" }],
        engageForm: [
          { required: true, message: "请输入聘用形式", trigger: "blur" },
        ],
        tiptopDegree: [
          { required: true, message: "请输入学历", trigger: "blur" },
        ],
        specialty: [{ required: true, message: "请输入专业", trigger: "blur" }],
        school: [
          { required: true, message: "请输入毕业院校", trigger: "blur" },
        ],
        beginDate: [
          { required: true, message: "请输入入职日期", trigger: "blur" },
        ],
        workState: [
          { required: true, message: "请输入工作状态", trigger: "blur" },
        ],
        workID: [{ required: true, message: "请输入工号", trigger: "blur" }],
        contractTerm: [
          { required: true, message: "请输入合同期限", trigger: "blur" },
        ],
        conversionTime: [
          { required: true, message: "请输入转正日期", trigger: "blur" },
        ],
        notworkDate: [
          { required: true, message: "请输入离职日期", trigger: "blur" },
        ],
        beginContract: [
          { required: true, message: "请输入合同起始日期", trigger: "blur" },
        ],
        endContract: [
          { required: true, message: "请输入合同结束日期", trigger: "blur" },
        ],
      },
      dialogVisible: false,
      tiptopDegrees: [
        "本科",
        "大专",
        "硕士",
        "博士",
        "高中",
        "初中",
        "小学",
        "其他",
      ],
      title: "",
      importDataBtnText: "导入数据",
      importDataBtnIcon: "el-icon-upload2",
      importDataDisabled: false,
      loading: false,
      politics: [],
      positions: [],
      nations: [],
      departments: [],
      jobLevels: [],
      searchCondition: {},
      roles:{},
      emp: {
        name: "",
        gender: "",
        birthday: "",
        idCard: "",
        wedlock: "",
        nation: "",
        nativePlace: "",
        politic: "",
        email: "",
        phone: "",
        address: "",
        departmentId: "",
        jobLevelId: "",
        posId: "",
        engageForm: "",
        tiptopDegree: "",
        specialty: "",
        school: "",
        beginDate: "",
        workID: "",
        contractTerm: "",
        conversionTime: "",
        beginContract: "",
        endContract: "",
      },
      defaultProps: {
        label: "name",
        value: "id",
        checkStrictly: true,
        emitPath: false,
      },
      employees: [],
      findCondition: {},
      currentPage: 1,
      total: 0,
    };
  },
  methods: {
    handleClose() {
      this.emptyEmp();
    },
    emptyEmp() {
      this.emp = {
        name: "",
        gender: "",
        birthday: "",
        idCard: "",
        wedlock: "",
        nation: "",
        nativePlace: "",
        politic: "",
        email: "",
        phone: "",
        address: "",
        departmentId: "",
        jobLevelId: "",
        posId: "",
        engageForm: "",
        tiptopDegree: "",
        specialty: "",
        school: "",
        beginDate: "",
        workID: "",
        contractTerm: "",
        conversionTime: "",
        beginContract: "",
        endContract: "",
      };
    },
    showUpdateEmp(row) {
      this.title = "更改员工信息";
      this.dialogVisible = true;
      Object.assign(this.emp, row);
      console.log(this.emp);
    },
    selectDepartmentId(value) {
      this.emp.departmentId = value[value.length - 1];
    },
    doSaveEmp(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          console.log(this.emp.birthday);
          this.$postRequest("/employee/basic", this.emp).then((resp) => {
            console.log(resp);
            if (resp.code == "00000") {
              this.$message.success("操作成功!");
              this.dialogVisible = false;
              this.emptyEmp();
              this.getEmployeeByPage(this.currentPage, this.searchCondition);
            } else {
              console.log(resp.message);
              this.$message.error(resp.message);
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    showAddEmpView() {
      this.title = "添加员工";
      this.dialogVisible = true;
      this.getRoles();
    },
    handleChange(value) {
      this.searchCondition.departmentId = value[value.length - 1];
    },
    exportData() {
      this.$downloadRequest("/employee/basic/export").then((resp) => {
        const blob = new Blob([resp], {
          type:
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8",
        });
        const downloadElement = document.createElement("a");
        const href = window.URL.createObjectURL(blob);
        downloadElement.href = href;
        downloadElement.download = "员工表.xlsx";
        document.body.appendChild(downloadElement);
        downloadElement.click();
        document.body.removeChild(downloadElement); // 下载完成移除元素
        window.URL.revokeObjectURL(href); // 释放掉blob对象
      });
    },
    initBaseInfo() {
      this.$getRequest("/employee/basic/basicInfo").then((resp) => {
        if (resp.code == "00000") {
          this.nations = resp.data.nations;
          this.politics = resp.data.politics;
          this.positions = resp.data.positions;
          this.jobLevels = resp.data.jobLevels;
          this.departments = resp.data.departments;
        }
      });
    },
    search() {
      this.findCondition = this.searchCondition;
      console.log(this.searchCondition.date);
      this.getEmployeeByPage(1, this.findCondition);
    },
    getEmployeeByPage(currentPage, condition) {
      this.findCondition = condition;
      this.$postRequest(
        "/employee/basic/" + currentPage,
        this.findCondition
      ).then((resp) => {
        if (resp.code == "00000") {
          this.employees = resp.data.employees;
          this.total = resp.data.total;
        }
      });
    },
    getRoles(){
      this.$getRequest("/system/basic/permission").then(resp => {
        if(resp.code="00000") {
          this.roles = resp.data.allRoles;
        }
      })
    }
  },
  mounted() {
    this.initBaseInfo();
    this.getEmployeeByPage(1, this.searchCondition);
  },
};
</script>
<style scoped>
.slide-fade-enter-active {
  transition: all 0.8s ease;
}

.slide-fade-leave-active {
  transition: all 0.8s cubic-bezier(1, 0.5, 0.8, 1);
}

.slide-fade-enter, .slide-fade-leave-to
        /* .slide-fade-leave-active for below version 2.1.8 */ {
  transform: translateX(10px);
  opacity: 0;
}
</style>