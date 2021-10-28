package net.valmati.valbank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Bank {

    private @Id
    @GeneratedValue
    long id;
    private String name;

    public Bank() {
    }

    public Bank(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void SetId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Bank)) {
            return false;
        }

        var bank = (Bank) obj;
        return Objects.equals(this.id, bank.id) &&
                Objects.equals(this.name, bank.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }

    @Override
    public String toString() {
        return "Bank{ id = " + id + ", name = '" + name + "'}";
    }
}
