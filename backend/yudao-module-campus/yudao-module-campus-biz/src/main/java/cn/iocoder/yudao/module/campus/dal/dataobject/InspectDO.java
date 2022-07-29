package cn.iocoder.yudao.module.campus.dal.dataobject;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/7/7
 */
@TableName("campus_inspect")
@KeySequence("campus_inspect_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
public class InspectDO extends BaseDO {

    @TableId
    private Long id;

    /**
     * 部门ID
     * <p>
     * 关联 {@link cn.iocoder.yudao.module.system.dal.dataobject.dept.DeptDO#getId()}
     */
    private Long deptId;

    /**
     * 检查等级
     *
     * 关联 {@link cn.iocoder.yudao.module.campus.enums.GradeEnums#}
     */
    private int grade;

    /**
     * 备注
     */
    private String remarks;

}
