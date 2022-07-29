package cn.iocoder.yudao.module.campus.controller.admin.vo.temprecord;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @program: 智慧校园
 * @description: 学生测温记录的 VO
 * @author: pengzhenlong
 * @date: 2022/6/16
 */
@ApiModel("智慧校园 - 学生测温记录 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TempRecordPageReqVO extends PageParam {

    @ApiModelProperty(value = "学校 ID")
    private Long schoolId;

    @ApiModelProperty(value = "部门 ID", example = "101", notes = "根据前端选择年级号、班级号设置")
    private Long deptId;

    @ApiModelProperty(value = "性别", example = "1")
    private Integer sex;

    @ApiModelProperty(value = "学号", example = "2204240120", notes = "模糊查询")
    private String studentNo;

    @ApiModelProperty(value = "学生姓名", example = "张狗蛋", notes = "模糊查询")
    private String studentName;

    @ApiModelProperty(value = "测温时间", example = "2022-06-17")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

}
