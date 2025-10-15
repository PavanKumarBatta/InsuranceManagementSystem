package com.cts.model;


import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data

@Entity
@Table
@Slf4j
public class Agent {
	
	public Agent() {
		log.info("This is default constructor");
	}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long agentId;

    private String name;

    private String contactInfo;

    @ElementCollection
    private List<String> assignedPolicies;
}
