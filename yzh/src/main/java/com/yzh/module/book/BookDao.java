package com.yzh.module.book;

import com.yzh.common.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yzh
 * @date 2018/4/10 22:56
 */
@Repository
public interface BookDao extends BaseDao<Book, String> {

    List<Book> listNewestBooks();

    List<Book> listhottestBooks();

}
