package cn.iocoder.yudao.module.campus.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.campus.controller.admin.vo.student.StudentTempRecordRespVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.temprecord.*;
import cn.iocoder.yudao.module.campus.dal.dataobject.StudentDO;
import cn.iocoder.yudao.module.campus.dal.dataobject.TempRecordDO;

import java.util.Date;
import java.util.List;

public interface TempRecordService {

    /**
     * 查询测温记录
     *
     * @param reqVO
     * @param deptIds
     * @return
     */
    PageResult<TempRecordRespVO> getTempRecordPage(TempRecordPageReqVO reqVO, List<Long> deptIds);

    /**
     * 查看学生某一天的测温记录
     *
     * @param studentId
     * @param measureTime      ： 2022-06-01
     * @return
     */
    StudentTempRecordRespVO getTempRecordByStudentIdAndDate(Long studentId, String measureTime);

    void deleteTempRecordsByStudentId(Long id);

    TempChartRespVO getTempChart(Date createTime);

    List<TempRecordDO> getTodayTempRecordsByStudentIdsAndDate(List<Long> studentIds);

    List<TempRecordPreviewRespVO> getGradeTempRecordPreviewList(List<Long> deptIds, Date date);

    List<TempRecordPreviewRespVO> getClassTempRecordPreviewList(List<Long> deptIds, Date date);

    List<TempRecordPreviewRespVO> getStudentCountByDeptId(List<Long> deptIds);

    List<TempRecordSchoolPreviewRespVO> getSchoolTempRecordPreviewList(List<Long> deptIds, Date date);

    Long create(TempRecordDO tempRecord);

}
