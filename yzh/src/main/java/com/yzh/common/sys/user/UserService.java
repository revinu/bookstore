package com.yzh.common.sys.user;

import com.yzh.common.base.BaseService;
import com.yzh.common.utils.ResultBean;
import com.yzh.common.utils.StringUtil;
import com.yzh.common.utils.ValidateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yzh
 * @date 2018/4/2 10:42
 */
@Service
@Transactional(readOnly = true)
public class UserService extends BaseService<UserDao, User, String> {

    @Transactional(readOnly = false)
    public ResultBean add(User user, String rePassword) {
        String username = user.getUsername();
        String password = user.getPassword();
        if (StringUtil.hasEmpty(username, password))
            return new ResultBean(ResultBean.CODE_PARAM_ERROR);
        if (!password.equals(rePassword))
            return new ResultBean(ResultBean.CODE_PARAM_ERROR, "两次密码不一致");
        if (!ValidateUtil.checkPhone(user.getMobile()))
            return new ResultBean(ResultBean.CODE_PARAM_ERROR, "手机号格式错误");
        dao.insert(user);
        return new ResultBean("添加成功");
    }

    @Transactional(readOnly = false)
    public ResultBean edit(User user, String rePassword) {
        String id = user.getId();
        String username = user.getUsername();
        String password = user.getPassword();
        if (StringUtil.hasEmpty(id, username, password))
            return new ResultBean(ResultBean.CODE_PARAM_ERROR);
        User oldUser = dao.get(id);
        if (oldUser == null)
            return new ResultBean(ResultBean.CODE_NOT_FOUND, ResultBean.MSG_PARAM_ERROR);
        if (!username.equals(oldUser.getUsername())) {
            User existUser = dao.getByUsername(username);
            if (existUser != null)
                return new ResultBean(ResultBean.CODE_REQUEST_FORBIDDEN, "该用户名已被注册");
        }
        if (password != null && !password.equals(rePassword))
            return new ResultBean(ResultBean.CODE_PARAM_ERROR, "两次密码不一致");
        if (!ValidateUtil.checkPhone(user.getMobile()))
            return new ResultBean(ResultBean.CODE_PARAM_ERROR, "手机号格式错误");
        dao.update(user);
        return new ResultBean("修改成功");
    }

}
