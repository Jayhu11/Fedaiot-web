package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.FedGroup;
import com.ruoyi.system.domain.UserGroup;

/**
 * 队伍管理Mapper接口
 * 
 * @author ruoyi
 * @date 2023-06-01
 */
public interface FedGroupMapper 
{
    /**
     * 查询队伍管理
     * 
     * @param groupId 队伍管理主键
     * @return 队伍管理
     */
    public FedGroup selectFedGroupByGroupId(Long groupId);

    /**
     * 查询队伍管理列表
     * 
     * @param fedGroup 队伍管理
     * @return 队伍管理集合
     */
    public List<FedGroup> selectFedGroupList(FedGroup fedGroup);

    /**
     * 新增队伍管理
     * 
     * @param fedGroup 队伍管理
     * @return 结果
     */
    public int insertFedGroup(FedGroup fedGroup);

    /**
     * 修改队伍管理
     * 
     * @param fedGroup 队伍管理
     * @return 结果
     */
    public int updateFedGroup(FedGroup fedGroup);

    /**
     * 删除队伍管理
     * 
     * @param groupId 队伍管理主键
     * @return 结果
     */
    public int deleteFedGroupByGroupId(Long groupId);

    /**
     * 批量删除队伍管理
     * 
     * @param groupIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFedGroupByGroupIds(Long[] groupIds);

    /**
     * 批量删除用户-组关联
     * 
     * @param groupIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserGroupByGroupIds(Long[] groupIds);
    
    /**
     * 批量新增用户-组关联
     * 
     * @param userGroupList 用户-组关联列表
     * @return 结果
     */
    public int batchUserGroup(List<UserGroup> batchUserGroup);
    

    /**
     * 通过队伍管理主键删除用户-组关联信息
     * 
     * @param groupId 队伍管理ID
     * @return 结果
     */
    public int deleteUserGroupByGroupId(Long groupId);
}
