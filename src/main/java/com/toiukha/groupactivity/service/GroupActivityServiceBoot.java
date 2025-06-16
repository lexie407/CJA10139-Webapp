package com.toiukha.groupactivity.service;

/**
 * 【轉換註解】
 * 定義 Spring Boot 重構時加入的 Spring Data JPA 服務介面。
 * 舊的 {@code GroupActivityService} 仍存在，
 * 此介面提供相同合約給新的 Repository 實作使用。
 */

import java.util.List;

import com.toiukha.groupactivity.entity.GroupActivityVO;

public interface GroupActivityServiceBoot {
    GroupActivityVO addAct(GroupActivityVO vo);

    GroupActivityVO updateAct(Integer actId, GroupActivityVO vo);

    int deleteAct(Integer actId);

    GroupActivityVO getByPK(Integer actId);

    List<GroupActivityVO> getAll();
}
