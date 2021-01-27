package com.beaninj.springboot_generator;

//import org.mybatis.generator.api.MyBatisGenerator;
//import org.mybatis.generator.config.Configuration;
//import org.mybatis.generator.config.xml.ConfigurationParser;
//import org.mybatis.generator.internal.DefaultShellCallback;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * mybatis的逆向生成
// */
//public class GeneratorSqlmap {
//    public static void main(String[] args) {
//        try {
//            List<String> warnings = new ArrayList<String>();
//            boolean overwrite = true;
//            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
//            File file = new File("generatorConfig.xml");
//            ConfigurationParser cp = new ConfigurationParser(warnings);
//            Configuration config = cp.parseConfiguration(file);
//            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
//            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
//            myBatisGenerator.generate(null);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
