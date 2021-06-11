package quiz01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class SpringMainClass {

	public static void main(String[] args) {

		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
		
		EngineerCalculator e = ctx.getBean("engineerCalculator", EngineerCalculator.class);
		
		e.add(5, 6);
		e.subtract(8, 2);
		e.multiply(7, 3);
		e.divide(9, 3);
		
		ctx.close();
		
	}

}
