import axios from '../../node_modules/axios'
//  生产地址
axios.defaults.baseURL = 'http://39.107.152.177:8000/'
/**
 * 拦截器， 对所有的请求。
 * 作用： 可以为每个请求加上额外参数
 */
axios.interceptors.request.use(config => {
  var token = sessionStorage.getItem('token')
  if (token) {
    // token放到header中
    config.headers.common.token = token
  }

  return config
}, error => {
  return Promise.reject(error)
})

/**
 * 拦截器， 对所有的响应
 * 返回到vue前可以先做一层过滤
 */
//  axios.interceptors.response.use(response => {
//  return response.data
//  }, error => {
//  return Promise.reject(error)
//  })

export default {
  /**
   * 获取数据
   * @param {* @/users/list or @/users/userId} url
   * @param {* {type: 1}} params
   */
  fetch (url, params = {}) {
    return axios.get(url, {
      params: params
    })
  },
  /**
   * 提交数据
   * @param {* @/users } url
   * @param {* {firstName: 'cui', lastName: 'lin', age: 18} } params
   */
  post (url, params = {}) {
    return axios.post(url, params)
  },
  uri: {
    // *** 广告热图 ***
    login: 'api/user/login',
    getBannerNavList: 'api/firstPage/getBannerNavList',
    getBannerByID: 'api/firstPage/getBannerByID',
    updateBannerByID: 'api/firstPage/updateBannerByID',
    uploadPic: 'api/firstPage/uploadPic',
    // *** 核心模块 ***
    getModuleNavList: 'api/firstPage/getModuleNavList',
    getModuleByID: 'api/firstPage/getModuleByID',
    updateModuleByID: 'api/firstPage/updateModuleByID',
    // *** 热点聚焦 ***
    getFocusNavList: 'api/firstPage/getFocusNavList',
    getFocusByID: 'api/firstPage/getFocusByID',
    updateFocusByID: 'api/firstPage/updateFocusByID',
    // *** 新闻 ***
    getNewsNavList: 'api/news/getNewsNavList',
    getNewsByID: 'api/news/getNewsByID',
    updateNewsByID: 'api/news/updateNewsByID',
    // *** 文化展览 ***
    getExhibitNavList: 'api/exhibit/getExhibitNavList',
    getExhibitEditPageInfoByID: 'api/exhibit/getExhibitEditPageInfoByID',
    updateExhibitByID: 'api/exhibit/updateExhibitByID'
  },
  uploadAPI: 'http://39.107.152.177:8000/api/firstPage/uploadPic',
  maxFileSizeBit: 500 * 1024,
  maxFileSize: '50kb',
  extension: ['JPG', 'PNG'],
  getUploadHeaders: function () {
    var token = sessionStorage.getItem('token')
    return {
      token: token
    }
  }
}
