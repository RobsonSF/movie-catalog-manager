package com.fernandes.catalog.admin.domain.validation;

public abstract class Validator {

    private ValidationHandler handler;

    protected Validator(final ValidationHandler aHandler) {
        this.handler = aHandler;
    }

    public abstract void validate();

    protected ValidationHandler ValidationHandler(){
        return this.handler;
    }
}
