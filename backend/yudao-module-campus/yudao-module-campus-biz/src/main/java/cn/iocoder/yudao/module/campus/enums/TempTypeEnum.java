package cn.iocoder.yudao.module.campus.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @program: 智慧校园
 * @description: 测温类型
 * @author: pengzhenlong
 * @date: 2022/7/12
 */
@Getter
@AllArgsConstructor
public enum TempTypeEnum {

    NORMAL(1),
    FEVER(2),
    ABNORMAL(3);


    private final Integer type;

}
