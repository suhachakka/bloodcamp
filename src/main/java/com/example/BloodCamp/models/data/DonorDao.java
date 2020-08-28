package com.example.BloodCamp.models.data;

import com.example.BloodCamp.models.Donor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface DonorDao extends CrudRepository<Donor, Integer> {
    Donor findByEmail(String email);
    Donor findByName(String name);

}