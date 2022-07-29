<template>
  <div class="dashboard-editor-container">


    <el-row :gutter="32">
      <el-col :xs="24" :sm="24" :lg="3" :offset="3">
        <div class="chart-wrapper">
          <el-avatar shape="square" :size="150" :src="url"></el-avatar>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="6" :offset="1">
        <el-descriptions :column="1" :size="size" style="margin-top: 30px">
          <el-descriptions-item label="姓名">{{ studentInform.studentName }}</el-descriptions-item>
          <el-descriptions-item label="年级">{{ studentInform.campusGradeName }}</el-descriptions-item>
          <el-descriptions-item label="班级">{{ studentInform.campusClassName }}</el-descriptions-item>
          <el-descriptions-item label="学号">{{ studentInform.studentNo }}</el-descriptions-item>
        </el-descriptions>
      </el-col>
    </el-row>

    <el-row :gutter="32">
      <el-col :xs="24" :sm="24" :lg="7">
        <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="80px">
          <el-form-item label="统计时间:">
            <el-date-picker
              v-model="queryParams.time"
              type="date"
              value-format="yyyy-MM-dd"
              placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
        </el-form>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="7">
        <el-button type="primary" icon="el-icon-search" @click="handleQuery">查询</el-button>
      </el-col>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="时间" align="center" prop="createTime" />
      <el-table-column label="温度" align="center" prop="temperature" />
    </el-table>


  </div>
</template>

<script>
import { getStuRecord,getGrade } from "@/api/principal/principal";

const defaultStudentInform = {
  studentName: '',
  campusGradeName: '',
  campusClassName: '',
  studentNo: ''
}

export default {
  name: 'princ-record',
  components: {

  },
  data() {
    return {
      //列表加载
      loading: true,
      //初始化学生信息
      studentInform: JSON.parse(JSON.stringify(defaultStudentInform)),
      // 查询参数
      queryParams: {
        time: '',
        id: null
      },
      //学生头像URL
      url: '',
      size:'',
      list: [],
      // 显示搜索条件
      showSearch: true,
    }
  },
  computed: {
    timeDefault() {
      var date = new Date();
      var s1 = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + (date.getDate());
      return s1;
    }
  },
  created() {
    //获取学生信息
    const stuId = this.$route.params && this.$route.params.stuId;
    this.queryParams.id = stuId;
    //默认为当天
    this.queryParams.time = this.timeDefault;
    let params = {...this.queryParams};
    this.studentInform = JSON.parse(JSON.stringify(defaultStudentInform));
    getStuRecord(params).then(response => {
      //获取班级
      const classId = response.data.deptId;
      getGrade(classId).then(response => {
        this.studentInform.campusClassName = response.data.name;
        const gradeId = response.data.parentId;
        getGrade(gradeId).then(response => {
          this.studentInform.campusGradeName = response.data.name;
        });
      });
      
      this.studentInform.studentName = response.data.name;
      this.studentInform.studentNo = response.data.studentNo;
      this.url = response.data.avatar;
      this.list = response.data.tempRecords;
      this.loading = false;
    });
  },
  methods: {
    //查询按钮
    handleQuery() {
      const stuId = this.$route.params && this.$route.params.stuId;
      this.queryParams.id = stuId;
      let params = {...this.queryParams};
      getStuRecord(params).then(response => {
        this.list = response.data.tempRecords;
      });
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  position: relative;

  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}

@media (max-width:1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}
</style>
