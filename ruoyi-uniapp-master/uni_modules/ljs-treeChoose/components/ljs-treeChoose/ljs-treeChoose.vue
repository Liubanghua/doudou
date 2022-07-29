<template>
	<view class="treeSelect">
		<view class="box">
			<view class="title">
				<text>部门选择</text>
				<view class="closeBut" @click="close">
					<image class="ico" :src="require('./images/ico_close.png')"></image>
				</view>
			</view>
			<view class="con">
				<treeSelectOne @getChooseValue="getValue" :data="listData"></treeSelectOne>
			</view>
			<view class="but">
				<button @click="save" class="but yes">确定</button>
				<button @click="close" class="but no">取消</button>
			</view>
		</view>
	</view>
</template>

<script>
  import ljsTreeChoose from './ljs-treeChoose.js'
  export default ljsTreeChoose
</script>

<style lang="scss" scoped>
	@import "./ljs-treeChoose.scss"
</style>
