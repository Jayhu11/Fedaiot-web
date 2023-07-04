package com.ruoyi.system.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 队伍管理对象 fed_group
 *
 * @author ruoyi
 * @date 2023-06-01
 */
public class FedGroup extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 组id
     */
    @Excel(name = "组id")
    private Long groupId;
    private Long userId2;
    /** 组名
     */
    @Excel(name = "组名")
    private String groupName;

    /** 用户-组关联信息 */
    private List<UserGroup> userGroupList;

    public void setGroupId(Long groupId)
    {
        this.groupId = groupId;
    }

    public Long getGroupId()
    {
        return groupId;
    }

    public void setUserId2(Long userId2)
    {
        this.userId2 = userId2;
    }

    public Long getUserId2()
    {
        return userId2;
    }

    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }

    public String getGroupName()
    {
        return groupName;
    }

    public List<UserGroup> getUserGroupList()
    {
        return userGroupList;
    }

    public void setUserGroupList(List<UserGroup> userGroupList)
    {
        this.userGroupList = userGroupList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("groupId", getGroupId())
                .append("groupName", getGroupName())
                .append("createBy", getCreateBy())
                .append("userGroupList", getUserGroupList())
                .append("userId2", getUserId2())
                .toString();
    }
}
