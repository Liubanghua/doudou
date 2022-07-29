package cn.iocoder.yudao.module.campus.controller.admin.vo.temprecord;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/7/13
 */
@Data
public class TempRecordPreviewRespVO {

    @ApiModelProperty(value = "部门ID", example = "101")
    private Long deptId;

    @ApiModelProperty(value = "年级名称", example = "一年级")
    private String campusGradeName;

    @ApiModelProperty(value = "班级名称", notes = "年级预览时此字段为null", example = "1版")
    private String campusClassName;

    @ApiModelProperty(value = "学生总人数", example = "121")
    private Integer total;

    @ApiModelProperty(value = "发烧人数", example = "20")
    private Integer fever;

    @ApiModelProperty(value = "体温正常人数", example = "20")
    private Integer normal;

    @ApiModelProperty(value = "体温异常人数", notes = "测量体温不在正常范围内的人数", example = "20")
    private Integer abnormal;

    @ApiModelProperty(value = "未测量体温人数", example = "20")
    private Integer unmeasured;


}
