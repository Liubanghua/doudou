package cn.iocoder.yudao.module.campus.dal.dataobject;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/7/27
 */
@TableName("campus_app_upgrade")
@KeySequence("campus_app_upgrade_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
public class AppUpgradeDO extends BaseDO {

    @TableId
    private Long id;

    /**
     * APP名称
     */
    private String appName;

    /**
     * 服务器端APP版本号
     */
    private String version;

    /**
     * 更新APK的下载链接
     */
    private String upgradeUrl;

    /**
     * app更新的信息
     */
    private String upgradeInfo;

    /**
     * app更新时间
     */
    private Date upgradeTime;


}
