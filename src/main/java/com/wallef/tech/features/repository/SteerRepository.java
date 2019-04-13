package com.wallef.tech.features.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wallef.tech.features.model.Steer;

@Repository
public interface SteerRepository extends JpaRepository<Steer, Long> {
	
	@Query( value = "SELECT SUM(current_weight) FROM steer;", nativeQuery = true)
	Double findTotalWeight();
	
	@Query(value = "call SP01(:id)", nativeQuery = true)
	String find(@Param("id")Long id);
}
