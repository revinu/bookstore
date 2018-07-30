package com.yzh.admin.controller;

import com.yzh.common.base.BaseAdminController;
import com.yzh.common.utils.ResultBean;
import com.yzh.module.book.Book;
import com.yzh.module.book.BookService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author yzh
 * @date 2018/4/18 17:17
 */
@Controller
@RequestMapping("${adminPath}/book")
@EnableAutoConfiguration
public class AdminBookController extends BaseAdminController<BookService, Book, String> {

    @Override
    @RequestMapping({"", "list"})
    protected String list(Book book, Model model) {
        book.setIsShelves((byte) -1);
        List list = service.listByEntity(book);
        model.addAttribute("list", list);
        return viewPreview + "List";
    }

    @RequestMapping("add")
    @ResponseBody
    public ResultBean add(Book book, @RequestParam(required = true) MultipartFile file, HttpServletRequest request) {
        return service.add(book, file, request);
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultBean delete(@RequestParam(required = true) String id, HttpServletRequest request) {
        return service.delete(id, request);
    }

    @RequestMapping("edit")
    @ResponseBody
    public ResultBean edit(Book book) {
        return service.edit(book);
    }

    @RequestMapping("updateImage")
    @ResponseBody
    public ResultBean updateImage(@RequestParam(required = true) String id, @RequestParam(required = true) MultipartFile file
            , HttpServletRequest request) {
        return service.updateImage(id, file, request);
    }

}
