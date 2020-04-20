package com.tsb.mbg.plugins;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * <p>@program: new-mybatis </p>
 * <p>@description: MyBatis Map类型下画线key转小写驼峰形式 </p>
 * <p>@author: Arise Tang </p>
 * <p>@create: 2020-04-19 14:40 </p>
 **/
// 配置拦截器要拦截的接口的方法
@Intercepts(
        // 在同一个拦截器中同时拦截不同的接口和方法
        @Signature(
                type = ResultSetHandler.class,
                method = "handleResultSets",
                args = {Statement.class}
        )
)
@SuppressWarnings({"unchecked", "rawtypes"})
public class CameHumpInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 先执行被拦截的方法, 得到结果, 再对结果进行处理
        // 拦截器接口ResultSetHandler的handleResultSets方法的返回值为List类型
        List<Object> list = (List<Object>) invocation.proceed();
        for (Object object : list) {
            if (object instanceof Map) {
                processMap((Map) object);
            } else {
                break;
            }
        }
        return list;
    }

    /**
     * 处理Map类型
     * @param map
     */
    private void processMap(Map<String, Object> map) {
        HashSet<String> keySet = new HashSet<>(map.keySet());
        for (String key : keySet) {
            // "Abc" 或 "a_bc" 都进入
            if (key.charAt(0) >= 'A' && key.charAt(0) <= 'Z'
                    || key.indexOf('_') >= 0) {
                Object value = map.get(key);
                map.remove(key);
                map.put(underlineToCamelCase(key), value);
            }
        }
    }

    /**
     * 将下划线风格转换为驼峰风格
     * @param inputString
     * @return
     */
    public String underlineToCamelCase(String inputString) {
        StringBuilder sb = new StringBuilder();
        boolean nextUpperCase = false;
        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);
            if (c == '_') {
                if (sb.length() > 0) {
                    nextUpperCase = true;
                }
            }else {
                if (nextUpperCase) {
                    sb.append(Character.toUpperCase(c));
                    nextUpperCase = false;
                } else {
                    sb.append(Character.toLowerCase(c));
                }
            }
        }
        return sb.toString();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
