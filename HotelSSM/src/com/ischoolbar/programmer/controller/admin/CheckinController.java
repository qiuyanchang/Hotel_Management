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
import com.ischoolbar.programmer.entity.admin.Checkin;
import com.ischoolbar.programmer.entity.admin.Room;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.BookOrderService;
import com.ischoolbar.programmer.service.RoomTypeService;
import com.ischoolbar.programmer.service.admin.CheckinService;
import com.ischoolbar.programmer.service.admin.RoomService;

/**
 * ��ס�����̨������
 * @author Administrator
 *
 */
@RequestMapping("/admin/checkin")
@Controller
public class CheckinController {
	
	@Autowired
	private RoomService roomService;
	@Autowired
	private RoomTypeService roomTypeService;
	@Autowired
	private BookOrderService bookOrderService;
	@Autowired
	private CheckinService checkinService;
	
	/**
	 * ��ס�����б�ҳ��
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.addObject("roomTypeList", roomTypeService.findAll());
		model.addObject("roomList", roomService.findAll());
		model.setViewName("checkin/list");
		return model;
	}
	
	/**
	 * ��ס��Ϣ��Ӳ���
	 * @param checkin
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Checkin checkin,
			@RequestParam(name="bookOrderId",required=false) Long bookOrderId
			){
		Map<String, String> ret = new HashMap<String, String>();
		if(checkin == null){
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ����ס��Ϣ!");
			return ret;
		}
		if(checkin.getRoomId() == null){
			ret.put("type", "error");
			ret.put("msg", "���䲻��Ϊ��!");
			return ret;
		}
		if(checkin.getRoomTypeId() == null){
			ret.put("type", "error");
			ret.put("msg", "���Ͳ���Ϊ��!");
			return ret;
		}
		if(StringUtils.isEmpty(checkin.getName())){
			ret.put("type", "error");
			ret.put("msg", "��ס��ϵ�����Ʋ���Ϊ��!");
			return ret;
		}
		if(StringUtils.isEmpty(checkin.getMobile())){
			ret.put("type", "error");
			ret.put("msg", "��ס��ϵ���ֻ��Ų���Ϊ��!");
			return ret;
		}
		if(StringUtils.isEmpty(checkin.getIdCard())){
			ret.put("type", "error");
			ret.put("msg", "��ϵ�����֤�Ų���Ϊ��!");
			return ret;
		}
		if(StringUtils.isEmpty(checkin.getArriveDate())){
			ret.put("type", "error");
			ret.put("msg", "����ʱ�䲻��Ϊ��!");
			return ret;
		}
		if(StringUtils.isEmpty(checkin.getLeaveDate())){
			ret.put("type", "error");
			ret.put("msg", "���ʱ�䲻��Ϊ��!");
			return ret;
		}
		
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		
		try {
			Date arrive=sdf.parse(checkin.getArriveDate());
			Date leave=sdf.parse(checkin.getLeaveDate());
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
		
		if(checkin.getMobile().length() != 11){
			ret.put("type", "error");
			ret.put("msg", "�ֻ��Ų���С��11λ");
			return ret;
		}else {
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(checkin.getMobile());
			boolean isMatch = m.matches();
			if(!isMatch){
				ret.put("type", "error");
				ret.put("msg", "�ֻ������ʽ����");
				return ret;
			}
		}
		
		
		String idCardRegex="^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
		
		if(checkin.getIdCard().length() != 18){
			ret.put("type", "error");
			ret.put("msg", "���֤���벻��С��18λ�򳬹�18λ");
			return ret;
		}else {
			Pattern p = Pattern.compile(idCardRegex);
			Matcher m = p.matcher(checkin.getIdCard());
			boolean isMatch = m.matches();
			if(!isMatch){
				ret.put("type", "error");
				ret.put("msg", "���֤�����ʽ����");
				return ret;
			}
		}
		
		
		
		checkin.setCreateTime(new Date());
		
		
	RoomType roomType = roomTypeService.find(checkin.getRoomTypeId());
		
		
		//��ѯ��ס�۸�
		if(roomType!=null) {
			checkin.setCheckinPrice(roomType.getPrice());
		}
		
		//У���Ƿ������������
		/*
		 * int countType=roomType.getRoomNum();
		 * 
		 * int countCheckIn=checkinService.getTotalCountByType(roomType.getId());
		 * 
		 * if(countType==countCheckIn) { ret.put("type", "error"); ret.put("msg",
		 * "�������ߣ��޷�������ӣ�!"); return ret; }
		 */
		
		
		if(checkinService.add(checkin) <= 0){
			ret.put("type", "error");
			ret.put("msg", "���ʧ�ܣ�����ϵ����Ա!");
			return ret;
		}
	
		
		
		
	
		
		if(bookOrderId != null){
			//��Ԥ��������ס��(��ס�ȿ�����ֱ����סҲ�������Ѿ�Ԥ����������ס)
			BookOrder bookOrder = bookOrderService.find(bookOrderId);
			bookOrder.setStatus(1);
			bookOrderService.edit(bookOrder);
			//roomType.setBookNum(roomType.getBookNum() - 1);//Ԥ������1
	
		}else{
			roomType.setAvilableNum(roomType.getAvilableNum() - 1);
		}
		//��ס�ɹ���ȥ�޸ĸ÷��͵�Ԥ����
		if(roomType != null){
			roomType.setLivedNum(roomType.getLivedNum() + 1);//��ס����1
			roomTypeService.updateNum(roomType);
			//������õķ�����Ϊ0�������ø÷���״̬����
			if(roomType.getAvilableNum() == 0){
				roomType.setStatus(0);
				roomTypeService.edit(roomType);
			}
		}
		Room room = roomService.find(checkin.getRoomId());
		if(room != null){
			//Ҫ�ѷ���״̬����Ϊ����ס
			room.setStatus(1);
			roomService.edit(room);
		}
		ret.put("type", "success");
		ret.put("msg", "��ӳɹ�!");
		return ret;
	}
	
	/**
	 * ��ס��Ϣ�༭����
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(Checkin checkin,
			@RequestParam(name="bookOrderId",required=false) Long bookOrderId
			){
		Map<String, String> ret = new HashMap<String, String>();
		if(checkin == null){
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ����ס��Ϣ!");
			return ret;
		}
		if(checkin.getRoomId() == null){
			ret.put("type", "error");
			ret.put("msg", "���䲻��Ϊ��!");
			return ret;
		}
		if(checkin.getRoomTypeId() == null){
			ret.put("type", "error");
			ret.put("msg", "���Ͳ���Ϊ��!");
			return ret;
		}
		if(StringUtils.isEmpty(checkin.getName())){
			ret.put("type", "error");
			ret.put("msg", "��ס��ϵ�����Ʋ���Ϊ��!");
			return ret;
		}
		if(StringUtils.isEmpty(checkin.getMobile())){
			ret.put("type", "error");
			ret.put("msg", "��ס��ϵ���ֻ��Ų���Ϊ��!");
			return ret;
		}
		if(StringUtils.isEmpty(checkin.getIdCard())){
			ret.put("type", "error");
			ret.put("msg", "��ϵ�����֤�Ų���Ϊ��!");
			return ret;
		}
		if(StringUtils.isEmpty(checkin.getArriveDate())){
			ret.put("type", "error");
			ret.put("msg", "����ʱ�䲻��Ϊ��!");
			return ret;
		}
		
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		
		try {
			Date arrive=sdf.parse(checkin.getArriveDate());
			Date leave=sdf.parse(checkin.getLeaveDate());
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
		
		
		
		if(StringUtils.isEmpty(checkin.getLeaveDate())){
			ret.put("type", "error");
			ret.put("msg", "���ʱ�䲻��Ϊ��!");
			return ret;
		}
		
		
String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
		
		if(checkin.getMobile().length() != 11){
			ret.put("type", "error");
			ret.put("msg", "�ֻ��Ų���С��11λ");
			return ret;
		}else {
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(checkin.getMobile());
			boolean isMatch = m.matches();
			if(!isMatch){
				ret.put("type", "error");
				ret.put("msg", "�ֻ������ʽ����");
				return ret;
			}
		}
		
		
		String idCardRegex="^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
		
		if(checkin.getIdCard().length() != 18){
			ret.put("type", "error");
			ret.put("msg", "���֤���벻��С��18λ�򳬹�18λ");
			return ret;
		}else {
			Pattern p = Pattern.compile(idCardRegex);
			Matcher m = p.matcher(checkin.getIdCard());
			boolean isMatch = m.matches();
			if(!isMatch){
				ret.put("type", "error");
				ret.put("msg", "���֤�����ʽ����");
				return ret;
			}
		}
		
		
		Checkin existCheckin = checkinService.find(checkin.getId());
		if(existCheckin == null){
			ret.put("type", "error");
			ret.put("msg", "��ѡ����ȷ����ס��Ϣ���б༭!");
			return ret;
		}
		
		
		
		
		
		//�༭�ɹ�֮��1���жϷ����Ƿ����仯��2���жϷ����Ƿ����仯��3���ж��Ƿ��Ǵ�Ԥ������������Ϣ
		//�����ж��Ƿ��Ǵ�Ԥ��������ס��Ϣ
		RoomType oldRoomType = roomTypeService.find(existCheckin.getRoomTypeId());
		RoomType newRoomType = roomTypeService.find(checkin.getRoomTypeId());
		
		//������ס������Ԥ��������Ӱ��
		if(oldRoomType.getId().longValue() != newRoomType.getId().longValue()){
			//˵�����ͷ����˱仯��ԭ���ķ�����ס���ָ����µķ�����ס������
			oldRoomType.setLivedNum(oldRoomType.getLivedNum() - 1);
			newRoomType.setLivedNum(newRoomType.getLivedNum() + 1);
			if(bookOrderId == null){
				oldRoomType.setAvilableNum(oldRoomType.getAvilableNum() + 1);
				newRoomType.setAvilableNum(newRoomType.getAvilableNum() - 1);
			}
		}
		
		
		checkin.setCheckinPrice(newRoomType.getPrice());
		
		if(checkinService.edit(checkin) <= 0){
			ret.put("type", "error");
			ret.put("msg", "�༭ʧ�ܣ�����ϵ����Ա!");
			return ret;
		}
		
		/**
		if(bookOrderId == null){
			//��ʾ���Ǵ�Ԥ���������ģ���ʱ���ж�ԭ������ס��Ϣ�Ƿ���Դ��Ԥ��
			if(existCheckin.getBookOrderId() == null){
				oldRoomType.setAvilableNum(oldRoomType.getAvilableNum() + 1);
				newRoomType.setAvilableNum(newRoomType.getAvilableNum() - 1);
			}
			if(existCheckin.getBookOrderId() != null){
				//��ʾԭ������ס��Ϣ��Դ��Ԥ���������µ���ס��Ϣ������Դ��Ԥ��,��ָ�ԭ����Ԥ��״̬
				BookOrder oldBookOrder = bookOrderService.find(existCheckin.getBookOrderId());
				oldBookOrder.setStatus(0);
				bookOrderService.edit(oldBookOrder);
				oldRoomType.setBookNum(oldRoomType.getBookNum() + 1);
			}
		}
		//��ʾ��ʱ�Ķ�������Դ��Ԥ��
		if(bookOrderId != null){
			//��ʾ�Ǵ�Ԥ���������ģ���ʱ���ж�ԭ������ס��Ϣ�Ƿ���Դ��Ԥ��
			if(existCheckin.getBookOrderId() != null){
				//��ʾԭ������ס��Ϣ��Դ��Ԥ���������µ���ס��Ϣ������Դ��Ԥ��,��ָ�ԭ����Ԥ��״̬
				BookOrder oldBookOrder = bookOrderService.find(existCheckin.getBookOrderId());
				if(bookOrderId.longValue() != oldBookOrder.getId().longValue()){
					oldBookOrder.setStatus(0);
					bookOrderService.edit(oldBookOrder);
					//oldRoomType.setBookNum(oldRoomType.getBookNum() + 1);
				}
			}
			if(oldRoomType.getId().longValue() != newRoomType.getId().longValue()){
				newRoomType.setBookNum(newRoomType.getBookNum() - 1);
				
				if(existCheckin.getBookOrderId() == null){
					oldRoomType.setAvilableNum(oldRoomType.getAvilableNum() + 1);
				}else{
					oldRoomType.setBookNum(oldRoomType.getBookNum() + 1);
				}
			}
			BookOrder newBookOrder = bookOrderService.find(bookOrderId);
			newBookOrder.setStatus(1);
			bookOrderService.edit(newBookOrder);
		}**/
		roomTypeService.updateNum(newRoomType);
		roomTypeService.updateNum(oldRoomType);
		//�жϷ����Ƿ����仯
		if(checkin.getRoomId().longValue() != existCheckin.getRoomId().longValue()){
			//��ʾ���䷢���˱仯
			Room oldRoom = roomService.find(existCheckin.getRoomId());
			Room newRoom = roomService.find(checkin.getRoomId());
			oldRoom.setStatus(0);//ԭ���ķ������ס
			newRoom.setStatus(1);//���ڵķ�������ס
			roomService.edit(newRoom);
			roomService.edit(oldRoom);
		}
	
		if(checkin.getStatus()==1) {
			if(checkin.getBookOrderId()!=null) {
				BookOrder bo = bookOrderService.find(checkin.getBookOrderId());
				bo.setStatus(2);
				bookOrderService.edit(bo);
				System.out.print("��������"+bo);
			}
		}else if(checkin.getStatus()==0){
			if(checkin.getBookOrderId()!=null) {
				BookOrder bo = bookOrderService.find(checkin.getBookOrderId());
				bo.setStatus(1);
				bookOrderService.edit(bo);
				System.out.print("��������"+bo);
			}
		}
		
	
		//�������Ϊ�Ѿ�����
		if(checkin.getStatus()==1) {
				Room room = roomService.find(checkin.getRoomId());
				//Ҫ�ѷ���״̬����Ϊ����ס
				if(room != null){
					room.setStatus(0);
					roomService.edit(room);
					
				}
				
				//��Ԥ����Ϊ�ѽ���
			
				
			
		}else {
			Room room = roomService.find(checkin.getRoomId());
			if(room != null){
				room.setStatus(1);
				roomService.edit(room);
			}
			//��Ԥ����ΪԤ����
			if(checkin.getBookOrderId()!=null) {
				if(checkin.getBookOrderId()!=null) {
					BookOrder bo = bookOrderService.find(checkin.getBookOrderId());
					bo.setStatus(1);
					bookOrderService.edit(bo);
				}
			}
		
		}
		
		
		
		ret.put("type", "success");
		ret.put("msg", "�޸ĳɹ�!");
		return ret;
	}
	
	/**
	 * ��ҳ��ѯ��ס��Ϣ
	 * @param name
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> list(
			@RequestParam(name="name",defaultValue="") String name,
			@RequestParam(name="roomId",defaultValue="") Long roomId,
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
		queryMap.put("roomId", roomId);
		queryMap.put("roomTypeId", roomTypeId);
		queryMap.put("idCard", idCard);
		queryMap.put("mobile", mobile);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", checkinService.findList(queryMap));
		ret.put("total", checkinService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * �˷�����
	 * @param checkId
	 * @return
	 */
	@RequestMapping(value="/checkout",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> checkout(Long checkId
			){
		Map<String, String> ret = new HashMap<String, String>();
		if(checkId == null){
			ret.put("type", "error");
			ret.put("msg", "��ѡ������!");
			return ret;
		}
		Checkin checkin = checkinService.find(checkId);
		if(checkin == null){
			ret.put("type", "error");
			ret.put("msg", "��ѡ����ȷ������!");
			return ret;
		}
		checkin.setStatus(1);
		if(checkinService.edit(checkin) <= 0){
			ret.put("type", "error");
			ret.put("msg", "�˷�ʧ�ܣ�����ϵ����Ա!");
			return ret;
		}
		//���Ȳ�������״̬
		Room room = roomService.find(checkin.getRoomId());
		if(room != null){
			room.setStatus(2);
			roomService.edit(room);
		}
		//����޸ķ��Ϳ���������ס����״̬
		RoomType roomType = roomTypeService.find(checkin.getRoomTypeId());
		if(roomType != null){
			roomType.setAvilableNum(roomType.getAvilableNum() + 1);
			if(roomType.getAvilableNum() > roomType.getRoomNum()){
				roomType.setAvilableNum(roomType.getRoomNum());
			}
			roomType.setLivedNum(roomType.getLivedNum() - 1);
			if(roomType.getStatus() == 0){
				roomType.setStatus(1);
			}
			if(checkin.getBookOrderId() != null){
				roomType.setBookNum(roomType.getBookNum() - 1);
			}
			roomTypeService.updateNum(roomType);
			roomTypeService.edit(roomType);
		}
		//�ж��Ƿ�����Ԥ��
		if(checkin.getBookOrderId() != null){
			BookOrder bookOrder = bookOrderService.find(checkin.getBookOrderId());
			bookOrder.setStatus(2);
			bookOrderService.edit(bookOrder);
			
		}
		ret.put("type", "success");
		ret.put("msg", "�˷��ɹ�!");
		return ret;
	}
	
	/**
	 * ���ݷ������ͻ�ȡ����
	 * @param roomTypeId
	 * @return
	 */
	@RequestMapping(value="/load_room_list",method=RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> load_room_list(Long roomTypeId){
		List<Map<String, Object>> retList = new ArrayList<Map<String,Object>>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("roomTypeId", roomTypeId);
		queryMap.put("status", 0);
		queryMap.put("offset", 0);
		queryMap.put("pageSize", 999);
		List<Room> roomList = roomService.findList(queryMap);
		for(Room room:roomList){
			
			if(room.getStatus()==0) {
				Map<String, Object> option = new HashMap<String, Object>();
				option.put("value", room.getId());
				option.put("text", room.getSn());
				retList.add(option);
			}
		
		}
		return retList;
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
			if(checkinService.delete(id) <= 0){
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
