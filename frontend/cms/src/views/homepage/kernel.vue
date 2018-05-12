<template>
  <div class="kernel">
    <common-header type="kernel"></common-header>
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
                <div class="tr" style="width: 100%;">
                  <span v-bind:class="{ active: this.lang == 'cn'}" @click="setLang('cn')">中文</span>
                  <span v-bind:class="{ active: this.lang == 'en'}" @click="setLang('en')">英文</span>
                </div>
              </li>
              <li class="list_state">
                <el-form-item label="模块名称" prop="title_cn">
                  <el-input v-model="ruleForm.title" placeholder="请在此输入名称"></el-input>
                </el-form-item>
              </li>
              <li class="list_state">
                <el-form-item label="模块简述" prop="desc_cn">
                  <el-input v-model="ruleForm.desc" placeholder="请在此输入描述"></el-input>
                </el-form-item>
              </li>
              <li class="list_state">
                <h4>宣传图片 :</h4>
                <div class="right_input">
                  <img :src="ruleForm.image | formatImg" alt="" class="style_hotImg fl">
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
                    <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb，图片尺寸 370*231</div>
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
      // 语言
      lang: 'cn',
      // 上传参数
      uploadAPI: api.uploadAPI,
      headers: {},
      // 里面只有一个附件， 第二个会替换第一个
      fileList: [],
      tmpid: 1,
      uploadData:{
        type:'module'
      },
      ruleForm: {
        module_id:0,
        title: '',
        desc: '',
        image:'',
        link: ''
      },
      rules: {
        title: [
          { required: true, message: '请在此输入标题', trigger: 'blur' },
        ],
        desc: [
          { required: true, message: '请在此输入描述', trigger: 'blur' }
        ],
        link: [
          { required: true, message: '请在此输入跳转链接', trigger: 'blur' }
        ]
      },
      items: [],
      resultForm:[]
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
            if (this.tmpid === 0) {
              this.getModuleByID(this.items[0].id)
            } else {
              this.getModuleByID(this.tmpid)
            }
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
      api.fetch(api.uri.getModuleByID, {module_id: id}).then(data => {
        console.log(data)
        if (data.data.status === 1) {
          this.resultForm = data.data.result
          this.ruleForm.module_id = this.resultForm.module_id
          this.setLang(this.lang)
        } else {
          this.msg = '返回错误'
        }
      }).catch((err) => {
        console.error(err.message)
      })
    },
    // 切换语言
    setLang: function(lang){
      this.lang = lang
      if (this.lang === 'cn') {
        this.ruleForm.title = this.resultForm.title_cn
        this.ruleForm.desc = this.resultForm.desc_cn
        this.ruleForm.image = this.resultForm.image_cn
        this.ruleForm.link = this.resultForm.link_cn
      } else {
        this.ruleForm.title = this.resultForm.title_en
        this.ruleForm.desc = this.resultForm.desc_en
        this.ruleForm.image = this.resultForm.image_en
        this.ruleForm.link = this.resultForm.link_en
      }
    },
    //更新banner
    update:function(){
      var params = {}
      if (this.lang === 'cn') {
        params = {
          image_cn: this.ruleForm.image,
          link_cn: this.ruleForm.link,
          title_cn: this.ruleForm.title,
          desc_cn: this.ruleForm.desc,
          module_id: this.ruleForm.module_id,
          lan: 'cn'
        }
      }else{
        params = {
          image_en: this.ruleForm.image,
          link_en: this.ruleForm.link,
          title_en: this.ruleForm.title,
          desc_en: this.ruleForm.desc,
          module_id: this.ruleForm.module_id,
          lan: 'en'
        }
      }
      api.post(
        api.uri.updateModuleByID,
        params
      ).then(data => {
        console.log(data)
        this.initNav()
        if (data.data.status === 1) {
          this.$message({
            type: 'info',
            message: '保存成功'
          })
        } else {
          this.$message({
            type: 'info',
            message: '保存失败'
          })
        }
      }).catch((err) => {
        console.error(err.message)
        this.$message({
          type: 'info',
          message: err.message
        })
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
      this.ruleForm.image = response.result
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
