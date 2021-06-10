package quiz04;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {
	
	@Bean
	public Lecture lecture2() {
		return new Lecture("스프링", "박교수");
	}
	
	@Bean
	public Student student2() {
		return new Student("엘리스", lecture2());
	}
	
}
