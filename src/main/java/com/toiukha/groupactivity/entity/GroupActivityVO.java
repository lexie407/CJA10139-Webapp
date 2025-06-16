package com.toiukha.groupactivity.entity;

/**
 * 【轉換註解】
 * 此 VO 結合 JPA 設定並供 Spring Boot 使用，
 * 新增 Bean Validation 註解以確保輸入正確。
 */

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "groupactivity")
public class GroupActivityVO implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACTID", updatable = false)
	private Integer actId;
	
        @Column(name = "ACTNAME")
        @NotBlank
        private String actName;
	
        @Column(name = "ACTDESC")
        @NotBlank
        private String actDesc;
	
        @Column(name = "IMGPATH")
        @NotBlank
        private String imgPath;
	
        @Column(name = "ITNID")
        @NotNull
        private Integer itnId;
	
        @Column(name = "HOSTID")
        @NotNull
        private Integer hostId;
	
        @Column(name = "SIGNUPSTART")
        @NotNull
        private Timestamp signupStart;
	
        @Column(name = "SIGNUPEND")
        @NotNull
        private Timestamp signupEnd;
	
        @Column(name = "MAXCAP")
        @NotNull
        @Positive
        private Integer maxCap;
	
        @Column(name = "SIGNUPCNT")
        @NotNull
        @Min(0)
        private Integer signupCnt;
	
        @Column(name = "ACTSTART")
        @NotNull
        private Timestamp actStart;
	
        @Column(name = "ACTEND")
        @NotNull
        private Timestamp actEnd;
	
        @Column(name = "ISPUBLIC")
        @NotNull
        private Byte isPublic;
	
        @Column(name = "ALLOWCANCEL")
        @NotNull
        private Byte allowCancel;
	
        @Column(name = "RECRUITSTATUS")
        @NotNull
        private Byte recruitStatus;
	
	
	public GroupActivityVO() {
		super();
	}
	
	
	public GroupActivityVO(Integer actId, String actName, String actDesc, String imgPath, Integer itnId, Integer hostId,
			Timestamp signupStart, Timestamp signupEnd, Integer maxCap, Integer signupCnt, Timestamp actStart,
			Timestamp actEnd, Byte isPublic, Byte allowCancel, Byte recruitStatus) {
		super();
		this.actId = actId;
		this.actName = actName;
		this.actDesc = actDesc;
		this.imgPath = imgPath;
		this.itnId = itnId;
		this.hostId = hostId;
		this.signupStart = signupStart;
		this.signupEnd = signupEnd;
		this.maxCap = maxCap;
		this.signupCnt = signupCnt;
		this.actStart = actStart;
		this.actEnd = actEnd;
		this.isPublic = isPublic;
		this.allowCancel = allowCancel;
		this.recruitStatus = recruitStatus;
	}


	public Integer getActId() {
		return actId;
	}
	public void setActId(Integer actId) {
		this.actId = actId;
	}
	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}
	public String getActDesc() {
		return actDesc;
	}
	public void setActDesc(String desc) {
		this.actDesc = desc;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public Integer getItnId() {
		return itnId;
	}
	public void setItnId(Integer itnId) {
		this.itnId = itnId;
	}
	public Integer getHostId() {
		return hostId;
	}
	public void setHostId(Integer hostId) {
		this.hostId = hostId;
	}
	public Timestamp getSignupStart() {
		return signupStart;
	}
	public void setSignupStart(Timestamp signupStart) {
		this.signupStart = signupStart;
	}
	public Timestamp getSignupEnd() {
		return signupEnd;
	}
	public void setSignupEnd(Timestamp signupEnd) {
		this.signupEnd = signupEnd;
	}
	public Integer getMaxCap() {
		return maxCap;
	}
	public void setMaxCap(Integer maxCap) {
		this.maxCap = maxCap;
	}
	public Integer getSignupCnt() {
		return signupCnt;
	}
	public void setSignupCnt(Integer signupCnt) {
		this.signupCnt = signupCnt;
	}
	public Timestamp getActStart() {
		return actStart;
	}
	public void setActStart(Timestamp actStart) {
		this.actStart = actStart;
	}
	public Timestamp getActEnd() {
		return actEnd;
	}
	public void setActEnd(Timestamp actEnd) {
		this.actEnd = actEnd;
	}
	public Byte getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(Byte isPublic) {
		this.isPublic = isPublic;
	}
	public Byte getAllowCancel() {
		return allowCancel;
	}
	public void setAllowCancel(Byte allowCancel) {
		this.allowCancel = allowCancel;
	}
	public Byte getRecruitStatus() {
		return recruitStatus;
	}
	public void setRecruitStatus(Byte recruitStatus) {
		this.recruitStatus = recruitStatus;
	}

	

}
