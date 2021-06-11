package quiz02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class SpringMainClass {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
		
		Tv tv = ctx.getBean("tv", Tv.class);
		
		tv.chUp();
		tv.chDown();
		tv.volUp();
		tv.volDown();
		
		ctx.close();

	}

}
