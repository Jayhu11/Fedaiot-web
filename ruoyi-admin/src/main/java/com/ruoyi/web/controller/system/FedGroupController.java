package com.ruoyi.web.controller.system;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.UserGroup;
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
import com.ruoyi.system.domain.FedGroup;
import com.ruoyi.system.service.IFedGroupService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 队伍管理Controller
 *
 * @author ruoyi
 * @date 2023-06-01
 */
@RestController
@RequestMapping("/system/group")
public class FedGroupController extends BaseController
{
    @Autowired
    private IFedGroupService fedGroupService;

    /**
     * 查询队伍管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:group:list')")
    @GetMapping("/list")
    public TableDataInfo list(FedGroup fedGroup)
    {

        startPage();
        /**
         UserGroup s = new UserGroup();
         s.setUserId(SecurityUtils.getUserId());
         List<UserGroup> userlist=new ArrayList<>();
         userlist.add(s);
         */
        fedGroup.setUserId2(SecurityUtils.getUserId());

        List<FedGroup> list = fedGroupService.selectFedGroupList(fedGroup);
        return getDataTable(list);
    }

    /**
     * 导出队伍管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:group:export')")
    @Log(title = "队伍管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FedGroup fedGroup)
    {
        List<FedGroup> list = fedGroupService.selectFedGroupList(fedGroup);
        ExcelUtil<FedGroup> util = new ExcelUtil<FedGroup>(FedGroup.class);
        util.exportExcel(response, list, "队伍管理数据");
    }

    /**
     * 获取队伍管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:group:query')")
    @GetMapping(value = "/{groupId}")
    public AjaxResult getInfo(@PathVariable("groupId") Long groupId)
    {
        return success(fedGroupService.selectFedGroupByGroupId(groupId));
    }

    /**
     * 新增队伍管理
     */
    @PreAuthorize("@ss.hasPermi('system:group:add')")
    @Log(title = "队伍管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FedGroup fedGroup)
    {
        return toAjax(fedGroupService.insertFedGroup(fedGroup));
    }

    /**
     * 修改队伍管理
     */
    @PreAuthorize("@ss.hasPermi('system:group:edit')")
    @Log(title = "队伍管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FedGroup fedGroup)
    {
        return toAjax(fedGroupService.updateFedGroup(fedGroup));
    }

    /**
     * 删除队伍管理
     */
    @PreAuthorize("@ss.hasPermi('system:group:remove')")
    @Log(title = "队伍管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{groupIds}")
    public AjaxResult remove(@PathVariable Long[] groupIds)
    {
        return toAjax(fedGroupService.deleteFedGroupByGroupIds(groupIds));
    }
}
