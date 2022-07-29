package cn.iocoder.yudao.module.campus.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/7/8
 */
@Getter
@AllArgsConstructor
public enum CovidTypeEnums {

    CLOSE(1),
    SUB_CLOSE(2);

    private final Integer type;

}
