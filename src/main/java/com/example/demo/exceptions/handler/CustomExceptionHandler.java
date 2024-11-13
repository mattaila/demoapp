package com.example.demo.exceptions.handler;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 例外ハンドリングクラス
 */
@ControllerAdvice
public class CustomExceptionHandler {
    @Autowired
    private MessageSource msgSource;

    /**
     * アップロードファイルサイズ上限
     */
    @Value("${spring.servlet.multipart.max-file-size}")
    private String maxUploadFileSize;
     
    /**
     * ファイルアップロードサイズ上限超過時の処理
     * エラーページへ遷移させる。
     * @return
     */
    @ExceptionHandler(MultipartException.class)
    public String handleMaxFileSizeExceededException(RedirectAttributes redirectAttributes) {

        String msg = msgSource.getMessage("message.common.upload.filesizeexceeded", new String[] {maxUploadFileSize}, Locale.JAPAN);
        redirectAttributes.addFlashAttribute("message", msg);
        return "redirect:/";
    }
}
