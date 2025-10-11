package com.cts.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Table("claim")
@Builder
public class Claim {

	@Id
	@Column("claim_id")
	private Long claimId;
	
	@Column("policy_id")
	private Long policyId;
	
	@Column("customer_id")
	private Long customerId;
	
	@Column("claimAmount")
	private Long claimAmount;
	
	@Column("status")
	private String status;
}
