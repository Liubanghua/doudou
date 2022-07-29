package cn.iocoder.yudao.module.campus.dal.dataobject;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/7/8
 */
@TableName("campus_covid")
@KeySequence("campus_covid_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
public class CovidCloseDO extends BaseDO {

    @TableId
    private Long id;

    private Long studentId;

    //冗余字段，学生所在部门
    private Long deptId;

    /**
     * 密接等级
     * <p>
     * 关联 {@link cn.iocoder.yudao.module.campus.enums.CovidTypeEnums }
     */
    private Integer grade;


}
