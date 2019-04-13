package com.wallef.tech.features.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallef.tech.features.model.Steer;
import com.wallef.tech.features.model.dto.TotalWeight;
import com.wallef.tech.features.model.dto.WeightDTO;
import com.wallef.tech.features.repository.SteerRepository;

@RestController
@RequestMapping("/v1/sicop")
public class SteerController {

	@Autowired
	private SteerRepository repository;

	@GetMapping(value = "/cattle")
	public ResponseEntity<List<Steer>> findAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@PostMapping(value = "/cattle/steer", produces = "application/json")
	public ResponseEntity<Void> save(@RequestBody Steer steer) {

		if (steer == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		else
			repository.save(steer);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/cattle/steer/{id}", produces = "application/json")
	public ResponseEntity<Optional<Steer>> findSteer(@PathVariable("id")Long id) {
		Optional<Steer> steer = repository.findById(id);
		
		if (steer == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else 
			return ResponseEntity.ok(steer);
		
	}
	
	@DeleteMapping(value = "/cattle/steer/{id}")
	public ResponseEntity<Void> deleteSteer(@PathVariable("id")Long id) {
		
		Steer steer = repository.getOne(id);
		
		if(steer == null) 
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			repository.delete(steer);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(value = "cattle/steer/weight/{id}")
	public WeightDTO findWeight(@PathVariable(value = "id") Long id) {
		
		Steer steer = repository.getOne(id);
		
		WeightDTO dto = new WeightDTO();
		
		dto.setId(steer.getId());
		dto.setCurrentWeight(steer.getCurrentWeight());
		
		return dto;
	}
	
	@GetMapping(value = "cattle/steers/weight")
	public TotalWeight findTotalWeight() {
		
		TotalWeight totalWeight = new TotalWeight();
		totalWeight.setTotalWeight(repository.findTotalWeight());
		return totalWeight;
	}
	
	
	// Request using calling procedure
	@GetMapping("cattle/steers/{id}")
	public String find(@PathVariable(value = "id")Long id) {
		return repository.find(id);
	}
}
