package com.finance.finance;

import com.finance.user.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FinanceService {

    @Autowired
    private FinanceRepository financeRepository;

    @Autowired
    private AppUserService appUserService;

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

    public FinRecord getById(Long id) {
        return financeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "FinRecord with such id is NOT exist!"));
    }


}
