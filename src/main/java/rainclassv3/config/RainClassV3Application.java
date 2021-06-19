package rainclassv3.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("rainclassv3")
@MapperScan("rainclassv3.mapper") //mybatis mapper接口扫描
public class RainClassV3Application {

    public static void main(String[] args) {
        SpringApplication.run(RainClassV3Application.class, args);
    }

}
