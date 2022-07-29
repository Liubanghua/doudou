package cn.iocoder.yudao.module.campus.controller.admin.vo.student;

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
 * @date: 2022/6/15
 */
@ApiModel("智慧校园 - 学生信息 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StudentRespVO extends StudentBaseVO {

    @ApiModelProperty(value = "学生编号", required = true, example = "1")
    private Long id;

    @ApiModelProperty(value = "年级名")
    private String campusGradeName;

    @ApiModelProperty(value = "班级名")
    private String campusClassName;

    @ApiModelProperty(value = "身高")
    private Float height;

    @ApiModelProperty(value = "体重")
    private Float weight;

}
