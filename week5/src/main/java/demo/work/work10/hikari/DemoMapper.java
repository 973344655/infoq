package demo.work.work10.hikari;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface DemoMapper {
    @Select("select * from user where id=#{id}")
    Map<String,Object> getUserById(String id);
}
