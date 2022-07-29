package cn.iocoder.yudao.module.campus.service.impl;

import cn.iocoder.yudao.module.campus.controller.admin.vo.device.MeasureDataUploadReqVO;
import cn.iocoder.yudao.module.campus.dal.dataobject.StudentDO;
import cn.iocoder.yudao.module.campus.dal.mysql.StudentMapper;
import cn.iocoder.yudao.module.campus.service.MeasureDataUploadService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.campus.enums.ErrorCodeConstants.*;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/7/28
 */
@Service
public class MeasureDataUploadServiceImpl implements MeasureDataUploadService {

    @Resource
    private StudentMapper studentMapper;

    @Override
    public void validateMeasureData(MeasureDataUploadReqVO reqVO) {

    }
}
