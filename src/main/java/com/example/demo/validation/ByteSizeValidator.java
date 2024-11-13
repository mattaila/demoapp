package com.example.demo.validation;

import java.nio.charset.Charset;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ByteSizeValidator implements ConstraintValidator<ByteSize, String> {

    /** エラー時のメッセージ */
    String message;

    /** キャラセット */
    String charset;

    /** 最大値 */
    int max;

    /** 最小値 */
    int min;

    @Override
    public void initialize(ByteSize constraintAnnotation) {
        message = constraintAnnotation.message();
        charset = constraintAnnotation.charset();
        max = constraintAnnotation.max();
        min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        int length = value.getBytes(Charset.forName(charset)).length;
        return min <= length && length <= max;
    }
}
