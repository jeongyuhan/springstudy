package quiz01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMainClass {

	public static void main(String[] args) {
		
		String resourcesLocations = "classpath:quiz01.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(resourcesLocations);
		
		// <bean id="person" class="quiz01.Person">
		Person p = ctx.getBean("person", Person.class);
		p.info();
		
		ctx.close();

	}

}
