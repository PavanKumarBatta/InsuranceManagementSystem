package com.cts.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table("policy")
public class Policy {

	@Id
	@Column("policy_id")
	private Long policyId;
	
	@Column("name")
	private String name;
	
	@Column("premiumAccount")
	private Long premiumAmount;
	
	@Column("coverageDetails")
	private String coverageDetails;
	
	@Column("validityPeriod")
	private LocalDate validityPeriod;
}
