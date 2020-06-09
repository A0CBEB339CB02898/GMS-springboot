package com.gms;

import com.entity.trading;
import com.mapper.tradingMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@MapperScan("com.mapper")
public class GmsApplication {


    public static void main(String[] args) {
        SpringApplication.run(GmsApplication.class, args);
    }

}
