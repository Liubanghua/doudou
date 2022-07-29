package cn.iocoder.yudao.module.campus.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.campus.controller.admin.vo.inspect.InspectCreateReqVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.inspect.InspectPageReqVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.inspect.InspectSchoolReqVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.inspect.InspectSchoolRespVO;
import cn.iocoder.yudao.module.campus.dal.dataobject.InspectDO;

import java.util.Date;
import java.util.List;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/7/8
 */
public interface InspectService {

    PageResult<InspectDO> getInspectPage(InspectPageReqVO reqVO);

    Long createInspect(Long deptId, InspectCreateReqVO reqVO);

    PageResult<InspectSchoolRespVO> getSchoolInspects(List<Long> schoolIds, Date inspectTime);

}
