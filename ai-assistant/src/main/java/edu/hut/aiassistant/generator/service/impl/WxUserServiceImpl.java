package edu.hut.aiassistant.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hut.aiassistant.generator.domain.WxUser;
import edu.hut.aiassistant.generator.service.WxUserService;
import edu.hut.aiassistant.generator.mapper.WxUserMapper;
import org.springframework.stereotype.Service;

/**
* @author 王海鑫
* @description 针对表【t_wx_user(用户信息表)】的数据库操作Service实现
* @createDate 2024-10-09 21:27:57
*/
@Service
public class WxUserServiceImpl extends ServiceImpl<WxUserMapper, WxUser>
    implements WxUserService{

}




