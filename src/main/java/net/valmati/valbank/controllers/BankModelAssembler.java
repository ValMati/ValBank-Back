package net.valmati.valbank.controllers;

import net.valmati.valbank.model.Bank;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BankModelAssembler implements RepresentationModelAssembler<Bank, EntityModel<Bank>> {

    @Override
    public EntityModel<Bank> toModel(Bank bank) {
        return EntityModel.of(bank,
                linkTo(methodOn(BankController.class).getBank(bank.getId())).withSelfRel(),
                linkTo(methodOn(BankController.class).getBanks()).withRel("banks"));
    }

    @Override
    public CollectionModel<EntityModel<Bank>> toCollectionModel(Iterable<? extends Bank> banks) {
        var collection = StreamSupport
                .stream(banks.spliterator(), false)
                .map(this::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(collection,
                linkTo(methodOn(BankController.class).getBanks()).withSelfRel());
    }
}
