package cn.iocoder.yudao.module.campus.controller.admin.vo.temprecord;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.text.DateFormat;
import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.TIME_ZONE_DEFAULT;

/**
 * @program: 智慧校园
 * @description: 测温记录 基础 VO
 * @author: pengzhenlong
 * @date: 2022/6/16
 */
@Data
public class TempRecordBaseVO {

    @ApiModelProperty(value = "学生id", required = true, example = "101")
    private Long studentId;

    @ApiModelProperty(value = "记录的创建时间", required = true, example = "2022-06-16 12:01:21")
    @JsonFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND, timezone = TIME_ZONE_DEFAULT)
    private Date createTime;


}
