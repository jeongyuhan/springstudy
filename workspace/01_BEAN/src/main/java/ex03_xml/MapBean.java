package ex03_xml;

import java.util.Map;

public class MapBean {
	
	// field(property)
	private Map<String, String> map;
	
	// constructor
	
	// method
	public void info() {
		for(Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		/*
		  - Set을 이용하여 map에 저장된 데이터 꺼내는 방법
		  Set<String> keys = map.keySet();
		  for(String key : keys){
		  	System.out.println(key + " : " + map.get(key));
		  }
		 */
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	
	
	
	
}
