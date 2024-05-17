package dev.swellington.forumhub.infra.validators;

import dev.swellington.forumhub.infra.annotations.AtLeastOneFieldNotNull;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

public class AtLeastOneFieldNotNullValidator implements ConstraintValidator<AtLeastOneFieldNotNull, Object> {

    private String[] fieldNames;

    @Override
    public void initialize(AtLeastOneFieldNotNull constraintAnnotation) {
        this.fieldNames = constraintAnnotation.fieldNames();
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        try {
            for (String fieldName : fieldNames) {
                Field field = obj.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                Object value = field.get(obj);
                if (value != null) {
                    return true;
                }
            }
        } catch (Exception e) {
            // Log the exception or handle it as necessary
            return false;
        }
        return false;
    }
}


