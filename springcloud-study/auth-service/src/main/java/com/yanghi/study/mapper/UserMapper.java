package com.yanghi.study.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yanghi.study.bean.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
