package com.example.mybatisplus;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 参考:
 * https://baomidou.com/pages/981406/#%E6%95%B0%E6%8D%AE%E5%BA%93%E9%85%8D%E7%BD%AE-datasourceconfig
 */
@Slf4j
@SpringBootTest
public class GeneratorTest {

    //输出目录
    public String outputDir = "D://autocode";
    //包名
    private String moduleName = "netdisk";
    //表名
    private List<String> tables = Arrays.asList(new String[]{"netdisk_file_segment", "netdisk_file_storage"});

    private String url = "jdbc:mysql://localhost:3306/tiangou?zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=utf-8&useSSL=false&allowMultiQueries=true&serverTimezone=Asia/Shanghai";

    private String username = "root";

    private String password = "root";

    @Test
    public void generatorTest() {

        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("orange") // 设置作者
                            .enableSwagger() //开启 swagger 模式
                            .outputDir(outputDir);// 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.orangecrde.naga.persistent.mybatis") // 设置父包名
                            .moduleName("") // 设置父包模块名
                            .entity("model." + moduleName)
                            .mapper("mapper." + moduleName)
                            .pathInfo(Collections.singletonMap(OutputFile.xml, outputDir));
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables) // 设置需要生成的表名
                            .addTablePrefix("t_", "c_")
                            .entityBuilder().enableLombok().formatFileName("%sModel").enableTableFieldAnnotation()
                            .mapperBuilder().enableBaseColumnList().enableBaseResultMap(); // 设置过滤表前缀
                })
                //  .templateEngine(new FreemarkerTemplateEngine()) 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
        log.info("===> mybatis-plus generator execute success! ");

    }
}
