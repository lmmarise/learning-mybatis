package com.tsb.mbg.generator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static org.mybatis.generator.internal.util.StringUtility.isTrue;

/**
 * @program: new-mybatis
 * @description: 自定义mybatis generator的注释生成器
 *  MBG通过JDBC的DatabaseMetaData方式来获取数据库表和字段的备注信息,
 *  大多数的JDBC驱动并不支持, 常用数据库中MySQL支持, SQLServer不支持
 * @author: Arise Tang
 * @create: 2020-04-15 09:26
 **/
public class TSBCommentGenerator extends DefaultCommentGenerator {
    // 由于默认实现类中的可配参数都没有提供给子类可以访问的方法，这里要定义一遍
    private boolean suppressAllComments;    // 阻止生成所有注释
    private boolean addRemarkComments;      // 是否添加数据库表的备注信息

    // 设置用户配置的参数
    @Override
    public void addConfigurationProperties(Properties properties) {
        // 保证父类方法可以正常使用
        super.addConfigurationProperties(properties);
        // 获取suppressAllComments参数值
        suppressAllComments = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS));
        // 获取addRemarkComments参数值
        addRemarkComments = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_ADD_REMARK_COMMENTS));
    }

    // 给字段条件注释信息
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        // 阻止生成所有注释
        if (suppressAllComments) {
            return;
        }
        // 文档注释开始
        field.addJavaDocLine("/**");
        // 获取数组库字段的备注信息
        String remarks = introspectedColumn.getRemarks();
        // 根据参数和备注信息判断是否添加备注信息
        if (addRemarkComments && StringUtility.stringHasValue(remarks)) {
            String[] remarkLines = remarks.split(System.getProperty("line.separator"));
            for (String remarkLine : remarkLines) {
                field.addJavaDocLine(" * <p>" + remarkLine + " </p>");
            }
        }
        // 注释中保留数据库字段名
        field.addJavaDocLine(" * <p>" + introspectedColumn.getActualColumnName() + " </p>");
        field.addJavaDocLine(" */");
    }


    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
        addClassComment(innerClass, introspectedTable, false);
    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
        if (!this.suppressAllComments) {
            StringBuilder sb = new StringBuilder();
            innerClass.addJavaDocLine("/**");
            innerClass.addJavaDocLine(" * <p>@program: new-mybatis </p>");
            sb.append(" * <p>@description: ");
            sb.append(introspectedTable.getFullyQualifiedTable());
            sb.append(" </p>");
            innerClass.addJavaDocLine(sb.toString());
            this.addJavadocTag(innerClass, markAsDoNotDelete);
            innerClass.addJavaDocLine(" * <p>@author: Arise Tang </p>");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            innerClass.addJavaDocLine(" * <p>@create: " + df.format(new Date()) + " </p>");
            innerClass.addJavaDocLine(" **/");
        }
    }
}
