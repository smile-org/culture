<template>
  <div>
    <section>
      <div class="con_main">
        <aside>
          <h4 class="ad_tit"><span class="img_icon01"></span>广告热图</h4>
          <ul class="aside_ad_list">
            <li>
              第一页热图
              <!--<span class="blockUp">停用</span>-->
            </li>
            <li>
              第一页热图
              <!--<span class="blockUp">停用</span>-->
            </li>
            <li>
              第一页热图
              <!--<span class="blockUp">停用</span>-->
            </li>
            <li>
              第一页热图
              <!--<span class="blockUp">停用</span>-->
            </li>
            <li>
              第一页热图
              <span class="blockUp">停用</span>
            </li>
          </ul>
        </aside>

        <div class="con_right">
          <h4 class="ad_tit">
            <el-switch
              v-model="value2"
              active-color="#ff4949"
              inactive-color="#13ce66" inactive-text="启用状态:">
            </el-switch>
          </h4>
          <ul class="con_ul">
            <li class="list_state">
              <h4>宣传图片 :</h4>
              <div class="right_input">
                <img src="./../../assets/img/style_banner.jpg" alt="" class="style_banner">

                <!--注意这个选择文件有俩个是因为一个input,一个是a标签，可以选择其中一个-->
                <p class="file_box">
                  <input type='button' class='self_btn' value='选择文件' />
                  <input type="file" name="fileField" class="file" id="fileField" size="28" />
                </p>
                <a href="#" class="choose_file">选择文件</a>
              </div>
            </li>
            <li class="list_state">
              <h4>宣传标题 :</h4>
              <div class="right_input">
                <input class="import_input" type="text" placeholder="请在此输入标题">
              </div>
            </li>
            <li class="list_state">
              <h4>宣传描述 :</h4>
              <div class="right_input">
                <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4}" placeholder="请在此输入宣传描述"
                          v-model="textarea3">
                </el-input>
              </div>
            </li>
            <li class="list_state">
              <h4>跳转链接 :</h4>
              <div class="right_input">
                <input class="import_input" type="text" placeholder="请在此输入标题">
              </div>
            </li>
            <li class="list_state">
              <h4>展示位置 :</h4>
              <div class="right_input">
                <input class="import_input" type="text" placeholder="请在此输入展示顺序">
              </div>
            </li>
          </ul>
          <P class="input_warn">
            请上传图片
          </P>
          <!--<P class="input_warn">-->
          <!--请上传图片-->
          <!--</P>-->
          <!--<P class="input_warn">-->
          <!--请输入宣传标题-->
          <!--</P>-->
          <!--<P class="input_warn">-->
          <!--请输入宣传描述-->
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
