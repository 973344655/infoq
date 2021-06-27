package demo.work.week7.mapper;

import demo.work.week7.annotation.DataSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface TestMapper {

//    @DataSource(value = "db2")
    @Select("select * from infoq_orders limit 0,10")
    List<Map<String,String>> getOrders();
}
