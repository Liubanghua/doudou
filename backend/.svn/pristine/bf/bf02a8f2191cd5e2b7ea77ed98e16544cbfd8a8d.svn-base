package cn.iocoder.yudao.module.campus.controller.admin.vo.student;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/7/1
 */
@ApiModel("智慧校园 - 学生批量转班 Request VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentUpdateDeptReqVO {

    @ApiModelProperty(value = "学生编号", required = true, example = "[1024, 1025]")
    private List<Long> studentIds;

    @ApiModelProperty(value = "部门编号", required = true, example = "[1024, 1025]")
    private Long deptId;

}
