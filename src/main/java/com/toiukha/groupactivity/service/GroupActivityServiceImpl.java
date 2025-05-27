package com.toiukha.groupactivity.service;

import java.sql.Timestamp;
import java.util.List;

import com.toiukha.groupactivity.entity.GroupActivityVO;
import com.toiukha.groupactivity.dao.*;

public class GroupActivityServiceImpl implements GroupActivityService{
	
	private GroupActivityDAO actdao;

	public GroupActivityServiceImpl() {
		actdao = new GroupActivityDAOJDBCimpl();
	}

	@Override
	public GroupActivityVO addAct(String actName, String actDesc, String imgPath, Integer itnId, Integer hostId,
			Timestamp signupStart, Timestamp signupEnd, Integer maxCap, Integer signupCnt, Timestamp actStart,
			Timestamp actEnd, Byte isPublic, Byte allowCancel, Byte recruitStatus) {
		
		GroupActivityVO actVO = new GroupActivityVO();
		
		actVO.setActName(actName);
		actVO.setActDesc(actDesc);
		actVO.setImgPath(imgPath);
		actVO.setItnId(itnId);
		actVO.setHostId(hostId);
		actVO.setSignupStart(signupStart);
		actVO.setSignupEnd(signupEnd);
		actVO.setMaxCap(maxCap);
		actVO.setSignupCnt(signupCnt);
		actVO.setActStart(actStart);
		actVO.setActEnd(actEnd);
		actVO.setIsPublic(isPublic);
		actVO.setAllowCancel(allowCancel);
		actVO.setRecruitStatus(recruitStatus);
		
		actdao.add(actVO);
		
		return actVO;
	}

	@Override
	public GroupActivityVO updateAct(Integer actId, String actName, String actDesc, String imgPath, Integer itnId, Integer hostId,
			Timestamp signupStart, Timestamp signupEnd, Integer maxCap, Integer signupCnt, Timestamp actStart,
			Timestamp actEnd, Byte isPublic, Byte allowCancel, Byte recruitStatus) {
		
		GroupActivityVO actVO = new GroupActivityVO();
		
		actVO.setActId(actId);
		actVO.setActName(actName);
		actVO.setActDesc(actDesc);
		actVO.setImgPath(imgPath);
		actVO.setItnId(itnId);
		actVO.setHostId(hostId);
		actVO.setSignupStart(signupStart);
		actVO.setSignupEnd(signupEnd);
		actVO.setMaxCap(maxCap);
		actVO.setSignupCnt(signupCnt);
		actVO.setActStart(actStart);
		actVO.setActEnd(actEnd);
		actVO.setIsPublic(isPublic);
		actVO.setAllowCancel(allowCancel);
		actVO.setRecruitStatus(recruitStatus);
		
		actdao.update(actVO);
		
		return actVO;
	}

	
	@Override
	public int delete(Integer actId) {
		return actdao.delete(actId);
	}
	
	@Override
	public GroupActivityVO getByPK(Integer actId) {
		return actdao.getByPK(actId);
	}

	@Override
	public List<GroupActivityVO> getAll() {
		return actdao.getAll();
	}

	

}
