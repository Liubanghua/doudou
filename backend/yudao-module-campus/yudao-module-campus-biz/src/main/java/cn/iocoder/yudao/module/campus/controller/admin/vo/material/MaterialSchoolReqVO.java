package cn.iocoder.yudao.module.campus.controller.admin.vo.material;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/7/20
 */
@ApiModel("智慧校园 - 学校防疫物资记录的 Request VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialSchoolReqVO {

    @ApiModelProperty(value = "学校ID", example = "101")
    private Long deptId;

}
