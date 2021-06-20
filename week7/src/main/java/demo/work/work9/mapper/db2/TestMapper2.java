package demo.work.work9.mapper.db2;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;


@Mapper
public interface TestMapper2 {

    @Select("select * from infoq_orders limit 0,10")
    List<Map<String,String>> getOrders();
}
