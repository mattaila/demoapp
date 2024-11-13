package com.example.demo.validation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CodeDuplicationValidator implements ConstraintValidator<CodeDuplication, Object> {

    /** エラー時のメッセージ */
    String message;

    @Override
    public void initialize(CodeDuplication constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        String code1 = null;
        String code2 = null;
        try {
            Method method1 = value.getClass().getMethod("getItemDescription1");
            Method method2 = value.getClass().getMethod("getItemDescription2");

            code1 = (String) method1.invoke(value);
            code2 = (String) method2.invoke(value);
        } catch (IllegalAccessException | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        if(code1.equals(code2)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("同じコードは入力できません")
                .addPropertyNode("itemDescription1")
                .addConstraintViolation();
                context.buildConstraintViolationWithTemplate("同じコードは入力できません")
                .addPropertyNode("itemDescription2")
                .addConstraintViolation();
            return false;
        }
        return true;
    }
}
