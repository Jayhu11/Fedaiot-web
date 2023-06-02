package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户关系对象 user_relation
 * 
 * @author ruoyi
 * @date 2023-06-01
 */
public class UserRelation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 表id */
    private Long urId;

    /** 用户id */
    private Long myId;

    /** 好友id */
    @Excel(name = "好友id")
    private Long userId;

    /** 用户名 */
    private String myName;

    /** 好友名 */
    @Excel(name = "好友名")
    private String userName;

    public void setUrId(Long urId) 
    {
        this.urId = urId;
    }

    public Long getUrId() 
    {
        return urId;
    }
    public void setMyId(Long myId) 
    {
        this.myId = myId;
    }

    public Long getMyId() 
    {
        return myId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setMyName(String myName) 
    {
        this.myName = myName;
    }

    public String getMyName() 
    {
        return myName;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("urId", getUrId())
            .append("myId", getMyId())
            .append("userId", getUserId())
            .append("myName", getMyName())
            .append("userName", getUserName())
            .toString();
    }
}
