package com.finance.finance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class FinanceService {

    @Autowired
    private FinanceRepository financeRepository;

    public void save(FinRecord finRecord) {
        financeRepository.save(finRecord);
    }

    public List<FinRecord> getAll() {
        return financeRepository.findAll();
    }

    public void delete(Long id) {
        financeRepository.deleteById(id);
    }

    public List<FinRecord> getAllByType(FinanceType type){
        return financeRepository.findAllByType(type);
    }
}
