package quiz01;

public class Person {
	
	private String name;
	private Car car;
	
	public Person() {} // 생략가능하다.

	// method
	public void info() {
		System.out.println("이름: " + name);
		car.info();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
	
	
	
}
