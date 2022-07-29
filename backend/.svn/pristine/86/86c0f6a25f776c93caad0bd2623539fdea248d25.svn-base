package cn.iocoder.yudao.module.campus.controller.admin.vo.material;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/7/6
 */
@Data
public class MaterialBaseVO {

    @ApiModelProperty(value = "物资名称", required = true, example = "口罩")
    @NotBlank(message = "物资名称不能为空")
    @Size(max = 30, message = "物资名称长度不能超过30个字符")
    private String name;

    @ApiModelProperty(value = "数量", example = "1024l")
    private Long quantity;

    @ApiModelProperty(value = "备注", example = "7月到货")
    private String remarks;

}
