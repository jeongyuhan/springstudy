package quiz05;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@ImportResource("quiz05.xml")

@Configuration
public class AppContext {
	
	@Bean
	public Person husband1() {
		return new Person("브라이언", "남자");
	}

	@Bean
	public Person wife1() {
		return new Person("제니", "여자");
	}
	
	@Bean
	public HoneyMoon honeyMoon1() {
		return new HoneyMoon("하와이", husband1(), wife1());
	}
}
