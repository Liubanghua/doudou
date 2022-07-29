package cn.iocoder.yudao.module.campus.convert;

import cn.iocoder.yudao.module.campus.controller.admin.vo.device.DeviceRegisterReqVO;
import cn.iocoder.yudao.module.campus.dal.dataobject.DeviceDO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-29T07:50:30+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_331 (Oracle Corporation)"
)
public class DeviceConvertImpl implements DeviceConvert {

    @Override
    public DeviceDO convert(DeviceRegisterReqVO reqVO) {
        if ( reqVO == null ) {
            return null;
        }

        DeviceDO deviceDO = new DeviceDO();

        deviceDO.setDeviceId( reqVO.getDeviceId() );
        deviceDO.setBaiduSn( reqVO.getBaiduSn() );

        return deviceDO;
    }
}
