package com.toiukha.groupactivity.service;

/**
 * 【轉換註解】
 * 以 Spring Data JPA 實作 {@link GroupActivityServiceBoot}。
 * 雖然取代 DAO 版的 {@code GroupActivityServiceImpl}，
 * 但為保留原有程式，故以獨立檔案存放。
 */

import java.sql.Timestamp;
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
    public GroupActivityVO addAct(String actName, String actDesc, String imgPath, Integer itnId, Integer hostId,
                                  Timestamp signupStart, Timestamp signupEnd, Integer maxCap, Integer signupCnt,
                                  Timestamp actStart, Timestamp actEnd, Byte isPublic, Byte allowCancel, Byte recruitStatus) {
        GroupActivityVO act = new GroupActivityVO();
        act.setActName(actName);
        act.setActDesc(actDesc);
        act.setImgPath(imgPath);
        act.setItnId(itnId);
        act.setHostId(hostId);
        act.setSignupStart(signupStart);
        act.setSignupEnd(signupEnd);
        act.setMaxCap(maxCap);
        act.setSignupCnt(signupCnt);
        act.setActStart(actStart);
        act.setActEnd(actEnd);
        act.setIsPublic(isPublic);
        act.setAllowCancel(allowCancel);
        act.setRecruitStatus(recruitStatus);
        return repository.save(act);
    }

    @Override
    public GroupActivityVO updateAct(Integer actId, String actName, String actDesc, String imgPath, Integer itnId, Integer hostId,
                                     Timestamp signupStart, Timestamp signupEnd, Integer maxCap, Integer signupCnt,
                                     Timestamp actStart, Timestamp actEnd, Byte isPublic, Byte allowCancel, Byte recruitStatus) {
        GroupActivityVO act = getByPK(actId);
        if (act == null) {
            return null;
        }
        act.setActName(actName);
        act.setActDesc(actDesc);
        act.setImgPath(imgPath);
        act.setItnId(itnId);
        act.setHostId(hostId);
        act.setSignupStart(signupStart);
        act.setSignupEnd(signupEnd);
        act.setMaxCap(maxCap);
        act.setSignupCnt(signupCnt);
        act.setActStart(actStart);
        act.setActEnd(actEnd);
        act.setIsPublic(isPublic);
        act.setAllowCancel(allowCancel);
        act.setRecruitStatus(recruitStatus);
        return repository.save(act);
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
