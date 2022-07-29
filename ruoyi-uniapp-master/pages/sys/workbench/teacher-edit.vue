<template>
	<view class="wrap">
		<u-gap height="20" bg-color="#f5f5f5"></u-gap>
		<u-form  class="task-form-field" :model="model" :rules="rules" ref="uForm">
			<u-form-item label="用户昵称" label-width="180">
				<u-input  placeholder="请输入姓名"></u-input>
			</u-form-item>
			<u-form-item label="归属部门" label-width="180">
				<input v-model="departmentName" @click="departmentOpen" class="input" type="text" placeholder="点击选择组织" disabled />
			</u-form-item>
			<u-form-item label="手机号码" label-width="180">
				<u-input  placeholder="请输入手机号"></u-input>
			</u-form-item>
			<u-form-item label="邮箱" label-width="180">
				<u-input  placeholder="请输入邮箱"></u-input>
			</u-form-item>
			<u-form-item label="用户性别" label-width="180">
				<u-input  placeholder="请输入性别"></u-input>
			</u-form-item>
			<u-form-item label="备注" label-width="180">
				<u-input  placeholder="请输入备注"></u-input>
			</u-form-item>
		</u-form>
		<view class="form-footer">
			<u-button class="btn" type="primary" @click="submit">保存</u-button>
			<!-- <u-button class="btn" type="default" @click="cancel">关闭</u-button> -->
		</view>
		<ljs-treeChoose :data="department" @getChooseValue="getDepartmentValue" @close="closeTree" v-if="departmentTag"/>
	</view>
</template>
<script>
/**
 * Copyright (c) 2013-Now http://aidex.vip All rights reserved.
 */
import base64 from '@/common/base64.js';
export default {
	data() {
		return {
			keywords:'',
			auiPicker: {
			    title: 'picker多级联动',
			    layer: null,
			    data: []
			},
			departmentTag: false,
			department: [],
			departmentName: "",
			model: {
				oldPassword: '',
				newPassword: '',
				confirmNewPassword: ''
			},
			rules: {
				oldPassword: [
					{
						required: true,
						message: '请输入旧密码',
						trigger: ['change','blur'],
					}
				],
				newPassword: [
					{
						required: true,
						message: '请输入新密码',
						trigger: ['change','blur'],
					},
					{
						pattern: /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]+\S{5,12}$/,
						message: '需同时含有字母和数字，长度在6-12之间',
						trigger: ['change','blur'],
					}
				],
				confirmPassword: [
					{
						required: true,
						message: '请重新输入密码',
						trigger: ['change','blur'],
					},
					{
						validator: (rule, value, callback) => {
							return value === this.model.newPassword;
						},
						message: '两次输入的密码不相等',
						trigger: ['change','blur'],
					}
				],
			}
		};
	},
	onLoad() {
		this.department = [{"id":100,"label":"九山科技","disabled":false,"type":null,"children":[{"id":101,"label":"深圳总公司","disabled":false,"type":null,"children":[{"id":103,"label":"研发部门","disabled":false,"type":null,"openTag":false,"choose":false},{"id":104,"label":"市场部门","disabled":false,"type":null,"openTag":false,"choose":false},{"id":105,"label":"测试部门","disabled":false,"type":null,"openTag":false,"choose":false},{"id":106,"label":"财务部门","disabled":false,"type":null,"openTag":false,"choose":false},{"id":107,"label":"运维部门","disabled":false,"type":null,"openTag":false,"choose":false}],"openTag":false,"choose":false},{"id":102,"label":"长沙分公司","disabled":false,"type":null,"children":[{"id":108,"label":"市场部门","disabled":false,"type":null,"openTag":false,"choose":false},{"id":109,"label":"财务部门","disabled":false,"type":null,"openTag":false,"choose":false}],"openTag":false,"choose":false},{"id":200,"label":"银川分公司","disabled":false,"type":null,"children":[{"id":202,"label":"人事部","disabled":false,"type":null,"openTag":false,"choose":false},{"id":201,"label":"研发部","disabled":false,"type":null,"openTag":false,"choose":false}],"openTag":false,"choose":false}],"openTag":false,"choose":false}];
	},
	onReady() {
	},
	methods: {
		// 所属部门 - 打开
		departmentOpen() {
			this.departmentTag = true;
		},
		// 所属部门 - 关闭
		closeTree(val){
			this.departmentTag = val;
		},
		// 所属部门 - 获取
		// ids: 集合
		// items: 节点对象集合
		getDepartmentValue(ids, items){
			console.log(ids, items)
			items.forEach((item, i)=>{
				this.departmentName = item.label + "-";
			});
			this.departmentName = this.departmentName.substring(0, this.departmentName.length - 1);
		},
		submit() {
			this.$refs.uForm.validate(valid => {
				if (valid) {
					this.$u.api.user.infoSavePwd({
						oldPassword: this.model.oldPassword,
						newPassword: this.model.newPassword
					}).then(res => {
						if(res.code == '200'){
							uni.showModal({
								title: '提示',
								content: res.msg,
								showCancel: false,
								success: function () {
									uni.navigateBack();
								}
							});
						}else{
							uni.showModal({
								title: '提示',
								content: res.msg,
								showCancel: false
							});
						}
					});
				} else {
					this.$u.toast('您填写的信息有误，请根据提示修正。');
				}
			});
		},
		cancel() {
			uni.navigateBack();
		},
		// 确定回调事件
		treeConfirm(e) {
			console.log(e)
		},
		// 取消回调事件
		treeCancel(e) {
			console.log(e)
		}
	}
};
</script>
<style lang="scss">
page{
	background: #f5f5f5;
}
.remind-text{
	padding: 20rpx 30rpx;
	color: #666666;
}
</style>
