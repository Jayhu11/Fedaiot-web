import request from '@/utils/request'

// 查询用户关系列表
export function listRelation(query) {
  return request({
    url: '/system/relation/list',
    method: 'get',
    params: query
  })
}

// 查询用户关系详细
export function getRelation(urId) {
  return request({
    url: '/system/relation/' + urId,
    method: 'get'
  })
}

// 新增用户关系
export function addRelation(data) {
  return request({
    url: '/system/relation',
    method: 'post',
    data: data
  })
}

// 修改用户关系
export function updateRelation(data) {
  return request({
    url: '/system/relation',
    method: 'put',
    data: data
  })
}

// 删除用户关系
export function delRelation(urId) {
  return request({
    url: '/system/relation/' + urId,
    method: 'delete'
  })
}
