package demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import javax.print.Doc;

@Configuration
@ComponentScan(basePackages = "demo")
@EnableAspectJAutoProxy

public class BeanConfig {

}