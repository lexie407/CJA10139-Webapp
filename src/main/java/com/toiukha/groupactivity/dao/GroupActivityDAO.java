package com.toiukha.groupactivity.dao;

import java.util.*;

import com.toiukha.groupactivity.entity.GroupActivityVO;


public interface GroupActivityDAO {
	int add(GroupActivityVO actVO);
	int update(GroupActivityVO actVO);
	int delete(Integer actId);
	GroupActivityVO getByPK(Integer actId);
	List<GroupActivityVO> getAll();
//	List<GroupActivityVO> ByCompositeQuery(Map<String, String> map);
	

}