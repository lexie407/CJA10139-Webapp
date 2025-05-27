package com.toiukha.groupactivity.service;

import java.sql.Timestamp;
import java.util.List;

import com.toiukha.groupactivity.entity.GroupActivityVO;

public interface GroupActivityService {
	
	GroupActivityVO addAct(String actName, String actDesc, String imgPath, Integer itnId, Integer hostId,
			Timestamp signupStart, Timestamp signupEnd, Integer maxCap, Integer signupCnt, Timestamp actStart,
			Timestamp actEnd, Byte isPublic, Byte allowCancel, Byte recruitStatus);
	GroupActivityVO updateAct(Integer actId, String actName, String actDesc, String imgPath, Integer itnId, Integer hostId,
			Timestamp signupStart, Timestamp signupEnd, Integer maxCap, Integer signupCnt, Timestamp actStart,
			Timestamp actEnd, Byte isPublic, Byte allowCancel, Byte recruitStatus);
	
	int deleteAct(Integer actId);
	
	GroupActivityVO getByPK(Integer actId);
	
	List<GroupActivityVO> getAll();
	
//	List<GroupActivityVO> ByCompositeQuery(Map<String, String> map);
	

}
