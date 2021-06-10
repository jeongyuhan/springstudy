package ex01_inject;

import javax.inject.Inject;

/*
	@Inject
	1. 객체의 타입(<bean class="">)이 일치하는 객체를 자동으로 주입한다.
	2. 필드, 생성자, setter를 대상으로 한다. 
*/

public class SelectListCommand {
	
	// 1. 필드를 이용해서 주입하기
	/*
	@Inject
	private Dao dao;
	*/
	
	// 2. 생성자를 이용해서 주입하기
	/*
	private Dao dao;
	
	@Inject
	public SelectListCommand(Dao dao) { // 매개변수 Dao dao에 주입된다.(주입되는 데이터는 app-context.xml에서 만들어진 dao Bean이다.)
		this.dao = dao;
	}
	*/
	
	// 3. setter를 이용해서 주입하기
	/*
	private Dao dao;
	
	// 강사님이 가장 선호하는 방법
	@Inject
	public void setDao(Dao dao) { // Inject가 적용되는 곳은 매개변수이다.(생성자와 같은 방식으로 실행된다.)(정상적으로 주입이 안되면 NullPointException 발생)
		this.dao = dao;
	}
	*/
	
	
	
	
	
	// Dao에 만든 selectList() 메소드 호출해보기
	
	// field
	private Dao dao;
	
	// setter
	@Inject
	public void setDao(Dao dao) {
		this.dao = dao;
	}
	
	public void execute() {
		dao.selectList(); // dao가 자동으로 주입되지 않았다면 NullPointerException 발생
	}
	
	
	
}
