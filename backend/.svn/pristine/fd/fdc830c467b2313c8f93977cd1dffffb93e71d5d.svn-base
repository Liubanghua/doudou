package cn.iocoder.yudao.module.campus.controller.admin.vo.material;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/7/21
 */
@ApiModel("智慧校园 - 学校防疫物资更新的 Request VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialSchoolUpdateReqVO {

    @ApiModelProperty(value = "部门ID")
    @NotNull(message = "学校ID不能为空")
    private Long deptId;

    @ApiModelProperty(value = "口罩总数")
    private Integer maskTotal;

    @ApiModelProperty(value = "酒精总数")
    private Integer alcoholTotal;

    @ApiModelProperty(value = "洗手液总数")
    private Integer handwashTotal;

    @ApiModelProperty(value = "帐篷总数")
    private Integer tentTotal;

}
