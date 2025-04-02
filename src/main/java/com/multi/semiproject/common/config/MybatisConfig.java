package com.multi.semiproject.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages ={
        "com.multi.semiproject.board.model.dao",
        "com.multi.semiproject.member.model.dao",
        "com.multi.semiproject.district.model.dao",
        "com.multi.semiproject.search.model.dao"
})
public class MybatisConfig {
}
