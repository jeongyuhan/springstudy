package ex03_xml;

import java.util.List;

public class ListBean {
	
	// field(property)
	private List<String> list;
	
	// constructor
	// 없음. 디폴트 생성자 사용
	
	// method
	public void info() {
		for(int i = 0, length = list.size(); i < length; i++) {
			System.out.println((i) + 1 + "번째 요소 : " + list.get(i));
		}
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}
	
	
	
}
