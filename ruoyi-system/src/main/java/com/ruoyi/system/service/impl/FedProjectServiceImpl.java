package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.system.domain.ProjectApplication;
import com.ruoyi.system.mapper.FedProjectMapper;
import com.ruoyi.system.domain.FedProject;
import com.ruoyi.system.service.IFedProjectService;

/**
 * 项目Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-05-30
 */
@Service
public class FedProjectServiceImpl implements IFedProjectService 
{
    @Autowired
    private FedProjectMapper fedProjectMapper;

    /**
     * 查询项目
     * 
     * @param projectId 项目主键
     * @return 项目
     */
    @Override
    public FedProject selectFedProjectByProjectId(Long projectId)
    {
        return fedProjectMapper.selectFedProjectByProjectId(projectId);
    }

    /**
     * 查询项目列表
     * 
     * @param fedProject 项目
     * @return 项目
     */
    @Override
    public List<FedProject> selectFedProjectList(FedProject fedProject)
    {
        return fedProjectMapper.selectFedProjectList(fedProject);
    }

    /**
     * 新增项目
     * 
     * @param fedProject 项目
     * @return 结果
     */
    @Transactional
    @Override
    public int insertFedProject(FedProject fedProject)
    {
        int rows = fedProjectMapper.insertFedProject(fedProject);
        insertProjectApplication(fedProject);
        return rows;
    }

    /**
     * 修改项目
     * 
     * @param fedProject 项目
     * @return 结果
     */
    @Transactional
    @Override
    public int updateFedProject(FedProject fedProject)
    {
        fedProjectMapper.deleteProjectApplicationByProjectid(fedProject.getProjectId());
        insertProjectApplication(fedProject);
        return fedProjectMapper.updateFedProject(fedProject);
    }

    /**
     * 批量删除项目
     * 
     * @param projectIds 需要删除的项目主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteFedProjectByProjectIds(Long[] projectIds)
    {
        fedProjectMapper.deleteProjectApplicationByProjectids(projectIds);
        return fedProjectMapper.deleteFedProjectByProjectIds(projectIds);
    }

    /**
     * 删除项目信息
     * 
     * @param projectId 项目主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteFedProjectByProjectId(Long projectId)
    {
        fedProjectMapper.deleteProjectApplicationByProjectid(projectId);
        return fedProjectMapper.deleteFedProjectByProjectId(projectId);
    }

    /**
     * 新增项目应用管理
信息
     * 
     * @param fedProject 项目对象
     */
    public void insertProjectApplication(FedProject fedProject)
    {
        List<ProjectApplication> projectApplicationList = fedProject.getProjectApplicationList();
        Long projectId = fedProject.getProjectId();
        if (StringUtils.isNotNull(projectApplicationList))
        {
            List<ProjectApplication> list = new ArrayList<ProjectApplication>();
            for (ProjectApplication projectApplication : projectApplicationList)
            {
                projectApplication.setProjectid(projectId);
                list.add(projectApplication);
            }
            if (list.size() > 0)
            {
                fedProjectMapper.batchProjectApplication(list);
            }
        }
    }
}
