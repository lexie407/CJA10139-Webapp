package com.toiukha.groupactivity.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.toiukha.groupactivity.entity.GroupActivityVO;

public class GroupActivityDAOJDBCimplH implements GroupActivityDAO {

    private static final SessionFactory factory = new Configuration().configure().buildSessionFactory();

    @Override
    public int add(GroupActivityVO actVO) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        Integer id = (Integer) session.save(actVO);
        tx.commit();
        session.close();
        return id != null ? 1 : 0; // 回傳1代表成功
    }

    @Override
    public int update(GroupActivityVO actVO) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(actVO);
        tx.commit();
        session.close();
        return 1;
    }

    @Override
    public int delete(Integer actId) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        GroupActivityVO vo = session.get(GroupActivityVO.class, actId);
        if (vo != null) {
            session.delete(vo);
            tx.commit();
            session.close();
            return 1;
        }
        session.close();
        return 0;
    }

    @Override
    public GroupActivityVO getByPK(Integer actId) {
        Session session = factory.openSession();
        GroupActivityVO vo = session.get(GroupActivityVO.class, actId);
        session.close();
        return vo;
    }

    @Override
    public List<GroupActivityVO> getAll() {
        Session session = factory.openSession();
        List<GroupActivityVO> list = session.createQuery("from GroupActivityVO", GroupActivityVO.class).list();
        session.close();
        return list;
    }
}