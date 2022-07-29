import request from '@/utils/request'


//根据日期获取温测概览数据
export function getTempData(date) {
  return request({
    url: '/school/temp/getTempData',
    method: 'get',
    params: date
  })
}

// 获得密接、次密接分页
export function getTouchPage(query) {
  return request({
    url: '/school/temp/getTouchPage',
    method: 'get',
    params: query
  })
}

// 获得检查记录分页
export function getInspectRecordPage(query) {
  return request({
    url: '/school/temp/getInspectRecordPage',
    method: 'get',
    params: query
  })
}

// 新增检查记录
export function createInspectRecord() {
  return request({
    url: '/school/temp/createInspectRecord',
    method: 'post'
  })
}

// 获得年级概览分页
export function getGradePage(query) {
  return request({
    url: '/school/temp/getGradePage',
    method: 'get',
    params: query
  })
}

// 根据年级ID获取年级名称
export function getGradeInformById(id) {
  return request({
    url: '/school/temp/getGradeInformById?id=' + id,
    method: 'get'
  })
}

// 获得班级概览分页
export function getClassPage(query) {
  return request({
    url: '/school/temp/getClassPage',
    method: 'get',
    params: query
  })
}

// 根据id查看班级概览详情
// export function getClassInformById(id) {
//   return request({
//     url: '/school/temp/getClassInformById?id=' + id,
//     method: 'get'
//   })
// }

// 获得学生概览分页
export function getStudentPage(query) {
  return request({
    url: '/school/temp/getStudentPage',
    method: 'get',
    params: query
  })
}

// 根据学生ID查看测温记录
export function getStudentInformById(id) {
  return request({
    url: '/school/temp/getStudentInformById?id=' + id,
    method: 'get'
  })
}

// 获得测温记录分页
export function getTempRecordPage(query) {
  return request({
    url: '/school/temp/getTempRecordPage',
    method: 'get',
    params: query
  })
}

// 根据学生ID获取学生信息
export function getStuInformById(id) {
  return request({
    url: '/school/temp/getStuInformById?id=' + id,
    method: 'get'
  })
}

// 根据学生ID获取测温记录
export function getTempRecordById(id) {
  return request({
    url: '/school/temp/getStuInformById?id=' + id,
    method: 'get'
  })
}

// 获得防疫物资分页
export function getMaterialPage(query) {
  return request({
    url: '/school/temp/getMaterialPage',
    method: 'get',
    params: query
  })
}

// 新增防疫物资
export function createMaterial() {
  return request({
    url: '/school/temp/createMaterial',
    method: 'post'
  })
}

// 更新防疫物资
export function updateMaterial() {
  return request({
    url: '/school/temp/updateMaterial',
    method: 'put'
  })
}

// 根据id查看防疫物资详情
export function getMaterialInformById(id) {
  return request({
    url: '/school/temp/getMaterialInformById?id=' + id,
    method: 'get'
  })
}

// 修改用户信息
export function updateUser(data) {
  return request({
    url: '/school/user/update',
    method: 'put',
    data: data
  })
}
