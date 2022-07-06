package livraria.imperial.book.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CategoryPatternValidation implements ConstraintValidator<CategoryValidation, CharSequence> {

    private List<String> acceptedValues;

    @Override
    public void initialize(CategoryValidation constraintAnnotation) {
        acceptedValues = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
                .map(category -> category.getName().toUpperCase())
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null) {
            return true;
        }
        return acceptedValues.contains(value.toString().toUpperCase());
    }
}
