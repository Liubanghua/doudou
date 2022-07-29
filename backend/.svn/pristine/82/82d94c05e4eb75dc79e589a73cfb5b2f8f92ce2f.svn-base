package cn.iocoder.yudao.module.campus.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.campus.controller.admin.vo.material.*;
import cn.iocoder.yudao.module.campus.dal.dataobject.MaterialDO;

import java.util.List;

public interface MaterialService {

    PageResult<MaterialDO> getMaterialPage(MaterialPageReqVO reqVO);

    Long createMaterial(Long deptId, MaterialCreateReqVO reqVO);

    void updateMaterial(MaterialUpdateReqVO reqVO);

    List<MaterialSchoolRespVO> getMaterialSchoolList(List<Long> schoolDeptIds);

    void updateSchoolMaterial(MaterialSchoolUpdateReqVO reqVO);
}
