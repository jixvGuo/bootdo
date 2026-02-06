package com.bootdo.cpe.controller.gongfa;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseGfProController;
import com.bootdo.cpe.domain.QcProStatEnum;
import com.bootdo.cpe.dto.GfProDataDto;
import com.bootdo.system.domain.UserDO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.cpe.domain.GfGroupApplyInfoDO;
import com.bootdo.cpe.service.GfGroupApplyInfoService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

import static com.bootdo.common.config.Constant.*;
import static com.bootdo.common.utils.ShiroUtils.getUserId;

/**
 * 公奖法申请信息
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-07-27 08:03:07
 */
 
@Controller
@RequestMapping("/cpe/gfGroupApplyInfo")
public class GfGroupApplyInfoController extends BaseGfProController {
	@Autowired
	private GongFaController gongFaController;
	@Autowired
	private GfGroupApplyInfoService gfGroupApplyInfoService;
	private Map<String, Object> newParams;

	@GetMapping()
	@RequiresPermissions("cpe:gfGroupApplyInfo:gfGroupApplyInfo")
	String GfGroupApplyInfo(){
	    return "cpe/gfGroupApplyInfo/gfGroupApplyInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cpe:gfGroupApplyInfo:gfGroupApplyInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		UserDO user = getUser();
        Long uid = getUserId();
        List<Long> roleIdList = user.getRoleIds();
        if (roleIdList.contains(ROLE_GONGFA_ASSOCIATION_ID)) {
            //todo 临时使用协会联系人的用户id
            params.put("associationUserId", roleIdList.contains(ROLE_QC_OFFLINE_VIEW_ID) ? 101 : user.getUserId());
        } else if (roleIdList.contains(ROLE_GONGFA_ENTERPRISE_ID)) {
            //企业用户查看自己创建项目
            params.put("enterpriseUid", uid);
        } else {
            //分派给自己的项目
            params.put("ass_assign_uid", uid);
        }
        getProListParamsByRole(params);
        this.newParams = params;

        params.put("proStatStr", "");
        Object keyWordObj = params.get("keyWord");
        if (keyWordObj != null) {
            List<String> proStatList = QcProStatEnum.getStatValByKey(keyWordObj.toString());
            if (proStatList.size() > 0) {
                String str = new String();
                for (String s : proStatList) {
                    str +=  s + ",";
                }
                params.put("proStatStr", str.substring(0, str.length() - 1));
            }
        }

        Query query = new Query(params);
        //TODO 为了下载使用
        this.newParams = params;

		//查询列表数据
		List<GfProDataDto> gfGroupApplyInfoList = gfGroupApplyInfoService.list(query);
		int total = gfGroupApplyInfoService.count(query);
		PageUtils pageUtils = new PageUtils(gfGroupApplyInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("cpe:gfGroupApplyInfo:add")
	String add(){
	    return "cpe/gfGroupApplyInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("cpe:gfGroupApplyInfo:edit")
	String edit(@PathVariable("id") Integer id, ModelMap map){
		Map<String, Object> params = new HashMap<>();
		params.put("proId", id);
		return gongFaController.toGfApply(map, params);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("cpe:gfGroupApplyInfo:add")
	public R save( GfGroupApplyInfoDO gfGroupApplyInfo){
		Integer id = gfGroupApplyInfo.getId();
		if(id != null && id > 0) {
			gfGroupApplyInfo.setUpdate(new Date());
			if(gfGroupApplyInfoService.update(gfGroupApplyInfo) > 0) {
				return R.ok();
			}else {
				return R.error();
			}
		}
		gfGroupApplyInfo.setCreated(new Date());
		gfGroupApplyInfo.setUpdate(new Date());
		if(gfGroupApplyInfoService.save(gfGroupApplyInfo)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cpe:gfGroupApplyInfo:edit")
	public R update( GfGroupApplyInfoDO gfGroupApplyInfo){
		gfGroupApplyInfoService.update(gfGroupApplyInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("cpe:gfGroupApplyInfo:remove")
	public R remove( Integer id){
		if(gfGroupApplyInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cpe:gfGroupApplyInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		gfGroupApplyInfoService.batchRemove(ids);
		return R.ok();
	}
	
}
