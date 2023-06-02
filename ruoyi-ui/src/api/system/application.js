import request from '@/utils/request'

// 查询应用管理列表
export function listApplication(query) {
  return request({
    url: '/system/application/list',
    method: 'get',
    params: query
  })
}

// 查询应用管理详细
export function getApplication(applicationId) {
  return request({
    url: '/system/application/' + applicationId,
    method: 'get'
  })
}

// 新增应用管理
export function addApplication(data) {
  return request({
    url: '/system/application',
    method: 'post',
    data: data
  })
}

// 修改应用管理
export function updateApplication(data) {
  return request({
    url: '/system/application',
    method: 'put',
    data: data
  })
}

// 删除应用管理
export function delApplication(applicationId) {
  return request({
    url: '/system/application/' + applicationId,
    method: 'delete'
  })
}
