package com.fernandes.catalog.admin.domain.validation.handler;

import com.fernandes.catalog.admin.domain.exceptions.DomainException;
import com.fernandes.catalog.admin.domain.validation.Error;
import com.fernandes.catalog.admin.domain.validation.ValidationHandler;

import java.util.List;

public class TrowsValidationHandler implements ValidationHandler {
    @Override
    public ValidationHandler append(final Error anError) {
        throw DomainException.with(anError);
    }

    @Override
    public ValidationHandler append(final ValidationHandler aHandler) {
        throw DomainException.with(aHandler.getErrors());
    }

    @Override
    public ValidationHandler validate(final Validation aValidation) {
        try{
            aValidation.validate();
        }  catch (Exception ex) {
            throw DomainException.with(new Error(ex.getMessage()));
        }

        return this;
    }

    @Override
    public List<Error> getErrors() {
        return List.of();
    }
}
