package cn.iocoder.yudao.module.campus.controller.admin.vo.inspect;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/7/7
 */
@ApiModel("智慧校园 - 检查记录查询分页 Request VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class InspectPageReqVO extends PageParam {

    @ApiModelProperty(value = "记录日期", example = "2022-06-17")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY)
    private Date date;

}
