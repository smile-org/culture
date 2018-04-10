<template>
  <div id="app">
    <!--<img src="./assets/logo.png">-->
    <common-header></common-header>
    <router-view></router-view>
  </div>
</template>

<script>
import commonHeader from './components/CommonHeader.vue'

export default {
  name: 'App',
  components: {
    commonHeader
  }
}
</script>

<style>
  @import './assets/css/common.css';
  @import './assets/css/style.css';
  @import './assets/css/mend.css';
  /*@import './assets/css/iconfont.css';*/

  #app {
    /*font-family: 'Avenir', Helvetica, Arial, sans-serif;*/
    /*-webkit-font-smoothing: antialiased;*/
    /*-moz-osx-font-smoothing: grayscale;*/
    /*text-align: center;*/
    /*color: #2c3e50;*/
  }
</style>
