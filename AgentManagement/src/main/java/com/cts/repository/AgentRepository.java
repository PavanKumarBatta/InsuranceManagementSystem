package com.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.model.Agent;

public interface AgentRepository extends JpaRepository<Agent, Long> {
    
}
