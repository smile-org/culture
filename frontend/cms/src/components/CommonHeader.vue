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
                <li v-on:click="routeByName('advertising')" v-bind:class="{active: type==='advertising'}"><a href="">广告热图</a></li>
                <li v-on:click="routeByName('kernel')" v-bind:class="{active: type==='kernel'}"><a href="">核心模块</a></li>
                <li v-on:click="routeByName('hotTopic')" v-bind:class="{active: type==='hotTopic'}"><a href="">热点聚焦</a></li>
              </ul>
            </li>
            <li class="nav_bar_li" v-on:click="routeByName('newsDetail')" v-bind:class="{active: type==='newsDetail'}">
              <span class="h_icon h_icon03"></span>
              <p>新闻</p>
            </li>
            <li class="nav_bar_li" v-on:click="routeByName('exhibitionDetail')" v-bind:class="{active: type==='exhibitionDetail'}">
              <span class="h_icon h_icon01"></span>
              <p>文化展览</p>
            </li>
            <li class="nav_bar_li" v-on:click="routeByName('advertising')" v-bind:class="{active: type==='advertising'}">
              <span class="h_icon h_icon02"></span>
              <p>文化旅游</p>
            </li>
          </ul>
        </el-col>
        <el-col :span="6" class="admin_num">
          <!--<i class="iconfont icon-tubiaozhizuomoban"></i>-->
          <span>管理员</span>
        </el-col>
      </el-row>
    </header>
  </div>
</template>

<script>
import router from '../router'
export default {
  data: function () {
    return {
    }
  },
props: ['type'],
methods : {
  routeByName: function (name) {
    router.push({ name: name })
  }
}
}
</script>

<style scoped>

</style>
