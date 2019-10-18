package cn.mju.hotel.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Reserve {
    private Integer id;

    private Integer userId;

    private String roomType;

    private BigDecimal roomPrice;

    private Date reserveInTime;

    private Date reserveOutTime;

    private Date reserveNowTime;

    private Long reserveMoney;

    private String reserveStatus;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType == null ? null : roomType.trim();
    }

    public BigDecimal getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(BigDecimal roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Date getReserveInTime() {
        return reserveInTime;
    }

    public void setReserveInTime(Date reserveInTime) {
        this.reserveInTime = reserveInTime;
    }

    public Date getReserveOutTime() {
        return reserveOutTime;
    }

    public void setReserveOutTime(Date reserveOutTime) {
        this.reserveOutTime = reserveOutTime;
    }

    public Date getReserveNowTime() {
        return reserveNowTime;
    }

    public void setReserveNowTime(Date reserveNowTime) {
        this.reserveNowTime = reserveNowTime;
    }

    public Long getReserveMoney() {
        return reserveMoney;
    }

    public void setReserveMoney(Long reserveMoney) {
        this.reserveMoney = reserveMoney;
    }

    public String getReserveStatus() {
        return reserveStatus;
    }

    public void setReserveStatus(String reserveStatus) {
        this.reserveStatus = reserveStatus == null ? null : reserveStatus.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}