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
      <el-col :xs="24" :sm="24" :lg="12">
        <div class="chart-wrapper">
          <temp-anomaly-pie-chart />
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="12">
        <div class="chart-wrapper">
          <leave-num-pie-chart />
        </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :xs="24" :sm="24" :lg="12">
        <div class="chart-wrapper">
          <touch-pie-char />
        </div>
      </el-col>
    </el-row>


  </div>
</template>

<script>
import TempAnomalyPieChart from '../../dashboard/educationBureauChard/TempAnomalyPieChart'
import LeaveNumPieChart from '../../dashboard/educationBureauChard/LeaveNumPieChart'
import TouchPieChar from '../../dashboard/educationBureauChard/TouchPieChar'

export default {
  name: 'Index',
  components: {
    TempAnomalyPieChart,
    LeaveNumPieChart,
    TouchPieChar
  },
  data() {
    return {
      value1: ''
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
