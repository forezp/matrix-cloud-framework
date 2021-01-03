package io.github.forezp;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"io.github.forezp.module.ns"})

public class McfAmdinApplication {

    public static void main(String[] args) {
        SpringApplication.run(McfAmdinApplication.class, args);
    }

}

