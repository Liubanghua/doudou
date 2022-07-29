<template>
  <div class="app-container">
    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="年级" prop="grade">
        <el-select v-model="queryParams.grade" placeholder="请选择年级" clearable>
          <el-option v-for="dict in this.getDictDatas(DICT_TYPE.SYSTEM_GRADE)"
                       :key="dict.value" :label="dict.label" :value="dict.value"/>
        </el-select>
      </el-form-item>
      <el-form-item label="班级" prop="grade">
        <el-select v-model="queryParams.grade" placeholder="请选择班级" clearable>
          <el-option v-for="dict in this.getDictDatas(DICT_TYPE.SYSTEM_GRADE)"
                       :key="dict.value" :label="dict.label" :value="dict.value"/>
        </el-select>
      </el-form-item>
      <el-form-item label="姓名" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入姓名" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-select v-model="queryParams.sex" placeholder="请选择性别" clearable>
          <el-option v-for="dict in this.getDictDatas(DICT_TYPE.SYSTEM_USER_SEX)"
                       :key="dict.value" :label="dict.label" :value="dict.value"/>
        </el-select>
      </el-form-item>
      <el-form-item label="学籍号" prop="stuNo">
        <el-input v-model="queryParams.stuNo" placeholder="请输入学籍号" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
        <el-button type="info" icon="el-icon-upload2">导入</el-button>
        <el-button type="warning" icon="el-icon-download">导出</el-button>
      </el-form-item>
    </el-form>

    <el-divider></el-divider>
    <!-- 操作工具栏 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain>批量转班</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain>批量删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="Info" plain @click="handleAdd">添加学生</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column type="selection" width="55"/>
      <el-table-column label="姓名" align="center" prop="id" />
      <el-table-column label="性别" align="center" prop="name" />
      <el-table-column label="年级" align="center" prop="packageId">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.packageId === 0" type="danger">系统租户</el-tag>
          <el-tag v-else> {{getPackageName(scope.row.packageId)}} </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="班级" align="center" prop="contactName" />
      <el-table-column label="学籍号" align="center" prop="contactMobile" />
      <el-table-column label="照片" align="center" prop="accountCount">
        <template slot-scope="scope">
          <el-tag> {{scope.row.accountCount}} </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <router-link :to="'/studentmemt/type/inform'" class="link-type">
            <span class="el-icon-edit">编辑</span>
          </router-link>
          <el-button size="mini" type="text" icon="el-icon-delete">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

  </div>
</template>

<script>
import { createTenant, updateTenant, deleteTenant, getTenant, getTenantPage, exportTenantExcel } from "@/api/system/tenant";
import { CommonStatusEnum } from '@/utils/constants'
import {getTenantPackageList} from "@/api/system/tenantPackage";

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
      // 表单参数
      form: {},
      // 表单校验
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        sex: undefined,
        name: null,
        stuNo: null,
        grade: undefined
      }
    };
  },
  created() {
    this.getList();
    // 获得租户套餐列表
    getTenantPackageList().then(response => {
      this.packageList = response.data;
    })
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 处理查询参数
      let params = {...this.queryParams};
      this.addBeginAndEndTime(params, this.dateRangeCreateTime, 'createTime');
      // 执行查询
      getTenantPage(params).then(response => {
        this.list = response.data.list;
        this.total = response.data.total;
        this.loading = false;
      });
    },
    /** 取消按钮 */
    cancel() {
      this.open = false;
      this.reset();
    },
    /** 表单重置 */
    reset() {
      this.form = {
        id: undefined,
        name: undefined,
        packageId: undefined,
        contactName: undefined,
        contactMobile: undefined,
        accountCount: undefined,
        expireTime: undefined,
        domain: undefined,
        status: CommonStatusEnum.ENABLE,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNo = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.dateRangeCreateTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加租户";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getTenant(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改租户";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (!valid) {
          return;
        }
        // 修改的提交
        if (this.form.id != null) {
          updateTenant(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createTenant(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除租户编号为"' + id + '"的数据项?').then(function() {
          return deleteTenant(id);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      // 处理查询参数
      let params = {...this.queryParams};
      params.pageNo = undefined;
      params.pageSize = undefined;
      this.addBeginAndEndTime(params, this.dateRangeCreateTime, 'createTime');
      // 执行导出
      this.$modal.confirm('是否确认导出所有租户数据项?').then(() => {
          this.exportLoading = true;
          return exportTenantExcel(params);
        }).then(response => {
          this.$download.excel(response, '租户.xls');
          this.exportLoading = false;
      }).catch(() => {});
    },
    /** 套餐名格式化 */
    getPackageName(packageId) {
      for (const item of this.packageList) {
        if (item.id === packageId) {
          return item.name;
        }
      }
      return '未知套餐';
    }
  }
};
</script>
