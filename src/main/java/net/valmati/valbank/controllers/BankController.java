package net.valmati.valbank.controllers;

import net.valmati.valbank.model.Bank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("api/v1.0/bank")
public class BankController {

    @GetMapping("greeting")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hello, " + name + "!";
    }

    @GetMapping
    public ResponseEntity<List<Bank>> getBanks() {
        var bank1 = new Bank(1, "Bank 1");
        var bank2 = new Bank(2, "Bank 2");

        var banks = new LinkedList<Bank>();
        banks.add(bank1);
        banks.add(bank2);

        return ResponseEntity.ok(banks);
    }

    @GetMapping("{id}")
    public ResponseEntity<Bank> getBank(@PathVariable long id) {
        var bank = new Bank(id, "Bank " + id);

        return ResponseEntity.ok(bank);
    }

    @PostMapping
    public ResponseEntity<Bank> addBank(@RequestBody Bank bank) {
        return ResponseEntity.ok(bank);
    }

    @PutMapping("{id}")
    public ResponseEntity<Bank> updateBank(@RequestBody Bank bank) {
        return ResponseEntity.ok(bank);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteBank(@PathVariable long id) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
