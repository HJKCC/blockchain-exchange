# org.mybatis.generator.api.ShellRunner
主程序入口

# 继承 org.mybatis.generator.api.PluginAdapter 类
分布式开发的话，由 mybatis generator 插件生成的 实体类 和 *Example 类都必须要序列化
```
<plugin type="com.cc.mybatis.generator.SerializablePlugin"/>
```

# 继承 org.mybatis.generator.internal.DefaultCommentGenerator 类
自定义代码生成器中生成实体类的中文注释
```
<commentGenerator type="com.cc.mybatis.generator.MyCommentGenerator">
    <property name="author" value="chencheng0816@gmail.com"/>
    <property name="dateFormat" value="yyyy/MM/dd"/>
</commentGenerator>
```

# 修改 org.mybatis.generator.config.TableConfiguration 类
自定义<table>属性的默认值，默认不生成 Example类

# org.mybatis.generator.config.xml.MyBatisGeneratorConfigurationParser 的 parseTable 方法
解析并处理<table>各个属性

# org.mybatis.generator.api.IntrospectedTable
calculateJavaClientAttributes(): 自定义DAO文件名
```
if (stringHasValue(tableConfiguration.getMapperName())) {
    sb.append(tableConfiguration.getMapperName());
} else {
    if (stringHasValue(fullyQualifiedTable.getDomainObjectSubPackage())) {
        sb.append(fullyQualifiedTable.getDomainObjectSubPackage());
        sb.append('.');
    }

    // 自定义DAO文件名
    String domainObjectName = fullyQualifiedTable.getDomainObjectName();
    int domainObjectNameLength = domainObjectName.length();
    sb.append(domainObjectName.substring(0, domainObjectNameLength - 2));
    sb.append("DAO");
}
```
calculateMyBatis3XmlMapperFileName(): 自定义map xml文件名
```
if (stringHasValue(tableConfiguration.getMapperName())) {
    String mapperName = tableConfiguration.getMapperName();
    int ind = mapperName.lastIndexOf('.');
    if (ind == -1) {
        sb.append(mapperName);
    } else {
        sb.append(mapperName.substring(ind + 1));
    }
    sb.append(".xml"); //$NON-NLS-1$
} else {
    // 自定义map xml文件名
    String domainObjectName = fullyQualifiedTable.getDomainObjectName();
    int domainObjectNameLength = domainObjectName.length();
    sb.append(domainObjectName.substring(0, domainObjectNameLength - 2));
    sb.append("_SqlMap.xml"); //$NON-NLS-1$
}
```

# 打包
mvn clean install

# 项目引用
项目添加 mybatis-generator-plugins 包依赖后，修改 resources 路径下的 generatorConfig.xml （如exchange-dao）,Maven 执行命令（mvn mybatis-generator:generate）自动生成代码
