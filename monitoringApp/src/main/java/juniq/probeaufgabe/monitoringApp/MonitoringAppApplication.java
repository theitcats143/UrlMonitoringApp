package juniq.probeaufgabe.monitoringApp;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import juniq.probeaufgabe.monitoringApp.Repo.UrlRepo;
import juniq.probeaufgabe.monitoringApp.model.Url;

@EnableScheduling
@SpringBootApplication
public class MonitoringAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitoringAppApplication.class, args);
	}
/*
	   @Bean
	    CommandLineRunner init(UrlRepo urlRepo) {
	        return args -> {
	            Stream.of("Google", "Facebook", "juniq-It", "Faaaaacccbok", "testtt4583745").forEach(name -> {
	                Url url = new Url(name,"XX-XX-XX-XX","http://www." + name.toLowerCase() + ".com", null);
	                urlRepo.save(url);
	            });
	                urlRepo.findAll().forEach(System.out::println);
	        };
	    }
	    */
}
