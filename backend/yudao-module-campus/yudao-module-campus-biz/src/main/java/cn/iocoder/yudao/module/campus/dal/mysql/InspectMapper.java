package cn.iocoder.yudao.module.campus.dal.mysql;

import cn.hutool.core.date.DateUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.campus.controller.admin.vo.inspect.InspectPageReqVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.inspect.InspectSchoolRespVO;
import cn.iocoder.yudao.module.campus.dal.dataobject.InspectDO;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/7/6
 */
@Mapper
public interface InspectMapper extends BaseMapperX<InspectDO> {

    default PageResult<InspectDO> selectPage(InspectPageReqVO reqVO) {
        LambdaQueryWrapperX<InspectDO> queryWrapper = new LambdaQueryWrapperX<>();
        if (reqVO.getDate() != null) {
            queryWrapper.apply("date_format(create_time, '%Y-%m-%d') = '" + DateUtil.format(reqVO.getDate(), "yyyy-MM-dd") + "'");
        }
        queryWrapper.orderByDesc(InspectDO::getCreateTime);
        return selectPage(reqVO, queryWrapper);
    }

    @Select({
            "<script>",
            "select dept.id as dept_id, dept.`name` as school_name, ",
            "inspect.grade as inspect_grade, inspect.create_time as inspect_time, ",
            "inspect.remarks from system_dept dept " +
                    "left join campus_inspect inspect on",
            "inspect.dept_id = dept.id",
            "${ew.customSqlSegment}",
            "</script>"
    })
    List<InspectSchoolRespVO> selectSchoolInspect(@Param("ew") Wrapper wrapper);

    @Select("select count(inspect.id)\n" +
            "from system_dept dept \n" +
            "left join campus_inspect inspect on\n" +
            "inspect.dept_id = dept.id\n" +
            "${ew.customSqlSegment}")
    Long selectCountByReqVO(@Param("ew") Wrapper wrapper);
}
