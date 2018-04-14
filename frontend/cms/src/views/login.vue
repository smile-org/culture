<template>
  <div>
    <section class="login_bg">
      <div class="pos_center">
        <div class="login_con">
          <h3>文化寻力</h3>
          <hr noshade="noshade">
          <form>
            <div class="form_list">
              <span class="img_person"></span>
              <input type="text" id="UserName" placeholder="请输入用户名" required autocomplete="off" v-model="username">
            </div>
            <div class="form_list">
              <span class="img_loack"></span>
              <input type="password" id="PassWord" placeholder="请输入密码" errormsg="密码长度为6-16位" autocomplete="off" v-model="password"
                     required/>
            </div>
            <ul class="hidden">
              <li class="fl">
                <input type="checkbox" name="memberPass" class="" v-model="keepAlive"/>
                <label>保持我的登录状态</label>
              </li>
              <li class="fr">
                <span class="">忘记密码</span>
              </li>
            </ul>
            <div>
              <button @click="login()">登  录</button>
            </div>
          </form>
        </div>
      </div>
    </section>
  </div>
</template>
<script>
import api from '../service/api'
import router from '../router'
import md5 from 'js-md5'
export default {
  data: function () {
    return {
      username: '',
      password: '',
      token: '',
      msg: '',
      keepAlive: false
    }
  },
  created () {
    var storage = window.sessionStorage
    var token = storage['token']
    this.username = storage['username']
    this.password = storage['password']
    console.log(token)
  },
  methods: {
    login () {
      this.msg = ''
      var md5Password = md5(this.password)
      if (!this.username || !this.password) {
        this.msg = '请输入用户名和密码'
        return
      }
      api.post(api.uri.login, {username: this.username, password: md5Password}).then(data => {
        if (data.data.status === 1) {
          this.token = data.data.result.token
          var storage = sessionStorage
          storage['token'] = this.token
          if (this.keepAlive === true) {
            localStorage['token'] = this.token
          }
          router.push({name: 'advertising'})
        } else {
          this.msg = '用户名或密码错误'
        }
      }).catch((err) => {
        console.error(err.message)
      })
    }
  }
}
</script>
<style>
  /*.rember_pws {*/
  /*height: 40px;*/
  /*}*/

  /*.rember_pws input {*/
  /*border: 1px solid #dcdcdc;*/
  /*background: #fff;*/
  /*width: 16px;*/
  /*height: 16px;*/
  /*}*/
</style>
