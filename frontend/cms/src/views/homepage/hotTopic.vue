<template>
  <div class="hotTopic">
    <common-header></common-header>
    <section>
      <div class="con_main">
        <aside>
          <h4 class="ad_tit"><span class="img_icon03"></span>热点聚焦</h4>
          <ul class="aside_ad_list">
            <li v-for="item in items" :id="item.id" @click="getFocusByID" :key="item.id">
              {{item.title}}
              <!--<span class="blockUp">停用</span>-->
              <span class="blockUp" v-if="!item.status">停用</span>
            </li>
          </ul>
        </aside>

        <div class="con_right">
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <h4 class="ad_tit">
              <el-switch
                v-model="ruleForm.status"
                active-color="#13ce66"
                inactive-value='0'
                active-value='1'
                inactive-color="#ff4949" inactive-text="启用状态:">
              </el-switch>
            </h4>
            <ul class="con_ul">
              <li class="list_state">
                <el-form-item label="热点名称" prop="title_cn">
                  <el-input v-model="ruleForm.title_cn" placeholder="请在此输入标题"></el-input>
                </el-form-item>
              </li>
              <li class="list_state">
                <el-form-item label="热点简述" prop="desc_cn">
                  <el-input v-model="ruleForm.desc_cn" placeholder="请在此输入描述"></el-input>
                </el-form-item>
              </li>
              <li class="list_state">
                <h4>宣传图片 :</h4>
                <div class="right_input">
                  <img alt="" class="style_banner fl" :src="imgUrl | formatImg">
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
      // end
      imgUrl:'',
      uploadData: {
        type: 'focus'
      },
      ruleForm: {
        title_cn: '',
        order: '',
        desc_cn: '',
        link: '',
        image:'',
        status: false
      },
      rules: {
        title_cn: [
          {required: true, message: '请在此输入标题', trigger: 'blur'},
        ],
        desc_cn: [
          {required: true, message: '请在此输入描述', trigger: 'blur'}
        ],
        order: [
          {required: true, message: '请在此输入展示位置', trigger: 'blur'}
        ],
        link: [
          {required: true, message: '请在此输入跳转链接', trigger: 'blur'}
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
  filters: {
    formatImg (img) {
      return axios.defaults.imageURL + img
    }
  },
  methods: {
    //init
    initNav:function(){
      api.fetch(api.uri.getFocusNavList).then(data => {
        console.log(data.data.result)
        if (data.data.status === 1) {
          this.items = data.data.result
          if (this.items.length > 0) {
            //根据id获取热图信息
            api.fetch(api.uri.getFocusByID, {focus_id: this.items[0].id}).then(data => {
              console.log(data)
              if (data.data.status === 1) {
                this.ruleForm = data.data.result
                if(this.ruleForm.status == 1){
                  this.ruleForm.status = '1'
                }else if(this.ruleForm.status = 0){
                  this.ruleForm.status = '0'
                }
                this.imgUrl = this.ruleForm.image
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
    //根据id获取信息
    getFocusByID:function(){
      api.fetch(api.uri.getFocusByID, {focus_id: event.currentTarget.id}).then(data => {
        console.log(data)
        if (data.data.status === 1) {
          this.ruleForm = data.data.result
          if(this.ruleForm.status == 1){
            this.ruleForm.status = '1'
          }else if(this.ruleForm.status = 0){
            this.ruleForm.status = '0'
          }
          this.imgUrl = this.ruleForm.image
        } else {
          this.msg = '返回错误'
        }
      }).catch((err) => {
        console.error(err.message)
      })
    },
    //更新focus
    update:function(){
      api.post(
        api.uri.updateFocusByID,
        {
          image:this.imgUrl,
          link:this.ruleForm.link,
          title_cn:this.ruleForm.title_cn,
          title_en:"",
          desc_cn:this.ruleForm.desc_cn,
          desc_en:"",
          status:parseInt(this.ruleForm.status),
          order:this.ruleForm.order,
          focus_id:this.ruleForm.focus_id
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
  .hotTopic .upAd{
    margin: 40px 0 0 40px;
  }
  .hotTopic .el-form-item{
    width: 100%;
  }
  .hotTopic .el-form-item{
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
