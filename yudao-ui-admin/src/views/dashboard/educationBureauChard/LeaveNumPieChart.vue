<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from '../mixins/resize'

export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '300px'
    }
  },
  data() {
    return {
      chart: null
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.initChart()
    })
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    initChart() {
      this.chart = echarts.init(this.$el, 'macarons')

      this.chart.setOption({
        title: {
          text: '请假人数'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
          left: 'center',
          bottom: '10',
          label: {
            normal: {
              show: true,
              formatter: '{b}: {c}({d}%)'
            }
          },
          data: ['一中', '二中', '三中', '四中', '五中', '六中']
        },
        series: [
          {
            name: '请假人数',
            type: 'pie',
            radius: '50%',
            data: [
              { value: 14, name: '一中' },
              { value: 23, name: '二中' },
              { value: 15, name: '三中' },
              { value: 12, name: '四中' },
              { value: 15, name: '五中' },
              { value: 19, name: '六中' }
            ],
            animationEasing: 'cubicInOut',
            animationDuration: 2600
          }
        ]
      })
    }
  }
}
</script>
