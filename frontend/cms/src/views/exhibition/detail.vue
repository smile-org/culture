<template>
  <div class="exhibition">
    <common-header></common-header>
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
          <h4 class="ad_tit"><span class="img_icon04"></span>文化展览  <span class="small_addBtn">添加</span></h4>
          <ul class="aside_ad_list">
            <li v-for="item in items" :id="item.id" @click="getExhibitEditPageInfoByID" :key="item.id">
              {{item.title}}返回为null
              <!--<span class="blockUp">停用</span>-->
              <span class="blockUp" v-if="!item.status">停用</span>
            </li>
          </ul>
        </aside>
        <div class="con_right">
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <h4 class="ad_tit">
              <el-switch
                v-model="value2"
                active-color="#ff4949"
                inactive-color="#13ce66" inactive-text="启用状态:">
              </el-switch>
            </h4>
            <ul class="con_ul">
              <!--<li class="list_state">-->
                <!--<h4>宣传图片 :</h4>-->
                <!--<div class="right_input">-->
                  <!--<img src="./../../assets/img/style_banner.jpg" alt="" class="style_banner">-->
                  <!--<a href="#" class="choose_file">选择文件</a>-->
                <!--</div>-->
              <!--</li>-->
              <li class="list_state">
                <el-form-item label="展览名称" prop="title_cn">
                  <el-input v-model="ruleForm.title_cn" placeholder="请在此输入标题"></el-input>
                </el-form-item>
              </li>
              <li class="list_state">
                <el-form-item label="展览类别" prop="category">
                  <el-select v-model="ruleForm.region" placeholder="请选择">
                    <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
                  </el-select>
                </el-form-item>
              </li>
              <li class="list_state">
                <h4>详细介绍 :</h4>
                <div class="right_input">
                  nbvnbvnvnnnnc

                </div>
              </li>
            </ul>
          </el-form>
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
import api from '../../service/api'
import commonHeader from '../../components/CommonHeader.vue'
export default {
  components: {
    commonHeader
  },
  data:function () {
    return {
      uploadData:{
        type:'banner'
      },
      ruleForm: {
        title_cn: '',
        order: '',
        desc_cn: '',
        link: '',
        status: false
      },
      rules: {
        title_cn: [
          { required: true, message: '请在此输入标题', trigger: 'blur' },
        ],
        desc_cn: [
          { required: true, message: '请在此输入描述', trigger: 'blur' }
        ],
        order: [
          { required: true, message: '请在此输入展示位置', trigger: 'blur' }
        ],
        link: [
          { required: true, message: '请在此输入跳转链接', trigger: 'blur' }
        ]
      },
      items:[],
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
  },
  created () {
    //获取左侧nav
    api.fetch(api.uri.getExhibitNavList).then(data => {
      console.log(data.data.result)
      if (data.data.status === 1) {
        this.items = data.data.result
        if (this.items.length > 0) {
          //根据id获取信息
          api.fetch(api.uri.getExhibitEditPageInfoByID, {exhibit_id: this.items[0].id}).then(data => {
            console.log(data)
            if (data.data.status === 1) {
              this.ruleForm = data.data.result.exhibitToBeEdit
              if(this.ruleForm.status == 1){
                this.ruleForm.status = '1'
              }else if(this.ruleForm.status = 0){
                this.ruleForm.status = '0'
              }
            } else {
              this.msg = data.data.result
            }
          }).catch((err) => {
            console.error(err.message)
          })
        }
      } else {
        this.msg = '返回错误'
      }
    }).catch((err) => {
      console.error(err.message)
    })
  },
  methods: {
    //根据id获取热图信息
    getExhibitEditPageInfoByID:function(){
      api.fetch(api.uri.getExhibitEditPageInfoByID, {exhibit_id: event.currentTarget.id}).then(data => {
        console.log(data)
        if (data.data.status === 1) {
          this.ruleForm = data.data.result.exhibitToBeEdit
          if(this.ruleForm.status == 1){
            this.ruleForm.status = '1'
          }else if(this.ruleForm.status = 0){
            this.ruleForm.status = '0'
          }
        } else {
          this.msg = '返回错误'
        }
      }).catch((err) => {
        console.error(err.message)
      })
    },
    //更新banner
    update:function(){
      api.fetch(
        api.uri.updateBannerByID,
        {
          image:"image",
          link:this.ruleForm.link,
          title_cn:this.ruleForm.title_cn,
          title_en:"",
          desc_cn:this.ruleForm.desc_cn,
          desc_en:"",
          status:this.ruleForm.status,
          order:this.ruleForm.order,
          banner_id:this.ruleForm.banner_id
        }
      ).then(data => {
        console.log(data)
        if (data.data.status === 1) {
          this.ruleForm = data.data.result
        } else {
          this.msg = '返回错误'
        }
      }).catch((err) => {
        console.error(err.message)
      })
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${ file.name }？`);
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.update()
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    }
  }
}
</script>
<style>
  .exhibition .upAd{
    margin: 40px 0 0 40px;
  }
  .exhibition .el-form-item{
    width: 100%;
  }
  .exhibition .el-form-item{
    margin: 22px 0;
  }
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
