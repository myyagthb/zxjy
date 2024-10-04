package edu.hut.aiassistant.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hut.aiassistant.generator.domain.User;
import edu.hut.aiassistant.generator.service.UserService;
import edu.hut.aiassistant.generator.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 王海鑫
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2024-10-04 14:56:58
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




