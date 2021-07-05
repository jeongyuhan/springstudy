package com.koreait.integration1.batch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SearchJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		// 1분마다 실행될 random검색을 통한 작업 구성
		
		String[] query = {"코미디", "공포", "멜로", "드라마", "SF"};
		
		BufferedReader br = null;
		BufferedWriter bw = null;
		int idx = 0;
		String apiURL = "";
		try {
			bw = new BufferedWriter(new FileWriter("error.txt"));
			for(int i = 0; i < query.length; i++) {
				idx = (int)(Math.random()*query.length);
				apiURL = "http://localhost:9090/integration1/selectQuery.do?column=TITLE&query=" + query[i];
			}
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String resultMap = "";
			String line = null;
			while((line = br.readLine()) != null) {
				resultMap += line;
			}	
			if(resultMap.isEmpty()) {
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
 		
		
	}

}
