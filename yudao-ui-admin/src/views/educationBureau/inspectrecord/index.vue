<template>
  <div class="app-container">
    <!-- 搜索工作栏 -->
    <el-form ref="queryForm" size="small" :inline="true" label-width="68px">
      <el-form-item label="学校">
        <el-select placeholder="请选择学校" clearable>
          <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="日期">
        <el-date-picker clearable size="small" type="date"
                        value-format="timestamp" placeholder="请选择日期" />
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search">搜索</el-button>
        <el-button icon="el-icon-refresh">重置</el-button>
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                   v-hasPermi="['system:tenant:create']">新增</el-button>
      </el-form-item>
    </el-form>

    <!-- 列表 -->
    <el-table>
      <el-table-column label="学校" align="center" />
      <el-table-column label="等级" align="center" />
      <el-table-column label="时间" align="center" />
      <el-table-column label="备注" align="center" />
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize" />

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body center>
      <el-form ref="form" label-width="80px">
        <el-form-item label="学校">
          <el-select placeholder="请选择学校" clearable>
            <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="等级">
          <el-select placeholder="请选择等级" clearable>
            <el-option
                  v-for="item in options2"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="时间">
          <el-date-picker clearable size="small" type="date"
                          value-format="timestamp" placeholder="请选择时间" />
        </el-form-item>
        <el-form-item label="备注">
          <el-input type="textarea" :rows="2" placeholder="请输入备注" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { CommonStatusEnum } from '@/utils/constants'

export default {
  name: "Tenant",
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 租户列表
      list: [],
      // 租户套餐列表
      packageList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      dateRangeCreateTime: [],
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        date: null,
      },
      options: [{
          value: '1',
          label: '一中'
        }, {
          value: '2',
          label: '二中'
        }, {
          value: '3',
          label: '三中'
        }, {
          value: '4',
          label: '四中'
        }, {
          value: '5',
          label: '五中'
      }],
      options2: [{
          value: '1',
          label: '优秀'
        }, {
          value: '2',
          label: '合格'
        }, {
          value: '3',
          label: '不合格'
      }]
    };
  },
  created() {

  },
  methods: {
    /** 取消按钮 */
    cancel() {
      this.open = false;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.open = true;
      this.title = "新增记录";
    }
  }
};
</script>
<style>
  .el-dialog {
       display: flex;
       flex-direction: column;
       margin:0 !important;
       position:absolute;
       top:50%;
       left:50%;
       transform:translate(-50%,-50%);
       /*height:600px;*/
       max-height:calc(100% - 200px);
       max-width:calc(100% - 30px);
  }
  .el-dialog .el-dialog__body {
       flex:1;
       overflow: auto;
  }
</style>
