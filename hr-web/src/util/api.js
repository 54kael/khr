import axios from 'axios'
import { Message } from 'element-ui';
import router from '../router'
axios.interceptors.request.use(config => {
    let token = window.localStorage.getItem("token");
    if (token) {
        config.headers.Authorization = token;
    }
    return config;
})

axios.interceptors.response.use(success => {
    // 响应成功!
    if (success.status == 200) {
        return success.data;
    }

}, error => {
    // 响应失败
    console.log(error);
    if (error.response.status == 403) {
        Message.error("权限不足，请联系管理员");
    } else if (error.response.status == 401) {
        Message.error("尚未登录");
        router.replace("/login");
    } else if(error.response.status == 400){
        Message.error(error.response.data.message);
    } else {
        Message.error("未知错误")
    }
    return;
})

//let base = "http://120.24.28.194/api"
let base = 'http://localhost:8090';
export const postRequest = (url, params) => {
    return axios({
        method: 'post',
        url: `${base}${url}`,
        data: params
    })
}
export const putRequest = (url, params) => {
    return axios({
        method: 'put',
        url: `${base}${url}`,
        data: params
    })
}
export const getRequest = (url, params) => {
    return axios({
        method: 'get',
        url: `${base}${url}`,
        params: params
    })
}
export const deleteRequest = (url, params) => {
    return axios({
        method: 'delete',
        url: `${base}${url}`,
        params: params
    })
}