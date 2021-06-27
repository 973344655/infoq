package demo.work.week7.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {


    @Override
    protected Object determineCurrentLookupKey() {
        //可以决定使用那个db
        return DatabaseContextHolder.getDbType();
    }
}
