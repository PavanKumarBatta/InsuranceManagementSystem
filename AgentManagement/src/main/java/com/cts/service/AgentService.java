package com.cts.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cts.model.Agent;

public interface AgentService {
	
	ResponseEntity<Agent> save(Agent agent);

	ResponseEntity<Agent> update(Long id, String policy);

	ResponseEntity<Agent> getById(Long id);

	ResponseEntity<List<Agent>> getAll();
	
}
