package cn.iocoder.yudao.module.campus.controller.admin.vo.student;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/7/25
 */
@ApiModel("智慧校园 - 查询学生测温详细信息的 Request VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentTempsDetailReqVO {

    @ApiModelProperty(value = "学生ID")
    @NotNull(message = "学生ID不能为空")
    private Long studentId;

    @ApiModelProperty(value = "测温时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY)
    private Date measureTime;

}
