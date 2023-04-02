package com.fernandes.catalog.admin.domain.category;

import com.fernandes.catalog.admin.domain.validation.Error;
import com.fernandes.catalog.admin.domain.validation.ValidationHandler;
import com.fernandes.catalog.admin.domain.validation.Validator;

public class CategoryValidator extends Validator {

    private final Category category;
    protected CategoryValidator(final Category aCategory,final ValidationHandler aHandler) {
        super(aHandler);
        this.category = aCategory;
    }

    @Override
    public void validate() {
        if(this.category.getName()  == null){
            this.ValidationHandler().append(new Error("'name' should not be null"));
        }

        if(this.category.getName().isEmpty()){
            this.ValidationHandler().append(new Error("'name' should not be empty"));
        }

        if(this.category.getName().isBlank()){
            this.ValidationHandler().append(new Error("'name' should not be blank"));
        }

        if(this.category.getName().trim().length() <= 3){
            this.ValidationHandler().append(new Error("'name' must have more than 3 characters"));
        }
    }
}
