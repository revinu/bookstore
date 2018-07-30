package com.yzh.common.base;

import com.yzh.common.utils.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public abstract class BaseService<D extends BaseDao<E, I>, E extends BaseEntity, I> {

    @Autowired
    protected D dao;

    @Transactional(readOnly = false)
    public ResultBean add(E entity) {
        dao.insert(entity);
        return new ResultBean("添加成功");
    }

    @Transactional(readOnly = false)
    public ResultBean remove(I id) {
        dao.delete(id);
        return new ResultBean("删除成功");
    }

    @Transactional(readOnly = false)
    public ResultBean edit(E entity) {
        dao.update(entity);
        return new ResultBean("修改成功");
    }

    @Transactional(readOnly = false)
    public void insert(E entity) {
        dao.insert(entity);
    }

    @Transactional(readOnly = false)
    public void insertEntity(E entity) {
        dao.insertEntity(entity);
    }

    @Transactional(readOnly = false)
    public void delete(I id) {
        dao.delete(id);
    }

    @Transactional(readOnly = false)
    public void update(E entity) {
        dao.update(entity);
    }

    public E get(I id) {
        return dao.get(id);
    }

    public E getByEntity(E entity) {
        return dao.getByEntity(entity);
    }

    public List<E> list() {
        return dao.list();
    }

    public List<E> listByEntity(E entity) {
        return dao.listByEntity(entity);
    }

}
