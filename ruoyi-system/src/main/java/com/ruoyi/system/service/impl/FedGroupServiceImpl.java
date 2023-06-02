package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.system.domain.UserGroup;
import com.ruoyi.system.mapper.FedGroupMapper;
import com.ruoyi.system.domain.FedGroup;
import com.ruoyi.system.service.IFedGroupService;

/**
 * 队伍管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-06-01
 */
@Service
public class FedGroupServiceImpl implements IFedGroupService 
{
    @Autowired
    private FedGroupMapper fedGroupMapper;

    /**
     * 查询队伍管理
     * 
     * @param groupId 队伍管理主键
     * @return 队伍管理
     */
    @Override
    public FedGroup selectFedGroupByGroupId(Long groupId)
    {
        return fedGroupMapper.selectFedGroupByGroupId(groupId);
    }

    /**
     * 查询队伍管理列表
     * 
     * @param fedGroup 队伍管理
     * @return 队伍管理
     */
    @Override
    public List<FedGroup> selectFedGroupList(FedGroup fedGroup)
    {
        return fedGroupMapper.selectFedGroupList(fedGroup);
    }

    /**
     * 新增队伍管理
     * 
     * @param fedGroup 队伍管理
     * @return 结果
     */
    @Transactional
    @Override
    public int insertFedGroup(FedGroup fedGroup)
    {
        int rows = fedGroupMapper.insertFedGroup(fedGroup);
        insertUserGroup(fedGroup);
        return rows;
    }

    /**
     * 修改队伍管理
     * 
     * @param fedGroup 队伍管理
     * @return 结果
     */
    @Transactional
    @Override
    public int updateFedGroup(FedGroup fedGroup)
    {
        fedGroupMapper.deleteUserGroupByGroupId(fedGroup.getGroupId());
        insertUserGroup(fedGroup);
        return fedGroupMapper.updateFedGroup(fedGroup);
    }

    /**
     * 批量删除队伍管理
     * 
     * @param groupIds 需要删除的队伍管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteFedGroupByGroupIds(Long[] groupIds)
    {
        fedGroupMapper.deleteUserGroupByGroupIds(groupIds);
        return fedGroupMapper.deleteFedGroupByGroupIds(groupIds);
    }

    /**
     * 删除队伍管理信息
     * 
     * @param groupId 队伍管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteFedGroupByGroupId(Long groupId)
    {
        fedGroupMapper.deleteUserGroupByGroupId(groupId);
        return fedGroupMapper.deleteFedGroupByGroupId(groupId);
    }

    /**
     * 新增用户-组关联信息
     * 
     * @param fedGroup 队伍管理对象
     */
    public void insertUserGroup(FedGroup fedGroup)
    {
        List<UserGroup> userGroupList = fedGroup.getUserGroupList();
        Long groupId = fedGroup.getGroupId();
        if (StringUtils.isNotNull(userGroupList))
        {
            List<UserGroup> list = new ArrayList<UserGroup>();
            for (UserGroup userGroup : userGroupList)
            {
                userGroup.setGroupId(groupId);
                list.add(userGroup);
            }
            if (list.size() > 0)
            {
                fedGroupMapper.batchUserGroup(list);
            }
        }
    }
}
