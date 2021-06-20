package demo.work.week7.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface TestMapper {
    @Select("select * from infoq_orders limit 0,10")
    List<Map<String,String>> getOrders();
}
