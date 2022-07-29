package cn.iocoder.yudao.module.campus.controller.admin.vo.inspect;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.*;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/7/8
 */
@ApiModel("智慧校园 - 物资信息返回的 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class InspectRespVO extends InspectBaseVO {

    @ApiModelProperty(value = "创建时间", required = true, example = "2022-06-16")
    @JsonFormat(pattern = FORMAT_YEAR_MONTH_DAY, timezone = TIME_ZONE_DEFAULT)
    private Date createTime;

}
