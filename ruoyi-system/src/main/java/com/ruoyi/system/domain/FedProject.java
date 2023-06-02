package com.ruoyi.system.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目对象 fed_project
 * 
 * @author ruoyi
 * @date 2023-05-30
 */
public class FedProject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 项目id */
    private Long projectId;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 组ID */
    @Excel(name = "组ID")
    private Long groupId;

    /** 创建者ID */
    @Excel(name = "创建者ID")
    private Long userId;

    /** 项目应用管理
信息 */
    private List<ProjectApplication> projectApplicationList;

    public void setProjectId(Long projectId) 
    {
        this.projectId = projectId;
    }

    public Long getProjectId() 
    {
        return projectId;
    }
    public void setProjectName(String projectName) 
    {
        this.projectName = projectName;
    }

    public String getProjectName() 
    {
        return projectName;
    }
    public void setGroupId(Long groupId) 
    {
        this.groupId = groupId;
    }

    public Long getGroupId() 
    {
        return groupId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public List<ProjectApplication> getProjectApplicationList()
    {
        return projectApplicationList;
    }

    public void setProjectApplicationList(List<ProjectApplication> projectApplicationList)
    {
        this.projectApplicationList = projectApplicationList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("projectId", getProjectId())
            .append("projectName", getProjectName())
            .append("groupId", getGroupId())
            .append("userId", getUserId())
            .append("projectApplicationList", getProjectApplicationList())
            .toString();
    }
}
