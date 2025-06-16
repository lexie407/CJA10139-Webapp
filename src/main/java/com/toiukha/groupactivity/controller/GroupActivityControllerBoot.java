package com.toiukha.groupactivity.controller;

/**
 * 【轉換註解】
 * 此 REST 控制器因導入 Spring Boot 與 Spring Data JPA 而新增，
 * 舊有的 {@link GroupActivityServlet} 仍然保留，
 * 本類別與其並存，在新框架中提供等同功能。
 */

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.toiukha.groupactivity.entity.GroupActivityVO;
import com.toiukha.groupactivity.service.GroupActivityServiceBoot;

@RestController
@RequestMapping("/api/activities")
public class GroupActivityControllerBoot {

    @Autowired
    private GroupActivityServiceBoot service;

    @GetMapping
    public List<GroupActivityVO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupActivityVO> getOne(@PathVariable("id") Integer id) {
        GroupActivityVO vo = service.getByPK(id);
        return vo != null ? ResponseEntity.ok(vo) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public GroupActivityVO create(@Valid @RequestBody GroupActivityVO vo) {
        return service.addAct(vo.getActName(), vo.getActDesc(), vo.getImgPath(), vo.getItnId(), vo.getHostId(),
                vo.getSignupStart(), vo.getSignupEnd(), vo.getMaxCap(), vo.getSignupCnt(),
                vo.getActStart(), vo.getActEnd(), vo.getIsPublic(), vo.getAllowCancel(), vo.getRecruitStatus());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupActivityVO> update(@PathVariable("id") Integer id, @Valid @RequestBody GroupActivityVO vo) {
        GroupActivityVO updated = service.updateAct(id, vo.getActName(), vo.getActDesc(), vo.getImgPath(), vo.getItnId(), vo.getHostId(),
                vo.getSignupStart(), vo.getSignupEnd(), vo.getMaxCap(), vo.getSignupCnt(),
                vo.getActStart(), vo.getActEnd(), vo.getIsPublic(), vo.getAllowCancel(), vo.getRecruitStatus());
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        service.deleteAct(id);
    }
}
