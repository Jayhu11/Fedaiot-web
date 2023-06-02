package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目应用管理
对象 project_application
 * 
 * @author ruoyi
 * @date 2023-05-30
 */
public class ProjectApplication extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** paid
 */
    private Long paid;

    /** 项目ID */
    @Excel(name = "项目ID")
    private Long projectid;

    /** 应用ID */
    @Excel(name = "应用ID")
    private Long applicationid;

    public void setPaid(Long paid) 
    {
        this.paid = paid;
    }

    public Long getPaid() 
    {
        return paid;
    }
    public void setProjectid(Long projectid) 
    {
        this.projectid = projectid;
    }

    public Long getProjectid() 
    {
        return projectid;
    }
    public void setApplicationid(Long applicationid) 
    {
        this.applicationid = applicationid;
    }

    public Long getApplicationid() 
    {
        return applicationid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("paid", getPaid())
            .append("projectid", getProjectid())
            .append("applicationid", getApplicationid())
            .toString();
    }
}
