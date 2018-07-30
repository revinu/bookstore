package com.yzh.module.book;

import com.yzh.common.base.BaseController;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author yzh
 * @date 2018/4/10 22:57
 */
@Controller
@RequestMapping("book")
@EnableAutoConfiguration
public class BookController extends BaseController<BookService, Book, String> {

    @Override
    @RequestMapping({"", "list"})
    public String list(Book entity, Model model) {
        entity.setIsShelves((byte) 1);
        return super.list(entity, model);
    }

    @RequestMapping("${category}/{category}")
    public String category(@PathVariable Integer category, Book book, Model model) {
        book.setCategory(category);
        book.forbiddenPagination();
        book.setIsShelves((byte) 1);
        List<Book> list = service.listByEntity(book);
        model.addAttribute("list", list);
        return "module/book/category";
    }

    @RequestMapping("detail/{id}")
    public String detail(@PathVariable String id, Model model) {
        Book book = service.get(id);
        model.addAttribute("book", book);
        return "module/book/detail";
    }

}
