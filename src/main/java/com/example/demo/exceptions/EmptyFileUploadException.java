package com.example.demo.exceptions;

/**
 * 空ファイルアップロード独自例外
 */
public class EmptyFileUploadException extends Exception {

    public EmptyFileUploadException(final String message) {
        super(message);
    }
}
