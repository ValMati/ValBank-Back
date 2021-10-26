package net.valmati.valbank.model;

public class Bank {

    private final long id;
    private final String name;

    public Bank(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
