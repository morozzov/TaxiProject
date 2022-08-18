package ru.taxi.orderprocessor.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.taxi.orderprocessor.entity.CarEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarOperationsRepository extends JpaRepository<CarEntity, UUID> {

    Optional<CarEntity> findByNumber(String number);
}
