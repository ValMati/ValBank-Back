package net.valmati.valbank.controllers;

public class BankNotFoundException extends RuntimeException {

    BankNotFoundException(long id) {
        super("Bank " + id + " not found");
    }
}
