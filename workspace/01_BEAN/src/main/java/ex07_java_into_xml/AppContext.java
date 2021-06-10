package ex07_java_into_xml;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {
	
	@Bean(name="publisher2")
	public Publisher xyz() {
		return new Publisher("스프링출판사", "경기도 고양시");
	}
	
	@Bean(name="book2")
	public Book xyzg() {
		return new Book("나는 스프링이다", 30000, xyz());
	}
	
}
