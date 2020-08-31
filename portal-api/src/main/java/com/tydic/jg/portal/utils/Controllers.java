package com.tydic.jg.portal.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class Controllers {
    public static Pageable pageable(int page, int size) {
        page = page -1;
        if(page < 0 || size < 1){
            return Pageable.unpaged();
        }
        return PageRequest.of(page, size);
    }
    public static Pageable pageable(Integer page, Integer size) {
        return pageable(page == null ? 1 : page, size == null ? 0 : size);
    }

    public static Pageable pageable(int page, int size, Sort sort) {
        page = page -1;
        if(page < 0 || size < 1){
            return Pageable.unpaged();
        }
        return PageRequest.of(page, size, sort);
    }

    public static Pageable pageable(Integer page, Integer size, Sort sort) {
        return pageable(page == null ? 1 : page, size == null ? 0 : size, sort);
    }
}
