package juniq.probeaufgabe.monitoringApp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import juniq.probeaufgabe.monitoringApp.Repo.UrlRepo;
import juniq.probeaufgabe.monitoringApp.model.Url;

@Service
@Transactional
public class UrlService {
	private final UrlRepo urlRepo;
	
	@Autowired
	public UrlService(UrlRepo urlRepo) {
		this.urlRepo =  urlRepo;
	}
	/*
	public Url addUrl(Url url) {
		return urlRepo.save(url);
	}

	public List<Url> findAllUrl() {
		return urlRepo.findAll();
	}
	
	public Url updateUrl(Url url) {
		return urlRepo.save(url);
	}
	
	public Url findUrlById(Long id) {
	 	return urlRepo.findUrlById(id);
	    }
				
	public void deleteUrlById(Long id) { 
		
	}
	
	public void deleteUrl(Long id) {
		urlRepo.deleteUrlById(id);
	}
	*/
}
