package com.lupulus.cheers.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lupulus.cheers.repository.entity.BeerData;

@Repository
public interface BeerRepository extends JpaRepository<BeerData, Integer> {

	
	@Query(value = "SELECT e FROM BeerData as e  JOIN e.manufacturer m  WHERE  (lower(e.name) like %:inputString% ) " +
			" or (lower(e.description) like %:inputString% )  " +
            " or (lower(e.type) like %:inputString% )  " + 
           // " or ( ISNUMERIC(:inputString) and e.graduation=CAST(:inputString AS NUMERIC(10,2)) )  " + //FIXME: search by graduation
            " or (lower(m.name) like %:inputString% )  " + 
            " or (lower(m.nationality) like %:inputString% )  "
    )
    Page<BeerData> findAllByInputString(String inputString, Pageable pageable);
}
