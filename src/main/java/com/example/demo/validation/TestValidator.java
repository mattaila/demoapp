package com.example.demo.validation;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeanWrapper;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TestValidator implements ConstraintValidator<TestValidate, Object> {

        /** エラー時のメッセージ */
        String message;

        String column1;
        String column2;

        @Override
        public void initialize(TestValidate constraintAnnotation) {
            column1 = constraintAnnotation.column1();
            column2 = constraintAnnotation.column2();
            message = constraintAnnotation.message();
        }

        @Override
        public boolean isValid(Object value, ConstraintValidatorContext context) {
            BeanWrapper beanWrapper = new BeanWrapperImpl(value);
    	    String col1Value = (String) beanWrapper.getPropertyValue(column1);
            String col2Value = (String) beanWrapper.getPropertyValue(column2);

            if(("".equals(col1Value) && !"".equals(col2Value))
                || (!"".equals(col1Value) && "".equals(col2Value))) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("両方未入力か両方入力してください")
                    .addPropertyNode(column1)
                    .addConstraintViolation();
                context.buildConstraintViolationWithTemplate("両方未入力か両方入力してください")
                    .addPropertyNode(column2)
                    .addConstraintViolation();
                return false;
            }

            return true;
        }
}
