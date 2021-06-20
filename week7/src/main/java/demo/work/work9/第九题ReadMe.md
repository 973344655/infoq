

第九题(必做2)

## 地址

第九题地址 : https://github.com/973344655/infoq/tree/master/week7/work9

## 1.配置多个从库

```
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

```


## 2.@ReadOnly 负载均衡


```
  @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String dsKey = getDSAnnotation(joinPoint).value();

        //@ReadOnly不能使用主库
        if(MATSR.equals(dsKey)){
            dsKey = "";
        }
        if("".equals(dsKey)){
            //负载均衡，随机负载
            List<String> slaves = DatabaseContextHolder.getDataSources();
            int index = new Random().nextInt(slaves.size());
            dsKey = slaves.get(index);
        }
        DatabaseContextHolder.setDbType(dsKey);
        try{
            return joinPoint.proceed();
        }finally {
            DatabaseContextHolder.clearDbType();
        }
    }
```
