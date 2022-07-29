import request from '@/utils/request'

// 获得测温记录分页
export function getTempRecordPage(query) {
  return request({
    url: '/smart-campus/school/temp/page',
    method: 'get',
    params: query
  })
}

// 获取班级/年级
export function getGradeList(deptId) {
  return request({
    url: '/smart-campus/school/dept/get?deptId=' + deptId,
    method: 'get'
  })
}

// 根据班级获取年级
export function getGrade(deptId) {
  return request({
    url: '/smart-campus/school/dept/get-parentDept?deptId=' + deptId,
    method: 'get'
  })
}

// 获得学生概览分页
export function getStudentPreviewPage(query) {
  return request({
    url: '/smart-campus/school/student-preview/page',
    method: 'get',
    params: query
  })
}

// 获得学生分页
export function getStudentPage(query) {
  return request({
    url: '/smart-campus/school/student/page',
    method: 'get',
    params: query
  })
}

//删除学生
export function deleteStudent(ids) {
  return request({
    url: '/smart-campus/school/student/delete/' + ids,
    method: 'delete'
  })
}

// 新增学生
export function createStudent(data) {
  return request({
    url: '/smart-campus/school/student/create',
    method: 'post',
    data: data
  })
}

// 批量转班
export function updateStudentDept(data) {
  return request({
    url: '/smart-campus/school/student/update-dept',
    method: 'put',
    data: data
  })
}

//修改学生信息
export function updateStudent(data) {
  return request({
    url: '/smart-campus/school/student/update',
    method: 'put',
    data: data
  })
}

// 用户头像上传
export function uploadAvatar(data) {
  return request({
    url: '/smart-campus/school/student/update-avatar',
    method: 'put',
    data: data
  })
}

// 根据学生ID获取学生信息
export function getStudent(id) {
  return request({
    url: '/smart-campus/school/student/get?id=' + id,
    method: 'get'
  })
}

// 根据学生ID和日期获取测温记录详情
export function getStuRecord(query) {
  return request({
    url: '/smart-campus/school/temp/get',
    method: 'get',
    params: query
  })
}

//获得班级分页
export function getClassPage(query) {
  return request({
    url: '/smart-campus/school/class-preview/page',
    method: 'get',
    params: query
  })
}

// 新增班级
export function createClass(data) {
  return request({
    url: '/smart-campus/school/class/create',
    method: 'post',
    data: data
  })
}

//删除学生
export function deleteClass(id) {
  return request({
    url: '/smart-campus/school/class/delete?deptId=' + deptId,
    method: 'delete'
  })
}

// 导出学生信息 Excel
export function exportStudentExcel(query) {
  return request({
    url: '/smart-campus/school/student/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}

// 下载学生导入模板
export function importTemplate() {
  return request({
    url: '/smart-campus/school/student/get-import-template',
    method: 'get',
    responseType: 'blob'
  })
}

// 获得防疫物资分页
export function getMaterialPage(query) {
  return request({
    url: '/smart-campus/school/material/page',
    method: 'get',
    params: query
  })
}

// 根据ID获取防疫物资信息
export function getMaterial(id) {
  return request({
    url: '/smart-campus/school/material/get?id=' + id,
    method: 'get'
  })
}

//修改防疫物资信息
export function updateMaterial(data) {
  return request({
    url: '/smart-campus/school/material/update',
    method: 'put',
    data: data
  })
}

// 新增防疫物资
export function createMaterial(data) {
  return request({
    url: '/smart-campus/school/material/create',
    method: 'post',
    data: data
  })
}

// 获得检查记录分页
export function getInspectPage(query) {
  return request({
    url: '/smart-campus/school/check-record/page',
    method: 'get',
    params: query
  })
}

// 新增新增检查记录
export function createInspect(data) {
  return request({
    url: '/smart-campus/school/check-record/create',
    method: 'post',
    data: data
  })
}

// 获得密接、次密接分页
export function getTouchPage(query) {
  return request({
    url: '/smart-campus/school/close-preview/page',
    method: 'get',
    params: query
  })
}

// 温测概览
export function getTempChart(createTime) {
  return request({
    url: '/smart-campus/school/temp-preview?createTime=' + createTime,
    method: 'get'
  })
}

// 获得老师分页
export function getTeacherPage(query) {
  return request({
    url: '/system/user/page',
    method: 'get',
    params: query
  })
}

//获得年级分页
export function getGradePage(query) {
  return request({
    url: '/smart-campus/school/grade-preview/page',
    method: 'get',
    params: query
  })
}
