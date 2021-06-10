package ex10_component;

import org.springframework.stereotype.Component;


// Component("이름")을 주면 bean의 id가 된다.
@Component("bk") // Component에 id를 주는 방법 <bean id="bk" class="Book"/>
public class Book {
	
	public void info() {
		System.out.println("나는 책이다.");
	}
	
}
