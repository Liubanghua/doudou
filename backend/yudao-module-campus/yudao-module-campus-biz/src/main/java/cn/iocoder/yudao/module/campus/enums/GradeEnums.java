package cn.iocoder.yudao.module.campus.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @program: 智慧校园
 * @description: 检查记录等级的枚举类
 * @author: pengzhenlong
 * @date: 2022/7/8
 */
@Getter
@AllArgsConstructor
public enum GradeEnums {

    BAD(1), //很差
    POOR(2), //差
    GENERAL(3), //一般
    GOOD(4),  //好
    SUPERIOR(5) //优秀
    ;



    private final Integer grade;

}
