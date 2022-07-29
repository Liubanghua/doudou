package cn.iocoder.yudao.module.campus.dal.dataobject;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @program: 智慧校园
 * @description: 学生表
 * @author: pengzhenlong
 * @date: 2022/6/15
 */
@TableName("campus_student")
@KeySequence("campus_student_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
public class StudentDO extends BaseDO {

    @TableId
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别. 0: 女  1: 男
     */
    private Integer sex;

    /**
     * 学籍号
     */
    private String studentNo;

    /**
     * 头像， 实际为头像保存地址
     */
    private String avatar;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 部门 ID
     */
    private Long deptId;

    /**
     * nfc号，预留
     */
    private String nfc;

}
