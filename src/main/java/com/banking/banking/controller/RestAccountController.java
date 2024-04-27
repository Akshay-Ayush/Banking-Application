package com.banking.banking.controller;

import com.banking.banking.dto.AccountDto;
import com.banking.banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class RestAccountController {

    private AccountService accountService;

    public RestAccountController(AccountService accountService) {
        this.accountService = accountService;
    }
// create account
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }
//  get account
//  http://localhost:8080/api/accounts/2
    @GetMapping("/{Id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long Id){
        AccountDto accountDto = accountService.getAccountById(Id);
        return ResponseEntity.ok(accountDto);
    }

//  http://localhost:8080/api/accounts/
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable long id,@RequestBody Map<String, Double> request){
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(id, amount);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

//  http://localhost:8080/api/accounts/2/withdraw
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable long id,@RequestBody Map<String, Double> request){
        double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id, amount);
        return ResponseEntity.ok(accountDto);
    }
//    http://localhost:8080/api/accounts/
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accounts = accountService.getAllAccount();
        return ResponseEntity.ok(accounts);
    }
//http://localhost:8080/api/accounts/
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted successfully");
    }
}