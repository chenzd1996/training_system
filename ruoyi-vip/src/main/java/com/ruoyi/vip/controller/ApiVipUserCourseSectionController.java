package com.ruoyi.vip.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.ExcelUtil;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.vip.domain.VipUserCourseSection;
import com.ruoyi.vip.domain.vo.VipUserCourseSectionVO;
import com.ruoyi.vip.service.IVipUserCourseSectionService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 我的课程学习 信息操作处理
 *
 * @author zhujj
 * @date 2019-01-15
 */
@Api("我的课程学习")
@RestController
@RequestMapping("/api")
public class ApiVipUserCourseSectionController extends BaseController
{

	@Autowired
	private IVipUserCourseSectionService vipUserCourseSectionService;
	@Autowired
	private ISysUserService sysUserService;
	@PostMapping("/v1/user/course/study")
	public AjaxResult courseSections(@RequestBody VipUserCourseSection trainCourseSection, ModelMap map) {

		SysUser sysUser = sysUserService.selectUserByLoginName( JwtUtil.getLoginName(), UserConstants.USER_VIP );
		trainCourseSection.setVipUserId( sysUser.getUserId().intValue() );
		vipUserCourseSectionService.updateStudy( trainCourseSection );
		return success("增加学习进度");
	}

}
