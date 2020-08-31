package com.tydic.jg.portal.utils.jpa;

import java.util.List;

public interface NativeQueryRepository {
    <T> List<T> nativeQuery(String sql, Class<T> clazz, Object ... parameters);
}
