package com.cts.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.model.Agent;
import com.cts.service.AgentService;

import lombok.RequiredArgsConstructor;

@RequestMapping("api/agent")
@RestController
@RequiredArgsConstructor
public class AgentController {

	private final AgentService service;
	
	@PostMapping("/save")
	public ResponseEntity<Agent> save(@RequestBody Agent agent) {
		
		return service.save(agent);
	}
	
	@PutMapping("/assignPolicy/{id}/{policy}")
	public ResponseEntity<Agent> update(@PathVariable Long id,@PathVariable String policy){
		
		return service.update(id,policy);
	}
	
	@GetMapping("/getAgentDetails/{id}")
	public ResponseEntity<Agent> getById(@PathVariable Long id){
		return service.getById(id);
	}
	
	@GetMapping("/getAgents")
	public ResponseEntity<List<Agent>> getAll(){
		return service.getAll();
	}
}
