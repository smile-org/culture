<template>
  <div class="exhibition">
    <common-header  type="exhibitionDetail"></common-header>
    <section>
      <div class="con_main">
        <aside>
          <h4 class="ad_tit"><span class="img_icon04"></span>文化展览  <span class="small_addBtn" @click="resetForm('ruleForm')">添加</span></h4>
          <ul class="aside_ad_list">
            <li v-for="item in items" :id="item.id" @click="getExByClicked(item.id)" :key="item.id" v-bind:class="{active: item.id == tmpid}">
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
                inactive-value='0'
                active-value='1'
                active-color="#13ce66"
                inactive-color="#ff4949" inactive-text="启用状态:">
              </el-switch>
            </h4>
            <ul class="con_ul">
              <li class="list_state">
                <el-form-item label="展览类别" prop="category" class="show_star">
                  <el-select v-model="ruleForm.category" placeholder="请选择">
                    <el-option v-for="item in ruleForm.options" :key="item.value" :label="item.text" :value="item.value"></el-option>
                  </el-select>
                </el-form-item>
              </li>
              <li class="list_state">
                <el-form-item label="表单选择" prop="category" >
                  <el-select v-model="ruleForm.form_id" placeholder="请选择">
                    <el-option v-for="item in ruleForm.formList" :key="item.value" :label="item.text" :value="item.value"></el-option>
                  </el-select>
                </el-form-item>
              </li>
              <li class="list_state language_con">
                <span @click="lanChange('cn')" v-bind:class="{active: this.lan == 'cn'}">中文</span>
                <span @click="lanChange('en')" v-bind:class="{active: this.lan == 'en'}">英文</span>
              </li>
              <!--<li class="list_state">-->
                <!--<h4>宣传图片 :</h4>-->
                <!--<div class="right_input">-->
                  <!--<img src="./../../assets/img/style_banner.jpg" alt="" class="style_banner">-->
                  <!--<a href="#" class="choose_file">选择文件</a>-->
                <!--</div>-->
              <!--</li>-->
              <li class="list_state">
                <el-form-item label="展览名称" prop="title">
                  <el-input v-model="ruleForm.title" placeholder="请在此输入标题"></el-input>
                </el-form-item>
              </li>

              <li class="list_state">
                <h4>详细介绍 :</h4>
                <div class="right_input">
                  <VueUEditor @ready="editorReady" style="maxWidth : calc(100% - 300px);padding: 20px 0;"></VueUEditor>
                </div>
              </li>
            </ul>
          </el-form>
          <!--<P class="input_warn">-->
            <!--请填写详细介绍-->
          <!--</P>-->
          <div class="tc">
            <button class="save_btn mt20 mb30" @click="submitForm('ruleForm')" v-if="addEx">保  存</button>
            <button class="save_btn mt20 mb30" style="background-color: #7dc71b" @click="submitFormAdd('ruleForm')" v-if="!addEx">添  加</button>
          </div>
        </div>
      </div>

    </section>
  </div>
</template>

<script>
import api from '../../service/api'
import VueUEditor from 'vue-ueditor'
import commonHeader from '../../components/CommonHeader.vue'
export default {
  components: {
    commonHeader,
    VueUEditor
  },
  data:function () {
    return {
      lan:'cn',
      operation:'edit',
      //判断添加还是更新
      addEx:true,
      uploadData:{
        type:'banner'
      },
      tmpid: 1,
      messageCn:{
        title:'',
        content:'',
        exhibit_id:'',
        status:'',
        category:'',
        form_id:'',
        form_cn_name:'',
        formList:[],
        options:[]
      },
      messageEn:{
        title:'',
        content:'',
        exhibit_id:'',
        status:'',
        category:'',
        form_id:'',
        form_en_name:'',
        formList:[],
        options:[]
      },
      ruleForm: {
        title: '',
        content: '',
        exhibit_id: '',
        status: '',
        category: '',
        form_id:'',
        formList:[]
      },
      // formList:{},
      rules: {
        title: [
          { required: true, message: '请在此输入标题', trigger: 'blur' },
        ],
        content: [
          { required: true, message: '请输入内容', trigger: 'blur' }
        ],
        category: [
          { required: true, message: '请选择类别', trigger: 'blur' }
        ]
      },
      imgUrl:'',
      currentUEditor:{},
      items:[],
      value1: 1,
      value2: true,
      // options: [],
      value: ''
    }
  },
  filters: {
    formatImg (img) {
      return axios.defaults.imageURL + img
    }
  },
  methods: {
    lanChange:function(type){
      if(type == 'cn'){
        this.lan = 'cn'
        if(this.operation === 'edit'){
          this.ruleForm = this.messageCn
        }else if(this.operation === 'new'){
          this.messageEn = this.ruleForm
          this.ruleForm = this.messageCn
        }
        if(this.ruleForm.content == null){
          this.ruleForm.content = ''
        }
        this.currentUEditor.setContent(this.ruleForm.content)
      }else if(type == 'en'){
        this.lan = 'en'
        if(this.operation == 'edit'){
          this.ruleForm = this.messageEn
        }else if(this.operation == 'new'){
          this.messageCn = this.ruleForm
          this.messageEn.form_id = this.messageCn.form_id
          this.messageEn.category = this.messageCn.category
          this.ruleForm = this.messageEn
        }
        if(this.ruleForm.content == null){
          this.ruleForm.content = ''
        }
        this.currentUEditor.setContent(this.ruleForm.content)
      }
    },

    editorReady:function (editorInstance) {
      api.fetch(api.uri.getExhibitNavList).then(data => {
        console.log(data.data.result)
        if (data.data.status === 1) {
          this.items = data.data.result
          this.getExhibit(editorInstance, this.items[0].id)
        }
      }).catch((err) => {
        console.error(err.message)
      })
      this.currentUEditor = editorInstance
      editorInstance.addListener('contentChange', () => {
        this.ruleForm.content = editorInstance.getContent()
      })
    },
    getExhibit: function (editorInstance, exId) {
      this.lan = 'cn'
      this.addEx = true
      this.operation = 'edit'
      api.fetch(api.uri.getExhibitEditPageInfoByID, {exhibit_id: exId}).then(data => {
        console.log(data.data.result)
        if (data.data.status === 1) {
          var message = data.data.result.exhibitToBeEdit
          //中文
          this.messageCn.title = message.title_cn
          this.messageCn.content = message.content_cn
          this.messageCn.status = message.status
          this.messageCn.category = message.category + ""
          this.messageCn.form_cn_name = message.form_cn_name
          this.messageCn.exhibit_id = message.exhibit_id
          this.messageCn.form_id = message.form_id
          this.messageCn.formList = data.data.result.formList
          this.messageCn.options = data.data.result.categoryList
          //英文
          this.messageEn.title = message.title_en
          this.messageEn.content = message.content_en
          this.messageEn.status = message.status
          this.messageEn.category = message.category + ""
          this.messageEn.form_en_name = message.form_en_name
          this.messageEn.exhibit_id = message.exhibit_id
          this.messageEn.form_id = message.form_id
          this.messageEn.formList = data.data.result.formList
          this.messageEn.options = data.data.result.categoryList

          this.ruleForm = this.messageCn
          if(this.ruleForm.status == 1){
            this.ruleForm.status = '1'
          }else if(this.ruleForm.status = 0){
            this.ruleForm.status = '0'
          }
          console.log(this.ruleForm)
          editorInstance.setContent(this.ruleForm.content || '')
        } else {
          this.msg = '返回错误'
        }
      }).catch((err) => {
        console.error(err.message)
      })
    },
    getExByClicked: function (id) {
      this.tmpid = id;
      this.getExhibit(this.currentUEditor, event.currentTarget.id)
    },
    //更新banner
    update:function(){
      var params = {}
      if (this.lan === 'cn') {
        params = {
          lan:'cn',
          status:parseInt(this.ruleForm.status),
          category:parseInt(this.ruleForm.category),
          title_cn:this.ruleForm.title,
          content_cn: this.ruleForm.content,
          exhibit_id:this.ruleForm.exhibit_id,
          form_id:parseInt(this.ruleForm.form_id)
        }
      }else{
        params = {
          lan:'en',
          status:parseInt(this.ruleForm.status),
          category:parseInt(this.ruleForm.category),
          title_en:this.ruleForm.title,
          content_en: this.ruleForm.content,
          exhibit_id:this.ruleForm.exhibit_id,
          form_id:parseInt(this.ruleForm.form_id)
        }
      }
      api.post(
        api.uri.updateExhibitByID,
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
    addExhibit:function(){
      var params = {}
      if (this.lan === 'cn') {
        params = {
          lan:'cn',
          status:parseInt(this.ruleForm.status),
          category:parseInt(this.ruleForm.category),
          title_cn:this.ruleForm.title,
          content_cn: this.ruleForm.content,
          exhibit:this.ruleForm.exhibit_id,
          form_id:parseInt(this.ruleForm.form_id)
        }
      }else{
        params = {
          lan:'en',
          status:parseInt(this.ruleForm.status),
          category:parseInt(this.ruleForm.category),
          title_en:this.ruleForm.title,
          content_en: this.ruleForm.content,
          exhibit:this.ruleForm.exhibit_id,
          form_id:parseInt(this.ruleForm.form_id)
        }
      }
      api.post(
        api.uri.addExhibit,
        params
      ).then(data => {
        // location.reload()
        this.$message('添加成功！')
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
      })
    },
    submitFormAdd(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          console.log('新增')
          this.addExhibit()
        } else {
          console.log('error submit!!');
          return false;
        }
      })
    },
    resetForm(formName) {
      this.lan = 'cn'
      this.operation = 'new'
      api.fetch(api.uri.getExhibitEditPageInfoByID, {exhibit_id: null}).then(data => {
        console.log(data.data.result)
        if (data.data.status === 1) {
          this.ruleForm = {
            title: '',
            content: '',
            exhibit_id: '',
            status: '',
            category: '',
            form_id:''
          }
          this.messageCn={
            title:'',
            content:'',
            exhibit_id:'',
            status:'',
            category:'',
            form_id:'',
            form_cn_name:'',
            formList:[],
            options:[]
          }
          this.messageEn={
            title:'',
            content:'',
            exhibit_id:'',
            status:'',
            category:'',
            form_id:'',
            form_en_name:'',
            formList:[],
            options:[]
          }
          this.ruleForm.formList = data.data.result.formList
          this.ruleForm.status = 0
          this.ruleForm.options = data.data.result.categoryList
        } else {
          this.msg = '返回错误'
        }
      }).catch((err) => {
        console.error(err.message)
      })
      this.currentUEditor.setContent('')
      this.$refs[formName].resetFields();
      this.$message('请在此添加');
      this.addEx = false
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
