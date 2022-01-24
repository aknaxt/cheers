package com.lupulus.cheers.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lupulus.cheers.repository.entity.*;

@Repository
public interface ManufacturerRepository extends JpaRepository<ManufacturerData, Integer> {

}
