package com.yzh.module.order.linkinfo;

import com.yzh.common.base.BaseService;
import com.yzh.common.utils.ResultBean;
import com.yzh.common.utils.StringUtil;
import com.yzh.common.utils.UserUtil;
import com.yzh.common.utils.ValidateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author yzh
 * @date 2018/4/22 8:28
 */
@Service
@Transactional(readOnly = true)
public class LinkInfoService extends BaseService<LinkInfoDao, LinkInfo, String> {

    @Transactional(readOnly = false)
    public ResultBean add(LinkInfo linkInfo, HttpServletRequest request) {
        String address = linkInfo.getAddress();
        String name = linkInfo.getName();
        String mobile = linkInfo.getMobile();
        String zipCode = linkInfo.getZipCode();
        if (StringUtil.hasEmpty(address, name, mobile))
            return new ResultBean(ResultBean.CODE_PARAM_ERROR, "地址、联系人及联系方式不能为空");
        if (StringUtil.notEmpty(zipCode) && !ValidateUtil.checkZipCode(zipCode))
            return new ResultBean(ResultBean.CODE_PARAM_ERROR, "邮编格式错误");
        if (!ValidateUtil.checkPhone(mobile))
            return new ResultBean(ResultBean.CODE_PARAM_ERROR, "手机号格式错误");
        String onlineUserId = UserUtil.getOrdinaryUser(request).getId();
        int count = dao.getCount(onlineUserId);
        if (count >= 5)
            return new ResultBean(ResultBean.CODE_REQUEST_FORBIDDEN, "最多可添加5条");
        linkInfo.setUserId(onlineUserId);
        linkInfo.setCreateTime(new Date());
        return super.add(linkInfo);
    }

    @Transactional(readOnly = false)
    public ResultBean edit(LinkInfo linkInfo, HttpServletRequest request) {
        String id = linkInfo.getId();
        String address = linkInfo.getAddress();
        String name = linkInfo.getName();
        String mobile = linkInfo.getMobile();
        String zipCode = linkInfo.getZipCode();
        if (StringUtil.hasEmpty(address, name, mobile))
            return new ResultBean(ResultBean.CODE_PARAM_ERROR, "地址、联系人及联系方式不能为空");
        if (StringUtil.notEmpty(zipCode) && !ValidateUtil.checkZipCode(zipCode))
            return new ResultBean(ResultBean.CODE_PARAM_ERROR, "邮编格式错误");
        if (!ValidateUtil.checkPhone(mobile))
            return new ResultBean(ResultBean.CODE_PARAM_ERROR, "手机号格式错误");
        LinkInfo existLinkInfo = new LinkInfo();
        existLinkInfo.setUserId(UserUtil.getOrdinaryUser(request).getId());
        existLinkInfo.setId(id);
        existLinkInfo = dao.getByEntity(linkInfo);
        if (existLinkInfo == null)
            return new ResultBean(ResultBean.CODE_PARAM_ERROR);
        existLinkInfo.setAddress(address);
        existLinkInfo.setZipCode(zipCode);
        existLinkInfo.setName(name);
        existLinkInfo.setMobile(mobile);
        return super.edit(existLinkInfo);
    }

    @Transactional(readOnly = false)
    public ResultBean remove(String id, HttpServletRequest request) {
        LinkInfo linkInfo = new LinkInfo();
        linkInfo.setUserId(UserUtil.getOrdinaryUser(request).getId());
        linkInfo.setId(id);
        linkInfo = dao.getByEntity(linkInfo);
        if (linkInfo != null) {
            return super.remove(id);
        }
        return new ResultBean("删除成功");
    }

}
