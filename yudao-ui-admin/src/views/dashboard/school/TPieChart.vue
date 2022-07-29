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
          text: '体温异常'
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
          data: ['一年级', '二年级', '三年级', '四年级', '五年级', '六年级']
        },
        series: [
          {
            name: '体温异常',
            type: 'pie',
            radius: '50%',
            data: [
              { value: 14, name: '一年级' },
              { value: 23, name: '二年级' },
              { value: 15, name: '三年级' },
              { value: 12, name: '四年级' },
              { value: 15, name: '五年级' },
              { value: 19, name: '六年级' }
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
