package com.xworkz.profile.repo;

import com.xworkz.profile.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Collections;
import java.util.List;

@Repository
public class UserRepoImpl implements UserRepo {

    @Autowired
    private EntityManagerFactory emf;



    @Override
    public void save(UserEntity userEntity) {
        EntityManager em =null;
        EntityTransaction et = null;

        try {
            em = emf.createEntityManager();
            et = em.getTransaction();
            et.begin();
            em.persist(userEntity);
            et.commit();
        }catch (Exception e) {
            et.rollback();
        }finally {
            em.close();
        }
    }

    @Override
    public List<UserEntity> findAll() {
        EntityManager em = null;
        EntityTransaction et = null;

        try {
            em = emf.createEntityManager();
            et = em.getTransaction();
            et.begin();
            List list = em.createQuery("select u from UserEntity u").getResultList();
            et.commit();
            return list;
        }catch (Exception e) {
            et.rollback();
            return Collections.emptyList();
        }finally {
            em.close();
        }



    }
}
