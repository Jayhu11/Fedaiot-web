package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.UserRelation;

/**
 * 用户关系Mapper接口
 * 
 * @author ruoyi
 * @date 2023-06-01
 */
public interface UserRelationMapper 
{
    /**
     * 查询用户关系
     * 
     * @param urId 用户关系主键
     * @return 用户关系
     */
    public UserRelation selectUserRelationByUrId(Long urId);

    /**
     * 查询用户关系列表
     * 
     * @param userRelation 用户关系
     * @return 用户关系集合
     */
    public List<UserRelation> selectUserRelationList(UserRelation userRelation);

    /**
     * 新增用户关系
     * 
     * @param userRelation 用户关系
     * @return 结果
     */
    public int insertUserRelation(UserRelation userRelation);

    /**
     * 修改用户关系
     * 
     * @param userRelation 用户关系
     * @return 结果
     */
    public int updateUserRelation(UserRelation userRelation);

    /**
     * 删除用户关系
     * 
     * @param urId 用户关系主键
     * @return 结果
     */
    public int deleteUserRelationByUrId(Long urId);

    /**
     * 批量删除用户关系
     * 
     * @param urIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserRelationByUrIds(Long[] urIds);
}
