package demo.work.work9.mapper.db1;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;


@Mapper
public interface TestMapper1 {

    @Select("select * from infoq_orders limit 0,10")
    List<Map<String,String>> getOrders();
}
