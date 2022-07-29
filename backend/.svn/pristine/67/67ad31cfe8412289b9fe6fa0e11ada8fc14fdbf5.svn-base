package cn.iocoder.yudao.module.campus.dal.mysql;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.campus.dal.dataobject.StudentHeightDO;
import cn.iocoder.yudao.module.campus.dal.dataobject.StudentWeightDO;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/7/27
 */
@Mapper
public interface StudentHeightMapper extends BaseMapperX<StudentHeightDO> {

    @Select("select student_id,height from campus_student_height \n" +
            "where id in \n" +
            "(select max(id) from \n" +
            "campus_student_height \n" +
            "${ew.customSqlSegment} \n" +
            "group by student_id)")
    List<StudentHeightDO> selectList(@Param("ew") Wrapper wrapper);

}
