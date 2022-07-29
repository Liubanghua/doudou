package cn.iocoder.yudao.module.campus.controller.admin.vo.student;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/7/1
 */
@ApiModel("智慧校园 - 学生更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class StudentUpdateReqVO extends StudentBaseVO {

    @ApiModelProperty(value = "学生编号", required = true, example = "1024")
    @NotNull(message = "学生编号不能为空")
    private Long id;

}
