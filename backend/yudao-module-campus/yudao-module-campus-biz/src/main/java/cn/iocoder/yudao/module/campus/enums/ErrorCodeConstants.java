package cn.iocoder.yudao.module.campus.enums;


import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * 智慧校园 错误码枚举类
 *
 * 智慧校园 系统，使用 2-002-000-000 段
 */
public interface ErrorCodeConstants {

    // ========== 智慧校园--学生 2002020000 ============
    ErrorCode STUDENT_EXISTSED = new ErrorCode(1002020000, "学号已存在");
    ErrorCode STUDENT_NOT_EXISTSED = new ErrorCode(1002020001, "学生不存在");

    // ========== 智慧校园--物资 2002021000 ============
    ErrorCode MATERIAL_EXISTSED = new ErrorCode(1002021000, "物资已存在");
    ErrorCode MATERIAL_NOT_EXISTSED = new ErrorCode(1002021001, "物资不存在");

    // ========== 智慧校园--设备 2002022000 ============
    ErrorCode DEVICE_EXISTSED = new ErrorCode(2002022000, "设备已存在");
    ErrorCode SCHOOL_NAME_NOT_EXISTSED = new ErrorCode(2002022001, "设备填写学校不存在");
    ErrorCode GRADE_NAME_NOT_EXISTSED = new ErrorCode(2002022002, "设备填写年级不存在");
    ErrorCode CLASS_NAME_NOT_EXISTSED = new ErrorCode(2002022003, "设备填写班级不存在");
    ErrorCode DEVICE_NOT_REGISTER = new ErrorCode(2002022004, "设备未注册");
    ErrorCode DEVICE_UPLOAD_FILE_NAME_ERROR = new ErrorCode(2002022005, "设备上传文件的文件名异常");

    // ========== 智慧校园--班级 2002023000 ============
    ErrorCode SCHOOL_NOT_EXISTSED = new ErrorCode(2002023000, "学校不存在");
    ErrorCode GRADE_NOT_EXISTSED = new ErrorCode(2002023001, "年级不存在");

    // ========== 智慧校园--测量数据 2002024000 ============
    ErrorCode HEIGHT_ABNORMAL = new ErrorCode(2002024000, "身高异常");
    ErrorCode WEIGHT_ABNORMAL = new ErrorCode(2002024001, "体重异常");
    ErrorCode TEMPERATURE_ABNORMAL = new ErrorCode(2002024001, "体温异常");
}
