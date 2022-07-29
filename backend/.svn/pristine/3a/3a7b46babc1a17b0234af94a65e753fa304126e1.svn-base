package cn.iocoder.yudao.module.campus.dal.mysql;

import cn.hutool.core.date.DateUtil;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.campus.controller.admin.vo.covid.CovidClosePageReqVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.covid.CovidCloseRespVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.covid.CovidCloseSchoolRespVO;
import cn.iocoder.yudao.module.campus.dal.dataobject.CovidCloseDO;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.poi.ss.formula.functions.T;

import java.util.Collection;
import java.util.List;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/7/8
 */
@Mapper
public interface CovidCloseMapper extends BaseMapperX<CovidCloseDO> {

    @Select({
            "select dept.id as dept_id, dept.`name` as class_name, ",
            "(select `name` from system_dept where dept.parent_id = id) as grade_name, ",
            "student.`name` as student_name, ",
            "student.student_no, covid.grade as grade, covid.create_time ",
            "from system_dept dept ",
            "inner join campus_covid covid on dept.id = covid.dept_id ",
            "inner join campus_student student on covid.student_id = student.id ",
            "${ew.customSqlSegment} limit #{pageStart},#{pageSize}"
    })
    List<CovidCloseRespVO> selectPage(@Param("ew") Wrapper<T> queryWrapper, @Param("pageStart")int pageStart, @Param("pageSize")int pageSize);

    @Select("select dept.parent_id as dept_id,\n" +
            "sum(IF(grade = 1, 1, 0)) as close_total,\n" +
            "sum(IF(grade = 2, 1, 0)) as sub_close_total from\n" +
            "(select dept.parent_id as grade_id, count(covid.id), covid.grade\n" +
            "from campus_covid covid\n" +
            "inner join system_dept dept on covid.dept_id = dept.id \n" +
            "${ew.customSqlSegment} group by grade_id, grade) as school \n" +
            "inner join system_dept dept on school.grade_id = dept.id GROUP BY dept_id")
    List<CovidCloseSchoolRespVO> selectSchoolPreviewList(@Param("ew") Wrapper wrapper);

    @Select("select dept.parent_id as dept_id, sum(total) as total from\n" +
            "(select dept.parent_id as school_id, count(student.id) as total\n" +
            "from campus_student student\n" +
            "inner join system_dept dept on student.dept_id = dept.id \n" +
            "${ew.customSqlSegment} group by dept.parent_id) as school \n" +
            "inner join system_dept dept on school.school_id = dept.id GROUP BY dept.parent_id")
    List<CovidCloseSchoolRespVO> selectSchoolPreviewTotalList(@Param("ew") Wrapper wrapper);

}
