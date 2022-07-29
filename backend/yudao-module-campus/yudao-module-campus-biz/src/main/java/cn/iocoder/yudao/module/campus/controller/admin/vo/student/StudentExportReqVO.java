package cn.iocoder.yudao.module.campus.controller.admin.vo.student;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @author chenwenxiang
 *
 */
@ApiModel(value = "管理后台 - 学生 Excel 导出 Request VO", description = "参数和 StudentPageReqVO 是一致的")
@Data
public class StudentExportReqVO {
	@ApiModelProperty(value = "部门 ID", example = "101", notes = "根据前端选择年级号、班级号设置")
    private Long deptId;

    @ApiModelProperty(value = "性别", example = "1")
    private Integer sex;

    @ApiModelProperty(value = "学号", example = "2204240120", notes = "模糊查询")
    private String studentNo;

    @ApiModelProperty(value = "学生姓名", example = "张狗蛋", notes = "模糊查询")
    private String studentName;
}
