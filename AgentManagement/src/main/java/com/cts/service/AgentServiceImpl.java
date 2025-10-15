package com.cts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cts.model.Agent;
import com.cts.repository.AgentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {
	
	private final AgentRepository repo;

	@Override
	public ResponseEntity<Agent> save(Agent agent) {
		
		agent.setAgentId(null);
		Agent a=repo.save(agent);
		return new ResponseEntity<Agent>(a,HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Agent> update(Long id, String policy) {
		
		Optional<Agent>option=repo.findById(id);
		if(option.isPresent()) {
			Agent a=option.get();
			
			if(a.getAssignedPolicies()==null) {
				a.setAssignedPolicies(new ArrayList<>());
			}
			a.getAssignedPolicies().add(policy);
			repo.save(a);
			return ResponseEntity.ok(a);
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	public ResponseEntity<Agent> getById(Long id) {
		
		Optional<Agent>option= repo.findById(id);
		if(option.isPresent()) {
			return ResponseEntity.ok(option.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	public ResponseEntity<List<Agent>> getAll() {
		
		List<Agent> op=repo.findAll();
		
		if(op.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(op);
		}
	}	
	
	
}
