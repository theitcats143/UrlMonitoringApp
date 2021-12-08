package juniq.probeaufgabe.monitoringApp.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import juniq.probeaufgabe.monitoringApp.Repo.UrlRepo;
import juniq.probeaufgabe.monitoringApp.model.Url;

@RestController
//@RequestMapping("/url")
@CrossOrigin(origins = "http://localhost:4200")
public class UrlController {

	private UrlRepo urlRepo;
	
	public UrlController(UrlRepo urlRepo) {
		this.urlRepo = urlRepo;
	}
	
	@GetMapping("/urls")
	public List<Url> getAllUrls() {
		return (List<Url>) urlRepo.findAll();
	}
	
	@PostMapping("/add")
	public void addUrl(@RequestBody Url url) {
		urlRepo.save(url);
	}
	
	/*
	@GetMapping("/find/{id}")
	public ResponseEntity<Url> getUrlById(@PathVariable("id") Long id) {
		Url url = urlRepo.findUrlById(id);
		return new ResponseEntity<>(url, HttpStatus.OK);
	}


	
	@PutMapping("/update")
	public ResponseEntity<Url> updateUrl(@RequestBody Url url) {
		Url updatedUrl = urlRepo.updateUrl(url);
		return new ResponseEntity<>(updatedUrl, HttpStatus.OK);
	}
	
	/*
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Url> deleteUrl(@PathVariable Long id) {
		urlRepo.deleteUrl(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	*/
}
