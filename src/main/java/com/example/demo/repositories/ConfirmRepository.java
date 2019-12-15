package com.example.demo.repositories;

import com.example.demo.model.Confirm;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ConfirmRepository extends CrudRepository<Confirm, Integer> {
    List<Confirm> findAllByEventEventId(Integer eventId);

    Optional<Confirm> findByEventEventIdAndUserUserId(Integer eventId, Integer userId);
}
