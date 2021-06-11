package quiz02;

import org.springframework.beans.factory.annotation.Autowired;

public class Tv {
	
	/*
	private int ch; // 0 ~ 100
	private Speaker speaker;
	
	public Tv() {}
	
	@Autowired
	public Tv(int ch, Speaker speaker) {
		super();
		this.ch = ch;
		this.speaker = speaker;
	}


	public void chUp() {
		if(ch >= 0 && ch < 100) {
			System.out.println(ch = ch + 1);
		}
	}
	
	public void chDown() {
		if(ch > 0 && ch <= 100) {
			System.out.println(ch = ch - 1);
		}
	}
	
	public void volUp() {
		speaker.volUp();
	}
	
	public void volDown() {
		speaker.volDown();
	}


	public int getCh() {
		return ch;
	}

	public void setCh(int ch) {
		this.ch = ch;
	}


	public Speaker getSpeaker() {
		return speaker;
	}

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}
	*/
	
	// 선생님 풀이
	
	// field
	private int ch;
	private Speaker speaker;
	
	// constuctor
	
	// method
	public void volUp() {
		speaker.volUp();
	}
	
	public void volDown() {
		speaker.volDown();
	}
	
	public void chUp() {
		ch++;
		if(ch > 100) ch = 0;
		System.out.println("현재 채널 : " + ch);
	}
	
	public void chDown() {
		ch--;
		if(ch < 0) ch = 100;
		System.out.println("현재 채널 : " + ch);

	}
	
	@Autowired
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}
	
}
