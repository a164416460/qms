package com.jrs_qms_llh.commons.dao;

import java.util.List;

/**
 * @param <T>
 * @author 黄浩文
 */
public interface BaseDao<T> {
    List<T> listAll();

    T getById(int id);

    int update(T t);

    int deleteById(int id);

    int save(T t);
}
