package com.cc.mybatis.generator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.DefaultCommentGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * 生成model中，字段增加注释
 *
 * @author chencheng0816@gmail.com
 */
public class MyCommentGenerator extends DefaultCommentGenerator {
    private Properties properties = new Properties();

    // 定义一个是否使用修改后的模式的标识
    private String author = "chencheng0816@gmail.com";
    private String dateFormat = "yyyy/MM/dd";
    private SimpleDateFormat dateFormatter;

    @Override
    public void addConfigurationProperties(Properties properties) {
        this.properties.putAll(properties);
        author = this.properties.getProperty("author", author);
        dateFormat = this.properties.getProperty("dateFormat", dateFormat);
        dateFormatter = new SimpleDateFormat(dateFormat);
    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
        innerClass.addJavaDocLine("/**");
        innerClass.addJavaDocLine(" * " + introspectedTable.getRemarks() + "基本操作方法");
        innerClass.addJavaDocLine(" *");
        innerClass.addJavaDocLine(" * @author " + author);
        innerClass.addJavaDocLine(" * @date " + dateFormatter.format(new Date()));
        innerClass.addJavaDocLine(" */");
    }

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * " + introspectedTable.getRemarks());  // 获取表注释
        topLevelClass.addJavaDocLine(" *");
        topLevelClass.addJavaDocLine(" * @author " + author);
        topLevelClass.addJavaDocLine(" * @date " + dateFormatter.format(new Date()));
        topLevelClass.addJavaDocLine(" */");
    }

    //设置实体类 属性注释
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if (introspectedColumn.getRemarks() != null && !"".equals(introspectedColumn.getRemarks())) {
            field.addJavaDocLine("/**");
            field.addJavaDocLine(" * " + introspectedColumn.getRemarks());  //填充 表结构中的备注信息
            field.addJavaDocLine(" */");
        }
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
        return;
    }

    // 去掉mapper原始注释
    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
        return;
    }

    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        return;
    }

    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        return;
    }

    // 去掉mapping原始注释
    @Override
    public void addComment(XmlElement xmlElement) {
        return;
    }

}
