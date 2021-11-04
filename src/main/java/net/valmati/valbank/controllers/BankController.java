package net.valmati.valbank.controllers;

import net.valmati.valbank.model.Bank;
import net.valmati.valbank.repository.BankRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1.0/bank")
public class BankController {

    private static final Logger logger = LoggerFactory.getLogger(BankController.class);

    private final BankRepository bankRepository;
    private final BankModelAssembler bankModelAssembler;

    BankController(BankRepository bankRepository, BankModelAssembler bankModelAssembler) {
        this.bankRepository = bankRepository;
        this.bankModelAssembler = bankModelAssembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Bank>> getBanks() {
        logger.info("Fetching banks.");

        var banks = bankRepository.findAll();

        return bankModelAssembler.toCollectionModel(banks);
    }

    @GetMapping("{id}")
    public EntityModel<Bank> getBank(@PathVariable long id) {
        logger.info("Fetching bank " + id);

        var bank = bankRepository.findById(id)
                .orElseThrow(() -> new BankNotFoundException(id));

        return bankModelAssembler.toModel(bank);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Bank>> addBank(@RequestBody Bank bank) {
        logger.info("Adding " + bank);

        bank = bankRepository.save(bank);

        var bankEntity = bankModelAssembler.toModel(bank);

        return ResponseEntity
                .created(bankEntity.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(bankEntity);
    }

    @PutMapping
    public ResponseEntity<EntityModel<Bank>> updateBank(@RequestBody Bank bank) {
        logger.info("Updating bank with id " + bank.getId());

        var updatedBank = bankRepository.findById(bank.getId())
                .map(tempBank -> {
                    tempBank.setName(bank.getName());
                    return bankRepository.save(tempBank);
                })
                .orElseThrow(() -> new BankNotFoundException(bank.getId()));

        var bankEntity = bankModelAssembler.toModel(updatedBank);

        return ResponseEntity.ok(bankEntity);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteBank(@PathVariable long id) {
        logger.info("Deleting bank " + id);

        var bank = bankRepository.findById(id)
                .orElseThrow(() -> new BankNotFoundException(id));

        bankRepository.delete(bank);

        return ResponseEntity.noContent().build();
    }
}
