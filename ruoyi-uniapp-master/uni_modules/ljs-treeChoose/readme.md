# 简介
树形结构选择器，单选，可选任一级。暂未开放多选后期增加。

# 平台兼容性
全平台兼容。
# 快速开始
### 使用 uni_modules 安装（推荐）
使用 uni_modules 方式安装组件库，可以直接通过插件市场导入，通过右键菜单快速更新组件，不需要引用、注册，直接在页面中使用 ljs-turntable 组件。

### 快速应用
###### 文件地址：
/ljs-treeChoose/index.vue
###### 引用：
```
<view class="myInput">
	<input v-model="departmentName" @click="departmentOpen" class="input" type="text" placeholder="点击选择组织" disabled />
</view>
<ljs-treeChoose :data="department" @getChooseValue="getDepartmentValue" @close="closeTree" v-if="departmentTag"/>
```
注意：微信小程序需要单独注册：
main.js
```
// 微信小程序需要注册
import treeSelectOne from '@/uni_modules/ljs-treeChoose/components/ljs-treeChoose/treeSelectOne.vue'
Vue.component('treeSelectOne', treeSelectOne)
```
###### 数据解构：
```
export default {
	data() {
		return {
			departmentTag: false,
			department: [],
			departmentName: "",
		}
	},
	onLoad() {
		this.department = [
			{
				"id":100,
				"label":"九山科技",
				"disabled":false,
				"type":null,
				"children":[
					{
						"id":101,
						"label":"深圳总公司",
						"disabled":false,
						"type":null,
						"children":[
							{
								"id":103,
								"label":"研发部门",
								"disabled":false,
								"type":null,
								"openTag":false,
								"choose":false
							},
							{
								"id":104,
								"label":"市场部门",
								"disabled":false,
								"type":null,
								"openTag":false,
								"choose":false
							}
						],
						"openTag":false,
						"choose":false
					},
					{
						"id":102,
						"label":"长沙分公司",
						"disabled":false,
						"type":null,
						"children":[
							{
								"id":108,
								"label":"市场部门",
								"disabled":false,
								"type":null,
								"openTag":false,
								"choose":false
							},
						],
						"openTag":false,
						"choose":false
					}
				],
				"openTag":false,
				"choose":false
			}
		];
	},
	methods: {
		// 所属部门 - 打开
		departmentOpen() {
			this.departmentTag = true;
		},
		// 所属部门 - 关闭
		closeTree(){
			this.departmentTag = false;
		},
		// 所属部门 - 获取
		// ids: 集合
		// items: 节点对象集合
		getDepartmentValue(ids, items){
			console.log(ids, items)
			items.forEach((item, i)=>{
				this.departmentName += item.label + "-";
			});
			this.departmentName = this.departmentName.substring(0, this.departmentName.length - 1);
		},
	}
}
```
### 参数

 参数  | 类型| 解释
 ---- | ----- | ------ 
 data  | Array | （必填）树（Tree）解构数据。
 @getChooseValue  | 回调函数 | （必填）function(ids, items)。ids: 集合；items: 节点对象集合。
 @close  | 回调函数 | （必填）function(val)。val: false。


tree数据解析：

 参数  | 类型| 解释
 ---- | ----- | ------ 
 id| String| 节点ID。
 label| String| 节点名称。
 openTag| Boolean| 节点展开。
 choose| Boolean| 选择标记。

# 贡献代码
龙九山。有任何问题，请在平台留言，在手头宽裕得情况下，我会尽快修复问题。