package quiz02;

public class Calculator {
	
	// field(property)
	// 없다.
	
	// constructor
	// field(property)가 없기 때문에 디폴트 생성자 사용
	
	// method
	public int add(int a, int b) {
		return a + b;
	}
	public int subtract(int a, int b) {
		return a - b;
	}
	public int multiply(int a, int b) {
		return a * b;
	}
	public double divide(int a, int b) {
		return (double)a / b;
	}
	
	
}
