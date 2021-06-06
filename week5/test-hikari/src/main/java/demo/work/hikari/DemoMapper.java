package demo.work.hikari;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface DemoMapper {
    @Select("select * from test_user where id=#{id}")
    Map<String,Object> getUserById(String id);
}
