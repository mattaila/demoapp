package com.example.demo.dto.form.item;

import java.util.Date;

import com.example.demo.validation.ByteSize;
import com.example.demo.validation.CodeDuplication;
import com.example.demo.validation.TestValidate;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@CodeDuplication
@TestValidate(column1 = "itemDescription1", column2 = "itemDescription2")
public class ItemSearchForm {
    
    @Size(max =10)
    String itemName;

    @ByteSize(max = 20)
    String itemDescription1;

    @ByteSize(max = 10)
    String itemDescription2;

    //Date createdAt;


}
