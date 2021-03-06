package ex03_xml;

import java.util.Iterator;
import java.util.Set;

public class SetBean {
	
	// field(property)
	private Set<String> set;
	
	// constructor
	// 생략 디폴트 생성자 사용
	
	// method
	public void info() {
		/*
		Iterator<String> itr = set.iterator();
		while(itr.hasNext()) { // itr이 있는지 확인
			System.out.println(itr.next()); // itr을 하나씩 꺼내온다.
		}
		*/
		for(String s : set) {
			System.out.println(s);
		}
	}

	public Set<String> getSet() {
		return set;
	}

	public void setSet(Set<String> set) {
		this.set = set;
	}
	
	
	
}
