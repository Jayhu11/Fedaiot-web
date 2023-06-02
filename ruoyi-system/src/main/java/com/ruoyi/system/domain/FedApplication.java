package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 应用对象 fed_application
 * 
 * @author ruoyi
 * @date 2023-06-01
 */
public class FedApplication extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 应用id */
    private Long applicationId;

    /** 应用名称 */
    @Excel(name = "应用名称")
    private String applicationName;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 应用配置 */
    @Excel(name = "应用配置")
    private String configuration;

    public void setApplicationId(Long applicationId) 
    {
        this.applicationId = applicationId;
    }

    public Long getApplicationId() 
    {
        return applicationId;
    }
    public void setApplicationName(String applicationName) 
    {
        this.applicationName = applicationName;
    }

    public String getApplicationName() 
    {
        return applicationName;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setConfiguration(String configuration) 
    {
        this.configuration = configuration;
    }

    public String getConfiguration() 
    {
        return configuration;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("applicationId", getApplicationId())
            .append("applicationName", getApplicationName())
            .append("userId", getUserId())
            .append("configuration", getConfiguration())
            .toString();
    }
}
