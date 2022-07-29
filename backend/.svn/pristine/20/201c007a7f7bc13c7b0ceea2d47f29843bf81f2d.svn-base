package cn.iocoder.yudao.module.campus.dal.mysql;

import cn.hutool.core.date.DateUtil;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.campus.controller.admin.vo.temprecord.TempRecordPreviewRespVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.temprecord.TempRecordRespVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.temprecord.TempRecordSchoolPreviewRespVO;
import cn.iocoder.yudao.module.campus.dal.dataobject.TempRecordDO;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/6/16
 */
@Mapper
public interface TempRecordMapper extends BaseMapperX<TempRecordDO> {

    @Select("select student.id as student_id, student.`name` as student_name, \n" +
            "student.student_no, student.sex, \n" +
            "(select `name` from system_dept where dept.parent_id = id) as campus_grade_name,\n" +
            "dept.id as dept_id, dept.`name` as campus_class_name,\n" +
            "record.temperature,record.create_time from campus_temp_record record\n" +
            "inner join campus_student student on record.student_id = student.id\n" +
            "inner join system_dept dept on student.dept_id = dept.id\n" +
            "${ew.customSqlSegment} limit #{pageStart}, #{pageSize}")
    List<TempRecordRespVO> selectPage(@Param("ew") Wrapper wrapper, @Param("pageStart") int pageStart, @Param("pageSize") int pageSize);

    @Select("select count(record.id)\n" +
            "from campus_temp_record record\n" +
            "inner join campus_student student on record.student_id = student.id\n" +
            "inner join system_dept dept on student.dept_id = dept.id\n" +
            "${ew.customSqlSegment}")
    long selectCountByDeptIds(@Param("ew") Wrapper wrapper);

    /**
     * student 和 temp_record 两张表的联合查询
     *
     * @param studentIds
     * @return
     */
    default List<TempRecordDO> selectStudentTempPage(List<Long> studentIds) {
        LambdaQueryWrapperX<TempRecordDO> queryWrapper = new LambdaQueryWrapperX<TempRecordDO>();
        queryWrapper.inIfPresent(TempRecordDO::getStudentId, studentIds)
                .apply("date_format(create_time, '%Y-%m-%d') = '" + DateUtil.format(new Date(), "yyyy-MM-dd") + "'");
        return selectList(queryWrapper);
    }

    default List<TempRecordDO> selectTempRecords(Long studentId, String measureTime) {
        LambdaQueryWrapperX<TempRecordDO> queryWrapper = new LambdaQueryWrapperX<TempRecordDO>();
        queryWrapper.eqIfPresent(TempRecordDO::getStudentId, studentId);
        queryWrapper.apply("date_format(create_time, '%Y-%m-%d') = '" + measureTime + "'");
        return selectList(queryWrapper);
    }

    @Select("select dept_id, \n" +
            "sum(IF(fever_no > 0, 1, 0)) as fever, \n" +
            "sum(IF(fever_no = 0,IF(normal_no > 0,1,0),0)) as normal,\n" +
            "sum(IF(fever_no = 0,IF(normal_no = 0,IF(abnormal_no > 0,1,0),0),0)) as abnormal\n" +
            "from\n" +
            "(select student_id, dept_id,\n" +
            "sum(case when body = 'fever' then 1 else 0 end) as fever_no,\n" +
            "sum(case when body = 'normal' then 1 else 0 end) as normal_no,\n" +
            "sum(case when body = 'abnormal' then 1 else 0 end) as abnormal_no\n" +
            " from \n" +
            "(select student_id, dept.parent_id as dept_id, \n" +
            "IF(record.temperature < 37.5, IF(record.temperature < 34.5, 'abnormal', 'normal'), 'fever') as body\n" +
            "FROM\n" +
            "campus_temp_record AS record\n" +
            "INNER JOIN campus_student AS student ON record.student_id = student.id\n" +
            "INNER JOIN system_dept AS dept ON student.dept_id = dept.id\n" +
            "${ew.customSqlSegment}) as student_temp GROUP BY student_id) \n" +
            "as dept_temp GROUP BY dept_id\n")
    List<TempRecordPreviewRespVO> selectGradePreview(@Param("ew") Wrapper wrapper);

    @Select("select dept_id, \n" +
            "sum(IF(fever_no > 0, 1, 0)) as fever, \n" +
            "sum(IF(fever_no = 0,IF(normal_no > 0,1,0),0)) as normal,\n" +
            "sum(IF(fever_no = 0,IF(normal_no = 0,IF(abnormal_no > 0,1,0),0),0)) as abnormal\n" +
            "from\n" +
            "(select student_id, dept_id,\n" +
            "sum(case when body = 'fever' then 1 else 0 end) as fever_no,\n" +
            "sum(case when body = 'normal' then 1 else 0 end) as normal_no,\n" +
            "sum(case when body = 'abnormal' then 1 else 0 end) as abnormal_no\n" +
            " from \n" +
            "(select student_id, dept.id as dept_id, \n" +
            "IF(record.temperature < 37.5, IF(record.temperature < 34.5, 'abnormal', 'normal'), 'fever') as body\n" +
            "FROM\n" +
            "campus_temp_record AS record\n" +
            "INNER JOIN campus_student AS student ON record.student_id = student.id\n" +
            "INNER JOIN system_dept AS dept ON student.dept_id = dept.id \n" +
            "${ew.customSqlSegment}) as student_temp GROUP BY student_id) \n" +
            "as dept_temp GROUP BY dept_id\n")
    List<TempRecordPreviewRespVO> selectClassPreview(@Param("ew") Wrapper wrapper);

    @Select("select dept.parent_id as dept_id, sum(fever) as fever, sum(normal) as normal,sum(abnormal) as abnormal from \n" +
            "(SELECT dept_id, sum(IF(fever_no > 0, 1, 0)) AS fever, \n" +
            "sum(IF(fever_no = 0, IF(normal_no > 0, 1, 0), 0)) AS normal, \n" +
            "sum(IF(fever_no = 0, IF(normal_no = 0, IF(abnormal_no > 0, 1, 0), 0), 0)) AS abnormal \n" +
            "FROM \n" +
            "(SELECT student_id, dept_id, \n" +
            "sum(CASE WHEN body = 'fever' THEN 1 ELSE 0 END) AS fever_no, \n" +
            "sum(CASE WHEN body = 'normal' THEN 1 ELSE 0 END) AS normal_no, \n" +
            "sum(CASE WHEN body = 'abnormal' THEN 1 ELSE 0 END) AS abnormal_no \n" +
            "FROM \n" +
            "(SELECT student_id, dept.parent_id AS dept_id, \n" +
            "IF(record.temperature < 37.5, IF(record.temperature < 34.5, 'abnormal', 'normal'), 'fever') AS body \n" +
            "FROM campus_temp_record AS record \n" +
            "INNER JOIN campus_student AS student ON record.student_id = student.id \n" +
            "INNER JOIN system_dept AS dept ON student.dept_id = dept.id \n" +
            "${ew.customSqlSegment}) AS student_temp GROUP BY student_id) AS dept_temp group by dept_id) as school inner join system_dept dept on school.dept_id = dept.id group by dept.parent_id")
    List<TempRecordSchoolPreviewRespVO> selectSchoolPreview(@Param("ew") Wrapper wrapper);

    @Select("select dept.parent_id as dept_id, count(student.id) as total from campus_student student \n" +
            "inner join system_dept dept on student.dept_id = dept.id ${ew.customSqlSegment}")
    List<TempRecordPreviewRespVO> getGradeStudentCountByDeptId(@Param("ew") Wrapper wrapper);

    @Select("select dept.id as dept_id, count(student.id) as total from campus_student student\n" +
            "inner join system_dept dept on student.dept_id = dept.id ${ew.customSqlSegment}")
    List<TempRecordPreviewRespVO> getClassStudentCountByDeptId(@Param("ew") Wrapper wrapper);

    @Select("select dept.parent_id as dept_id, sum(total) as total from\n" +
            "(select dept.parent_id as school_id, count(student.id) as total \n" +
            "from campus_student student\n" +
            "inner join system_dept dept on student.dept_id = dept.id \n" +
            "${ew.customSqlSegment}) as school \n" +
            "inner join system_dept dept on school.school_id = dept.id GROUP BY dept.parent_id")
    List<TempRecordSchoolPreviewRespVO> getSchoolStudentCountByDeptId(@Param("ew") Wrapper wrapper);


}
