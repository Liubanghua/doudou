package cn.iocoder.yudao.module.campus.controller.admin.vo.inspect;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.TIME_ZONE_DEFAULT;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/7/20
 */
@ApiModel("智慧校园 - 学校检查信息返回的 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InspectSchoolRespVO {

    @ApiModelProperty(value = "部门ID")
    private Long deptId;

    @ApiModelProperty(value = "学校名称")
    private String schoolName;

    @ApiModelProperty(value = "检查等级")
    private Integer inspectGrade;

    @ApiModelProperty(value = "检查时间")
    @JsonFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND, timezone = TIME_ZONE_DEFAULT)
    private Date inspectTime;

    @ApiModelProperty(value = "备注")
    private String remarks;

}
