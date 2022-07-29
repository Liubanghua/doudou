<template>
	<view class="wrap" style="padding-bottom: 60px;">
		<view v-if="current === 0">
			<view class="search">
				<u-search v-model="keywords" @custom="search" @search="search"></u-search>
			</view>
			<view v-for="(item,itemIndex) in list" :key="itemIndex" class="card-item">
			<u-card class="task-list-item" :border="false" padding="20" margin="20rpx">
				<view slot="head" style="display: flex;align-items: center;justify-content: space-between;">
					<view style="display: flex;align-items: center;font-size: 30rpx;">
						{{item.createTime}}
					</view>
				</view>
				<view class="" slot="body">
					<view style="color: #999999;font-size: 22rpx;"><span>温度：</span>{{item.temperature}}℃</view>
				</view>
			</u-card>
		</view>
		</view>
	</view>
</template>
<script>
import { getStuRecord,getGradeList } from "@/static/api/principal/principal";

export default {
  name: "Touch",
  components: {
  },
  data() {
    return {
	  keywords:'',
      // 遮罩层
      loading: true,
	  current:0,
      // 导出遮罩层
      exportLoading: false,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 表单参数
      form: {},
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        createTime: null,
        studentId: 0
      },
      gradeId: null,
      classId: null,
      gradeOption: [],
      classOption: []
    };
  },
  created() {
    const deptId = this.queryParams.deptId;
    getGradeList(deptId).then(response => {
      this.gradeOption = response.data;
    });
    this.getList();
  },
  onLoad: function (options) {
  	this.studentId = options.studentId;
	this.createTime = options.createTime;
  	this.getList();
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 处理查询参数
		this.queryParams.studentId = this.studentId;
		this.queryParams.measureTime = this.createTime+'';
      let params = {...this.queryParams};
      // 执行查询
      getStuRecord(params).then(response => {
        this.list = response.data.tempRecords;
        this.total = response.data.total;
        this.loading = false;
      });
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNo = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.gradeId = null;
      this.classId = null;
      this.classOption = null;
      this.resetForm("queryForm");
      this.queryParams.date = null;
      this.handleQuery();
    },
    //根据年级获取班级
    getClass(val){
      const deptId = ""+val;
      if(deptId != null && deptId != ""){
        getGradeList(val).then(response => {
          this.classOption = response.data;
        });
      }else{
        this.classOption = null;
        this.classId = null;
      }
    },
	search(value) {
		// value自动接收输入框中的内容
		if (value == ''){
		    //如果输入的值为空则加载所有的列表
			this.getList();
		}else {
		//先清空展示的数据
			let totalList = [];
			totalList = this.list;
			let displayList = []
			console.log(totalList)
			//然后开始循环全部数据
			for (var i = 0; i < totalList.length; i++) {
				//判断数据里面是否有符合输入的内容  不符合返回-1 只需要大于或等于0就是符合
				//（核心所在，其它都是根据需求来自己写）
				if (totalList[i].name.indexOf(value) >= 0) {
					displayList.push(totalList[i])
				}
			}
			this.list = displayList;
		}
	}
  }
};
</script>
<style lang="scss" scoped>
@import 'index.scss';
page {
	background-color: #f5f5f5;
}
.wrap .search{
	background: #ffffff;
}
.apply-text{
	font-size: 28rpx;
	color: #333333;
	span{
		color: #999999;
	}
}
.user-images{
	width: 28px;
	height:28px;
	margin-right: 8px;
}
.apply-list-foot{
	font-size: 28rpx;
}
.personnel-list{
	display: flex;
	align-items: center;
	flex-wrap:wrap;
	.personnel-user{
		position: relative;
		margin: 5px 9px 0;
	}
	.user-images{
		width: 48px;
		height:48px;
		margin-right:0;
		
	}
	.iconfont{
		position: absolute;
		top:-12px;
		right:-5px;
		color: #FE0100;
	}
}
</style>
