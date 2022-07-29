package cn.iocoder.yudao.module.campus.controller.admin.vo.student;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
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
@ApiModel("智慧校园 - 学生分页 Request VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StudentPageReqVO extends PageParam {

    @ApiModelProperty(value = "学校ID")
    private Long schoolId;

    @ApiModelProperty(value = "部门 ID", example = "101", notes = "根据前端选择年级号、班级号设置")
    private Long deptId;

    @ApiModelProperty(value = "性别", example = "1")
    private Integer sex;

    @ApiModelProperty(value = "学号", example = "2204240120", notes = "模糊查询")
    private String studentNo;

    @ApiModelProperty(value = "学生姓名", example = "张狗蛋", notes = "模糊查询")
    private String studentName;

}
