<template>
	<view>
		<view class="treeOne" v-for="(item, i) in data" :key="i">
			<view class="one" :class="item.choose?'active':''" @click.stop="select(item)">
				<image @click.stop="open(item)" v-show="item.children !== undefined?(item.children.length > 0?true:false):false" class="jt" 
					:src="require('./images/ico_jt_'+(!item.openTag?'r':'b')+'.png')"></image>
				<text class="label">{{item.label}}</text>
			</view>
			<view class="children" v-if="item.openTag && item.children !== undefined">
				<treeSelectOne @getChooseValue="getValue" :data="item.children"></treeSelectOne>
			</view>
		</view>
	</view>
</template>

<script>
  import treeSelectOne from './treeSelectOne.js'
  export default treeSelectOne
</script>

<style lang="scss" scoped>
	@import "./treeSelectOne.scss"
</style>
