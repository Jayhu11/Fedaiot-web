package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.SecurityUtils;
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
import com.ruoyi.system.domain.UserRelation;
import com.ruoyi.system.service.IUserRelationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户关系Controller
 * 
 * @author ruoyi
 * @date 2023-06-01
 */
@RestController
@RequestMapping("/system/relation")
public class UserRelationController extends BaseController
{
    @Autowired
    private IUserRelationService userRelationService;

    /**
     * 查询用户关系列表
     */
    @PreAuthorize("@ss.hasPermi('system:relation:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserRelation userRelation)
    {
        startPage();
        boolean already_add=false;
        userRelation.setMyId(SecurityUtils.getUserId());
        List<UserRelation> list = userRelationService.selectUserRelationList(userRelation);
        for (UserRelation userRelation2 : list) {
            if (userRelation2.getMyId() == userRelation2.getUserId()) {
                already_add=true;
                break;
            }
        }
        if(already_add==false){
            UserRelation userRelation3=new UserRelation();
            userRelation3.setMyId(SecurityUtils.getUserId());
            userRelation3.setMyName(SecurityUtils.getUsername());
            userRelation3.setUserId(SecurityUtils.getUserId());
            userRelation3.setUserName(SecurityUtils.getUsername());
            userRelationService.insertUserRelation(userRelation3);
        }
        return getDataTable(list);
    }

    /**
     * 导出用户关系列表
     */
    @PreAuthorize("@ss.hasPermi('system:relation:export')")
    @Log(title = "用户关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserRelation userRelation)
    {
        List<UserRelation> list = userRelationService.selectUserRelationList(userRelation);
        ExcelUtil<UserRelation> util = new ExcelUtil<UserRelation>(UserRelation.class);
        util.exportExcel(response, list, "用户关系数据");
    }

    /**
     * 获取用户关系详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:relation:query')")
    @GetMapping(value = "/{urId}")
    public AjaxResult getInfo(@PathVariable("urId") Long urId)
    {
        return success(userRelationService.selectUserRelationByUrId(urId));
    }

    /**
     * 新增用户关系
     */
    @PreAuthorize("@ss.hasPermi('system:relation:add')")
    @Log(title = "用户关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserRelation userRelation)
    {
        userRelation.setMyId(SecurityUtils.getUserId());
        userRelation.setMyName(SecurityUtils.getUsername());
        return toAjax(userRelationService.insertUserRelation(userRelation));
    }

    /**
     * 修改用户关系
     */
    @PreAuthorize("@ss.hasPermi('system:relation:edit')")
    @Log(title = "用户关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserRelation userRelation)
    {
        userRelation.setMyId(SecurityUtils.getUserId());
        userRelation.setMyName(SecurityUtils.getUsername());
        return toAjax(userRelationService.updateUserRelation(userRelation));
    }

    /**
     * 删除用户关系
     */
    @PreAuthorize("@ss.hasPermi('system:relation:remove')")
    @Log(title = "用户关系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{urIds}")
    public AjaxResult remove(@PathVariable Long[] urIds)
    {
        return toAjax(userRelationService.deleteUserRelationByUrIds(urIds));
    }
}
