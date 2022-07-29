package cn.iocoder.yudao.module.campus.controller.admin.vo.device;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @program: 智慧校园
 * @description: 测量数据上传
 * @author: pengzhenlong
 * @date: 2022/7/27
 */
@ApiModel("智慧校园 - 设备测量数据上传的 Request VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeasureDataUploadReqVO {

    @ApiModelProperty(value = "姓名", required = true, example = "李四")
    private String studentName;

    @ApiModelProperty(value = "学号", required = true, example = "李四")
    private String studentNo;

    @ApiModelProperty(value = "身高")
    private Float height;

    @ApiModelProperty(value = "体重")
    private Float weight;

    @ApiModelProperty(value = "体脂")
    private Float bodyFatRate;

    @ApiModelProperty(value = "体重指数")
    private Float bmi;

    @ApiModelProperty(value = "测量体温")
    private Float temperature;


}
