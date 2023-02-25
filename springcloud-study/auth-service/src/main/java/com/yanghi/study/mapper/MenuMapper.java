package com.yanghi.study.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yanghi.study.bean.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long userid);
}
