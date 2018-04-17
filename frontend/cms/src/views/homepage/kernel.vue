<template>
  <div class="kernel">
    <common-header type="kernel"></common-header>
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
            <li v-for="item in items" :id="item.id" @click="getModuleByID(item.id)" :key="item.id" v-bind:class="{active: item.id == tmpid}">
              {{item.title}}
            </li>
          </ul>
        </aside>

        <div class="con_right">
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <ul class="con_ul">
              <li class="list_state language_con">
                <span class="active">中文</span>  <span>英文</span>
              </li>
              <li class="list_state">
                <el-form-item label="模块名称" prop="title_cn">
                  <el-input v-model="ruleForm.title_cn" placeholder="请在此输入名称"></el-input>
                </el-form-item>
              </li>
              <li class="list_state">
                <el-form-item label="模块简述" prop="desc_cn">
                  <el-input v-model="ruleForm.desc_cn" placeholder="请在此输入描述"></el-input>
                </el-form-item>
              </li>
              <li class="list_state">
                <h4>宣传图片 :</h4>
                <div class="right_input">
                  <img :src="imgUrl | formatImg" alt="" class="style_banner fl">
                  <el-upload
                    class="upload-demo fl upAd"
                    :action="uploadAPI"
                    :data="uploadData"
                    :headers="headers"
                    :on-success="onContentSuccess"
                    :before-upload="beforeContentUpload"
                    :auto-upload="true"
                    :on-change="changeContentUpload"
                    :file-list="fileList"

                    >
                    <el-button size="small" type="primary">点击上传</el-button>
                    <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
                  </el-upload>
                  <!--<a href="#" class="choose_file">选择文件</a>-->
                </div>
              </li>
              <li class="list_state">
                <el-form-item label="跳转链接" prop="link">
                  <el-input v-model="ruleForm.link" placeholder="请在此输入链接"></el-input>
                </el-form-item>
              </li>
            </ul>
          </el-form>
          <div class="tc">
            <button class="save_btn mt20 mb30" @click="submitForm('ruleForm')">保  存</button>
          </div>
        </div>
      </div>

    </section>
  </div>
</template>

<script>
import api from '../../service/api'
import axios from 'axios'
import commonHeader from '../../components/CommonHeader.vue'
export default {
  components: {
    commonHeader
  },
  data:function () {
    return {
      // 上传参数
      uploadAPI: api.uploadAPI,
      headers: {},
      // 里面只有一个附件， 第二个会替换第一个
      fileList: [],
      imgUrl:'',
      tmpid: 1,
      uploadData:{
        type:'module'
      },
      ruleForm: {
        title_cn: '',
        desc_cn: '',
        link: ''
      },
      rules: {
        title_cn: [
          { required: true, message: '请在此输入标题', trigger: 'blur' },
        ],
        desc_cn: [
          { required: true, message: '请在此输入描述', trigger: 'blur' }
        ],
        link: [
          { required: true, message: '请在此输入跳转链接', trigger: 'blur' }
        ]
      },
      items: []
    }
  },
  filters: {
    formatImg (img) {
      return axios.defaults.imageURL + img
    }
  },
  created () {
    this.headers = api.getUploadHeaders();
    //获取左侧nav
    this.initNav()
  },
  methods: {
    // init
    initNav:function(){
      api.fetch(api.uri.getModuleNavList).then(data => {
        console.log(data.data.result)
        if (data.data.status === 1) {
          this.items = data.data.result
          if (this.items.length > 0) {
            //根据id获取热图信息
            api.fetch(api.uri.getModuleByID, {module_id: this.items[0].id}).then(data => {
              console.log(data)
              if (data.data.status === 1) {
                this.ruleForm = data.data.result
                this.imgUrl = data.data.result.image
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
    //根据id信息
    getModuleByID:function(id){
      this.tmpid = id;
      api.fetch(api.uri.getModuleByID, {module_id: event.currentTarget.id}).then(data => {
        console.log(data)
        if (data.data.status === 1) {
          this.ruleForm = data.data.result
          this.imgUrl = this.ruleForm.image
        } else {
          this.msg = '返回错误'
        }
      }).catch((err) => {
        console.error(err.message)
      })
    },
    //更新banner
    update:function(){
      api.post(
        api.uri.updateModuleByID,
        {
          image:this.imgUrl,
          link:this.ruleForm.link,
          title_cn:this.ruleForm.title_cn,
          title_en:"",
          desc_cn:this.ruleForm.desc_cn,
          desc_en:"",
          module_id:this.ruleForm.module_id
        }
      ).then(data => {
        console.log(data)
        if (data.data.status === 1) {
          this.ruleForm = data.data.result
          this.initNav()
        } else {
          this.msg = '返回错误'
        }
      }).catch((err) => {
        console.error(err.message)
      })
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
    },

    // 上传文件
    onContentSuccess (response, file, fileList) {
      console.log(response)
      this.imgUrl = response.result
    },

    changeContentUpload (file, fileList) {
      // 保证页面显示一个附件
      if (fileList.length > 0) {
        this.fileList = [file]
      }
    },

    beforeContentUpload (file) {
      if (file.name.indexOf('.') !== -1) {
        var arrLen = file.name.split('.').length - 1
        var extension = file.name.split('.')[arrLen].toUpperCase()
        var targetExtensionArray = api.extension
        if (targetExtensionArray.indexOf(extension) === -1) {
          this.$message({
            type: 'info',
            message: '不支持的上传文件格式'
          })
          this.fileList = []
          return false
        }
      } else {
        this.$message({
          type: 'info',
          message: '不支持的上传文件格式'
        })
        this.fileList = []
        return false
      }

      if (file.size > api.maxFileSizeBit) {
        this.$message({
          type: 'info',
          message: '附件不能大于' + api.maxFileSize
        })
        this.fileList = []
        return false
      }
    }
  }
}
</script>
<style>
  .kernel .upAd{
    margin: 40px 0 0 40px;
  }
  .kernel .el-form-item{
    width: 100%;
  }
  .kernel .el-form-item{
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
