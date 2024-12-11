package edu.hut.aiassistant.custom.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import edu.hut.aiassistant.custom.service.UserServiceCustom;
import edu.hut.aiassistant.enums.RespEnum;
import edu.hut.aiassistant.enums.SystemExceptionEnum;
import edu.hut.aiassistant.enums.UserRoleEnum;
import edu.hut.aiassistant.exception.SystemException;
import edu.hut.aiassistant.generator.domain.User;
import edu.hut.aiassistant.generator.mapper.UserMapper;
import edu.hut.aiassistant.req.UserInfoReq;
import edu.hut.aiassistant.req.UserReq;
import edu.hut.aiassistant.resp.R;
import edu.hut.aiassistant.resp.UserInfoResp;
import edu.hut.aiassistant.resp.UserResp;
import edu.hut.aiassistant.utils.MD5Util;
import edu.hut.aiassistant.utils.MobileUtil;
import edu.hut.aiassistant.utils.EmailUtil;
import edu.hut.aiassistant.utils.MyJWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class UserServiceCustomImpl implements UserServiceCustom {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceCustomImpl.class);


    @Autowired
    private UserMapper userMapper;


    @Override
    public R register(UserReq userReq) {
        //验证手机号是否正确
        if (!MobileUtil.isValidMobile(userReq.getMobile())) {
            return new R(RespEnum.FAIL.getCode(), "手机号错误",null);
        }
        //验证邮箱是否正确
        if (!EmailUtil.isValidEmail(userReq.getEmail())) {
            return new R(RespEnum.FAIL.getCode(), "邮箱错误",null);
        }        
        //判断手机号是否注册
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getMobile,userReq.getMobile().trim());
        User user = userMapper.selectOne(queryWrapper);
        if(user != null){
            return new R(RespEnum.FAIL.getCode(), "该手机号已注册",null);
        }
        //判断邮箱是否注册
        queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail,userReq.getEmail().trim());
        user = userMapper.selectOne(queryWrapper);
        if(user != null){
            return new R(RespEnum.FAIL.getCode(), "该邮箱已注册",null);
        }        
        DateTime now = DateTime.now();
        User newUser = new User();
        //设置电话号码
        newUser.setMobile(userReq.getMobile());
        newUser.setEmail(userReq.getEmail());
        //设置加密后密码字符串
        newUser.setPassword(MD5Util.MD5(userReq.getPassword()));
        newUser.setRole(userReq.getRole());//设置用户角色字段
        newUser.setCreateTime(now);
        newUser.setUpdateTime(now);
        //默认用户角色为学生
        //newUser.setRole(UserRoleEnum.STUDENT.getRoleCode());
        userMapper.insert(newUser);
        return new R(RespEnum.SUCCESS.getCode(), "注册成功！",null);
    }

    @Override
    public R login(UserReq req) {
        //验证手机号是否正确
        if (!MobileUtil.isValidMobile(req.getMobile())) {
            return new R(RespEnum.FAIL.getCode(), "手机号错误",null);
        }

        //判断账号和密码是否正确
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getMobile,req.getMobile().trim())
                .eq(User::getPassword,MD5Util.MD5(req.getPassword()));
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            return new R(RespEnum.FAIL.getCode(), "账号或密码错误",null);
        }

        String jwtString = MyJWTUtil.objectToJwtString(user);
        UserResp userResp = new UserResp();
        userResp.setUserId(user.getUserId());
        userResp.setRole(user.getRole());
        userResp.setToken(jwtString);
        return new R(RespEnum.SUCCESS.getCode(), "登录成功",userResp);
    }


    /**
     * 更新或者保存用户信息
     * @param userInfoReq
     * @return
     */
    @Override
    public R saveOrUpdateUserInfo(UserInfoReq userInfoReq) {
        LOGGER.info("正在更新的用户信息对象：{}",userInfoReq);
        if (ObjectUtils.isEmpty(userInfoReq) || userInfoReq.getUserId() == null){
            return new R(RespEnum.FAIL.getCode(), "参数异常",null);
        }
        User saveOrUpdateUser = BeanUtil.copyProperties(userInfoReq, User.class);
        saveOrUpdateUser.setUpdateTime(DateTime.now());
        //更新用户信息
        userMapper.updateById(saveOrUpdateUser);
        return new R(RespEnum.SUCCESS.getCode(),"更新成功！" ,null);
    }

    /**
     * 根据用户ID查询用户信息
     * @param userId
     * @return
     */
    @Override
    public R getUserInfoById(Long userId) {
        LOGGER.info("正在查询用户ID为：{}的用户信息",userId);
        if (userId == null){
            return new R(RespEnum.FAIL.getCode(), "用户ID不能为空",null);
        }
        User user = userMapper.selectById(userId);
        if(user == null){
            return new R(RespEnum.SUCCESS.getCode(), "未找到当前用户",null);
        }
        UserInfoResp userInfoResp = BeanUtil.copyProperties(user, UserInfoResp.class);
        return new R(RespEnum.SUCCESS.getCode(), "查询成功",userInfoResp);
    }
}
