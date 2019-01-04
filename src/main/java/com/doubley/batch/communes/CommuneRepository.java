package com.doubley.batch.communes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doubley.batch.communes.bean.Commune;

@Repository
public interface CommuneRepository extends JpaRepository<Commune, Integer>{
	
}
