package com.tsb.mbg;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: new-mybatis
 * @description: 读取MBG配置生成代码
 * @author: Arise Tang
 * @create: 2020-04-15 12:35
 **/
public class Generator {
    public static void main(String[] args) throws Exception{
        // MBG执行过程中的警告信息
        List<String> warnings = new ArrayList<>();
        // 当生成的代码重复时, 覆盖
        boolean overwrite = true;

        // xml解析转换为java实例
        InputStream is = Generator.class.getResourceAsStream("/generator/generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(is);
        is.close();

        // 回调
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);

        // 创建MBG
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);

        // 执行生成代码
        myBatisGenerator.generate(null);

        // 输出警告
        for (String warning : warnings) {
            System.out.println(warning);
        }
    }
}
