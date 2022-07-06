package livraria.imperial.book.validation;

import livraria.imperial.book.Category;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(FIELD)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = CategoryPatternValidation.class)
public @interface CategoryValidation {
    Class<Category> enumClass();
    String message() default "Deve ser igual a uma das categorias: \"{anyOf}\"";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
