package edu.hut.aiassistant.custom.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import edu.hut.aiassistant.custom.service.UserServiceCustom;
import edu.hut.aiassistant.enums.RespEnum;
import edu.hut.aiassistant.generator.domain.WxUser;
import edu.hut.aiassistant.generator.mapper.WxUserMapper;
import edu.hut.aiassistant.req.UserReq;
import edu.hut.aiassistant.resp.R;
import edu.hut.aiassistant.resp.UserResp;
import edu.hut.aiassistant.utils.MD5Util;
import edu.hut.aiassistant.utils.MobileUtil;
import edu.hut.aiassistant.utils.MyJWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceCustomImpl implements UserServiceCustom {


    @Autowired
    private WxUserMapper wxUserMapper;


    @Override
    public R register(UserReq req) {
        //验证手机号是否正确
        if (!MobileUtil.isValidMobile(req.getMobile())) {
            return new R(RespEnum.FAIL.getCode(), "手机号错误",null);
        }
        //判断手机号是否注册
        LambdaQueryWrapper<WxUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WxUser::getMobile,req.getMobile().trim());
        WxUser user = wxUserMapper.selectOne(queryWrapper);
        if(user != null){
            return new R(RespEnum.FAIL.getCode(), "该手机号已注册",null);
        }
        DateTime now = DateTime.now();
        WxUser wxUser = new WxUser();
        //设置电话号码
        wxUser.setMobile(req.getMobile());
        //设置加密后密码字符串
        wxUser.setPassword(MD5Util.MD5(req.getPassword()));
        wxUser.setCreateTime(now);
        wxUser.setUpdateTime(now);
        wxUserMapper.insert(wxUser);
        return new R(RespEnum.SUCCESS.getCode(), "注册成功！",null);
    }

    @Override
    public R login(UserReq req) {
        //验证手机号是否正确
        if (!MobileUtil.isValidMobile(req.getMobile())) {
            return new R(RespEnum.FAIL.getCode(), "手机号错误",null);
        }

        //判断账号和密码是否正确
        LambdaQueryWrapper<WxUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WxUser::getMobile,req.getMobile().trim())
                .eq(WxUser::getPassword,MD5Util.MD5(req.getPassword()));
        WxUser user = wxUserMapper.selectOne(queryWrapper);
        if (user == null) {
            return new R(RespEnum.FAIL.getCode(), "账号或密码错误",null);
        }
        UserResp userResp = BeanUtil.copyProperties(user, UserResp.class);
        String jwtString = MyJWTUtil.objectToJwtString(userResp);
        return new R(RespEnum.SUCCESS.getCode(), "登录成功",jwtString);
    }
}
