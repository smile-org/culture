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
          <h4 class="ad_tit"><span class="img_icon02"></span>核心模块</h4>
          <ul class="aside_ad_list">
            <li>
              文化展览
            </li>
            <li>
              文化之旅
            </li>
            <li>
              文化记忆
            </li>
            <li>
              文化教育
            </li>
            <li>
              非遗地图
            </li>
            <li>
              服务升级
            </li>
          </ul>
        </aside>

        <div class="con_right">
          <ul class="con_ul">
            <li class="list_state">
              <h4>模块名称 :</h4>
              <div class="right_input">
                <input class="import_input" type="text" placeholder="请在此输入模块名称">
              </div>
            </li>
            <li class="list_state">
              <h4>模块简述 :</h4>
              <div class="right_input">
                <input class="import_input" type="text" placeholder="请在此输入模块简述">
              </div>
            </li>
            <li class="list_state">
              <h4>宣传图片 :</h4>
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
          </ul>
          <P class="input_warn">
            请输入模块名称
          </P>
          <!--<P class="input_warn">-->
          <!--请输入模块简述-->
          <!--</P>-->
          <!--<P class="input_warn">-->
          <!--请上传图片-->
          <!--</P>-->
          <!--<P class="input_warn">-->
          <!--请输入跳转链接-->
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
      value2: true
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
