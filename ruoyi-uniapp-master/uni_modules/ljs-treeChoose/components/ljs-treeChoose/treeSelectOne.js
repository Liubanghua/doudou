import treeSelectOne from "./treeSelectOne.vue"
export default {
	name: "treeSelectOne",
	data() {
		return {
			chooseValue: "", // 选中的值
		}
	},
	props: {
		data: Array,
		// chooseValue: {
		// 	type: Array,
		// 	default: [],
		// },
	},
	updated(){
		// console.log(this.data)
	},
	components: {
		'treeSelectOne':treeSelectOne
	},
	methods: {
		// 获取
		getValue(ids, items){
			ids.push(this.chooseValue.id);
			items.push(this.chooseValue);
			this.$emit("getChooseValue",  ids, items); // 继续向上传递
		},
		// 打开下级菜单
		open(item){
			this.chooseValue = item;
			item.openTag = item.openTag?false:true;
		},
		// 点击选中一级
		select(item){
			this.chooseValue = item;
			this.$emit("getChooseValue",  [item.id], [item]);
			// item.choose = true;
		},
	}
}