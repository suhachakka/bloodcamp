package com.example.BloodCamp.models.data;

import com.example.BloodCamp.models.Event;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface EventDao extends CrudRepository <Event,Integer> {
   List<Event> findEventsOrderById(int id);
}
