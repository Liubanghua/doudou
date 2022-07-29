package cn.iocoder.yudao.module.campus.dal.mysql;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.campus.controller.admin.vo.student.StudentExportReqVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.student.StudentPageReqVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.student.StudentRespVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.temprecord.TempRecordPreviewRespVO;
import cn.iocoder.yudao.module.campus.dal.dataobject.StudentDO;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;
import java.util.List;

@Mapper
public interface StudentMapper extends BaseMapperX<StudentDO> {

    default PageResult<StudentDO> selectPage(StudentPageReqVO reqVO, Collection<Long> deptIds) {
        return selectPage(reqVO, new LambdaQueryWrapperX<StudentDO>()
                .likeIfPresent(StudentDO::getStudentNo, reqVO.getStudentNo())
                .likeIfPresent(StudentDO::getName, reqVO.getStudentName())
                .eqIfPresent(StudentDO::getSex, reqVO.getSex())
                .inIfPresent(StudentDO::getDeptId, deptIds));
    }

    @Select("select student.*,\n" +
            "(select `name` from system_dept where dept.parent_id = id) as campus_grade_name,\n" +
            "dept.`name` as campus_class_name from campus_student student\n" +
            "LEFT JOIN system_dept dept on student.dept_id = dept.id\n" +
            "${ew.customSqlSegment} limit #{start}, #{pageSize}")
    List<StudentRespVO> selectRespVoPage(@Param("ew") Wrapper wrapper, @Param("start") int start, @Param("pageSize") int pageSize);

    @Select("select count(student.id)\n" +
            "from campus_student student \n" +
            "left join system_dept dept on\n" +
            "student.dept_id = dept.id\n" +
            "${ew.customSqlSegment}")
    Long selectCount(@Param("ew") Wrapper wrapper);

    default List<StudentDO> selectList(StudentExportReqVO exportReqVO, Collection<Long> deptIds) {
        return selectList(new LambdaQueryWrapperX<StudentDO>()
                .likeIfPresent(StudentDO::getStudentNo, exportReqVO.getStudentNo())
                .likeIfPresent(StudentDO::getName, exportReqVO.getStudentName())
                .eqIfPresent(StudentDO::getSex, exportReqVO.getSex())
                .inIfPresent(StudentDO::getDeptId, deptIds));
    }

    default StudentDO selectByStudentNo(String studentNo) {
        return selectOne(StudentDO::getStudentNo, studentNo);
    }


}
