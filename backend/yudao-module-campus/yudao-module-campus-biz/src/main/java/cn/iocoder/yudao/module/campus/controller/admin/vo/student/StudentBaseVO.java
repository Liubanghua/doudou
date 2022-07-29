package cn.iocoder.yudao.module.campus.controller.admin.vo.student;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @program: 智慧校园
 * @description: 学生 Base vo
 * @author: pengzhenlong
 * @date: 2022/6/15
 */
@Data
public class StudentBaseVO {

    @ApiModelProperty(value = "学生姓名", required = true, example = "张某某")
    @NotBlank(message = "学生姓名不能为空")
    @Size(max = 30, message = "学生姓名长度不能超过30个字符")
    private String name;

    @ApiModelProperty(value = "学生性别", example = "1")
    private Integer sex;

    @ApiModelProperty(value = "学籍号", example = "2204240120")
    @NotBlank(message = "学籍号不能为空")
    private String studentNo;

    @ApiModelProperty(value = "头像保存地址", example = "/photo")
    private String avatar;

    @ApiModelProperty(value = "身份证号", example = "429006199909092899")
    private String idCard;

    @ApiModelProperty(value = "部门 ID", example = "101", notes = "根据前端选择年级号、班级号设置")
    private Long deptId;

}
