package com.finance.finance;

import com.finance.user.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/finances")
public class FinanceController {

    @Autowired
    private FinanceService financeService;

    @PostMapping("/new")
    public String save(@AuthenticationPrincipal AppUser appUser,
            @Valid @RequestBody FinRecord finRecord) {
        finRecord.setUser(appUser);
        financeService.save(finRecord);
        return "saved";
    }

    @GetMapping
    public List<FinRecord> getAll() {
        return financeService.getAll();
    }

    @GetMapping("/search")
    public List<FinRecord> getAllByType(@RequestParam(name = "type") String type) {
        if (type.equalsIgnoreCase(FinanceType.INCOME.name())) {
            return financeService.getAllByType(FinanceType.INCOME);
        }
        return financeService.getAllByType(FinanceType.EXPENSE);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        financeService.delete(id);
    }


}
