package com.tydic.jg.portal.utils.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class NativeQueryRepositoryImpl implements NativeQueryRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public <T> List<T> nativeQuery(String sql, Class<T> clazz, Object ... parameters) {
        final Query nativeQuery = em.createNativeQuery(sql, clazz);
        for(int i=0; i< parameters.length; ++i){
            nativeQuery.setParameter(i+1, parameters[i]);
        }
        //noinspection unchecked
        return nativeQuery.getResultList();
    }
}
