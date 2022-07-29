package cn.iocoder.yudao.module.campus.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.date.DateUtils;
import cn.iocoder.yudao.framework.common.util.number.NumberUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.QueryWrapperX;
import cn.iocoder.yudao.module.campus.controller.admin.vo.covid.CovidClosePageReqVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.covid.CovidCloseRespVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.covid.CovidCloseSchoolRespVO;
import cn.iocoder.yudao.module.campus.dal.mysql.CovidCloseMapper;
import cn.iocoder.yudao.module.campus.service.CovidCloseService;
import cn.iocoder.yudao.module.system.service.dept.DeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/7/8
 */
@Service
public class CovidCloseServiceImpl implements CovidCloseService {

    @Resource
    private DeptService deptService;

    @Resource
    private CovidCloseMapper covidCloseMapper;

    @Override
    public PageResult<CovidCloseRespVO> getCovidClosePage(CovidClosePageReqVO reqVO, List<Long> gradeDeptIds) {
        QueryWrapperX queryWrapper = new QueryWrapperX();
        queryWrapper.inIfPresent("dept.parent_id", gradeDeptIds);
        if (reqVO.getDate() != null) {
            queryWrapper.apply("date_format(covid.create_time, '%Y-%m-%d') = '" + DateUtils.getDate(reqVO.getDate()) + "'");
        }
        int pageSize = reqVO.getPageSize();
        int pageStart = (reqVO.getPageNo() - 1) * pageSize;
        List<CovidCloseRespVO> results = covidCloseMapper.selectPage(queryWrapper, pageStart, pageSize);

        return new PageResult<CovidCloseRespVO>(results, (long) results.size());
    }

    @Override
    public List<CovidCloseSchoolRespVO> getCovidCloseSchoolPreviewList(List<Long> schoolDeptIds, Date date) {
        List<Long> gradeDeptIds = deptService.getDeptsByParents(schoolDeptIds);
        if (CollUtil.isEmpty(gradeDeptIds)) {
            return null;
        }
        QueryWrapperX queryWrapper = new QueryWrapperX();
        queryWrapper.inIfPresent("dept.parent_id", gradeDeptIds);

        //获取学校总人数
        List<CovidCloseSchoolRespVO> studentTotalResults = covidCloseMapper.selectSchoolPreviewTotalList(queryWrapper);
        Map<Long, CovidCloseSchoolRespVO> studentTotalMap = studentTotalResults.stream().collect(Collectors.toMap(CovidCloseSchoolRespVO::getDeptId, covid -> covid));
        //获取学校密接、次密接人数
        queryWrapper.apply("date_format(covid.create_time, '%Y-%m-%d') = '" + DateUtils.getDate(date) + "'");
        List<CovidCloseSchoolRespVO> partResults = covidCloseMapper.selectSchoolPreviewList(queryWrapper);
        Map<Long, CovidCloseSchoolRespVO> partResultsMap = partResults.stream().collect(Collectors.toMap(CovidCloseSchoolRespVO::getDeptId, covid -> covid));

        //数据整合
        List<CovidCloseSchoolRespVO> results = new ArrayList<>();
        for (Map.Entry<Long, CovidCloseSchoolRespVO> entry : studentTotalMap.entrySet()) {
            CovidCloseSchoolRespVO result = partResultsMap.get(entry.getKey());
            Integer total = entry.getValue().getTotal();
            if (result == null) {
                result = new CovidCloseSchoolRespVO();
                result.setDeptId(entry.getKey());
                result.setCloseTotal(0);
                result.setSubCloseTotal(0);
                result.setNormalTotal(total);
            } else {
                result.setNormalTotal(total - NumberUtils.parseInteger(result.getCloseTotal()) - NumberUtils.parseInteger(result.getSubCloseTotal()));
            }
            result.setTotal(total);
            results.add(result);
        }
        return results;
    }
}
