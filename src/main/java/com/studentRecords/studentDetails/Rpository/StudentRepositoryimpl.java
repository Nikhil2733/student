package com.studentRecords.studentDetails.Rpository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StudentRepositoryimpl implements CoustomizedRepository {

    @Autowired
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public List<Object[]> findPagedResultForStudents(int offset, int limit) {
        Query query = em.createQuery("select s.id,s.name,s.birthDate,s.address,s.regNo from Student s order by s.id desc");
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        List<Object[]> resultList =  query.getResultList();
        return resultList;
    }
}
