package ex05_java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
	@Configuration 
	: bean(객체)을 만들어 주는 Java 클래스이다. 
*/
@Configuration
public class AppContext {

	// Bean을 만드는 메소드를 하나씩 만든다.
	// 메소드 => Bean 생성
	
	@Bean // Bean을 만드는 메소드
	// 반환타입 : Song => <bean class="Song">
	// 메소드명 : song => <bean id="song">
	public Song song() {
		Song s = new Song();
		s.setTitle("hello"); // <property name="title">
		s.setGenre("balad"); // <property name="genre">
		return s;
	}
	
	@Bean(name="singer") // <bean id="singer">
	// 반환타입 : Singer => <beam class="Singer">
	public Singer xyz() { // @Bean(name="singer")에서 이미 bean의 id가 결정되었기 때문에 메소드명으로 id를 나타내지 않는다.(메소드명은 아무거나 사용해도 된다.)
		return new Singer("adel", song()); // <constructor-arg>를 배치하는 방법
	}
	
	
}
