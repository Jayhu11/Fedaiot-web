package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.UserRelationMapper;
import com.ruoyi.system.domain.UserRelation;
import com.ruoyi.system.service.IUserRelationService;

/**
 * 用户关系Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-06-01
 */
@Service
public class UserRelationServiceImpl implements IUserRelationService 
{
    @Autowired
    private UserRelationMapper userRelationMapper;

    /**
     * 查询用户关系
     * 
     * @param urId 用户关系主键
     * @return 用户关系
     */
    @Override
    public UserRelation selectUserRelationByUrId(Long urId)
    {
        return userRelationMapper.selectUserRelationByUrId(urId);
    }

    /**
     * 查询用户关系列表
     * 
     * @param userRelation 用户关系
     * @return 用户关系
     */
    @Override
    public List<UserRelation> selectUserRelationList(UserRelation userRelation)
    {
        return userRelationMapper.selectUserRelationList(userRelation);
    }

    /**
     * 新增用户关系
     * 
     * @param userRelation 用户关系
     * @return 结果
     */
    @Override
    public int insertUserRelation(UserRelation userRelation)
    {
        return userRelationMapper.insertUserRelation(userRelation);
    }

    /**
     * 修改用户关系
     * 
     * @param userRelation 用户关系
     * @return 结果
     */
    @Override
    public int updateUserRelation(UserRelation userRelation)
    {
        return userRelationMapper.updateUserRelation(userRelation);
    }

    /**
     * 批量删除用户关系
     * 
     * @param urIds 需要删除的用户关系主键
     * @return 结果
     */
    @Override
    public int deleteUserRelationByUrIds(Long[] urIds)
    {
        return userRelationMapper.deleteUserRelationByUrIds(urIds);
    }

    /**
     * 删除用户关系信息
     * 
     * @param urId 用户关系主键
     * @return 结果
     */
    @Override
    public int deleteUserRelationByUrId(Long urId)
    {
        return userRelationMapper.deleteUserRelationByUrId(urId);
    }
}
