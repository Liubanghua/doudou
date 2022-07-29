<template>
  <el-container style="width: 40%;margin-left: 20%;margin-top: 2%;">
    <el-main>
      <el-form ref="form" label-width="80px" title="基本信息">
        <el-form-item label="照片:" >
          <el-avatar shape="square" :size="120" :src="'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg'"></el-avatar>
        </el-form-item>
        <el-form-item label="照片上传:" >
          <el-upload
            class="avatar-uploader"
            action=""
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload">
            <img v-if="imageUrl" :src="imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="姓名:">
          <el-input placeholder="请输入姓名" style="width: 100%;"/>
        </el-form-item>
        <el-form-item label="性别:">
          <el-select placeholder="请选择性别" clearable style="width: 100%;">
            <el-option v-for="dict in this.getDictDatas(DICT_TYPE.SYSTEM_USER_SEX)"
                         :key="dict.value" :label="dict.label" :value="dict.value"/>
          </el-select>
        </el-form-item>
        <el-form-item label="年级:">
          <el-select placeholder="请选择年级" clearable style="width: 100%;">
            <el-option v-for="dict in this.getDictDatas(DICT_TYPE.SYSTEM_GRADE)"
                         :key="dict.value" :label="dict.label" :value="dict.value"/>
          </el-select>
        </el-form-item>
        <el-form-item label="班级:">
          <el-select placeholder="请选择班级" clearable style="width: 100%;">
            <el-option v-for="dict in this.getDictDatas(DICT_TYPE.SYSTEM_GRADE)"
                         :key="dict.value" :label="dict.label" :value="dict.value"/>
          </el-select>
        </el-form-item>
        <el-form-item label="学籍号:" prop="contactMobile" style="width: 100%;">
          <el-input placeholder="请输入学籍号" />
        </el-form-item>
        <el-form-item label="身份证号:" prop="contactMobile" style="width: 100%;">
          <el-input placeholder="请输入身份证号" />
        </el-form-item>
        <el-form-item label="出生日期:" prop="expireTime" style="width: 100%;">
          <el-date-picker clearable size="small" type="date"
                          value-format="timestamp" placeholder="请选择日期" style="width: 100%;"/>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" style="margin-left: 50%;">提交</el-button>
        </el-form-item>
      </el-form>
    </el-main>
  </el-container>
</template>

<script>
  export default {
      data() {
        return {
          imageUrl: ''
        };
      },
      methods: {
        handleAvatarSuccess(res, file) {
          this.imageUrl = URL.createObjectURL(file.raw);
        },
        beforeAvatarUpload(file) {
          const isJPG = file.type === 'image/jpeg';
          const isLt2M = file.size / 1024 / 1024 < 2;

          if (!isJPG) {
            this.$message.error('上传头像图片只能是 JPG 格式!');
          }
          if (!isLt2M) {
            this.$message.error('上传头像图片大小不能超过 2MB!');
          }
          return isJPG && isLt2M;
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
