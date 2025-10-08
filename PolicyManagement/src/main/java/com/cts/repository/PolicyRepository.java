package com.cts.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import com.cts.model.Policy;

public interface PolicyRepository extends ReactiveCrudRepository<Policy,Long> {

}
