package com.yzh.common.base;

import java.util.List;

public interface BaseDao<E extends BaseEntity, I> {

    void insert(E entity);

    /**
     * 返回主键
     *
     * @param entity
     */
    void insertEntity(E entity);

    void delete(I id);

    void update(E entity);

    E get(I id);

    E getByEntity(E entity);

    List<E> listByEntity(E entity);

    List<E> list();

}
