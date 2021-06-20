package demo.work.week7.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;


import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@MapperScan(basePackages = "demo.work.week7.mapper")
public class DataSourceConfig {

    /**
     * master
     * @return
     */
    @Bean(name = "db1")
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * slave1
     * @return
     */
    @Bean(name = "db2")
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }
    /**
     * slave2
     * @return
     */
    @Bean(name = "db3")
    @ConfigurationProperties(prefix = "spring.datasource.db3")
    public DataSource thirdDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DataSource dynamicDataSource() {
        Map<Object, Object> dataSourceMap = new HashMap<>(4);
        dataSourceMap.put("db1", primaryDataSource());
        dataSourceMap.put("db2", secondaryDataSource());
        dataSourceMap.put("db3", thirdDataSource());

        //配置从库多个
        List<String> list = new ArrayList<>();
        list.add("db2");
        list.add("db3");

        DatabaseContextHolder.setDataSources(list);

        //设置动态数据源
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        dynamicDataSource.setDefaultTargetDataSource(primaryDataSource());

        return dynamicDataSource;
    }


}
