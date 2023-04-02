package com.fernandes.catalog.admin.domain.category;

import com.fernandes.catalog.admin.domain.exceptions.DomainException;
import com.fernandes.catalog.admin.domain.validation.handler.TrowsValidationHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {

    @Test
    public void givenAValidParam_WhenCallNewCategory_ThenInstantiatedACategory(){
        final var expectedName = "movies";
        final var expectedDescription = "most watched";
        final var expectedIsActive = true;

        final var actualCategory =
                Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        assertNotNull(actualCategory);
        assertNotNull(actualCategory.getId());
        assertEquals(expectedName, actualCategory.getName());
        assertEquals(expectedDescription, actualCategory.getDescription());
        assertEquals(expectedIsActive, actualCategory.isActive());
        assertNotNull(actualCategory.getCreatedAt());
        assertNotNull(actualCategory.getUpdatedAt());
        assertNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAnInvalidNullName_WhenCallNewCategoryAndValidate_ThenReturnException(){
        final String expectedName = null;
        final var expectedDescription = "most watched";
        final var expectedIsActive = true;
        final var  expectedErrorMessage = "'name' should not be null";
        final var  expectedErrorCount = 1;

        final var actualCategory =
                Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        final var actualException =
                assertThrows(DomainException.class, () -> actualCategory.validate(new TrowsValidationHandler()));

        assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    public void givenAnInvalidEmptyName_WhenCallNewCategoryAndValidate_ThenReturnException(){
        final String expectedName = "";
        final var expectedDescription = "most watched";
        final var expectedIsActive = true;
        final var  expectedErrorMessage = "'name' should not be empty";
        final var  expectedErrorCount = 1;

        final var actualCategory =
                Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        final var actualException =
                assertThrows(DomainException.class, () -> actualCategory.validate(new TrowsValidationHandler()));

        assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    public void givenAnInvalidBlankName_WhenCallNewCategoryAndValidate_ThenReturnException(){
        final String expectedName = "   ";
        final var expectedDescription = "most watched";
        final var expectedIsActive = true;
        final var  expectedErrorMessage = "'name' should not be blank";
        final var  expectedErrorCount = 1;

        final var actualCategory =
                Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        final var actualException =
                assertThrows(DomainException.class, () -> actualCategory.validate(new TrowsValidationHandler()));

        assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    public void givenAnInvalidNameLengthLessThanTreeCharacters_WhenCallNewCategoryAndValidate_ThenReturnException(){
        final String expectedName = "go ";
        final var expectedDescription = "most watched";
        final var expectedIsActive = true;
        final var  expectedErrorMessage = "'name' must be between 3 and 255 characters";
        final var  expectedErrorCount = 1;

        final var actualCategory =
                Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        final var actualException =
                assertThrows(DomainException.class, () -> actualCategory.validate(new TrowsValidationHandler()));

        assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    public void givenAnInvalidNameLengthGreaterThan255Characters_WhenCallNewCategoryAndValidate_ThenReturnException(){
        final String expectedName = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
                                    + "Etiam massa urna, lacinia ac turpis quis, consectetur tincidunt ex."
                                    + " Nunc leo sem, convallis sed lacus quis, interdum pretium nisl. "
                                    + "Ut nibh mi, efficitur eget interdum eget, pharetra eget nisl."
                                    + "Donec maximus tempor metus.";
        final var expectedDescription = "most watched";
        final var expectedIsActive = true;
        final var  expectedErrorMessage = "'name' must be between 3 and 255 characters";
        final var  expectedErrorCount = 1;

        final var actualCategory =
                Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        final var actualException =
                assertThrows(DomainException.class, () -> actualCategory.validate(new TrowsValidationHandler()));

        assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    public void givenAValidEmptyDescription_WhenCallNewCategory_ThenInstantiatedACategory(){
        final var expectedName = "movies";
        final var expectedDescription = "   ";
        final var expectedIsActive = true;

        final var actualCategory =
                Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        assertDoesNotThrow(() -> actualCategory.validate(new TrowsValidationHandler()));
        assertNotNull(actualCategory);
        assertNotNull(actualCategory.getId());
        assertEquals(expectedName, actualCategory.getName());
        assertEquals(expectedDescription, actualCategory.getDescription());
        assertEquals(expectedIsActive, actualCategory.isActive());
        assertNotNull(actualCategory.getCreatedAt());
        assertNotNull(actualCategory.getUpdatedAt());
        assertNull(actualCategory.getDeletedAt());
    }


}
