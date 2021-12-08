package juniq.probeaufgabe.monitoringApp.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import juniq.probeaufgabe.monitoringApp.Repo.UrlRepo;
import juniq.probeaufgabe.monitoringApp.model.Url;
import juniq.probeaufgabe.monitoringApp.service.UrlController;


@Component
public class ScheduledTasks {
	
	private List<Url> urls =  new ArrayList<>();
	
	@Autowired
	private UrlRepo urlRepo;
	
	/*
	public ScheduledTasks(UrlRepo urlRepo) {
		this.urlRepo = urlRepo;
	};
	*/
	
	
	@PostConstruct
	public void readUrlFile() {
		
        Stream.of("Google", "Facebook", "juniq-It", "Faaaaacccbok", "testtt4583745").forEach(name -> {
            Url url = new Url(name,"XX-XX-XX-XX","http://www." + name.toLowerCase() + ".de", null);
            urlRepo.save(url);
        });
		
		/*
		try {
			Scanner scanner = new Scanner(new File("urls.txt"));
			while(scanner.hasNextLine()) {
				urls.add(scanner.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		*/
		urls = (List<Url>) urlRepo.findAll();
	}
	
	
	public String checkHttpStatusCode(int responseCode, String url) {
		String responseCodeStr = "";
		
		switch (responseCode) {
		case HttpURLConnection.HTTP_OK: System.out.println(url+" ---> UP");
			responseCodeStr = "UP"; break;
		case HttpURLConnection.HTTP_BAD_GATEWAY: System.out.println(url+" ---> BAD GATEWAY");
			responseCodeStr =  "BAD GATEWAY"; break;
		case HttpURLConnection.HTTP_BAD_REQUEST: System.out.println(url+" ---> BAD REQUEST");
			responseCodeStr =  "BAD_REQUEST"; break;
		case HttpURLConnection.HTTP_FORBIDDEN: System.out.println(url+" ---> FORBIDDEN");
			responseCodeStr =  "FORBIDDEN"; break;
		case HttpURLConnection.HTTP_NOT_FOUND: System.out.println(url+" ---> NOT FOUND/DOWN");
			responseCodeStr = "NOT FOUND/DOWN" ; break;
		case HttpURLConnection.HTTP_INTERNAL_ERROR: System.out.println(url+" ---> INTERNEL ERROR");
			responseCodeStr =  "NOT FOUND/DOWN"; break;
		default: System.out.println(url+" ---> UNKNOWN");
			responseCodeStr =  "UNKNOWN"; break;
		}	
		
		return responseCodeStr;
	}
	
	@Scheduled(fixedRate = 10000)
	public void reportCurrentTime() throws IOException {
		for(Url url:urls) {
			try {
				URL urlObj = new URL(url.getWebUrl().strip());
				HttpURLConnection huc = (HttpURLConnection) urlObj.openConnection();
				int responseCode = huc.getResponseCode();
				String responseCodeStr = checkHttpStatusCode(responseCode, url.getWebUrl());
				url.setStatus(responseCodeStr);
				urlRepo.save(url);
			} catch (Exception e) {
				System.out.println("Exception occurred!");
			}
			
		}
		System.out.println("****************** Scheduled Job Done *******************************");
	}

}
