package com.yzh.module.book;

import com.yzh.common.base.BaseService;
import com.yzh.common.sys.dict.Dict;
import com.yzh.common.sys.user.User;
import com.yzh.common.utils.DictUtil;
import com.yzh.common.utils.ResultBean;
import com.yzh.common.utils.StringUtil;
import com.yzh.common.utils.UserUtil;
import com.yzh.module.order.item.ItemDao;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author yzh
 * @date 2018/4/10 22:56
 */
@Service
@Transactional(readOnly = true)
public class BookService extends BaseService<BookDao, Book, String> {

    @Autowired
    private ItemDao itemDao;

    /**
     * 最新图书
     *
     * @return
     */
    public List<Book> listNewestBooks() {
        return dao.listNewestBooks();
    }

    /**
     * 热门图书
     *
     * @return
     */
    public List<Book> listhottestBooks() {
        return dao.listhottestBooks();
    }

    @Transactional(readOnly = false, rollbackFor = {Exception.class})
    public ResultBean add(Book book, MultipartFile image, HttpServletRequest request) {
        String name = book.getName();
        BigDecimal price = book.getPrice();
        Integer category = book.getCategory();
        if (StringUtil.empty(name) || category == null || price == null || new BigDecimal(0).compareTo(price) != -1) {
            return new ResultBean(ResultBean.CODE_PARAM_ERROR);
        }
        for (Dict dict : DictUtil.listByType(DictUtil.TYPE_CATEGORY)) {
            if (dict.getKey().compareTo(category) == 0) {
                String id = StringUtil.UUID();
                book.setId(id);
                book.setShelvesTime(new Date());
                User adminUser = UserUtil.getAdminUser(request);
                if (adminUser == null)
                    return new ResultBean(ResultBean.CODE_REQUEST_FORBIDDEN);
                ResultBean resultBean = uploadImg(image, request, adminUser.getId(), id);
                if (resultBean.getCode() == 200) {
                    book.setImage(resultBean.getMsg());
                    dao.insert(book);
                    return new ResultBean("发布成功");
                }
                return resultBean;
            }
        }
        return new ResultBean(ResultBean.CODE_PARAM_ERROR);
    }

    @Transactional(readOnly = false)
    public ResultBean edit(Book book) {
        String id = book.getId();
        String name = book.getName();
        BigDecimal price = book.getPrice();
        Integer category = book.getCategory();
        String author = book.getAuthor();
        Date publishDate = book.getPublishDate();
        Book oldBook;
        if (StringUtil.hasEmpty(id, name) || category == null || price == null
                || new BigDecimal(0).compareTo(price) != -1 || (oldBook = dao.get(id)) == null) {
            return new ResultBean(ResultBean.CODE_PARAM_ERROR);
        }
        for (Dict dict : DictUtil.listByType(DictUtil.TYPE_CATEGORY)) {
            if (dict.getKey().compareTo(category) == 0) {
                oldBook.setName(name);
                oldBook.setPrice(price);
                oldBook.setCategory(category);
                oldBook.setAuthor(author);
                oldBook.setPublishDate(publishDate);
                dao.update(oldBook);
                return new ResultBean("修改成功");
            }
        }
        return new ResultBean(ResultBean.CODE_PARAM_ERROR);
    }

    @Transactional(readOnly = false)
    public ResultBean updateImage(String id, MultipartFile image, HttpServletRequest request) {
        Book book = dao.get(id);
        String bookImage = book.getImage();
        if (book == null)
            return new ResultBean(ResultBean.CODE_PARAM_ERROR);
        User adminUser = UserUtil.getAdminUser(request);
        if (adminUser == null)
            return new ResultBean(ResultBean.CODE_REQUEST_FORBIDDEN);
        ResultBean resultBean = uploadImg(image, request, adminUser.getId(), id);
        if (resultBean.getCode() == 200) {
            book.setImage(resultBean.getMsg());
            dao.update(book);
            deleteImage(request, bookImage);
            return new ResultBean("修改成功");
        }
        return resultBean;
    }

    public ResultBean uploadImg(MultipartFile file, HttpServletRequest request, String userId, String bookId) {
        try {
            // 获取图片原始文件名
            String originalFilename = file.getOriginalFilename();
            // 获取上传图片的扩展名
            String extension = FilenameUtils.getExtension(originalFilename);
            if (StringUtil.empty(extension) || (!"bmp".equalsIgnoreCase(extension)
                    && !"jpg".equalsIgnoreCase(extension)
                    && !"png".equalsIgnoreCase(extension)
                    && !"jpeg".equalsIgnoreCase(extension))) {
                return new ResultBean(ResultBean.CODE_PARAM_ERROR, "格式错误,不支持");
            }
            long size = file.getSize();
            if (size > 5 * 1024 * 1024) {
                return new ResultBean(ResultBean.CODE_PARAM_ERROR, originalFilename + "图片最大不可超过5M");
            }
            // 图片上传的相对路径
            String path = "/image/book/" + userId + "/" + bookId;
            // 图片上传的绝对路径
            String src = request.getSession().getServletContext().getRealPath("/") + path;
            File imagePath = new File(src);
            if (!imagePath.exists()) {
                imagePath.mkdirs();
            }
            File image = new File(imagePath, originalFilename);
            image.createNewFile();
            // 上传图片
            file.transferTo(image);
            return new ResultBean(path + "/" + originalFilename);
        } catch (IOException e) {
            e.printStackTrace();
//          TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            if (e instanceof IOException) {
                return new ResultBean(ResultBean.CODE_UNKNOW_ERROR, "图片上传失败");
            }
            return new ResultBean(ResultBean.CODE_UNKNOW_ERROR, "系统错误");
        }
    }

    @Transactional(readOnly = false)
    public ResultBean delete(String id, HttpServletRequest request) {
        Book book = dao.get(id);
        if (book == null)
            return new ResultBean(ResultBean.CODE_REQUEST_FORBIDDEN);
        deleteImage(request, book.getImage());
        itemDao.deleteByBookId(id);
        return remove(id);
    }

    private void deleteImage(HttpServletRequest request, String bookImagePath) {
        String src = request.getSession().getServletContext().getRealPath("/") + bookImagePath;
        File image = new File(src);
        if (image.exists())
            image.delete();
        int i = src.lastIndexOf("/");
        if (i != -1)
            src = src.substring(0, i);
        image = new File(src);
        if (image.exists())
            image.delete();
    }

}
