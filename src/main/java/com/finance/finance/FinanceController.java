package com.finance.finance;

import com.finance.user.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        if (!appUser.getEmail().equalsIgnoreCase(finRecord.getUserEmail())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "User can add finRecord only for himself");
        }
        finRecord.setUser(appUser);
        financeService.save(finRecord);
        return "saved";
    }

    @GetMapping("/test")
    public String test() {
        return "test works!";
    }

    @GetMapping
    public List<FinRecord> getAll() {
        return financeService.getAll();
    }

    @GetMapping("/search")
    public List<FinRecord> getAllByType(@AuthenticationPrincipal AppUser appUser,
            @RequestParam(name = "type") String type) {
        if (type.equalsIgnoreCase(FinanceType.INCOME.name())) {
            return financeService.getAllByType(FinanceType.INCOME);
        }
        return financeService.getAllByType(FinanceType.EXPENSE);
    }

    @DeleteMapping("/{id}")
    public String delete(@AuthenticationPrincipal AppUser appUser,
            @PathVariable Long id) {
        if (!appUser.getFinances().contains(financeService.getById(id))) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "User can delete only his finRecord");
        }
        financeService.delete(id);
        return "deleted";
    }


}
