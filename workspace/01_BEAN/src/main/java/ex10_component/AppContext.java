package ex10_component;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan(basePackages="ex10_component")
// @ComponentScan(basePackages= {"ex10_component"}) // 여러개 등록할 때는 배열로 등록한다.
public class AppContext {
	
}
