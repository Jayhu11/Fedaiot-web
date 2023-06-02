package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.FedApplication;
import com.ruoyi.system.service.IFedApplicationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 应用Controller
 * 
 * @author ruoyi
 * @date 2023-06-01
 */
@RestController
@RequestMapping("/system/application")
public class FedApplicationController extends BaseController
{
    @Autowired
    private IFedApplicationService fedApplicationService;

    /**
     * 查询应用列表
     */
    @PreAuthorize("@ss.hasPermi('system:application:list')")
    @GetMapping("/list")
    public TableDataInfo list(FedApplication fedApplication)
    {
        startPage();
        List<FedApplication> list = fedApplicationService.selectFedApplicationList(fedApplication);
        return getDataTable(list);
    }

    /**
     * 导出应用列表
     */
    @PreAuthorize("@ss.hasPermi('system:application:export')")
    @Log(title = "应用", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FedApplication fedApplication)
    {
        List<FedApplication> list = fedApplicationService.selectFedApplicationList(fedApplication);
        ExcelUtil<FedApplication> util = new ExcelUtil<FedApplication>(FedApplication.class);
        util.exportExcel(response, list, "应用数据");
    }

    /**
     * 获取应用详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:application:query')")
    @GetMapping(value = "/{applicationId}")
    public AjaxResult getInfo(@PathVariable("applicationId") Long applicationId)
    {
        return success(fedApplicationService.selectFedApplicationByApplicationId(applicationId));
    }

    /**
     * 新增应用
     */
    @PreAuthorize("@ss.hasPermi('system:application:add')")
    @Log(title = "应用", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FedApplication fedApplication)
    {
        return toAjax(fedApplicationService.insertFedApplication(fedApplication));
    }

    /**
     * 修改应用
     */
    @PreAuthorize("@ss.hasPermi('system:application:edit')")
    @Log(title = "应用", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FedApplication fedApplication)
    {
        return toAjax(fedApplicationService.updateFedApplication(fedApplication));
    }

    /**
     * 删除应用
     */
    @PreAuthorize("@ss.hasPermi('system:application:remove')")
    @Log(title = "应用", businessType = BusinessType.DELETE)
	@DeleteMapping("/{applicationIds}")
    public AjaxResult remove(@PathVariable Long[] applicationIds)
    {
        return toAjax(fedApplicationService.deleteFedApplicationByApplicationIds(applicationIds));
    }
}
