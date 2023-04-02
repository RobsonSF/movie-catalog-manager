package com.fernandes.catalog.admin.domain.category;

import com.fernandes.catalog.admin.domain.validation.Error;
import com.fernandes.catalog.admin.domain.validation.ValidationHandler;
import com.fernandes.catalog.admin.domain.validation.Validator;

public class CategoryValidator extends Validator {

    public static final int NAME_MIN_LENGTH = 3;
    public static final int NAME_MAX_LENGTH = 255;
    private final Category category;
    protected CategoryValidator(final Category aCategory,final ValidationHandler aHandler) {
        super(aHandler);
        this.category = aCategory;
    }

    @Override
    public void validate() {
        checkNameConstraints();
    }

    private void checkNameConstraints() {
        final var name = this.category.getName();
        if(name == null){
            this.ValidationHandler().append(new Error("'name' should not be null"));
            return;
        }

        if(name.isEmpty()){
            this.ValidationHandler().append(new Error("'name' should not be empty"));
            return;
        }

        if(name.isBlank()){
            this.ValidationHandler().append(new Error("'name' should not be blank"));
            return;
        }

        final var length = name.trim().length();
        if(length < NAME_MIN_LENGTH || length > NAME_MAX_LENGTH){
            this.ValidationHandler().append(new Error("'name' must be between 3 and 255 characters"));
        }
    }
}
