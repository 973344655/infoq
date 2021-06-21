package demo.work10.week7.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface TestMapper {
    @Insert("INSERT into infoq_user (username, password) VALUES ('name1','pwd1')")
    void insert();

    @Select("select * from infoq_orders limit 0,10")
    List<Map<String,String>> getOrders();
}
