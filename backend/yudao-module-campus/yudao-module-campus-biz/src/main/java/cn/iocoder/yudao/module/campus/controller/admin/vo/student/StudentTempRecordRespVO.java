package cn.iocoder.yudao.module.campus.controller.admin.vo.student;

import cn.iocoder.yudao.module.campus.controller.admin.vo.temprecord.TempRecordSimpleRespVO;
import cn.iocoder.yudao.module.campus.dal.dataobject.TempRecordDO;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.*;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/6/17
 */
@ApiModel("智慧校园 - 学生测温信息 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StudentTempRecordRespVO extends StudentBaseVO {

    @ApiModelProperty(value = "学生编号", required = true, example = "1")
    private Long id;

    @ApiModelProperty(value = "测温时间", example = "2022-06-17")
    @JsonFormat(pattern = FORMAT_YEAR_MONTH_DAY, timezone = TIME_ZONE_DEFAULT)
    private Date recordCreateTime;

    @ApiModelProperty(value = "测温记录", required = true)
    private List<TempRecordSimpleRespVO> tempRecords;


}
