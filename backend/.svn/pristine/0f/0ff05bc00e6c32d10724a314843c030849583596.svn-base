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
 * @date: 2022/7/27
 */
@TableName("campus_student_height")
@KeySequence("campus_student_height_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
public class StudentHeightDO extends BaseDO {

    @TableId
    private Long id;

    /**
     * 学生ID
     */
    private Long studentId;

    /**
     * 学生身高
     */
    private Float height;

}
