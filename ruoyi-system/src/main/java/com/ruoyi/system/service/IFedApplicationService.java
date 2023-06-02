package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.FedApplication;

/**
 * 应用Service接口
 * 
 * @author ruoyi
 * @date 2023-06-01
 */
public interface IFedApplicationService 
{
    /**
     * 查询应用
     * 
     * @param applicationId 应用主键
     * @return 应用
     */
    public FedApplication selectFedApplicationByApplicationId(Long applicationId);

    /**
     * 查询应用列表
     * 
     * @param fedApplication 应用
     * @return 应用集合
     */
    public List<FedApplication> selectFedApplicationList(FedApplication fedApplication);

    /**
     * 新增应用
     * 
     * @param fedApplication 应用
     * @return 结果
     */
    public int insertFedApplication(FedApplication fedApplication);

    /**
     * 修改应用
     * 
     * @param fedApplication 应用
     * @return 结果
     */
    public int updateFedApplication(FedApplication fedApplication);

    /**
     * 批量删除应用
     * 
     * @param applicationIds 需要删除的应用主键集合
     * @return 结果
     */
    public int deleteFedApplicationByApplicationIds(Long[] applicationIds);

    /**
     * 删除应用信息
     * 
     * @param applicationId 应用主键
     * @return 结果
     */
    public int deleteFedApplicationByApplicationId(Long applicationId);
}
