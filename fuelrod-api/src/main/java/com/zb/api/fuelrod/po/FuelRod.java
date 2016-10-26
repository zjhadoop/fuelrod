package com.zb.api.fuelrod.po;

import java.io.Serializable;

/**
 * FuelRod
 *
 * @author zj
 * @date 2016/8/18
 */
public class FuelRod implements Serializable{

    private Long liveId;//直播ID
    private Integer roomId;//房间ID
    private Integer clickNum;//点赞数
    private Integer createTime;//创建时间
    private Integer updateTime;//更新时间
    private String ext;//扩展字段

    public Long getLiveId() {
        return liveId;
    }

    public void setLiveId(Long liveId) {
        this.liveId = liveId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getClickNum() {
        return clickNum;
    }

    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
}
