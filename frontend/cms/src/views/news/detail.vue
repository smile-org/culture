<template>
  <div class="news">
    <common-header type="newsDetail"></common-header>
    <section>
      <div class="con_main">
        <aside>
          <h4 class="ad_tit"><span class="img_icon05"></span>新闻列表</h4>
          <ul class="aside_ad_list">
            <li v-for="item in items" :id="item.id" @click="getNewsByClicked(item.id)" :key="item.id" v-bind:class="{active: item.id == tmpid}">
              {{item.title}}
            </li>
          </ul>
        </aside>
        <div class="con_right">
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <ul class="con_ul">
              <li class="list_state language_con">
               <div class="tr" style="width: 100%;">
                 <span @click="lanChange('cn')" v-bind:class="{active: this.lan == 'cn'}">中文</span>
                 <span @click="lanChange('en')" v-bind:class="{active: this.lan == 'en'}">英文</span>
               </div>
              </li>
              <li class="list_state">
                <el-form-item label="新闻标题" prop="title">
                  <el-input v-model="ruleForm.title" placeholder="请输入标题"></el-input>
                </el-form-item>
              </li>
              <li class="list_state">
                <el-form-item label="新闻摘要" prop="desc">
                  <el-input type="textarea" v-model="ruleForm.desc" placeholder="请输入新闻摘要"></el-input>
                </el-form-item>
              </li>
              <li class="list_state">
              <h4>标题图片 :</h4>
              <div class="right_input">
                <img :src="ruleForm.img | formatImg" alt="" class="style_banner fl">
                <el-upload
                    class="upload-demo fl upAd"
                    :action="uploadAPI"
                    :data="uploadData"
                    :headers="headers"
                    :on-success="onContentSuccess"
                    :before-upload="beforeContentUpload"
                    :auto-upload="true"
                    :on-change="changeContentUpload"
                    :file-list="fileList">
                  <el-button size="small" type="primary">点击上传</el-button>
                  <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
                </el-upload>
              </div>
              </li>
              <li class="list_state">
                <el-form-item label="跳转链接" prop="link">
                  <el-input type="textarea" v-model="ruleForm.link" placeholder="请输入跳转链接"></el-input>
                </el-form-item>
              </li>
              <li class="list_state">
                <h4>新闻内容 :</h4>
                <div class="right_input" >
                  <VueUEditor @ready="editorReady" style="max-Width : calc(100% - 400px);padding: 20px 0;"></VueUEditor>
                </div>
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
import VueUEditor from 'vue-ueditor'
import axios from 'axios'
import router from '../../router'
import commonHeader from '../../components/CommonHeader.vue'

export default {
  components: {
    VueUEditor,
    commonHeader
  },
  data:function () {
    return {
      // 上传参数
      uploadAPI: api.uploadAPI,
      lan:'cn',
      headers: {},
      // 里面只有一个附件， 第二个会替换第一个
      fileList: [],
      // end
      tmpid: 1,
      imgUrl:'',
      uploadData:{
        type:'news'
      },
      messageCn:{
        title:'',
        content:'',
        desc:'',
        img:'',
        link:'',
        news_id:'',
        status:''
      },
      messageEn:{
        title:'',
        content:'',
        desc:'',
        img:'',
        link:'',
        news_id:'',
        status:''
      },
      ruleForm: {
        title_cn: '',
        order: '',
        desc_cn: '',
        link: '',
        img:'',
        status: false
      },
      rules: {
        title: [
          { required: true, message: '请在此输入标题', trigger: 'blur' },
        ],
        desc: [
          { required: true, message: '请在此输入描述', trigger: 'blur' }
        ],
        order: [
          { required: true, message: '请在此输入展示位置', trigger: 'blur' }
        ],
        link: [
          { required: true, message: '请在此输入跳转链接', trigger: 'blur' }
        ]
      },
      value: '',
      items: [],
      currentUEditor: {}
    }
  },
  filters: {
    formatImg (img) {
      return axios.defaults.imageURL + img
    }
  },
  created () {
    this.headers = api.getUploadHeaders();
    // //获取左侧nav
    // api.fetch(api.uri.getNewsNavList).then(data => {
    //   if (data.data.status === 1) {
    //     this.items = data.data.result
    //   } else {
    //     this.msg = '返回错误'
    //   }
    // }).catch((err) => {
    //   console.error(err.message)
    // })
  },
  methods: {
    editorReady (editorInstance) {
      //获取左侧nav
      api.fetch(api.uri.getNewsNavList).then(data => {
        if (data.data.status === 1) {
          this.items = data.data.result
          if (this.items.length > 0) {
            this.getNews(editorInstance, this.items[0].id)
          }
        } else {
          this.msg = '返回错误'
        }
      }).catch((err) => {
        console.error(err.message)
      })
      this.currentUEditor = editorInstance
      editorInstance.addListener('contentChange', () => {
        this.ruleForm.content = editorInstance.getContent()
      })
    },
    lanChange:function(type){
      if(type == 'cn'){
        this.lan = 'cn'
        this.ruleForm = this.messageCn
        this.currentUEditor.setContent(this.ruleForm.content)
      }else if(type == 'en'){
        this.lan = 'en'
        this.ruleForm = this.messageEn
        if(this.messageEn.content == null){
          this.messageEn.content = '请输入'
        }
        this.currentUEditor.setContent(this.ruleForm.content)
      }
    },
    getNews: function (editorInstance, newsId) {
      this.lan = 'cn'
      api.fetch(api.uri.getNewsByID, {news_id: newsId}).then(data => {
          console.log(data.data)
          if (data.data.status === 1) {
//            this.ruleForm = data.data.result
            var message = data.data.result
            //中文
            this.messageCn.content = message.content_cn
            this.messageCn.desc = message.desc_cn
            this.messageCn.img = message.image_cn
            this.messageCn.link = message.link_cn
            this.messageCn.title = message.title_cn
            this.messageCn.news_id = message.news_id
            this.messageCn.status = message.status
            //英文
            this.messageEn.content = message.content_en
            this.messageEn.desc = message.desc_en
            this.messageEn.img = message.image_en
            this.messageEn.link = message.link_en
            this.messageEn.title = message.title_en
            this.messageEn.news_id = message.news_id
            this.messageEn.status = message.status
            //初始为中文
            this.ruleForm = this.messageCn
            editorInstance.setContent(this.ruleForm.content)
          } else {
            this.msg = '返回错误'
          }
        }).catch((err) => {
          console.error(err.message)
        })
    },

    getNewsByClicked: function (id) {
      this.tmpid = id;
      this.getNews(this.currentUEditor, event.currentTarget.id)
    },

    //更新新闻
    update:function(){
      var params = {}
      if (this.lan === 'cn') {
        params = {
          lan:'cn',
          image_cn:this.ruleForm.img,
          link_cn:this.ruleForm.link,
          title_cn:this.ruleForm.title,
          desc_cn:this.ruleForm.desc,
          content_cn: this.ruleForm.content,
          news_id:this.ruleForm.news_id
        }
      }else{
        params = {
          lan:'en',
          image_en:this.ruleForm.img,
          link_en:this.ruleForm.link,
          title_en:this.ruleForm.title,
          desc_en:this.ruleForm.desc,
          content_en: this.ruleForm.content,
          news_id:this.ruleForm.news_id
        }
      }

      api.post(
        api.uri.updateNewsByID,
        params
      ).then(data => {
        console.log(data)
        if (data.data.status === 1) {
        location.reload()
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
      this.ruleForm.img = response.result
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
  .news .upAd{
    margin: 40px 0 0 40px;
  }
  .news .el-form-item{
    width: 100%;
  }
  .news .el-form-item{
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
.right_input {

}
</style>
