package cn.iocoder.yudao.module.campus.service.impl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.date.DateUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.QueryWrapperX;
import cn.iocoder.yudao.module.campus.controller.admin.vo.inspect.InspectCreateReqVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.inspect.InspectPageReqVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.inspect.InspectSchoolRespVO;
import cn.iocoder.yudao.module.campus.convert.InspectConvert;
import cn.iocoder.yudao.module.campus.dal.dataobject.InspectDO;
import cn.iocoder.yudao.module.campus.dal.mysql.InspectMapper;
import cn.iocoder.yudao.module.campus.service.InspectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/7/8
 */
@Service
public class InspectServiceImpl implements InspectService {

    @Resource
    private InspectMapper inspectMapper;

    @Override
    public PageResult<InspectDO> getInspectPage(InspectPageReqVO reqVO) {
        return inspectMapper.selectPage(reqVO);
    }

    @Override
    public Long createInspect(Long deptId, InspectCreateReqVO reqVO) {
        InspectDO inspectDO = InspectConvert.INSTANCE.convert(reqVO);
        inspectDO.setDeptId(deptId);

        inspectMapper.insert(inspectDO);
        return inspectDO.getId();
    }

    @Override
    public PageResult<InspectSchoolRespVO> getSchoolInspects(List<Long> schoolIds, Date inspectTime) {
        QueryWrapperX<InspectDO> queryWrapperX = new QueryWrapperX<>();
        queryWrapperX.inIfPresent("dept.id", schoolIds);
        if (inspectTime != null) {
            queryWrapperX.apply("date_format(inspect.create_time, '%Y-%m-%d') = '" + DateUtils.getDate(inspectTime) + "'");
        }
        queryWrapperX.orderByDesc("inspect.create_time");

        List<InspectSchoolRespVO> results = inspectMapper.selectSchoolInspect(queryWrapperX);
        Long total = inspectMapper.selectCountByReqVO(queryWrapperX);
        return new PageResult<>(results, total);
    }
}
