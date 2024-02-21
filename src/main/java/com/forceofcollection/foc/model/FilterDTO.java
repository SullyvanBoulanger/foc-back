package com.forceofcollection.foc.model;

import lombok.Data;

@Data
public class FilterDTO {
    private String columnName;
    private Object columnValue;
}
