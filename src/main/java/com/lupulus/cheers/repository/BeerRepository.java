package com.lupulus.cheers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lupulus.cheers.repository.entity.BeerData;

@Repository
public interface BeerRepository extends JpaRepository<BeerData, Integer> {

}
