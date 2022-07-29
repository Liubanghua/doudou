package cn.iocoder.yudao.module.campus.dal.dataobject;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/7/15
 */
@TableName("campus_device")
@KeySequence("campus_device_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeviceDO extends BaseDO {

    @TableId
    private Long id;

    /**
     * 与班级绑定
     */
    private Long deptId;

    /**
     * 设备自身编号，唯一
     */
    private String deviceId;

    /**
     * 预留字段，使用百度人脸识别功能可能会用到
     */
    private String baiduSn;


}
