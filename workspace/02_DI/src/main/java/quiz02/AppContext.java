package quiz02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {
/*
	@Bean
	public Speaker speaker() {
		return new Speaker(5);
	}
	
	@Bean
	public Tv tv() {
		return new Tv(5, speaker());
	}
*/
	
	// 선생님 풀이
	@Bean
	public Speaker speaker() {
		return new Speaker();
	}
	
	@Bean
	public Tv tv() {
		return new Tv();
	}
	
}
