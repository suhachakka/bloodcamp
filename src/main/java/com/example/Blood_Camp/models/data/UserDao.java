package com.example.Blood_Camp.models.data;

import com.example.Blood_Camp.models.Donor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserDao extends CrudRepository<Donor, Integer> {

}
