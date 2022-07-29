package cn.iocoder.yudao.module.campus.controller.admin.vo.inspect;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/7/7
 */
@Data
public class InspectBaseVO {

    @ApiModelProperty(value = "检查等级", required = true, example = "1")
    @NotNull(message = "检查等级不能为空")
    private Integer grade;


    @ApiModelProperty(value = "备注", example = "要在三个月内改正完后")
    private String remarks;

}
