package com.fernandes.catalog.admin.application.category.create;

import com.fernandes.catalog.admin.domain.category.CategoryGateway;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CreateCategoryUseCaseTest {

    public static final String EXPECTED_NAME = "movies";
    public static final String EXPECTED_DESCRIPTION = "most watched";
    public static final boolean EXPECTED_IS_ACTIVE = true;

    @Test
    public void givenAValidCommand_whenCallsCreateCategory_shouldReturnCategoryId(){

        final var  aCommand = CreateCategoryCommand.with(EXPECTED_NAME, EXPECTED_DESCRIPTION, EXPECTED_IS_ACTIVE);

        final CategoryGateway categoryGateway = Mockito.mock(CategoryGateway.class);
        when(categoryGateway.create(any())).thenAnswer(returnsFirstArg());

        final var useCase = new DefaultCreateCategoryUseCase(categoryGateway);
        final var actualOutput = useCase.execute(aCommand);

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
}
