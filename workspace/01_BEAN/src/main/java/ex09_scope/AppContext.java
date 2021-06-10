package ex09_scope;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppContext {
	
	@Bean
	@Scope(value="prototype") // @Scope("prototype")과 같다. 자바클래스에서의 애너테이션 추가로 Scope를 조정해준다.
	public Person person2() {
		return new Person("데이빗", 27);
	}
	
}
