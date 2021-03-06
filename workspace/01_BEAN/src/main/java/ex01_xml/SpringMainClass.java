package ex01_xml;

import javax.print.attribute.standard.MediaSize.Engineering;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMainClass {

	public static void main(String[] args) {
		
		// spring bean configuration file 에 정의된 <bean>을 생성하는 클래스
		// GenericXmlApplicationContext
		
		// AbstractApplicationContext는 GenericXmlApplicationContext의 슈퍼클래스
		
		String resourceLocations = "classpath:app-context1.xml"; // classpath = src/main/resources 이다.
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations);
		
		// 이미 spring은 app-context1.xml에 정의된 Bean을 모두 생성해서 가지고 있다.
		// 개발자는 필요한 Bean을 getBean() 메소드를 이용하여 가져다 사용한다.
		
		// 기존   : 개발자가 직접 Bean을 생성한다.
		// 스프링 : 스프링이 미리 Bean을 생성해 둔다.
		//  IoC   : Inversion of Control, 제어의 역전(제어권을 개발자에서 스프링이 가져간다.)

		// <bean id="engineerCalculator1" class="ex01_xml.EngineerCalculator">
		EngineerCalculator e1 = ctx.getBean("engineerCalculator1", EngineerCalculator.class);
		e1.add();
		e1.subtract();
		e1.multiply();
		e1.divide();
		
		// <bean id="engineerCalculator2" class="ex01_xml.EngineerCalculator">
		EngineerCalculator e2 = ctx.getBean("engineerCalculator2", EngineerCalculator.class);
		e2.add();
		e2.subtract();
		e2.multiply();
		e2.divide();
		
		ctx.close();
		
	}

}
