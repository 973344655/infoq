package demo.week8.work6.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface TestMapper {


    @Insert("insert into t_order (id, customer_id) values (#{id}, #{customerId})")
    void insert(@Param("id") Integer id, @Param("customerId") Integer customerId);
}
