package cn.iocoder.yudao.module.campus.controller.admin.vo.device;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.TIME_ZONE_DEFAULT;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/7/27
 */
@ApiModel("智慧校园 - App更新信息的 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUpgradeRespVO {

    @ApiModelProperty(value = "app名")
    private String appName;

    @ApiModelProperty(value = "app版本号")
    private String version;

    @ApiModelProperty(value = "app更新的信息")
    private String upgradeUrl;

    @ApiModelProperty(value = "app更新的信息")
    private String upgradeInfo;

    @ApiModelProperty(value = "app更新时间", example = "2022-06-17")
    @JsonFormat(pattern = FORMAT_YEAR_MONTH_DAY, timezone = TIME_ZONE_DEFAULT)
    private Date updateTime;

}
