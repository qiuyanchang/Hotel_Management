 package com.ischoolbar.programmer.controller.home;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ischoolbar.programmer.entity.Account;
import com.ischoolbar.programmer.entity.BookOrder;
import com.ischoolbar.programmer.entity.RoomType;
import com.ischoolbar.programmer.service.AccountService;
import com.ischoolbar.programmer.service.BookOrderService;
import com.ischoolbar.programmer.service.RoomTypeService;

/**
 * 前台用户控制器
 * @author Administrator
 *
 */
@RequestMapping("/home/account")
@Controller
public class HomeAccountController {
	
	@Autowired
	private RoomTypeService roomTypeService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private BookOrderService bookOrderService;
	
	/**
	 * 前台用户中心首页
	 * @param model
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model,HttpServletRequest request
			){
		Account account = (Account)request.getSession().getAttribute("account");
		Map<String,Object> queryMap = new HashMap<String, Object>();
		queryMap.put("accountId", account.getId());
		queryMap.put("offset", 0);
		queryMap.put("pageSize", 999);
		model.addObject("bookOrderList", bookOrderService.findList(queryMap));
		model.addObject("roomTypeList", roomTypeService.findAll());
		model.setViewName("home/account/index");
		return model;
	}
	
	
	
	
	
	
	
	
	@RequestMapping(value="/index/order",method=RequestMethod.GET)
	public ModelAndView listOrder(ModelAndView model,HttpServletRequest request
			){
		Account account = (Account)request.getSession().getAttribute("account");
		Map<String,Object> queryMap = new HashMap<String, Object>();
		queryMap.put("accountId", account.getId());
		queryMap.put("offset", 0);
		queryMap.put("pageSize", 999);
		model.addObject("bookOrderList", bookOrderService.findList(queryMap));
		model.addObject("roomTypeList", roomTypeService.findAll());
		model.setViewName("home/account/order");
		return model;
	}
	
	
	
	@RequestMapping(value="/index/order1")
	public ModelAndView listOrder1(
			@RequestParam(name="name",defaultValue="") String name,
			@RequestParam(name="status",defaultValue="") int status,
			HttpServletRequest request
			){
		
				
		Account account = (Account)request.getSession().getAttribute("account");
		Map<String,Object> queryMap = new HashMap<String, Object>();
		queryMap.put("accountId", account.getId());
		queryMap.put("offset", 0);
		queryMap.put("pageSize", 999);
		
		if(status==-1) {
			
		}else if(status==1) {
			queryMap.put("status",1);
		}else if(status==0) {
			queryMap.put("status",0);
		}else if(status==2) {
			queryMap.put("status",2);
		}
		
	
		if(!StringUtils.isEmpty(name)) {
			Integer typeId=roomTypeService.findByName(name);
			
			if(typeId!=null) {
				System.out.print("TypeId==="+typeId);
				
				queryMap.put("roomTypeId",typeId);
			}else {
				queryMap.put("roomTypeId",1981021260);
			}
		}
		
		ModelAndView model=new ModelAndView();
		List<RoomType> allRoomTypeList=roomTypeService.findAll();
		model.addObject("bookOrderList",bookOrderService.findList(queryMap) );
		model.addObject("roomTypeList", allRoomTypeList);
		model.addObject("kw", name);
		model.setViewName("home/account/order"); 
		return model;
	}
	
	
	@RequestMapping(value="/index/ziliao",method=RequestMethod.GET)
	public ModelAndView listZiliao(ModelAndView model,HttpServletRequest request
			){
		Account account = (Account)request.getSession().getAttribute("account");
		Map<String,Object> queryMap = new HashMap<String, Object>();
		queryMap.put("accountId", account.getId());
		queryMap.put("offset", 0);
		queryMap.put("pageSize", 999);
		model.addObject("bookOrderList", bookOrderService.findList(queryMap));
		model.addObject("roomTypeList", roomTypeService.findAll());
		model.setViewName("home/account/ziliao");
		return model;
	}
	
	
	@RequestMapping(value="/index/changePwd",method=RequestMethod.GET)
	public ModelAndView changePwd(ModelAndView model,HttpServletRequest request
			){
		Account account = (Account)request.getSession().getAttribute("account");
		Map<String,Object> queryMap = new HashMap<String, Object>();
		queryMap.put("accountId", account.getId());
		queryMap.put("offset", 0);
		queryMap.put("pageSize", 999);
		model.addObject("bookOrderList", bookOrderService.findList(queryMap));
		model.addObject("roomTypeList", roomTypeService.findAll());
		model.setViewName("home/account/changePwd");
		return model;
	}
	
	
	
	/**
	 * 预定房间页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/book_order",method=RequestMethod.GET)
	public ModelAndView bookOrder(ModelAndView model,Long roomTypeId
			){
		model.addObject("roomType", roomTypeService.find(roomTypeId));
		model.setViewName("home/account/book_order");
		return model;
	}
	
	
	/**
	 * 预定信息提交
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/book_order",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> bookOrderAct(BookOrder bookOrder,HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		if(bookOrder == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的预定订单信息!");
			return ret;
		}
		Account account = (Account)request.getSession().getAttribute("account");
		if(account == null){
			ret.put("type", "error");
			ret.put("msg", "客户不能为空!");
			return ret;
		}
		bookOrder.setAccountId(account.getId());
		if(bookOrder.getRoomTypeId() == null){
			ret.put("type", "error");
			ret.put("msg", "房型不能为空!");
			return ret;
		}
		if(StringUtils.isEmpty(bookOrder.getName())){
			ret.put("type", "error");
			ret.put("msg", "预定订单联系人名称不能为空!");
			return ret;
		}
		if(StringUtils.isEmpty(bookOrder.getMobile())){
			ret.put("type", "error");
			ret.put("msg", "预定订单联系人手机号不能为空!");
			return ret;
		}
		if(StringUtils.isEmpty(bookOrder.getIdCard())){
			ret.put("type", "error");
			ret.put("msg", "联系人身份证号不能为空!");
			return ret;
		}
		if(StringUtils.isEmpty(bookOrder.getArriveDate())){
			ret.put("type", "error");
			ret.put("msg", "到达时间不能为空!");
			return ret;
		}
		if(StringUtils.isEmpty(bookOrder.getLeaveDate())){
			ret.put("type", "error");
			ret.put("msg", "离店时间不能为空!");
			return ret;
		}
		bookOrder.setCreateTime(new Date());
		bookOrder.setStatus(0);
		if(bookOrderService.add(bookOrder) <= 0){
			ret.put("type", "error");
			ret.put("msg", "添加失败，请联系管理员!");
			return ret;
		}
		RoomType roomType = roomTypeService.find(bookOrder.getRoomTypeId());
		//预定成功后去修改该房型的预定数
		if(roomType != null){
			roomType.setBookNum(roomType.getBookNum() + 1);
			roomType.setAvilableNum(roomType.getAvilableNum() - 1);
			roomTypeService.updateNum(roomType);
			//如果可用的房间数为0，则设置该房型状态已满
			if(roomType.getAvilableNum() == 0){
				roomType.setStatus(0);
				roomTypeService.edit(roomType);
			}
		}
		ret.put("type", "success");
		ret.put("msg", "预定成功!");
		return ret;
	}
	
	/**
	 * 修改个人信息提交
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/update_info",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> updateInfoAct(Account account,HttpServletRequest request){
		Map<String,String> retMap = new HashMap<String, String>();
		if(account == null){
			retMap.put("type", "error");
			retMap.put("msg", "请填写正确的用户信息！");
			return retMap;
		}
		if(StringUtils.isEmpty(account.getName())){
			retMap.put("type", "error");
			retMap.put("msg", "用户名不能为空！");
			return retMap;
		}
		
String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
		
		if(account.getMobile()!=null){
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(account.getMobile());
			boolean isMatch = m.matches();
			if(!isMatch){
				retMap.put("type", "error");
				retMap.put("msg", "手机号码格式错误！");
				return retMap;
			}
		}
		
		
		String idCardRegex="^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
		
		if(account.getIdCard().length() != 18){
			retMap.put("type", "error");
			retMap.put("msg", "身份证号码不能小于18位或超过18位");
			return retMap;
		}else {
			Pattern p = Pattern.compile(idCardRegex);
			Matcher m = p.matcher(account.getIdCard());
			boolean isMatch = m.matches();
			if(!isMatch){
				retMap.put("type", "error");
				retMap.put("msg", "身份证号码格式错误！");
				return retMap;
			}
		}
		
		
		
		Account loginedAccount = (Account)request.getSession().getAttribute("account");
		if(isExist(account.getName(),loginedAccount.getId())){
			retMap.put("type", "error");
			retMap.put("msg", "该用户名已经存在！");
			return retMap;
		}
		
		
		
		loginedAccount.setAddress(account.getAddress());
		loginedAccount.setIdCard(account.getIdCard());
		loginedAccount.setMobile(account.getMobile());
		loginedAccount.setName(account.getName());
		loginedAccount.setRealName(account.getRealName());
		if(accountService.edit(loginedAccount) <= 0){
			retMap.put("type", "error");
			retMap.put("msg", "修改失败，请联系管理员！");
			return retMap;
		}
		request.getSession().setAttribute("account", loginedAccount);
		retMap.put("type", "success");
		retMap.put("msg", "修改成功！");
		return retMap;
	}
	
	
	@RequestMapping(value="/index/update_info",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> updateInfoActIndex(Account account,HttpServletRequest request){
		Map<String,String> retMap = new HashMap<String, String>();
		if(account == null){
			retMap.put("type", "error");
			retMap.put("msg", "请填写正确的用户信息！");
			return retMap;
		}
		if(StringUtils.isEmpty(account.getName())){
			retMap.put("type", "error");
			retMap.put("msg", "用户名不能为空！");
			return retMap;
		}
		
		String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
		

		System.out.print(account.getMobile()+"==="+account.getMobile().length());

		if(account.getMobile().length()!=11){
			
			retMap.put("type", "error");
			retMap.put("msg", "手机号应为11位数！");
			return retMap;
		}else {
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(account.getMobile());
			boolean isMatch = m.matches();
			if(!isMatch){
				retMap.put("type", "error");
				retMap.put("msg", "手机号码格式错误！");
				return retMap;
			}
		}
		
		
		
		String idCardRegex="^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
		
		if(account.getIdCard().length()!=18){
			retMap.put("type", "error");
			retMap.put("msg", "身份证号码不能小于18位或超过18位");
			return retMap;
		}else {
			Pattern p = Pattern.compile(idCardRegex);
			Matcher m = p.matcher(account.getIdCard());
			boolean isMatch = m.matches();
			if(!isMatch){
				retMap.put("type", "error");
				retMap.put("msg", "身份证号码格式错误！");
				return retMap;
			}
		}
		
		
		Account loginedAccount = (Account)request.getSession().getAttribute("account");
		if(isExist(account.getName(),loginedAccount.getId())){
			retMap.put("type", "error");
			retMap.put("msg", "该用户名已经存在！");
			return retMap;
		}
		
		

		loginedAccount.setAddress(account.getAddress());
		loginedAccount.setIdCard(account.getIdCard());
		loginedAccount.setMobile(account.getMobile());
		loginedAccount.setName(account.getName());
		loginedAccount.setRealName(account.getRealName());
		if(accountService.edit(loginedAccount) <= 0){
			retMap.put("type", "error");
			retMap.put("msg", "修改失败，请联系管理员！");
			return retMap;
		}
		request.getSession().setAttribute("account", loginedAccount);
		retMap.put("type", "success");
		retMap.put("msg", "修改成功！");
		return retMap;
	}
	
	/**
	 * 修改密码提交
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/update_pwd",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> updatePwdAct(String oldPassword,String newPassword,HttpServletRequest request){
		Map<String,String> retMap = new HashMap<String, String>();
		if(StringUtils.isEmpty(oldPassword)){
			retMap.put("type", "error");
			retMap.put("msg", "请填写原来的密码！");
			return retMap;
		}
		if(StringUtils.isEmpty(newPassword)){
			retMap.put("type", "error");
			retMap.put("msg", "请填写新密码！");
			return retMap;
		}
		Account loginedAccount = (Account)request.getSession().getAttribute("account");
		if(!oldPassword.equals(loginedAccount.getPassword())){
			retMap.put("type", "error");
			retMap.put("msg", "原密码错误！");
			return retMap;
		}
		loginedAccount.setPassword(newPassword);
		if(accountService.edit(loginedAccount) <= 0){
			retMap.put("type", "error");
			retMap.put("msg", "修改失败，请联系管理员！");
			return retMap;
		}
		retMap.put("type", "success");
		retMap.put("msg", "修改密码成功！");
		return retMap;
	}
	
	

	/**
	 * 修改密码提交
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/index/update_pwd",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> updatePwdActIndex(String oldPassword,String newPassword,HttpServletRequest request){
		Map<String,String> retMap = new HashMap<String, String>();
		if(StringUtils.isEmpty(oldPassword)){
			retMap.put("type", "error");
			retMap.put("msg", "请填写原来的密码！");
			return retMap;
		}
		if(StringUtils.isEmpty(newPassword)){
			retMap.put("type", "error");
			retMap.put("msg", "请填写新密码！");
			return retMap;
		}
		Account loginedAccount = (Account)request.getSession().getAttribute("account");
		if(!oldPassword.equals(loginedAccount.getPassword())){
			retMap.put("type", "error");
			retMap.put("msg", "原密码错误！");
			return retMap;
		}
		loginedAccount.setPassword(newPassword);
		if(accountService.edit(loginedAccount) <= 0){
			retMap.put("type", "error");
			retMap.put("msg", "修改失败，请联系管理员！");
			return retMap;
		}
		retMap.put("type", "success");
		retMap.put("msg", "修改密码成功！");
		return retMap;
	}
	
	
	/**
	 * 判断用户是否存在
	 * @param name
	 * @param id
	 * @return
	 */
	private boolean isExist(String name,Long id){
		Account account = accountService.findByName(name);
		if(account == null)return false;
		if(account != null && account.getId().longValue() == id)return false;
		return true;
	}
	
	
	
	
	
	
	@RequestMapping(value="/index/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(Long id){
		Map<String, String> ret = new HashMap<String, String>();
		if(id == null){
			ret.put("type", "error");
			ret.put("msg", "请选择要删除的信息!");
			return ret;
		}
		try {
			
			
			BookOrder existBookOrder = bookOrderService.find(id);
			
			if(existBookOrder!=null&&existBookOrder.getStatus()==1) {
				ret.put("type", "error");
				ret.put("msg", "该订单已入住，无法取消!");
				return ret;
			}
			
			if(existBookOrder!=null&&existBookOrder.getStatus()==2) {
				ret.put("type", "error");
				ret.put("msg", "该订单已结账，无法取消!");
				return ret;
			}
			
			RoomType oldRoomType = roomTypeService.find(existBookOrder.getRoomTypeId());
			oldRoomType.setAvilableNum(oldRoomType.getAvilableNum() + 1);
			oldRoomType.setBookNum(oldRoomType.getBookNum() - 1);
			roomTypeService.updateNum(oldRoomType);
			if(oldRoomType.getStatus() == 0){
				//旧的房间原来是满房，现在不满房的话，恢复状态
				if(oldRoomType.getAvilableNum() > 0){
					//设置成状态可用
					oldRoomType.setStatus(1);
					roomTypeService.edit(oldRoomType);
				}
			}
		
			
			if(bookOrderService.delete(id) <= 0){
				ret.put("type", "error");
				ret.put("msg", "删除失败，请联系管理员!");
				return ret;
			}
		} catch (Exception e) {
			// TODO: handle exception
			ret.put("type", "error");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "删除成功!");
		return ret;
	}
	
	
	
	
}
