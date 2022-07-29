<template>
  <div class="app-container">
    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="年级" prop="gradeId">
        <el-select v-model="gradeId" placeholder="请选择年级" @change="getClass" clearable>
          <el-option v-for="item in this.gradeOption"
            :key="item.id" :label="item.name" :value="item.id"/>
        </el-select>
      </el-form-item>
      <el-form-item label="班级" prop="classId">
        <el-select v-model="classId" placeholder="请选择班级" clearable>
          <el-option v-for="item in this.classOption"
            :key="item.id" :label="item.name" :value="item.id"/>
        </el-select>
      </el-form-item>
      <el-form-item label="姓名" prop="studentName">
        <el-input v-model="queryParams.studentName" placeholder="请输入姓名" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-select v-model="queryParams.sex" placeholder="请选择性别" clearable>
          <el-option v-for="dict in this.getDictDatas(DICT_TYPE.SYSTEM_USER_SEX)"
                       :key="dict.value" :label="dict.label" :value="dict.value"/>
        </el-select>
      </el-form-item>
      <el-form-item label="学籍号" prop="studentNo">
        <el-input v-model="queryParams.studentNo" placeholder="请输入学籍号" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="日期" prop="createTime">
        <el-date-picker v-model="queryParams.createTime" clearable size="small" type="date"
                        value-format="yyyy-MM-dd" placeholder="请选择日期" />
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list" ref="tempTable" border>
      <el-table-column label="序号" type="index" width="50" align="center">
        <template slot-scope="scope">
           {{(queryParams.pageNo - 1) * queryParams.pageSize + scope.$index + 1}}
        </template>
      </el-table-column>
      <el-table-column label="时间" align="center" prop="createTime" />
      <el-table-column label="姓名" align="center" prop="studentName" />
      <el-table-column label="性别" align="center" prop="sex">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.sex === 1">男</el-tag>
          <el-tag v-else type="warning">女</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="年级" align="center" prop="campusGradeName" />
      <el-table-column label="班级" align="center" prop="campusClassName" />
      <el-table-column label="学籍号" align="center" prop="studentNo" />
      <el-table-column label="今日测温" align="center" prop="temperature">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.temperature >= 37" type="danger">异常</el-tag>
          <el-tag v-else-if="scope.row.temperature === 0" type="info">未知</el-tag>
          <el-tag v-else type="success">正常</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <router-link :to="'/student/prin/record/' + scope.row.studentId" class="link-type">
            <span>查看测温记录</span>
          </router-link>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

  </div>
</template>

<script>
import { getTempRecordPage,getGradeList } from "@/api/principal/principal";
import { CommonStatusEnum } from '@/utils/constants';

export default {
  name: "temprecord",
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
      // 测温记录列表
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
        sex: null,
        studentName: null,
        studentNo: null,
        createTime: null,
        deptId:0
      },
      gradeId: null,
      classId: null,
      gradeOption: [],
      classOption: [],
      ids: []
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
      getTempRecordPage(params).then(response => {
        this.list = response.data.list;
        this.total = response.data.total;
        this.loading = false;
      });
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
      this.handleQuery();
    }
  }
};
</script>
