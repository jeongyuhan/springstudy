package quiz05;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class SpringMainClass {

	public static void main(String[] args) {

		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
		
		HoneyMoon hm1 = ctx.getBean("honeyMoon1", HoneyMoon.class);
		HoneyMoon hm2 = ctx.getBean("honeyMoon2", HoneyMoon.class);
		
		hm1.info();
		hm2.info();
		
		ctx.close();
	}

}
