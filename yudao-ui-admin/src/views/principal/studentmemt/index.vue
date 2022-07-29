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
        <el-input v-model="queryParams.name" placeholder="请输入姓名" clearable @keyup.enter.native="handleQuery"/>
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
        <el-button type="primary" plain @click="updateDept">批量转班</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain @click="deleteAllStudent" >批量删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="Info" plain @click="handleAdd()">添加学生</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list" ref="stuTable">
      <el-table-column type="selection" width="55"/>
      <el-table-column label="序号" type="index" width="50" align="center">
        <template slot-scope="scope">
           {{(queryParams.pageNo - 1) * queryParams.pageSize + scope.$index + 1}}
        </template>
      </el-table-column>
      <el-table-column label="头像" align="center" prop="avatar">
        <template slot-scope="scope">
          <img :src="scope.row.avatar" min-width="70" height="70" />
        </template>
      </el-table-column>
      <el-table-column label="姓名" align="center" prop="name" />
      <el-table-column label="性别" align="center" prop="sex">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.sex === 1">男</el-tag>
          <el-tag v-else type="warning">女</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="年级" align="center" prop="campusGradeName" />
      <el-table-column label="班级" align="center" prop="campusClassName" />
      <el-table-column label="学籍号" align="center" prop="studentNo" />
      <el-table-column label="照片" align="center" prop="avatar">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.avatar != null">已上传</el-tag>
          <el-tag v-else type="danger">未上传</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <router-link :to="'/studentmemt/prin/updStu/' + scope.row.id" class="link-type">
            <span class="el-icon-edit">编辑</span>
          </router-link>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(批量转班) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form :model="updateDeptParams" ref="updateDeptForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
          <el-form-item label="年级" prop="updGradeId">
            <el-select v-model="updGradeId" placeholder="请选择年级" @change="getClass" clearable>
              <el-option v-for="item in this.gradeOption"
                :key="item.id" :label="item.name" :value="item.id"/>
            </el-select>
          </el-form-item>
          <el-form-item label="班级" prop="updClassId">
            <el-select v-model="updClassId" placeholder="请选择班级" clearable>
              <el-option v-for="item in this.classOption"
                :key="item.id" :label="item.name" :value="item.id"/>
            </el-select>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitUpdateDeptForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
    </el-dialog>

  </div>
</template>

<script>
import { getStudentPage,getGradeList,deleteStudent,updateStudentDept } from "@/api/principal/principal";
import { CommonStatusEnum } from '@/utils/constants';

export default {
  name: "Student",
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
        deptId:0
      },
      updateDeptParams: {
        deptId: null,
        studentIds: []
      },
      gradeId: null,
      classId: null,
      updGradeId: null,
      updClassId: null,
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
      getStudentPage(params).then(response => {
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
        this.updClassId = null;
      }
    },
    /** 取消按钮 */
    cancel() {
      this.open = false;
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
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除该学生?').then(function() {
          return deleteStudent(id);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    //批量删除
    deleteAllStudent() {
      let delIds = "";
      if(this.$refs.stuTable.selection.length != 0){
        this.$refs.stuTable.selection.forEach((item) => {
          delIds = delIds + item.id + ",";
        });
        delIds = delIds.substring(0, delIds.length - 1);
        this.$modal.confirm('是否确认删除选中的学生?').then(function() {
            return deleteStudent(delIds);
          }).then(() => {
            this.getList();
            this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      }else {
        this.$modal.msgWarning("请选择学生!");
      }

    },
    //添加学生
    handleAdd(){
      this.$router.push({
          path:'/studentmemt/prin/addStu',
      })
    },
    //批量转班
    updateDept(){
      if(this.$refs.stuTable.selection.length != 0){
        this.title = "批量转班";
        this.open = true;
      }else {
        this.$modal.msgWarning("请选择学生!");
      }
    },
    //表单提交
    submitUpdateDeptForm(){
      this.$refs.stuTable.selection.forEach((item) => {
        this.updateDeptParams.studentIds.push(item.id);
      });
      this.updateDeptParams.deptId = this.updClassId;
      let params = {...this.updateDeptParams};
      this.$modal.confirm('是否确认转入该班级?').then(function() {
          return updateStudentDept(params);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("转班成功");
          this.open = false;
      }).catch(() => {});
    }
  }
};

</script>
