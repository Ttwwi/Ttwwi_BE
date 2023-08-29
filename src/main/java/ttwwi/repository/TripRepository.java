package ttwwi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ttwwi.entity.Trip;



public interface TripRepository extends JpaRepository<Trip, Long>
{
	
}