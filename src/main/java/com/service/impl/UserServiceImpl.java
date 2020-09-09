package com.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mybatis.mapper.UserMapper;
import com.mybatis.model.User;
import com.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Create user for db 服务实现类
 * </p>
 *
 * @author macro
 * @since 2020-09-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
