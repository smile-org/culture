<template>
  <div>
    <header>
      <el-row class="header_tab">
        <el-col :span="6">
          <a class="logo fl">
            <!--<img class="vm" src="./img/small_logo.png"/>-->
            <span class="vm">文化寻力营销平台</span>
          </a>
        </el-col>
        <el-col :span="12">
          <ul class="nav_bar clear">
            <li class="nav_bar_li">
              <span class="h_icon h_icon04"></span>
              <p>首页</p>
              <ul class="extra_box">
                <li v-on:click="routeByName('advertising')" v-bind:class="{active: type==='advertising'}">广告热图
                </li>
                <li v-on:click="routeByName('kernel')" v-bind:class="{active: type==='kernel'}">核心模块</li>
                <li v-on:click="routeByName('hotTopic')" v-bind:class="{active: type==='hotTopic'}">热点聚焦
                </li>
              </ul>
            </li>
            <li class="nav_bar_li" v-on:click="routeByName('newsDetail')" v-bind:class="{active: type==='newsDetail'}">
              <span class="h_icon h_icon03"></span>
              <p>新闻</p>
            </li>
            <li class="nav_bar_li" v-on:click="routeByName('exhibitionDetail')"
                v-bind:class="{active: type==='exhibitionDetail'}">
              <span class="h_icon h_icon01"></span>
              <p>文化展览</p>
            </li>
            <!--<li class="nav_bar_li" v-on:click="routeByName('advertising')"-->
                <!--v-bind:class="{active: type==='advertising'}">-->
              <!--<span class="h_icon h_icon02"></span>-->
              <!--<p>文化旅游</p>-->
            <!--</li>-->
          </ul>
        </el-col>
        <el-col :span="6" class="admin_num">
          <i class="iconfont icon-tubiaozhizuomoban"></i>
          <div class="admin_tab fr active">管理员
            <div class="extra_quit">
              <p v-on:click="exit()">退出
              </p>
            </div>
          </div>
        </el-col>
      </el-row>
    </header>
  </div>
</template>

<script>
import router from '../router'
export default {
  data: function () {
    return {}
  },
  props: ['type'],
  methods: {
    routeByName: function (name) {
      router.push({name: name})
    },
    exit: function () {
      var storage = window.localStorage
      storage.removeItem('token')
      storage.removeItem('username')
      storage.removeItem('password')
      this.routeByName('login')
    }
  },

}
</script>

<style scoped>

</style>
