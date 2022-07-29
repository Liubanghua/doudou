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

//批量转班
export function updateStudent(data) {
  return request({
    url: '/smart-campus/school/student/update',
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
