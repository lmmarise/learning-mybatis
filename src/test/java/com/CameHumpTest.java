package com;

import com.tsb.mbg.plugins.CameHumpInterceptor;
import org.junit.Test;

/**
 * <p>@program: new-mybatis </p>
 * <p>@description:  </p>
 * <p>@author: Arise Tang </p>
 * <p>@create: 2020-04-19 15:07 </p>
 **/
public class CameHumpTest {
    @Test
    public void underlineToCamelhump(){
        CameHumpInterceptor cameHump = new CameHumpInterceptor();
        String str = cameHump.underlineToCamelCase("sncjd_cjsdncjd");
        System.out.println(str);
        String str2 = cameHump.underlineToCamelCase("_Abc_de");
        System.out.println(str2);
        System.out.println(Character.toLowerCase('_'));
        System.out.println(cameHump.underlineToCamelCase("_ba1"));
    }
}
