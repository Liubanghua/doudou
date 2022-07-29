import treeSelectOne from "./treeSelectOne.vue"
export default {
	data() {
		return {
			chooseIds: "",
			chooseItems: "",
			listData: [],
		}
	},
	props: {
		data: Array,
	},
	components: {
		treeSelectOne,
	},
	created(){
		this.listData = this.data;
		// console.log(this.listData)
	},
	methods: {
		// 关闭
		close(){
			this.$emit("close", false);
			this.chooseIds = [];
			this.chooseItems = [];
			this.initValue(this.data);
		},
		// 选中的值
		save(){
			if(this.chooseIds.length > 0){
				this.$emit("getChooseValue", this.chooseIds, this.chooseItems); // 继续向上传递
				this.close();
			}else{
				this.BASE.msg("您还没有选择任何项目。");
			}
		},
		// 获取tree选择的结果
		// ids的数组
		// items对象数组
		getValue(ids, items){
			this.chooseIds = ids.reverse();
			this.chooseItems = items.reverse();
			this.changeValue(this.listData, JSON.parse(JSON.stringify(this.chooseIds)));
		},
		// 递归改值
		changeValue(data, chooseIds){
			data.forEach((item, i)=>{
				item.choose = chooseIds[chooseIds.length - 1] === item.id?(item.choose?false:true):false;
				// 小程序选择bug优化。
				if(chooseIds[0] === item.id){
					chooseIds.splice(0, 1);
					item.openTag = true;
				}
				
				if(item.children !== undefined){
					this.changeValue(item.children, chooseIds);
				}
			});
		},
		// 初始化
		initValue(data){
			data.forEach((item, i)=>{
				item.choose = false;
				item.openTag = false;
				if(item.children !== undefined){
					this.initValue(item.children);
				}
			});
		},
	}
}