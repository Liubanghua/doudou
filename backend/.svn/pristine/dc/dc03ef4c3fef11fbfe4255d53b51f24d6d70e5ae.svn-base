package cn.iocoder.yudao.module.campus.controller.admin.vo.temprecord;

import cn.iocoder.yudao.module.system.controller.admin.user.vo.user.UserPageItemRespVO;
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
 * @date: 2022/6/16
 */
@ApiModel("智慧校园 - 学生信息 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TempRecordRespVO extends TempRecordBaseVO {

    @ApiModelProperty(value = "学生姓名", example = "小彭")
    private String studentName;

    @ApiModelProperty(value = "学生学号", example = "2204240120")
    private String studentNo;

    private Integer sex;

    @ApiModelProperty(value = "部门 ID", example = "101", notes = "根据前端选择年级号、班级号设置")
    private Long deptId;

    @ApiModelProperty(value = "年级", example = "三年级")
    private String campusGradeName;

    @ApiModelProperty(value = "班级号", example = "2班")
    private String campusClassName;

    @ApiModelProperty(value = "测量温度", example = "36.5f")
    private float temperature;



}
