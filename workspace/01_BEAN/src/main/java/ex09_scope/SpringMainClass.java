package ex09_scope;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMainClass {

	public static void main(String[] args) {

		AbstractApplicationContext ctx = new GenericXmlApplicationContext("app-context9.xml");
		
		Person p1 = ctx.getBean("person1", Person.class);
		Person p2 = ctx.getBean("person1", Person.class);
		
		System.out.println(p1 == p2);
		
		p1.info();
		p2.info();
		// p1과 p2는 내용은 같지만 scope="prototype"에 의해 호출할 때마다 생성하기 때문에 만들어진 주소값이 달라지게 된다.
		ctx.close();

		
		AbstractApplicationContext ctx2 = new AnnotationConfigApplicationContext(AppContext.class);
		
		Person p3 = ctx2.getBean("person2", Person.class);
		Person p4 = ctx2.getBean("person2", Person.class);
		
		System.out.println(p3 == p4);
		
		p3.info();
		p4.info();
		
		ctx2.close();
	}

}
