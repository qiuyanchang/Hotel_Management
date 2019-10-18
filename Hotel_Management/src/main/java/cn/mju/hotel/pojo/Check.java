package cn.mju.hotel.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Check {
    private Integer id;

    private Integer reserveId;

    private String checkIdNum;

    private String checkPhoneNum;

    private String checkName;

    private String checkSex;

    private Date checkInTime;

    private Date checkOutTime;

    private String checkRoomId;

    private Integer checkDay;

    private BigDecimal checkPrice;

    private String checkStatus;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReserveId() {
        return reserveId;
    }

    public void setReserveId(Integer reserveId) {
        this.reserveId = reserveId;
    }

    public String getCheckIdNum() {
        return checkIdNum;
    }

    public void setCheckIdNum(String checkIdNum) {
        this.checkIdNum = checkIdNum == null ? null : checkIdNum.trim();
    }

    public String getCheckPhoneNum() {
        return checkPhoneNum;
    }

    public void setCheckPhoneNum(String checkPhoneNum) {
        this.checkPhoneNum = checkPhoneNum == null ? null : checkPhoneNum.trim();
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName == null ? null : checkName.trim();
    }

    public String getCheckSex() {
        return checkSex;
    }

    public void setCheckSex(String checkSex) {
        this.checkSex = checkSex == null ? null : checkSex.trim();
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Date getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Date checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public String getCheckRoomId() {
        return checkRoomId;
    }

    public void setCheckRoomId(String checkRoomId) {
        this.checkRoomId = checkRoomId == null ? null : checkRoomId.trim();
    }

    public Integer getCheckDay() {
        return checkDay;
    }

    public void setCheckDay(Integer checkDay) {
        this.checkDay = checkDay;
    }

    public BigDecimal getCheckPrice() {
        return checkPrice;
    }

    public void setCheckPrice(BigDecimal checkPrice) {
        this.checkPrice = checkPrice;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus == null ? null : checkStatus.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}