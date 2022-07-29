<template>
  <el-container style="width: 40%;margin-left: 20%;margin-top: 2%;">
    <el-main>
      <el-form ref="form" :model="form" :rules="rules" label-width="90px" title="基本信息">
        <el-form-item v-if="form.id != undefined" label="照片:" >
          <el-avatar shape="square" :size="120" :src="form.avatar"></el-avatar>
        </el-form-item>
        <el-form-item label="照片上传:" >
          <el-upload
            class="avatar-uploader"
            action="#"
            :http-request="requestUpload"
            :show-file-list="false"
            :before-upload="beforeUpload">
            <img v-if="imageUrl" :src="imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="姓名:" prop="name">
          <el-input v-model="form.name" placeholder="请输入姓名" style="width: 100%;"/>
        </el-form-item>
        <el-form-item label="性别:" prop="sex">
          <el-select v-model="form.sex" placeholder="请选择性别" clearable style="width: 100%;">
            <el-option v-for="dict in sexOptions"
                         :key="dict.value" :label="dict.label" :value="dict.value"/>
          </el-select>
        </el-form-item>
        <el-form-item label="年级:" prop="gradeId">
          <el-select v-model="gradeId" placeholder="请选择年级" @change="getClass">
            <el-option v-for="item in this.gradeOption"
              :key="item.id" :label="item.name" :value="item.id"/>
          </el-select>
        </el-form-item>
        <el-form-item label="班级:" prop="classId">
          <el-select v-model="classId" placeholder="请选择班级">
            <el-option v-for="item in this.classOption"
              :key="item.id" :label="item.name" :value="item.id"/>
          </el-select>
        </el-form-item>
        <el-form-item label="学籍号:" prop="studentNo" style="width: 100%;">
          <el-input v-model="form.studentNo"  placeholder="请输入学籍号" />
        </el-form-item>
        <el-form-item label="身份证号:" prop="idCard" style="width: 100%;">
          <el-input v-model="form.idCard" placeholder="请输入身份证号" />
        </el-form-item>
        <!-- <el-form-item label="出生日期:" prop="expireTime" style="width: 100%;">
          <el-date-picker clearable size="small" type="date"
                          value-format="timestamp" placeholder="请选择日期" style="width: 100%;"/>
        </el-form-item> -->
        <el-form-item>
            <el-button type="primary" style="margin-left: 50%;" @click="submitForm">提交</el-button>
        </el-form-item>
      </el-form>
    </el-main>
  </el-container>
</template>

<script>
import { getGradeList,createStudent,getStudent,updateStudent,getGrade } from "@/api/principal/principal";

export default {
  name: "StudentDetail",
  components: {
  },
  data() {
    return {
      dId: null,
      sexOptions: [{
          value: 1,
          label: '男'
        }, {
          value: 0,
          label: '女'
      }],
      imageUrl: '',
      // 表单参数
      form: {},
      //年级
      gradeOption: [],
      gradeId: null,
      //班级
      classOption: [],
      classId: null,
      // 表单校验
      rules: {
        name: [{ required: true, message: "姓名不能为空", trigger: "blur" }],
        sex: [{ required: true, message: "性别不能为空", trigger: "blur" }],
        studentNo: [{ required: true, message: "学号不能为空", trigger: "blur" }],
        idCard: [{ required: true, message: "身份证号不能为空", trigger: "blur" }],
      }
    };
  },
  beforeCreate() {
      const deptId = 0;
      getGradeList(deptId).then(response => {
        this.gradeOption = response.data;
        this.gradeId = response.data[0].id;
        console.log(this.gradeId);
        console.log(response.data[0].id);
        getGradeList(this.gradeId).then(response => {
          this.classOption = response.data;
          this.classId = response.data[0].id;
          console.log(this.gradeId);
        });
      });
      const stuId = this.$route.params && this.$route.params.stuId;
      var deId = null;
      if(stuId != null){
        getStudent(stuId).then(response => {
          this.form = response.data;
          this.dId = response.data.deptId;
          deId = response.data.deptId;
          getGrade(this.dId).then(response => {
            this.gradeId = response.data.parentId;
            getGradeList(this.gradeId).then(response => {
              this.classOption = response.data;
              this.classId = deId;
            });
          });
        });
      }
  },
  methods: {
    requestUpload(){},
    //根据年级获取班级
    getClass(val){
      getGradeList(val).then(response => {
        this.classOption = response.data;
      });
    },
    /** 提交按钮 */
    submitForm() {
      //this.form.avatar = this.imageUrl;
      this.form.deptId = this.classId;
      this.$refs["form"].validate(valid => {
        if (!valid) {
          return;
        }
        // 修改的提交
        if (this.form.id != null) {
          updateStudent(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            // 关闭指定页签
            const obj = { path: "/studentmemt/prin/updStu/" + this.form.id, name: "StudentDetail" };
            this.$tab.closePage(obj);
            this.$router.push({
                path:'/principal/studentmemt',
            })
          });
          return;
        }
        // 添加的提交
        createStudent(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          // 关闭指定页签
          const obj = { path: "/studentmemt/prin/addStu", name: "StudentDetail" };
          this.$tab.closePage(obj);
          this.$router.push({
              path:'/principal/studentmemt',
          })
        });
      });
    },
    // 上传预处理
    beforeUpload(file) {
      if (file.type.indexOf("image/") == -1) {
        this.$modal.msgError("文件格式错误，请上传图片类型,如：JPG，PNG后缀的文件。");
      } else {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => {
          this.imageUrl = reader.result;
          console.log(this.imageUrl);
        };
      }
    }
  }
}
</script>

<style>
  .avatar-uploader .el-upload {
      border: 1px dashed #d9d9d9;
      border-radius: 6px;
      cursor: pointer;
      position: relative;
      overflow: hidden;
    }
    .avatar-uploader .el-upload:hover {
      border-color: #409EFF;
    }
    .avatar-uploader-icon {
      font-size: 28px;
      color: #8c939d;
      width: 120px;
      height: 120px;
      line-height: 120px;
      text-align: center;
    }
    .avatar {
      width: 120px;
      height: 120px;
      display: block;
    }
</style>
