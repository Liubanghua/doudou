package cn.iocoder.yudao.module.campus.service;


import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.campus.controller.admin.vo.student.*;
import cn.iocoder.yudao.module.campus.controller.admin.vo.temprecord.TempRecordPreviewRespVO;
import cn.iocoder.yudao.module.campus.dal.dataobject.StudentDO;
import cn.iocoder.yudao.module.campus.dal.dataobject.StudentHeightDO;
import cn.iocoder.yudao.module.campus.dal.dataobject.StudentWeightDO;
import cn.iocoder.yudao.module.system.dal.dataobject.dept.DeptDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

public interface StudentService extends IService<StudentDO> {

    /**
     * 获取学生分页列表
     *
     * @param reqVO
     * @return
     */
    PageResult<StudentDO> getStudentPage(StudentPageReqVO reqVO, Collection<Long> deptIds);

    PageResult<StudentRespVO> studentPage(StudentPageReqVO reqVO, Collection<Long> deptIds);

    /**
     * 获取符合查询条件的学生 ID
     *
     * @param reqVO
     * @return
     */
    List<Long> getStudentIdsByCondition(StudentPageReqVO reqVO);

    List<StudentDO> getStudentsByIds(List<Long> ids);

    Long createStudent(StudentCreateReqVO reqVO);

    StudentDO selectById(Long id);

    void deleteStudent(Long id);

    void updateStudent(StudentUpdateReqVO reqVO);

    void updateStudentsDept(StudentUpdateDeptReqVO reqVO);

	String updateStudentAvatar(InputStream inputStream) throws Exception;

	List<StudentDO> getStudentList(@Valid StudentExportReqVO exportReqVO);

	StudentImportRespVO importStudent(List<StudentExcelVo> list, Boolean updateSupport);

	Map<Long, StudentDO> getStudentMapById(List<Long> studentIds);

	Long uploadStudent(StudentCreateReqVO reqVO);

	StudentDO getStudentByStudentNo(String studentNo);

}
