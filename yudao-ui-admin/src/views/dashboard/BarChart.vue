<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from './mixins/resize'

const animationDuration = 6000

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
      default: '500px'
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
          text: '体温测量'
        },
        xAxis: {
            type: 'category',
            data: ['一年级', '二年级', '三年级', '四年级', '五年级', '六年级']
          },
          yAxis: {
            type: 'value'
          },
          legend: {
            data: ['正常', '发烧', '未测量']
          },
          series: [
            {
              name: '正常',
              type: 'bar',
              label: {
                show: true,
                position: 'top'
              },
              data: [23, 24, 18, 25, 27, 28]
            },
            {
              name: '发烧',
              type: 'bar',
              label: {
                show: true,
                position: 'top'
              },
              itemStyle: {
                color: '#a90000'
              },
              data: [26, 24, 18, 22, 23, 20]
            },
            {
              name: '未测量',
              type: 'bar',
              label: {
                show: true,
                position: 'top'
              },
              data: [26, 24, 18, 22, 23, 20]
            }
          ]
      })
    }
  }
}
</script>
