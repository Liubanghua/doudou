<template>
  <div class="dashboard-editor-container">


      <el-row :gutter="32">
        <el-col :xs="24" :sm="24" :lg="7">
          <el-form ref="form" label-width="80px">
            <el-form-item label="统计时间:">
              <el-date-picker
                v-model="value1"
                type="date"
                placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
          </el-form>
        </el-col>
        <el-col :xs="24" :sm="24" :lg="7">
          <el-button type="primary" icon="el-icon-search">查询</el-button>
        </el-col>
      </el-row>


    <el-row :gutter="32">
      <el-col :xs="24" :sm="24" :lg="3" :offset="3">
        <div class="chart-wrapper">
          <el-avatar shape="square" :size="150" :src="url"></el-avatar>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="6" :offset="1">
        <el-descriptions :column="1" :size="size" style="margin-top: 30px">
          <el-descriptions-item label="姓名">张三</el-descriptions-item>
          <el-descriptions-item label="班级">六年级三班</el-descriptions-item>
          <el-descriptions-item label="学号">6704110302</el-descriptions-item>
        </el-descriptions>
      </el-col>
    </el-row>
    
    <!-- 列表 -->
    <el-table>
      <el-table-column label="第一次" align="center" />
      <el-table-column label="第二次" align="center" />
      <el-table-column label="第三次" align="center" />
      <el-table-column label="第四次" align="center" />
      <el-table-column label="第五次" align="center" />
    </el-table>
    <!-- 分页组件 -->
    <!-- <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/> -->


  </div>
</template>

<script>

export default {
  name: 'Index',
  components: {
    
  },
  data() {
    return {
      value1: '',
      url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
      size:''
    }
  },
  methods: {

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
