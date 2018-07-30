package com.yzh.module.index;

import com.yzh.common.utils.ResultBean;
import com.yzh.module.book.Book;
import com.yzh.module.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author yzh
 * @date 2018/4/2 14:23
 */
@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class IndexController {

    @Autowired
    private IndexService indexService;
    @Autowired
    private BookService bookService;

    @RequestMapping("")
    public String home() {
        return "redirect:/index";
    }

    @RequestMapping("index")
    public String index(Model model) {
        List<Book> newestProductsList = bookService.listNewestBooks();
        List<Book> hottestProductsList = bookService.listhottestBooks();
        model.addAttribute("newestBooksList", newestProductsList);
        model.addAttribute("hottestBooksList", hottestProductsList);
        return "index/index";
    }

    @RequestMapping("login")
    @ResponseBody
    public ResultBean login(@RequestParam(required = true) String username, @RequestParam(required = true) String password,
                            String rememberMe, HttpServletRequest request, HttpServletResponse response) {
        return indexService.login(username, password, rememberMe, request, response);
    }

    @RequestMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        indexService.logout(request, response);
        return "redirect:/";
    }

    @RequestMapping("register")
    @ResponseBody
    public ResultBean register(@RequestParam(required = true) String username, @RequestParam(required = true) String password
            , @RequestParam(required = true) String rePassword, HttpServletRequest request, HttpServletResponse response) {
        return indexService.register(username, password, rePassword, request, response);
    }

    @RequestMapping("reset")
    @ResponseBody
    public ResultBean reset(@RequestParam(required = true) String oldPassword, @RequestParam(required = true) String password
            , @RequestParam(required = true) String rePassword, HttpServletRequest request, HttpServletResponse response) {
        return indexService.reset(oldPassword, password, rePassword, request, response);
    }

    @RequestMapping("search")
    public String search(Book book, Model model) {
        book.setIsShelves((byte) 1);
        book.getPage().setPageSize(12);
        List<Book> list = bookService.listByEntity(book);
        model.addAttribute("list", list);
        return "index/search";
    }

}
