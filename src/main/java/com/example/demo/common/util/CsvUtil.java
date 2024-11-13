package com.example.demo.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.exceptions.EmptyFileUploadException;
import com.example.demo.exceptions.MaxDataCountException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

/**
 * CSV関連Utilityクラス
 */
public class CsvUtil {

    /**
     * アップロードファイル最大データ件数
     */
    @Value("${spring.servlet.multipart.max-file-size}")
    private static long maxUploadDataCount;

    /**
     * CSVファイルをT型のリストに変換する
     * @param <T>
     * @param file
     * @param clazz
     * @return
     * @throws IOException
     * @throws EmptyFileUploadException 
     * @throws MaxDataCountException 
     */
    public static <T> List<T> convertToObjectList(MultipartFile file, Class<T> clazz) 
        throws IOException, EmptyFileUploadException, MaxDataCountException {
        List<T> list = new ArrayList<T>();

        try(InputStream inputStream = file.getInputStream()) {
            CsvMapper csvMapper = new CsvMapper();
            CsvSchema schema = csvMapper.schemaFor(clazz)
                                    .withHeader()
                                    .withColumnSeparator(',')
                                    .withQuoteChar('"')
                                    .withNullValue("");
            list = csvMapper.readerFor(clazz).with(schema).<T>readValues(inputStream).readAll();

        } catch(IOException e) {
            e.printStackTrace();
            throw e;
        }

        if(list.isEmpty()) {
            //空のファイルがアップロードされた場合
            throw new EmptyFileUploadException("null");
        }

        if(list.size() > maxUploadDataCount) {
            //最大データ件数超えのファイルがアップロードされた場合
            throw new MaxDataCountException("");
        }
 
        return list;
    }
}
