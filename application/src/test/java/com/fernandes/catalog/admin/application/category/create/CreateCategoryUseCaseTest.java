package com.fernandes.catalog.admin.application.category.create;

import com.fernandes.catalog.admin.domain.category.CategoryGateway;
import com.fernandes.catalog.admin.domain.exceptions.DomainException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateCategoryUseCaseTest {

    @InjectMocks
    private DefaultCreateCategoryUseCase defaultCreateCategoryUseCase;

    @Mock
    private CategoryGateway categoryGateway;


    public static final String EXPECTED_NAME = "movies";
    public static final String EXPECTED_DESCRIPTION = "most watched";
    public static final boolean EXPECTED_IS_ACTIVE = true;

    @Test
    public void givenAValidCommand_whenCallsCreateCategory_thenShouldReturnCategoryId(){

        final var  aCommand = CreateCategoryCommand.with(EXPECTED_NAME, EXPECTED_DESCRIPTION, EXPECTED_IS_ACTIVE);

        when(categoryGateway.create(any())).thenAnswer(returnsFirstArg());

        final var actualOutput = defaultCreateCategoryUseCase.execute(aCommand);

        assertNotNull(actualOutput);
        assertNotNull(actualOutput.id());

        verify(categoryGateway, times(1)).create(argThat(aCategory -> {
            return Objects.equals(EXPECTED_NAME, aCategory.getName())
                    && Objects.equals(EXPECTED_DESCRIPTION, aCategory.getDescription())
                    && Objects.equals(EXPECTED_IS_ACTIVE, aCategory.isActive())
                    && Objects.nonNull(aCategory.getId())
                    && Objects.nonNull(aCategory.getCreatedAt())
                    && Objects.nonNull(aCategory.getUpdatedAt())
                    && Objects.isNull(aCategory.getDeletedAt());
        }));
    }

    @Test
    public void givenAInvalidEmptyName_whenCallsCreateCategory_thenShouldReturnDomainException(){
        final var invalidName = "";
        final var expectedErrorMassage = "'name' should not be empty";

        final var  aCommand = CreateCategoryCommand.with(invalidName, EXPECTED_DESCRIPTION, EXPECTED_IS_ACTIVE);

        final var actualException =
                assertThrows(DomainException.class, () -> defaultCreateCategoryUseCase.execute(aCommand));

        assertEquals(expectedErrorMassage, actualException.getMessage());

        verify(categoryGateway, times(0)).create(any());
    }
}
