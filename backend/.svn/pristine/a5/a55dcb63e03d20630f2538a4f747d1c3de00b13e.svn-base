package cn.iocoder.yudao.module.campus.controller.admin.vo.covid;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/7/8
 */
@Data
public class CovidCloseBaseVO {

    @ApiModelProperty(value = "班级编号", example = "103")
    private Long deptId;

    @ApiModelProperty(value = "记录时间", example = "2022-06-17")
    @JsonFormat(pattern = FORMAT_YEAR_MONTH_DAY)
    private Date createTime;

}
