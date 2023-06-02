package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FedApplicationMapper;
import com.ruoyi.system.domain.FedApplication;
import com.ruoyi.system.service.IFedApplicationService;

/**
 * 应用Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-06-01
 */
@Service
public class FedApplicationServiceImpl implements IFedApplicationService 
{
    @Autowired
    private FedApplicationMapper fedApplicationMapper;

    /**
     * 查询应用
     * 
     * @param applicationId 应用主键
     * @return 应用
     */
    @Override
    public FedApplication selectFedApplicationByApplicationId(Long applicationId)
    {
        return fedApplicationMapper.selectFedApplicationByApplicationId(applicationId);
    }

    /**
     * 查询应用列表
     * 
     * @param fedApplication 应用
     * @return 应用
     */
    @Override
    public List<FedApplication> selectFedApplicationList(FedApplication fedApplication)
    {
        return fedApplicationMapper.selectFedApplicationList(fedApplication);
    }

    /**
     * 新增应用
     * 
     * @param fedApplication 应用
     * @return 结果
     */
    @Override
    public int insertFedApplication(FedApplication fedApplication)
    {
        return fedApplicationMapper.insertFedApplication(fedApplication);
    }

    /**
     * 修改应用
     * 
     * @param fedApplication 应用
     * @return 结果
     */
    @Override
    public int updateFedApplication(FedApplication fedApplication)
    {
        return fedApplicationMapper.updateFedApplication(fedApplication);
    }

    /**
     * 批量删除应用
     * 
     * @param applicationIds 需要删除的应用主键
     * @return 结果
     */
    @Override
    public int deleteFedApplicationByApplicationIds(Long[] applicationIds)
    {
        return fedApplicationMapper.deleteFedApplicationByApplicationIds(applicationIds);
    }

    /**
     * 删除应用信息
     * 
     * @param applicationId 应用主键
     * @return 结果
     */
    @Override
    public int deleteFedApplicationByApplicationId(Long applicationId)
    {
        return fedApplicationMapper.deleteFedApplicationByApplicationId(applicationId);
    }
}
