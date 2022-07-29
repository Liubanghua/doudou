package cn.iocoder.yudao.module.campus.controller.admin.vo.temprecord;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.TIME_ZONE_DEFAULT;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/6/30
 */
@ApiModel("智慧校园 - 测温记录简易 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TempRecordSimpleRespVO {

    @ApiModelProperty(value = "测温时间", required = true, example = "2022-06-16 12:01:21")
    @JsonFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND, timezone = TIME_ZONE_DEFAULT)
    private Date createTime;

    @ApiModelProperty(value = "测量温度", example = "36.5f")
    private float temperature;

}
