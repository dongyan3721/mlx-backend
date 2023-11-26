package com.mlx.genshinimpactcookbook;

import com.mlx.genshinimpactcookbook.mapper.ConnTestMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GenshinimpactcookbookApplicationTests {
    @Autowired
    ConnTestMapper connTestMapper;

    @Test
    void contextLoads() {
        System.out.println(connTestMapper.selectClassById(1));
    }

}
