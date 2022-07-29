package cn.iocoder.yudao.module.campus.convert;

import cn.iocoder.yudao.module.campus.controller.admin.vo.device.DeviceRegisterReqVO;
import cn.iocoder.yudao.module.campus.dal.dataobject.DeviceDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/7/15
 */
@Mapper
public interface DeviceConvert {

    DeviceConvert INSTANCE = Mappers.getMapper(DeviceConvert.class);

    DeviceDO convert(DeviceRegisterReqVO reqVO);

}
