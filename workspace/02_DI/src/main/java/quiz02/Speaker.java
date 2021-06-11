package quiz02;

public class Speaker {
	/*
	private int vol; // 0 ~ 100 사이
	
	public Speaker() {}
	
	
	public Speaker(int vol) {
		super();
		this.vol = vol;
	}


	public void volUp() {
		if(vol >= 0 && vol <100) {
			System.out.println(vol = vol + 1);
		}
	}
	
	public void volDown() {
		if(vol > 0 && vol <= 100) {
			System.out.println(vol = vol - 1);
		}		
	}
	*/
	
	// 선생님 풀이
	
	// field
	private int vol;
	
	// constructor
	
	// method
	public void volUp() {
		vol++;
		if(vol > 100) vol = 100;
		System.out.println("현재 볼륨 : " + vol);
	}
	
	public void volDown() {
		vol--;
		if(vol < 0) vol = 0;
		System.out.println("현재 볼륨 : " + vol);
	}
	
}
