package cn.iocoder.yudao.module.campus.convert;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.iocoder.yudao.module.campus.controller.admin.vo.covid.CovidCloseRespVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.covid.CovidCloseSchoolRespVO;
import cn.iocoder.yudao.module.campus.dal.dataobject.CovidCloseDO;
import cn.iocoder.yudao.module.campus.dal.dataobject.StudentDO;
import cn.iocoder.yudao.module.system.dal.dataobject.dept.DeptDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper
public interface CovidCloseConvert {

    CovidCloseConvert INSTANCE = Mappers.getMapper(CovidCloseConvert.class);

    default List<CovidCloseRespVO> convert(List<CovidCloseDO> covidCloseDOS, Map<Long, StudentDO> studentMap, Map<Long, DeptDO> deptMap) {
        List<CovidCloseRespVO> vos = new ArrayList<>();
        for (CovidCloseDO covidCloseDO : covidCloseDOS) {
            CovidCloseRespVO vo = new CovidCloseRespVO();
            vo.setGrade(covidCloseDO.getGrade());
            vo.setDeptId(covidCloseDO.getDeptId());
            vo.setCreateTime(covidCloseDO.getUpdateTime());

            if (CollectionUtil.isNotEmpty(studentMap)) {
                StudentDO student = studentMap.get(covidCloseDO.getStudentId());
                vo.setStudentName(student.getName());
                vo.setStudentNo(student.getStudentNo());
            }

            if (CollectionUtil.isNotEmpty(deptMap)) {
                DeptDO dept = deptMap.get(covidCloseDO.getDeptId());
                vo.setClassName(dept.getName());
                vo.setGradeName(deptMap.get(dept.getParentId()).getName());
            }

            vos.add(vo);
        }

        return vos;
    }

    default List<CovidCloseSchoolRespVO> convert(List<CovidCloseSchoolRespVO> respVOS, Map<Long, DeptDO> schoolDeptMap){
        List<CovidCloseSchoolRespVO> results = new ArrayList<>();
        Map<Long, CovidCloseSchoolRespVO> respVOMap = new HashMap<>();
        if(CollUtil.isNotEmpty(respVOS)){
            respVOMap = respVOS.stream().collect(Collectors.toMap(CovidCloseSchoolRespVO::getDeptId, covid -> covid));
        }

        for(Map.Entry<Long, DeptDO> entry : schoolDeptMap.entrySet()){
            CovidCloseSchoolRespVO result = respVOMap.get(entry.getKey());
            if(result == null){
                result = new CovidCloseSchoolRespVO();
                result.setDeptId(entry.getKey());
                result.setTotal(0);
                result.setCloseTotal(0);
                result.setSubCloseTotal(0);
                result.setNormalTotal(0);
            }
            result.setSchoolName(entry.getValue().getName());
            results.add(result);
        }
        return results;
    }

}
