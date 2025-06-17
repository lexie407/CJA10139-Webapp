package com.toiukha.groupactivity.service;

/**
 * 【轉換註解】
 * 以 Spring Data JPA 實作 {@link GroupActivityServiceBoot}。
 * 雖然取代 DAO 版的 {@code GroupActivityServiceImpl}，
 * 但為保留原有程式，故以獨立檔案存放。
 */

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.toiukha.groupactivity.entity.GroupActivityVO;
import com.toiukha.groupactivity.repository.GroupActivityRepository;

@Service
public class GroupActivityServiceBootImpl implements GroupActivityServiceBoot {

    @Autowired
    private GroupActivityRepository repository;

    @Override
    public GroupActivityVO addAct(GroupActivityVO vo) {
        return repository.save(vo);
    }

    @Override
    public GroupActivityVO updateAct(Integer actId, GroupActivityVO vo) {
        GroupActivityVO act = getByPK(actId);
        if (act == null) {
            return null;
        }
        vo.setActId(actId);
        return repository.save(vo);
    }

    @Override
    public int deleteAct(Integer actId) {
        repository.deleteById(actId);
        return 1;
    }

    @Override
    public GroupActivityVO getByPK(Integer actId) {
        return repository.findById(actId).orElse(null);
    }

    @Override
    public List<GroupActivityVO> getAll() {
        return repository.findAll();
    }
}
