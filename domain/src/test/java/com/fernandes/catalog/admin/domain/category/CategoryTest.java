package com.fernandes.catalog.admin.domain.category;

import com.fernandes.catalog.admin.domain.exceptions.DomainException;
import com.fernandes.catalog.admin.domain.validation.handler.TrowsValidationHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {

    public static final String EXPECTED_NAME = "movies";
    public static final String EXPECTED_DESCRIPTION = "most watched";
    public static final boolean EXPECTED_IS_ACTIVE = true;
    public static final int EXPECTED_ERROR_COUNT = 1;

    @Test
    public void givenAValidParam_WhenCallNewCategory_ThenInstantiatedACategory(){
        final var actualCategory =
                Category.newCategory(EXPECTED_NAME, EXPECTED_DESCRIPTION, EXPECTED_IS_ACTIVE);

        assertNotNull(actualCategory);
        assertNotNull(actualCategory.getId());
        assertEquals(EXPECTED_NAME, actualCategory.getName());
        assertEquals(EXPECTED_DESCRIPTION, actualCategory.getDescription());
        assertEquals(EXPECTED_IS_ACTIVE, actualCategory.isActive());
        assertNotNull(actualCategory.getCreatedAt());
        assertNotNull(actualCategory.getUpdatedAt());
        assertNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAnInvalidNullName_WhenCallNewCategoryAndValidate_ThenReturnException(){
        final String InvalidNullName = null;
        final var  expectedErrorMessage = "'name' should not be null";

        final var actualCategory =
                Category.newCategory(InvalidNullName, EXPECTED_DESCRIPTION, EXPECTED_IS_ACTIVE);
        final var actualException =
                assertThrows(DomainException.class, () -> actualCategory.validate(new TrowsValidationHandler()));

        assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        assertEquals(EXPECTED_ERROR_COUNT, actualException.getErrors().size());
    }

    @Test
    public void givenAnInvalidEmptyName_WhenCallNewCategoryAndValidate_ThenReturnException(){
        final String InvalidEmptyName = "";
        final var  expectedErrorMessage = "'name' should not be empty";

        final var actualCategory =
                Category.newCategory(InvalidEmptyName, EXPECTED_DESCRIPTION, EXPECTED_IS_ACTIVE);
        final var actualException =
                assertThrows(DomainException.class, () -> actualCategory.validate(new TrowsValidationHandler()));

        assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        assertEquals(EXPECTED_ERROR_COUNT, actualException.getErrors().size());
    }

    @Test
    public void givenAnInvalidBlankName_WhenCallNewCategoryAndValidate_ThenReturnException(){
        final String expectedName = "   ";
        final var  expectedErrorMessage = "'name' should not be blank";

        final var actualCategory =
                Category.newCategory(expectedName, EXPECTED_DESCRIPTION, EXPECTED_IS_ACTIVE);
        final var actualException =
                assertThrows(DomainException.class, () -> actualCategory.validate(new TrowsValidationHandler()));

        assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        assertEquals(EXPECTED_ERROR_COUNT, actualException.getErrors().size());
    }

    @Test
    public void givenAnInvalidNameLengthLessThanTreeCharacters_WhenCallNewCategoryAndValidate_ThenReturnException(){
        final String expectedName = "go ";
        final var  expectedErrorMessage = "'name' must be between 3 and 255 characters";

        final var actualCategory =
                Category.newCategory(expectedName, EXPECTED_DESCRIPTION, EXPECTED_IS_ACTIVE);
        final var actualException =
                assertThrows(DomainException.class, () -> actualCategory.validate(new TrowsValidationHandler()));

        assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        assertEquals(EXPECTED_ERROR_COUNT, actualException.getErrors().size());
    }

    @Test
    public void givenAnInvalidNameLengthGreaterThan255Characters_WhenCallNewCategoryAndValidate_ThenReturnException(){
        final String expectedName = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
                                    + "Etiam massa urna, lacinia ac turpis quis, consectetur tincidunt ex."
                                    + " Nunc leo sem, convallis sed lacus quis, interdum pretium nisl. "
                                    + "Ut nibh mi, efficitur eget interdum eget, pharetra eget nisl."
                                    + "Donec maximus tempor metus.";

        final var  expectedErrorMessage = "'name' must be between 3 and 255 characters";

        final var actualCategory =
                Category.newCategory(expectedName, EXPECTED_DESCRIPTION, EXPECTED_IS_ACTIVE);
        final var actualException =
                assertThrows(DomainException.class, () -> actualCategory.validate(new TrowsValidationHandler()));

        assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        assertEquals(EXPECTED_ERROR_COUNT, actualException.getErrors().size());
    }

    @Test
    public void givenAValidEmptyDescription_WhenCallNewCategory_ThenInstantiatedACategory(){
        final var expectedEmptyDescription = "   ";

        final var actualCategory =
                Category.newCategory(EXPECTED_NAME, expectedEmptyDescription, EXPECTED_IS_ACTIVE);

        assertDoesNotThrow(() -> actualCategory.validate(new TrowsValidationHandler()));
        assertNotNull(actualCategory);
        assertNotNull(actualCategory.getId());
        assertEquals(EXPECTED_NAME, actualCategory.getName());
        assertEquals(expectedEmptyDescription, actualCategory.getDescription());
        assertEquals(EXPECTED_IS_ACTIVE, actualCategory.isActive());
        assertNotNull(actualCategory.getCreatedAt());
        assertNotNull(actualCategory.getUpdatedAt());
        assertNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAValidFalseIsActive_WhenCallNewCategory_ThenInstantiatedACategory(){
        final var expectedFalseIsActive = false;

        final var actualCategory =
                Category.newCategory(EXPECTED_NAME, EXPECTED_DESCRIPTION, expectedFalseIsActive);

        assertDoesNotThrow(() -> actualCategory.validate(new TrowsValidationHandler()));
        assertNotNull(actualCategory);
        assertNotNull(actualCategory.getId());
        assertEquals(EXPECTED_NAME, actualCategory.getName());
        assertEquals(EXPECTED_DESCRIPTION, actualCategory.getDescription());
        assertEquals(expectedFalseIsActive, actualCategory.isActive());
        assertNotNull(actualCategory.getCreatedAt());
        assertNotNull(actualCategory.getUpdatedAt());
        assertNotNull(actualCategory.getDeletedAt());
    }
}
