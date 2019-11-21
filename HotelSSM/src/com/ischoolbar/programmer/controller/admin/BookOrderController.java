package com.ischoolbar.programmer.controller.admin;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ischoolbar.programmer.entity.BookOrder;
import com.ischoolbar.programmer.entity.RoomType;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.AccountService;
import com.ischoolbar.programmer.service.BookOrderService;
import com.ischoolbar.programmer.service.RoomTypeService;

/**
 * Ԥ�����������̨������
 * @author Administrator
 *
 */
@RequestMapping("/admin/book_order")
@Controller
public class BookOrderController {
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private RoomTypeService roomTypeService;
	@Autowired
	private BookOrderService bookOrderService;
	
	
	/**
	 * Ԥ�����������б�ҳ��
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.addObject("roomTypeList", roomTypeService.findAll());
		
		
		
List<RoomType> returnList=new ArrayList<>();
		
		List<RoomType> typeList=new ArrayList<>();
				typeList=roomTypeService.findAll();
		
		if(typeList==null) {
			returnList=new ArrayList<>();
		}else {
			if(typeList.size()>0) {
				for (int i = 0; i <typeList.size(); i++) {
					System.out.print(typeList.get(i));
					if(typeList.get(i).getStatus()==1) {
						returnList.add(typeList.get(i));
					}
				
		        }
				
			}
			
		}
				
		model.addObject("roomTypeListB", returnList);	
		
		
		
		
		
		model.addObject("accountList", accountService.findAll());
		model.setViewName("book_order/list");
		return model;
	}
	
	/**
	 * Ԥ��������Ϣ��Ӳ���
	 * @param bookOrder
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(BookOrder bookOrder){
		Map<String, String> ret = new HashMap<String, String>();
		if(bookOrder == null){
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ��Ԥ��������Ϣ!");
			return ret;
		}
		if(bookOrder.getAccountId() == null){
			ret.put("type", "error");
			ret.put("msg", "�ͻ�����Ϊ��!");
			return ret;
		}
		if(bookOrder.getRoomTypeId() == null){
			ret.put("type", "error");
			ret.put("msg", "���Ͳ���Ϊ��!");
			return ret;
		}
		if(StringUtils.isEmpty(bookOrder.getName())){
			ret.put("type", "error");
			ret.put("msg", "Ԥ��������ϵ�����Ʋ���Ϊ��!");
			return ret;
		}
		if(StringUtils.isEmpty(bookOrder.getMobile())){
			ret.put("type", "error");
			ret.put("msg", "Ԥ��������ϵ���ֻ��Ų���Ϊ��!");
			return ret;
		}
		if(StringUtils.isEmpty(bookOrder.getIdCard())){
			ret.put("type", "error");
			ret.put("msg", "��ϵ�����֤�Ų���Ϊ��!");
			return ret;
		}
		if(StringUtils.isEmpty(bookOrder.getArriveDate())){
			ret.put("type", "error");
			ret.put("msg", "����ʱ�䲻��Ϊ��!");
			return ret;
		}
		if(StringUtils.isEmpty(bookOrder.getLeaveDate())){
			ret.put("type", "error");
			ret.put("msg", "���ʱ�䲻��Ϊ��!");
			return ret;
		}
		
		
DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		
		try {
			Date arrive=sdf.parse(bookOrder.getArriveDate());
			Date leave=sdf.parse(bookOrder.getLeaveDate());
			System.out.print("arrive==="+arrive);
			System.out.print("leave==="+leave);
			if(!arrive.before(leave)) {
				ret.put("type", "error");
				ret.put("msg", "���ʱ�䲻�����ڵ���ʱ��");
				return ret;
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
		
		if(bookOrder.getMobile().length() != 11){
			ret.put("type", "error");
			ret.put("msg", "�ֻ��Ų���С��11λ");
			return ret;
		}else {
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(bookOrder.getMobile());
			boolean isMatch = m.matches();
			if(!isMatch){
				ret.put("type", "error");
				ret.put("msg", "�ֻ������ʽ����");
				return ret;
			}
		}
		
		
		String idCardRegex="^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
		
		if(bookOrder.getIdCard().length() != 18){
			ret.put("type", "error");
			ret.put("msg", "���֤���벻��С��18λ�򳬹�18λ");
			return ret;
		}else {
			Pattern p = Pattern.compile(idCardRegex);
			Matcher m = p.matcher(bookOrder.getIdCard());
			boolean isMatch = m.matches();
			if(!isMatch){
				ret.put("type", "error");
				ret.put("msg", "���֤�����ʽ����");
				return ret;
			}
		}
		
		
		
		bookOrder.setCreateTime(new Date());
		if(bookOrderService.add(bookOrder) <= 0){
			ret.put("type", "error");
			ret.put("msg", "���ʧ�ܣ�����ϵ����Ա!");
			return ret;
		}
		RoomType roomType = roomTypeService.find(bookOrder.getRoomTypeId());
		//Ԥ���ɹ���ȥ�޸ĸ÷��͵�Ԥ����
		if(roomType != null){
			roomType.setBookNum(roomType.getBookNum() + 1);
			roomType.setAvilableNum(roomType.getAvilableNum() - 1);
			roomTypeService.updateNum(roomType);
			//������õķ�����Ϊ0�������ø÷���״̬����
			if(roomType.getAvilableNum() == 0){
				roomType.setStatus(0);
				roomTypeService.edit(roomType);
			}
		}
		ret.put("type", "success");
		ret.put("msg", "��ӳɹ�!");
		return ret;
	}
	
	/**
	 * Ԥ��������Ϣ�༭����
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(BookOrder bookOrder){
		Map<String, String> ret = new HashMap<String, String>();
		if(bookOrder == null){
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ��Ԥ��������Ϣ!");
			return ret;
		}
		if(bookOrder.getAccountId() == null){
			ret.put("type", "error");
			ret.put("msg", "�ͻ�����Ϊ��!");
			return ret;
		}
		if(bookOrder.getRoomTypeId() == null){
			ret.put("type", "error");
			ret.put("msg", "���Ͳ���Ϊ��!");
			return ret;
		}
		if(StringUtils.isEmpty(bookOrder.getName())){
			ret.put("type", "error");
			ret.put("msg", "Ԥ��������ϵ�����Ʋ���Ϊ��!");
			return ret;
		}
		if(StringUtils.isEmpty(bookOrder.getMobile())){
			ret.put("type", "error");
			ret.put("msg", "Ԥ��������ϵ���ֻ��Ų���Ϊ��!");
			return ret;
		}
		if(StringUtils.isEmpty(bookOrder.getIdCard())){
			ret.put("type", "error");
			ret.put("msg", "��ϵ�����֤�Ų���Ϊ��!");
			return ret;
		}
		if(StringUtils.isEmpty(bookOrder.getArriveDate())){
			ret.put("type", "error");
			ret.put("msg", "����ʱ�䲻��Ϊ��!");
			return ret;
		}
		if(StringUtils.isEmpty(bookOrder.getLeaveDate())){
			ret.put("type", "error");
			ret.put("msg", "���ʱ�䲻��Ϊ��!");
			return ret;
		}
		
String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
		
		if(bookOrder.getMobile().length() != 11){
			ret.put("type", "error");
			ret.put("msg", "�ֻ��Ų���С��11λ");
			return ret;
		}else {
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(bookOrder.getMobile());
			boolean isMatch = m.matches();
			if(!isMatch){
				ret.put("type", "error");
				ret.put("msg", "�ֻ������ʽ����");
				return ret;
			}
		}
		
		
		String idCardRegex="^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
		
		if(bookOrder.getIdCard().length() != 18){
			ret.put("type", "error");
			ret.put("msg", "���֤���벻��С��18λ�򳬹�18λ");
			return ret;
		}else {
			Pattern p = Pattern.compile(idCardRegex);
			Matcher m = p.matcher(bookOrder.getIdCard());
			boolean isMatch = m.matches();
			if(!isMatch){
				ret.put("type", "error");
				ret.put("msg", "���֤�����ʽ����");
				return ret;
			}
		}
		
		
		BookOrder existBookOrder = bookOrderService.find(bookOrder.getId());
		if(existBookOrder == null){
			ret.put("type", "error");
			ret.put("msg", "��ѡ����ȷ�����ݽ��б༭!");
			return ret;
		}
		
DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		
		try {
			Date arrive=sdf.parse(bookOrder.getArriveDate());
			Date leave=sdf.parse(bookOrder.getLeaveDate());
			System.out.print("arrive==="+arrive);
			System.out.print("leave==="+leave);
			if(!arrive.before(leave)) {
				ret.put("type", "error");
				ret.put("msg", "���ʱ�䲻�����ڵ���ʱ��");
				return ret;
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

		
		
		
		if(bookOrderService.edit(bookOrder) <= 0){
			ret.put("type", "error");
			ret.put("msg", "�༭ʧ�ܣ�����ϵ����Ա!");
			return ret;
		}
		//�жϷ����Ƿ����仯
		if(existBookOrder.getRoomTypeId().longValue() != bookOrder.getRoomTypeId().longValue()){
			//���ͷ����˱仯
			//���Ȼָ�ԭ�����͵�Ԥ������������
			RoomType oldRoomType = roomTypeService.find(existBookOrder.getRoomTypeId());
			oldRoomType.setAvilableNum(oldRoomType.getAvilableNum() + 1);
			oldRoomType.setBookNum(oldRoomType.getBookNum() - 1);
			roomTypeService.updateNum(oldRoomType);
			if(oldRoomType.getStatus() == 0){
				//�ɵķ���ԭ�������������ڲ������Ļ����ָ�״̬
				if(oldRoomType.getAvilableNum() > 0){
					//���ó�״̬����
					oldRoomType.setStatus(1);
					roomTypeService.edit(oldRoomType);
				}
			}
			//�޸��µķ��͵Ŀ�������Ԥ����
			RoomType newRoomType = roomTypeService.find(bookOrder.getRoomTypeId());
			newRoomType.setAvilableNum(newRoomType.getAvilableNum() - 1);
			newRoomType.setBookNum(newRoomType.getBookNum() + 1);
			roomTypeService.updateNum(newRoomType);
			if(newRoomType.getAvilableNum() <= 0){
				//û�п��÷�����
				newRoomType.setStatus(0);//���ó�����
				roomTypeService.edit(newRoomType);
			}
		}
		ret.put("type", "success");
		ret.put("msg", "�޸ĳɹ�!");
		return ret;
	}
	
	/**
	 * ��ҳ��ѯԤ��������Ϣ
	 * @param name
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> list(
			@RequestParam(name="name",defaultValue="") String name,
			@RequestParam(name="accountId",defaultValue="") Long accountId,
			@RequestParam(name="roomTypeId",defaultValue="") Long roomTypeId,
			@RequestParam(name="idCard",defaultValue="") String idCard,
			@RequestParam(name="mobile",defaultValue="") String mobile,
			@RequestParam(name="status",required=false) Integer status,
			Page page
			){
		Map<String,Object> ret = new HashMap<String, Object>();
		Map<String,Object> queryMap = new HashMap<String, Object>();
		queryMap.put("name", name);
		queryMap.put("status", status);
		queryMap.put("accountId", accountId);
		queryMap.put("roomTypeId", roomTypeId);
		queryMap.put("idCard", idCard);
		queryMap.put("mobile", mobile);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", bookOrderService.findList(queryMap));
		ret.put("total", bookOrderService.getTotal(queryMap));
		return ret;
	}
	
	
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(Long id){
		Map<String, String> ret = new HashMap<String, String>();
		if(id == null){
			ret.put("type", "error");
			ret.put("msg", "��ѡ��Ҫɾ������Ϣ!");
			return ret;
		}
		try {
			if(bookOrderService.delete(id) <= 0){
				ret.put("type", "error");
				ret.put("msg", "ɾ��ʧ�ܣ�����ϵ����Ա!");
				return ret;
			}
		} catch (Exception e) {
			// TODO: handle exception
			ret.put("type", "error");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "ɾ���ɹ�!");
		return ret;
	}
	
	
	
	
}
