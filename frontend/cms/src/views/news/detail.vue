<template>
  <div>
    <!--<header>-->
      <!--<el-row class="header_tab">-->
        <!--<el-col :span="6">-->
          <!--<a class="logo fl">-->
            <!--<img class="vm" src="img/small_logo.png"/>-->
            <!--<span class="vm">文化寻力营销平台</span>-->
          <!--</a>-->
        <!--</el-col>-->
        <!--<el-col :span="12">-->
          <!--<ul class="nav_bar clear">-->
            <!--<li class="nav_bar_li">-->
              <!--<span class="h_icon h_icon04"></span>-->
              <!--<p>首页</p>-->
              <!--<ul class="extra_box">-->
                <!--<li><a href="">广告热图</a></li>-->
                <!--<li><a href="">核心模块</a></li>-->
                <!--<li><a href="">热点聚焦</a></li>-->
              <!--</ul>-->
            <!--</li>-->
            <!--<li class="nav_bar_li">-->
              <!--<span class="h_icon h_icon03"></span>-->
              <!--<p>新闻</p>-->
            <!--</li>-->
            <!--<li class="nav_bar_li">-->
              <!--<span class="h_icon h_icon01"></span>-->
              <!--<p>文化展览</p>-->
            <!--</li>-->
            <!--<li class="nav_bar_li">-->
              <!--<span class="h_icon h_icon02"></span>-->
              <!--<p>文化旅游</p>-->
            <!--</li>-->
          <!--</ul>-->
        <!--</el-col>-->
        <!--<el-col :span="6" class="admin_num">-->
          <!--<span>管理员</span>-->
        <!--</el-col>-->
      <!--</el-row>-->
    <!--</header>-->
    <section>
      <div class="con_main">
        <aside>
          <h4 class="ad_tit"><span class="img_icon05"></span>新闻列表</h4>
          <ul class="aside_ad_list">
            <li>
              新闻标题
            </li>
            <li>
              新闻标题
            </li>
            <li>
              新闻标题
            </li>
          </ul>
        </aside>
        <div class="con_right">
          <ul class="con_ul">
            <li class="list_state">
              <h4>新闻标题 :</h4>
              <div class="right_input">
                <input class="import_input" type="text" placeholder="请在此输入标题">
              </div>
            </li>
            <li class="list_state">
              <h4>新闻摘要 :</h4>
              <div class="right_input">
                <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4}" placeholder="请在此输入宣传描述" v-model="textarea3">
                </el-input>
              </div>
            </li>
            <li class="list_state">
            <h4>标题图片 :</h4>
            <div class="right_input">
            <img src="./../../assets/img/style_banner.jpg" alt="" class="style_banner">
            <a href="#" class="choose_file">选择文件</a>
            </div>
            </li>
            <li class="list_state">
              <h4>跳转链接 :</h4>
              <div class="right_input">
                <input class="import_input" type="text" placeholder="请输入跳转链接">
              </div>
            </li>
            <li class="list_state">
              <h4>新闻内容 :</h4>
              <div class="right_input">
                nbvnbvnvnnnnc

              </div>
            </li>
          </ul>
          <P class="input_warn">
            请输入展览名称
          </P>
          <!--<P class="input_warn">-->
            <!--请填写详细介绍-->
          <!--</P>-->
          <div class="tc">
            <button class="save_btn mt20 mb30">保  存</button>
          </div>
        </div>
      </div>

    </section>
  </div>
</template>

<script>
export default {
  data () {
    return {
      value1: true,
      value2: true,
      options: [{
        value: '选项1',
        label: '国内'
      }, {
        value: '选项2',
        label: '国际'
      }],
      value: ''
    }
  }
}
</script>
<style>
  .el-switch__label * {
    font-size: 16px;
    margin-right: 10px;
    vertical-align: middle;
  }

  .el-switch__label {
    line-height: 1;
    color: #999;
    font-size: 16px;
    display: inline-block;
    margin-right: 10px;
  }

  .el-switch__label.is-active {
    color: #333 !important;
  }
</style>
