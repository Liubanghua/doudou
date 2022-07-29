package cn.iocoder.yudao.module.campus.controller.admin.vo.material;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/7/7
 */
@ApiModel("智慧校园 - 新增物资的 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class MaterialCreateReqVO extends MaterialBaseVO{

    @ApiModelProperty(value = "学校ID")
    private Long schoolId;

}
