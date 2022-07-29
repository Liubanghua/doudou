import request from '@/utils/request'


//根据日期获取学校概览数据
export function getSchoolData(date) {
  return request({
    url: '/educbure/temp/getSchoolData',
    method: 'get',
    params: date
  })
}

// 获得体温统计分页
export function getTempStatPage(query) {
  return request({
    url: '/educbure/temp/getTempStatPage',
    method: 'get',
    params: query
  })
}

// 获得密接、次密接分页
export function getTouchPage(query) {
  return request({
    url: '/educbure/temp/getTouchPage',
    method: 'get',
    params: query
  })
}

// 获得检查记录分页
export function getInspectRecordPage(query) {
  return request({
    url: '/educbure/temp/getInspectRecordPage',
    method: 'get',
    params: query
  })
}

// 新增检查记录
export function createInspectRecord() {
  return request({
    url: '/educbure/temp/createInspectRecord',
    method: 'post'
  })
}

// 获得防疫物资分页
export function getMaterialPage(query) {
  return request({
    url: '/educbure/temp/getMaterialPage',
    method: 'get',
    params: query
  })
}

// 新增防疫物资
export function createMaterial() {
  return request({
    url: '/educbure/temp/createMaterial',
    method: 'post'
  })
}

// 更新防疫物资
export function updateMaterial() {
  return request({
    url: '/educbure/temp/updateMaterial',
    method: 'put'
  })
}

// 根据id查看防疫物资详情
export function getMaterialInformById(id) {
  return request({
    url: '/educbure/temp/getMaterialInformById?id=' + id,
    method: 'get'
  })
}

// 修改用户信息
export function updateUser(data) {
  return request({
    url: '/educbure/user/update',
    method: 'put',
    data: data
  })
}
