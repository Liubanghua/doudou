package cn.iocoder.yudao.module.campus.controller.admin.vo.covid;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/7/19
 */
@ApiModel("智慧校园 - 学校密接信息返回的 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CovidCloseSchoolRespVO {

    @ApiModelProperty(value = "部门ID")
    private Long deptId;

    @ApiModelProperty(value = "学校名称")
    private String schoolName;

    @ApiModelProperty(value = "学校总人数")
    private Integer total;

    @ApiModelProperty(value = "学校密接人数")
    private Integer closeTotal;

    @ApiModelProperty(value = "学校次密接人数")
    private Integer subCloseTotal;

    @ApiModelProperty(value = "正常人数")
    private Integer normalTotal;
}
