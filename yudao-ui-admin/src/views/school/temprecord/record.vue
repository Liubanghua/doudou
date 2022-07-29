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
              v-model="queryParams.creatTime"
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
      <el-table-column label="第一次" align="center" />
      <el-table-column label="第二次" align="center" />
      <el-table-column label="第三次" align="center" />
      <el-table-column label="第四次" align="center" />
      <el-table-column label="第五次" align="center" />
    </el-table>


  </div>
</template>

<script>
import { getStudent,getStuRecord } from "@/api/principal/principal";

const defaultStudentInform = {
  studentName: '',
  campusGradeName: '',
  campusClassName: '',
  studentNo: ''
}

export default {
  name: 'scho-record',
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
        creatTime: '',
        stuId: null
      },
      //学生头像URL
      url: '',
      size:''
    }
  },
  computed: {
    timeDefault() {
      var date = new Date();
      var s1 = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + (date.getDate());
      return s1;
    }
  },
  mounted() {
      //默认为当天
      this.queryParams.creatTime = this.timeDefault;
  },
  created() {
    //获取学生信息
    const stuId = this.$route.params && this.$route.params.stuId;
    this.queryParams.stuId = stuId;
    this.studentInform = JSON.parse(JSON.stringify(defaultStudentInform));
    getStudent(stuId).then(response => {
      this.studentInform = response.data;
    });
    this.getList();
  },
  methods: {
    //获取体温记录列表
    getList() {
      let params = {...this.queryParams};
      getStuRecord(params).then(response => {
        this.list = response.data.list;
      });
    },
    //查询按钮
    handleQuery() {
      this.getList();
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
