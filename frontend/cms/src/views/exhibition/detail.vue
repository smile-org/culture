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
                <el-form-item label="展览类别" prop="category" class="show_star">
                  <el-select v-model="ruleForm.category" placeholder="请选择">
                    <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
                  </el-select>
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
      //判断添加还是更新
      addEx:true,
      uploadData:{
        type:'banner'
      },
      tmpid: 1,
      ruleForm: {
        title_cn: '',
        order: '',
        content_cn: '',
        link: '',
        status: false
      },
      rules: {
        title_cn: [
          { required: true, message: '请在此输入标题', trigger: 'blur' },
        ],
        content_cn: [
          { required: true, message: '请输入内容', trigger: 'blur' }
        ],
        category: [
          { required: true, message: '请选择类别', trigger: 'blur' }
        ]
      },
      exContent: '',
      imgUrl:'',
      currentUEditor:{},
      items:[],
      value1: 1,
      value2: true,
      options: [{
        value: 1,
        label: '国内'
      }, {
        value: 2,
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
      }
    }).catch((err) => {
      console.error(err.message)
    })
  },
  filters: {
    formatImg (img) {
      return axios.defaults.imageURL + img
    }
  },
  methods: {
    editorReady:function (editorInstance) {
      this.getExhibit(editorInstance, this.items[0].id)
      this.currentUEditor = editorInstance
      editorInstance.addListener('contentChange', () => {
        this.exContent = editorInstance.getContent()
      })
    },
    getExhibit: function (editorInstance, exId) {
      this.addEx = true
      api.fetch(api.uri.getExhibitEditPageInfoByID, {exhibit_id: exId}).then(data => {
        console.log(data.data.result)
        if (data.data.status === 1) {
          this.ruleForm = data.data.result.exhibitToBeEdit
          if(this.ruleForm.status == 1){
            this.ruleForm.status = '1'
          }else if(this.ruleForm.status = 0){
            this.ruleForm.status = '0'
          }
          this.exContent = this.ruleForm.content_cn
          editorInstance.setContent(this.exContent)
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
      api.post(
        api.uri.updateExhibitByID,
        {
          title_cn:this.ruleForm.title_cn,
          title_en:"",
          content_cn:this.exContent,
          content_en:"",
          status:parseInt(this.ruleForm.status),
          category:this.ruleForm.category,
          form_id:1,
          exhibit_id:this.ruleForm.exhibit_id
        }
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
      api.post(
        api.uri.addExhibit,
        {
          title_cn:this.ruleForm.title_cn,
          title_en:"",
          content_cn:this.exContent,
          content_en:"",
          status:parseInt(this.ruleForm.status),
          category:this.ruleForm.category,
          form_id:null,
          exhibit_id:this.ruleForm.exhibit_id
        }
      ).then(data => {
        location.reload()
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
