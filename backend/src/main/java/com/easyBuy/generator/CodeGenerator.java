package com.easyBuy.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import java.util.Collections;
import java.util.List;

/**
 * MyBatis-Plus Generator 一键生成
 * — parent 包：com.easyBuy.backend
 * — 覆盖旧文件
 * — 使用自定义 Controller 模板
 */
public class CodeGenerator {

    /** 需要生成的表名 */
    private static final List<String> TABLES = List.of(
            "auth_user_details",
            "auth_authority",
            "auth_user_authority",
            "addresses"
            /* 继续追加表名即可 */
    );

    public static void main(String[] args) {

        String projectDir = System.getProperty("user.dir");

        FastAutoGenerator.create(
                        "jdbc:mysql://192.168.1.157:3306/easyBuy?useUnicode=true&characterEncoding=utf8&useSSL=false",
                        "root",
                        "root")
                /* 全局配置 */
                .globalConfig(cfg -> cfg
                        .author("Joe Xuanqiao Zhang")
                        .commentDate("dd-MM-yyyy")
                        .enableSwagger()          // 需要 swagger 注解就保留
                        .outputDir(projectDir + "/src/main/java")
                        .disableOpenDir())
                /* 包配置 */
                .packageConfig(cfg -> cfg
                        .parent("com.easyBuy")    // 注意大小写
                        .moduleName("backend")    // → com.easyBuy.backend.*
                        .pathInfo(Collections.singletonMap(
                                OutputFile.xml,
                                projectDir + "/src/main/resources/mapper")))
                /* 策略配置 */
                .strategyConfig(cfg -> {
                    cfg.addInclude(TABLES);

                    cfg.entityBuilder()
                            .enableLombok()
                            .logicDeleteColumnName("is_deleted")
                            .idType(IdType.AUTO)       // 若是自增主键
                            .enableFileOverride();

                    cfg.mapperBuilder()
                            .enableBaseResultMap()
                            .enableBaseColumnList()
                            .enableFileOverride();

                    cfg.serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .enableFileOverride();

                    cfg.controllerBuilder()
                            .enableRestStyle()
                            .enableFileOverride();
                })
                /* 指定自定义模板，只改 Controller，其他用默认 */
                .templateConfig(t -> t.controller("/templates/controller.java.vm"))
                .execute();
    }
}