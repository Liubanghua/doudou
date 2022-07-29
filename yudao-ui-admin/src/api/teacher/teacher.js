import request from '@/utils/request'


//根据日期获取温测概览数据
export function getTempData(date) {
  return request({
    url: '/teacher/temp/getTempData',
    method: 'get',
    params: date
  })
}

// 获得学生概览分页
export function getStudentPage(query) {
  return request({
    url: '/teacher/temp/getStudentPage',
    method: 'get',
    params: query
  })
}

// 根据id查看学生当日记录详情
export function getStudentInformById(id) {
  return request({
    url: '/teacher/temp/getStudentInformById?id=' + id,
    method: 'get'
  })
}

// 获得测温记录分页
export function getTempRecordPage(query) {
  return request({
    url: '/teacher/temp/getTempRecordPage',
    method: 'get',
    params: query
  })
}

// 根据学生ID获取学生信息
export function getStuInformById(id) {
  return request({
    url: '/teacher/temp/getStuInformById?id=' + id,
    method: 'get'
  })
}

// 根据学生ID获取测温记录
export function getTempRecordById(id) {
  return request({
    url: '/teacher/temp/getStuInformById?id=' + id,
    method: 'get'
  })
}

// 获得学生管理分页
export function getStuMemtPage(query) {
  return request({
    url: '/teacher/temp/getClassMemtPage',
    method: 'get',
    params: query
  })
}

//批量转班
export function moveClassByIds(ids) {
  return request({
    url: '/teacher/temp/moveClassByIds',
    method: 'get',
    params: ids
  })
}

//批量删除
export function deleteClassByIds(ids) {
  return request({
    url: '/teacher/temp/deleteClassByIds',
    method: 'get',
    params: ids
  })
}

// 添加学生
export function createStudent(data) {
  return request({
    url: '/teacher/temp/createStudent',
    method: 'post',
    data: data
  })
}

// 修改学生信息
export function updateStudent(data) {
  return request({
    url: '/teacher/temp/updateStudent',
    method: 'put',
    data: data
  })
}

// 根据id查看学生信息详情
export function getStudentInformById(id) {
  return request({
    url: '/teacher/temp/getStudentInformById?id=' + id,
    method: 'get'
  })
}

// 导出学生信息 Excel
export function exportStudentExcel(query) {
  return request({
    url: '/teacher/temp/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}

// 修改用户信息
export function updateUser(data) {
  return request({
    url: '/teacher/user/update',
    method: 'put',
    data: data
  })
}
