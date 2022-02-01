package com.finance.finance;

import org.apache.catalina.LifecycleState;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FinanceRepository extends CrudRepository<FinRecord, Long> {
    Optional<FinRecord> findByName(String name);
    List<FinRecord> findAll();
    List<FinRecord> findAllByType(FinanceType type);
}
