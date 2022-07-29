package cn.iocoder.yudao.module.campus.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.campus.controller.admin.vo.covid.CovidClosePageReqVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.covid.CovidCloseRespVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.covid.CovidCloseSchoolReqVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.covid.CovidCloseSchoolRespVO;
import cn.iocoder.yudao.module.campus.dal.dataobject.CovidCloseDO;

import java.util.Date;
import java.util.List;

public interface CovidCloseService {

    PageResult<CovidCloseRespVO> getCovidClosePage(CovidClosePageReqVO reqVO, List<Long> classDeptIds);

    List<CovidCloseSchoolRespVO> getCovidCloseSchoolPreviewList(List<Long> schoolDeptIds, Date date);

}
