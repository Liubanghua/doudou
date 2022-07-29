package cn.iocoder.yudao.module.campus.controller.admin.vo.material;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/7/7
 */
@ApiModel("智慧校园 - 更新物资的 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class MaterialUpdateReqVO extends MaterialBaseVO {

    @ApiModelProperty(value = "物资编号", required = true, example = "1024")
    @NotNull(message = "物资编号不能为空")
    private Long id;

    @ApiModelProperty(value = "学校ID")
    private Long schoolId;

}
