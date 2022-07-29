package cn.iocoder.yudao.module.campus.service;

import cn.iocoder.yudao.module.campus.controller.admin.vo.device.MeasureDataUploadReqVO;

public interface MeasureDataUploadService {

    void validateMeasureData(MeasureDataUploadReqVO reqVO);

}
