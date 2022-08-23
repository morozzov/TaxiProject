package ru.taxi.orderprocessor.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.taxi.orderprocessor.entity.DriverEntity;

import java.util.UUID;

@Repository
public interface DriversOperationsRepository extends JpaRepository<DriverEntity, UUID> {
}
