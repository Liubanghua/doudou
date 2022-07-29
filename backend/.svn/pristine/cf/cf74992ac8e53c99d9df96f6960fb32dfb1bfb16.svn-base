package cn.iocoder.yudao.module.campus.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.date.DateUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.QueryWrapperX;
import cn.iocoder.yudao.module.campus.controller.admin.vo.student.StudentTempRecordRespVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.temprecord.*;
import cn.iocoder.yudao.module.campus.convert.StudentConvert;
import cn.iocoder.yudao.module.campus.convert.TempRecordConvert;
import cn.iocoder.yudao.module.campus.dal.dataobject.StudentDO;
import cn.iocoder.yudao.module.campus.dal.dataobject.TempRecordDO;
import cn.iocoder.yudao.module.campus.dal.mysql.TempRecordMapper;
import cn.iocoder.yudao.module.campus.service.StudentService;
import cn.iocoder.yudao.module.campus.service.TempRecordService;
import cn.iocoder.yudao.module.system.dal.dataobject.dept.DeptDO;
import cn.iocoder.yudao.module.system.service.dept.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserDeptId;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/6/17
 */
@Service
@Slf4j
public class TempRecordServiceImpl implements TempRecordService {

    @Resource
    private TempRecordMapper tempRecordMapper;

    @Resource
    private StudentService studentService;

    @Resource
    private DeptService deptService;

    @Override
    public PageResult<TempRecordRespVO> getTempRecordPage(TempRecordPageReqVO reqVO, List<Long> classDeptIds) {
        QueryWrapperX queryWrapper = new QueryWrapperX();
        if (reqVO.getCreateTime() != null) {
            queryWrapper.eqIfPresent("date_format(record.create_time, '%Y-%m-%d')", DateUtils.getDate(reqVO.getCreateTime()));
        }
        queryWrapper.inIfPresent("dept.id", classDeptIds)
                .eqIfPresent("student.sex", reqVO.getSex())
                .likeIfPresent("student.student_no", reqVO.getStudentNo())
                .likeIfPresent("student.`name`", reqVO.getStudentName())
                .orderByDesc("record.create_time");
        int pageSize = reqVO.getPageSize();
        int pageStart = (reqVO.getPageNo() - 1) * pageSize;
        List<TempRecordRespVO> results = tempRecordMapper.selectPage(queryWrapper, pageStart, pageSize);

        long count = tempRecordMapper.selectCountByDeptIds(queryWrapper);
        return new PageResult<>(results, count);
    }

    @Override
    public StudentTempRecordRespVO getTempRecordByStudentIdAndDate(Long studentId, String measureTime) {
        //sql操作，查询到原始数据
        StudentDO student = studentService.selectById(studentId);
        List<TempRecordDO> tempRecords = tempRecordMapper.selectTempRecords(studentId, measureTime);

        StudentTempRecordRespVO respVO = StudentConvert.INSTANCE.convert(student);
        if (respVO != null) {
            respVO.setTempRecords(TempRecordConvert.INSTANCE.convert(tempRecords));
            respVO.setRecordCreateTime(DateUtil.parse(measureTime, FORMAT_YEAR_MONTH_DAY));
        }

        return respVO;
    }

    @Override
    public void deleteTempRecordsByStudentId(Long id) {
        List<TempRecordDO> tempRecords = tempRecordMapper.selectList(TempRecordDO::getStudentId, id);
        if (CollUtil.isEmpty(tempRecords)) {
            return;
        }
        List<Long> ids = tempRecords.stream().map(TempRecordDO::getId).collect(Collectors.toList());
        tempRecordMapper.deleteBatchIds(ids);

    }


    @Override
    public List<TempRecordDO> getTodayTempRecordsByStudentIdsAndDate(List<Long> studentIds) {
        return tempRecordMapper.selectStudentTempPage(studentIds);
    }

    @Override
    public TempChartRespVO getTempChart(Date createTime) {
        TempChartRespVO tempChartRespVO = new TempChartRespVO();
        List<StudentDO> stuDoList = studentService.list();
        List<DeptDO> gradeList = deptService.getDeptsByParentIdFromCache(getLoginUserDeptId(), false);
        List<String> gradeNameTitle = new ArrayList<String>();
        if (CollUtil.isNotEmpty(gradeList)) {
            for (int i = 0; i < gradeList.size(); i++) {
                gradeNameTitle.add(gradeList.get(i).getName());
            }
        }

        //全校测温饼状图数据
        List<TempPieChartVO> allTempList = new ArrayList<>();
        String[] tempChartTitle = {"正常", "发烧", "未测量"};
        int[] tempChartNum = new int[3];

        //各个年级体温异常饼状图数据
        List<TempPieChartVO> tempUnusualList = new ArrayList<>();
        int[] tempUnusualNum = new int[gradeList.size()];

        //各个年级体温情况柱状图数据
        int[] tempNormal = new int[gradeList.size()];
        int[] tempFever = new int[gradeList.size()];
        int[] tempNull = new int[gradeList.size()];

        if (CollUtil.isNotEmpty(stuDoList)) {
            tempChartRespVO.setStuTotal(stuDoList.size());

            //获取学生班级、年级
            Collection<Long> classDeptIds = CollectionUtils.convertList(stuDoList, StudentDO::getDeptId);
            List<DeptDO> classDepts = deptService.getDepts(classDeptIds);
            Map<Long, DeptDO> classDeptMap = CollectionUtils.convertMap(classDepts, DeptDO::getId);
            Collection<Long> gradeDeptIds = CollectionUtils.convertList(classDepts, DeptDO::getParentId);
            List<DeptDO> gradeDepts = deptService.getDepts(gradeDeptIds);
            Map<Long, DeptDO> gradeDeptMap = CollectionUtils.convertMap(gradeDepts, DeptDO::getId);

            for (StudentDO studentDO : stuDoList) {
                // 全校测温饼状图
                List<TempRecordDO> tempRecordList = tempRecordMapper.selectTempRecords(studentDO.getId(), DateUtils.getDate(createTime));
                String gradeName = gradeDeptMap.get(classDeptMap.get(studentDO.getDeptId()).getParentId()).getName();
                if (CollUtil.isEmpty(tempRecordList)) {
                    tempChartNum[2] += 1;
                    if (gradeNameTitle.indexOf(gradeName) != -1) {
                        tempNull[gradeNameTitle.indexOf(gradeName)] += 1;
                    }
                } else {
                    if (tempRecordList.get(0).getTemperature() >= 37.5) {
                        tempChartNum[1] += 1;
                        if (gradeNameTitle.indexOf(gradeName) != -1) {
                            tempUnusualNum[gradeNameTitle.indexOf(gradeName)] += 1;
                            tempFever[gradeNameTitle.indexOf(gradeName)] += 1;
                        }
                    } else if (tempRecordList.get(0).getTemperature() < 37.5 && tempRecordList.get(0).getTemperature() > 34.5) {
                        tempChartNum[0] += 1;
                        if (gradeNameTitle.indexOf(gradeName) != -1) {
                            tempNormal[gradeNameTitle.indexOf(gradeName)] += 1;
                        }
                    }
                }
            }
        }
        //封装全校测温饼状图数据
        for (int i = 0; i < tempChartTitle.length; i++) {
            TempPieChartVO tempPieChartVO = new TempPieChartVO();
            tempPieChartVO.setName(tempChartTitle[i]);
            tempPieChartVO.setValue(tempChartNum[i]);
            allTempList.add(tempPieChartVO);
        }
        //各个年级体温异常饼状图数据
        for (int i = 0; i < gradeNameTitle.size(); i++) {
            TempPieChartVO tempPieChartVO = new TempPieChartVO();
            tempPieChartVO.setName(gradeNameTitle.get(i));
            tempPieChartVO.setValue(tempUnusualNum[i]);
            tempUnusualList.add(tempPieChartVO);
        }
        //各个年级体温情况柱状图数据
        List<List<Integer>> gradeTempList = new ArrayList<List<Integer>>();
        gradeTempList.add(Arrays.stream(tempNormal).boxed().collect(Collectors.toList()));
        gradeTempList.add(Arrays.stream(tempFever).boxed().collect(Collectors.toList()));
        gradeTempList.add(Arrays.stream(tempNull).boxed().collect(Collectors.toList()));

        tempChartRespVO.setAllTempData(allTempList);
        tempChartRespVO.setTempUnusualData(tempUnusualList);
        tempChartRespVO.setGradeTempList(gradeTempList);
        tempChartRespVO.setGradeNameList(gradeNameTitle);
        return tempChartRespVO;
    }


    @Override
    public List<TempRecordPreviewRespVO> getGradeTempRecordPreviewList(List<Long> deptIds, Date date) {
        List<TempRecordPreviewRespVO> results = new ArrayList<>();

        QueryWrapperX queryWrapper = new QueryWrapperX();
        queryWrapper.inIfPresent("dept.parent_id", deptIds)
                .apply("date_format(record.create_time, '%Y-%m-%d') = '" + DateUtils.getDate(date) + "'" +
                        " and student.deleted = 0");
        List<TempRecordPreviewRespVO> partResults = tempRecordMapper.selectGradePreview(queryWrapper);
        Map<Long, TempRecordPreviewRespVO> partResultMap = partResults.stream().collect(Collectors.toMap(TempRecordPreviewRespVO::getDeptId, respVo -> respVo));


        QueryWrapperX queryWrapper2 = new QueryWrapperX();
        queryWrapper2.inIfPresent("dept.parent_id", deptIds)
                .apply("student.deleted = 0");
        queryWrapper2.groupBy("dept.parent_id");
        List<TempRecordPreviewRespVO> studentTotal = tempRecordMapper.getGradeStudentCountByDeptId(queryWrapper2);
        Map<Long, TempRecordPreviewRespVO> studentTotalMap = studentTotal.stream().collect(Collectors.toMap(TempRecordPreviewRespVO::getDeptId, respVo -> respVo));

        for (Map.Entry<Long, TempRecordPreviewRespVO> entry : studentTotalMap.entrySet()) {
            TempRecordPreviewRespVO result = partResultMap.get(entry.getKey());
            Integer total = entry.getValue().getTotal();
            if (result == null) {
                result = new TempRecordPreviewRespVO();
                result.setDeptId(entry.getKey());
                result.setTotal(total);
                result.setFever(0);
                result.setNormal(0);
                result.setAbnormal(0);
                result.setUnmeasured(total);
            } else {
                result.setTotal(total);
                result.setUnmeasured(total - result.getFever() - result.getNormal() - result.getAbnormal());
            }
            results.add(result);
        }
        return results;
    }

    @Override
    public List<TempRecordPreviewRespVO> getClassTempRecordPreviewList(List<Long> deptIds, Date date) {
        List<TempRecordPreviewRespVO> results = new ArrayList<>();

        QueryWrapperX queryWrapper = new QueryWrapperX();
        queryWrapper.inIfPresent("dept.id", deptIds)
                .apply("date_format(record.create_time, '%Y-%m-%d') = '" + DateUtils.getDate(date) + "'" +
                        " and student.deleted = 0");
        List<TempRecordPreviewRespVO> partResults = tempRecordMapper.selectClassPreview(queryWrapper);
        Map<Long, TempRecordPreviewRespVO> partResultMap = partResults.stream().collect(Collectors.toMap(TempRecordPreviewRespVO::getDeptId, respVo -> respVo));

        QueryWrapperX queryWrapper2 = new QueryWrapperX();
        queryWrapper2.inIfPresent("dept.id", deptIds)
                .apply("student.deleted = 0");
        queryWrapper2.groupBy("dept.id");
        List<TempRecordPreviewRespVO> studentTotal = tempRecordMapper.getClassStudentCountByDeptId(queryWrapper2);
        Map<Long, TempRecordPreviewRespVO> studentTotalMap = studentTotal.stream().collect(Collectors.toMap(TempRecordPreviewRespVO::getDeptId, respVo -> respVo));

        for (Map.Entry<Long, TempRecordPreviewRespVO> entry : studentTotalMap.entrySet()) {
            TempRecordPreviewRespVO result = partResultMap.get(entry.getKey());
            Integer total = entry.getValue().getTotal();
            if (result == null) {
                result = new TempRecordPreviewRespVO();
                result.setDeptId(entry.getKey());
                result.setTotal(total);
                result.setFever(0);
                result.setNormal(0);
                result.setAbnormal(0);
                result.setUnmeasured(total);
            } else {
                result.setTotal(total);
                result.setUnmeasured(total - result.getFever() - result.getNormal() - result.getAbnormal());
            }
            results.add(result);
        }

        return results;
    }


    @Override
    public List<TempRecordPreviewRespVO> getStudentCountByDeptId(List<Long> deptIds) {
        QueryWrapperX queryWrapper = new QueryWrapperX();
        queryWrapper.inIfPresent("dept.parent_id", deptIds)
                .apply("student.deleted = 0");
        queryWrapper.groupBy("dept.parent_id");
        return tempRecordMapper.getGradeStudentCountByDeptId(queryWrapper);
    }

    @Override
    public List<TempRecordSchoolPreviewRespVO> getSchoolTempRecordPreviewList(List<Long> deptIds, Date date) {
        List<TempRecordSchoolPreviewRespVO> results = new ArrayList<>();
        List<Long> gradeDeptIds = deptService.getDeptsByParents(deptIds);
        if (CollUtil.isEmpty(gradeDeptIds)) {
            return null;
        }
        QueryWrapperX queryWrapper = new QueryWrapperX();
        queryWrapper.inIfPresent("dept.parent_id", gradeDeptIds)
                .apply("date_format(record.create_time, '%Y-%m-%d') = '" + DateUtils.getDate(date) + "'" +
                        " and student.deleted = 0");
        List<TempRecordSchoolPreviewRespVO> partResults = tempRecordMapper.selectSchoolPreview(queryWrapper);
        Map<Long, TempRecordSchoolPreviewRespVO> partResultMap = partResults.stream().collect(Collectors.toMap(TempRecordSchoolPreviewRespVO::getDeptId, respVo -> respVo));

        QueryWrapperX queryWrapper2 = new QueryWrapperX();
        queryWrapper2.inIfPresent("dept.parent_id", gradeDeptIds)
                .apply("student.deleted = 0");
        queryWrapper2.groupBy("dept.parent_id");
        List<TempRecordSchoolPreviewRespVO> studentTotal = tempRecordMapper.getSchoolStudentCountByDeptId(queryWrapper2);
        Map<Long, TempRecordSchoolPreviewRespVO> studentTotalMap = studentTotal.stream().collect(Collectors.toMap(TempRecordSchoolPreviewRespVO::getDeptId, respVo -> respVo));

        for (Map.Entry<Long, TempRecordSchoolPreviewRespVO> entry : studentTotalMap.entrySet()) {
            TempRecordSchoolPreviewRespVO result = partResultMap.get(entry.getKey());
            Integer total = entry.getValue().getTotal();
            if (result == null) {
                result = new TempRecordSchoolPreviewRespVO();
                result.setDeptId(entry.getKey());
                result.setFever(0);
                result.setNormal(0);
                result.setAbnormal(0);
                result.setUnmeasured(total);
            } else {
                result.setUnmeasured(total - result.getFever() - result.getNormal() - result.getAbnormal());
            }
            result.setTotal(total);
            results.add(result);
        }

        return results;
    }

    @Override
    public Long create(TempRecordDO tempRecord) {
        if(tempRecord != null){
            tempRecordMapper.insert(tempRecord);
            return tempRecord.getId();
        }
        return null;
    }
}
