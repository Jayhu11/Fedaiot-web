package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.FedProject;

/**
 * 项目Service接口
 * 
 * @author ruoyi
 * @date 2023-05-30
 */
public interface IFedProjectService 
{
    /**
     * 查询项目
     * 
     * @param projectId 项目主键
     * @return 项目
     */
    public FedProject selectFedProjectByProjectId(Long projectId);

    /**
     * 查询项目列表
     * 
     * @param fedProject 项目
     * @return 项目集合
     */
    public List<FedProject> selectFedProjectList(FedProject fedProject);

    /**
     * 新增项目
     * 
     * @param fedProject 项目
     * @return 结果
     */
    public int insertFedProject(FedProject fedProject);

    /**
     * 修改项目
     * 
     * @param fedProject 项目
     * @return 结果
     */
    public int updateFedProject(FedProject fedProject);

    /**
     * 批量删除项目
     * 
     * @param projectIds 需要删除的项目主键集合
     * @return 结果
     */
    public int deleteFedProjectByProjectIds(Long[] projectIds);

    /**
     * 删除项目信息
     * 
     * @param projectId 项目主键
     * @return 结果
     */
    public int deleteFedProjectByProjectId(Long projectId);
}
