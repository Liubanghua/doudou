package cn.iocoder.yudao.module.campus.controller.admin.vo.covid;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/7/8
 */
@ApiModel("智慧校园 - 密接记录的 Request VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CovidClosePageReqVO extends PageParam {

    @ApiModelProperty(value = "学校编号", example = "103")
    private Long schoolId;

    @ApiModelProperty(value = "班级编号", example = "103")
    private Long deptId;

    @ApiModelProperty(value = "记录时间", example = "2022-06-17")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY)
    private Date date;
}
