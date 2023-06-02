package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.FedGroup;

/**
 * 队伍管理Service接口
 * 
 * @author ruoyi
 * @date 2023-06-01
 */
public interface IFedGroupService 
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
     * 批量删除队伍管理
     * 
     * @param groupIds 需要删除的队伍管理主键集合
     * @return 结果
     */
    public int deleteFedGroupByGroupIds(Long[] groupIds);

    /**
     * 删除队伍管理信息
     * 
     * @param groupId 队伍管理主键
     * @return 结果
     */
    public int deleteFedGroupByGroupId(Long groupId);
}
