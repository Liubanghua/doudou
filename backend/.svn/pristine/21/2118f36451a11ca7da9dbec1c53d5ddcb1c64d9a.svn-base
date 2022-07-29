package cn.iocoder.yudao.module.campus.controller.admin.vo.inspect;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/7/8
 */
@ApiModel("智慧校园 - 新增物资的 Request VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class InspectCreateReqVO extends InspectBaseVO {

    @ApiModelProperty(value = "记录日期", example = "2022-06-17 14:22:23")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date createTime;

    @ApiModelProperty(value = "部门ID")
    private Long schoolId;
}
