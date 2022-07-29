package cn.iocoder.yudao.module.campus.controller.admin.vo.device;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/7/15
 */
@ApiModel("智慧校园 - 设备注册的 Request VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DeviceRegisterReqVO extends DeviceBaseVO {

    @ApiModelProperty(value = "设备编号", required = true, example = "ST15825Ty")
    @NotEmpty(message = "deviceId 不能为空")
    private String deviceId;

    @ApiModelProperty(value = "学校完整名称", required = true, example = "武汉市邮科院小学")
    @NotEmpty(message = "学校 不能为空")
    private String schoolName;

    @ApiModelProperty(value = "年级完整名称", required = true, example = "一年级")
    @NotEmpty(message = "年级 不能为空")
    private String gradeName;

    @ApiModelProperty(value = "班级完整名称", required = true, example = "2班")
    @NotEmpty(message = "班级 不能为空")
    private String className;

    @ApiModelProperty(value = "百度人脸识别SN号", example = "jk14785sa")
    private String baiduSn;

}
