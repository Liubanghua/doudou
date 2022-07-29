package cn.iocoder.yudao.module.campus.convert;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.module.campus.controller.admin.vo.student.*;
import cn.iocoder.yudao.module.campus.controller.admin.vo.temprecord.TempRecordPageReqVO;
import cn.iocoder.yudao.module.campus.dal.dataobject.StudentDO;
import cn.iocoder.yudao.module.campus.dal.dataobject.StudentHeightDO;
import cn.iocoder.yudao.module.campus.dal.dataobject.StudentWeightDO;
import cn.iocoder.yudao.module.campus.dal.dataobject.TempRecordDO;
import cn.iocoder.yudao.module.campus.enums.TempTypeEnum;
import cn.iocoder.yudao.module.system.dal.dataobject.dept.DeptDO;
import com.alibaba.excel.util.CollectionUtils;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper
public interface StudentConvert {

    StudentConvert INSTANCE = Mappers.getMapper(StudentConvert.class);

    List<StudentRespVO> convertDO2RespVO(List<StudentDO> list);

    StudentDO convert(StudentCreateReqVO reqVO);

    StudentTempRecordRespVO convert(StudentDO studentDO);

    StudentRespVO convertDo(StudentDO studentDO);

    StudentDO convertUpdate(StudentUpdateReqVO reqVO);

    StudentDO convert(StudentExcelVo bean);

    List<StudentTempPreviewRespVO> convertDO2TempRespVO(List<StudentDO> list);

    default List<StudentTempPreviewRespVO> convert(List<StudentDO> studentDOs, Map<Long, List<TempRecordDO>> studentTempRecordsMap, Map<Long, DeptDO> deptMap) {
        List<StudentTempPreviewRespVO> results = convertDO2TempRespVO(studentDOs);
        if (results.size() > 0) {
            for (StudentTempPreviewRespVO vo : results) {
                Long deptId = vo.getDeptId();

                if (CollUtil.isNotEmpty(deptMap)) {
                    vo.setCampusClassName(deptMap.get(deptId).getName());
                    vo.setCampusGradeName(deptMap.get(deptMap.get(deptId).getParentId()).getName());
                }

                List<TempRecordDO> tempRecords = studentTempRecordsMap.get(vo.getId());
                if (CollectionUtils.isEmpty(tempRecords)) {
                    vo.setTempType(TempTypeEnum.ABNORMAL.getType());
                } else {
                    for (TempRecordDO tempRecord : tempRecords) {
                        if (tempRecord.getTemperature() > 37.5) {
                            vo.setTempType(TempTypeEnum.FEVER.getType());
                        }
                    }
                }

                if (vo.getTempType() == null) {
                    vo.setTempType(TempTypeEnum.NORMAL.getType());
                }
            }
            return results;
        }

        return null;
    }

    default StudentPageReqVO convert(TempRecordPageReqVO reqVO) {
        StudentPageReqVO studentPageReqVO = new StudentPageReqVO();
        studentPageReqVO.setDeptId(reqVO.getDeptId());
        studentPageReqVO.setSex(reqVO.getSex());
        studentPageReqVO.setStudentNo(reqVO.getStudentNo());
        studentPageReqVO.setStudentName(reqVO.getStudentName());

        return studentPageReqVO;
    }

    default List<StudentRespVO> convert(List<StudentRespVO> results, List<StudentHeightDO> heights, List<StudentWeightDO> weights) {
        if (CollUtil.isEmpty(results)) {
            return null;
        }

        Map<Long, Float> heightMap = new HashMap<>();
        if (CollUtil.isNotEmpty(heights)) {
            heightMap = heights.stream().collect(Collectors.toMap(StudentHeightDO::getStudentId, StudentHeightDO::getHeight));
        }

        Map<Long, Float> weightMap = new HashMap<>();
        if (CollUtil.isNotEmpty(weights)) {
            weightMap = weights.stream().collect(Collectors.toMap(StudentWeightDO::getStudentId, StudentWeightDO::getWeight));
        }

        for(StudentRespVO respVO : results){
            respVO.setHeight(heightMap.get(respVO.getId()));
            respVO.setWeight(weightMap.get(respVO.getId()));
        }
        return results;
    }

}
