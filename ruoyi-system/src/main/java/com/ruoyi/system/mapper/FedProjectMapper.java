package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.FedProject;
import com.ruoyi.system.domain.ProjectApplication;

/**
 * 项目Mapper接口
 * 
 * @author ruoyi
 * @date 2023-05-30
 */
public interface FedProjectMapper 
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
     * 删除项目
     * 
     * @param projectId 项目主键
     * @return 结果
     */
    public int deleteFedProjectByProjectId(Long projectId);

    /**
     * 批量删除项目
     * 
     * @param projectIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFedProjectByProjectIds(Long[] projectIds);

    /**
     * 批量删除项目应用管理

     * 
     * @param projectIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProjectApplicationByProjectids(Long[] projectIds);
    
    /**
     * 批量新增项目应用管理

     * 
     * @param projectApplicationList 项目应用管理
列表
     * @return 结果
     */
    public int batchProjectApplication(List<ProjectApplication> projectApplicationList);
    

    /**
     * 通过项目主键删除项目应用管理
信息
     * 
     * @param projectId 项目ID
     * @return 结果
     */
    public int deleteProjectApplicationByProjectid(Long projectId);
}
