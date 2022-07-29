package cn.iocoder.yudao.module.campus.dal.dataobject;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @program: 智慧校园
 * @description: 学生和班级关联
 * @author: pengzhenlong
 * @date: 2022/6/15
 */
@TableName("campus_student_class")
@KeySequence("campus_student_class_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
public class StudentClassDO extends BaseDO {

    @TableId
    private Long id;

    /**
     * 学生 ID
     */
    private Long studentId;

    /**
     * 班级 ID
     */
    private Long classId;

}
