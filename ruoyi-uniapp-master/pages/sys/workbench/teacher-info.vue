<template>
	<view class="wrap" style="padding-bottom: 60px;">
		<view v-if="current === 0">
			<view class="search">
				<u-search v-model="keywords" @custom="search" @search="search"></u-search>
			</view>
			<view v-for="(item,itemIndex) in list" :key="itemIndex" class="card-item">
			<u-card class="task-list-item" :border="false" padding="20" margin="20rpx" @click="navTo('/pages/sys/workbench/teacher-edit')" >
				<view slot="head" style="display: flex;align-items: center;justify-content: space-between;">
					<view style="display: flex;align-items: center;font-size: 30rpx;">
						<!--<image class="user-images" src="item.avatar"></image>-->{{item.username}}
					</view>
				</view>
				<view class="" slot="body">
					<u-row gutter="16">
						<u-col span="12">
							<view class="apply-text"><span>用户昵称：</span>{{item.nickname}}</view>
						</u-col>
						<u-col span="12">
							<view class="apply-text"><span>部门：</span><span v-if = "item.dept != null">{{item.dept.name}}</span></view>
						</u-col>
						<u-col span="12">
							<view class="apply-text"><span>手机号码：</span>{{item.mobile}}</view>
						</u-col>
						<u-col span="12">
							<view class="apply-text"><span>状态：</span>
							<span v-if = "item.status === 0">已启用</span>
							<span v-else = "item.status === 1">已禁用</span>
							</view>
						</u-col>
						<u-col span="12">
							<view class="apply-text"><span>创建时间：</span>{{item.createTime}}</view>
						</u-col>
					</u-row>
				</view>
			</u-card>
			</view>
		</view>
	</view>
</template>
<script>
import { getTeacherPage,getGradeList } from "@/static/api/principal/principal";

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
        date: null,
        deptId: 0
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
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 处理查询参数
      if(this.classId != null){
        this.queryParams.deptId = this.classId;
      }else {
        this.queryParams.deptId = this.gradeId;
      }
      let params = {...this.queryParams};
      // 执行查询
      getTeacherPage(params).then(response => {
        this.list = response.data.list;
        this.total = response.data.total;
        this.loading = false;
      });
    },
	change(e) {
		console.log('change', e);
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
	navTo(url) {
		uni.navigateTo({
			url: url
		});
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
