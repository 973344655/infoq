package demo.work.week7.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {


    @Override
    protected Object determineCurrentLookupKey() {
        return DatabaseContextHolder.getDbType();//可以决定使用那个db
    }
}
