package com.tsb.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * (Country)实体类
 *
 * @author makejava
 * @since 2020-04-11 21:15:13
 */
@Data
@ToString
public class Country implements Serializable {
    private static final long serialVersionUID = -93311811439384220L;
    
    private Integer id;
    
    private String countryname;
    
    private String countrycode;
}