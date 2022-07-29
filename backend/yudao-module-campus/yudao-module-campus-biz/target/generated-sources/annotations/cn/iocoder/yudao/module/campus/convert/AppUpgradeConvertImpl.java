package cn.iocoder.yudao.module.campus.convert;

import cn.iocoder.yudao.module.campus.controller.admin.vo.device.AppUpgradeRespVO;
import cn.iocoder.yudao.module.campus.dal.dataobject.AppUpgradeDO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-29T07:50:30+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_331 (Oracle Corporation)"
)
public class AppUpgradeConvertImpl implements AppUpgradeConvert {

    @Override
    public AppUpgradeRespVO convert(AppUpgradeDO app) {
        if ( app == null ) {
            return null;
        }

        AppUpgradeRespVO appUpgradeRespVO = new AppUpgradeRespVO();

        appUpgradeRespVO.setAppName( app.getAppName() );
        appUpgradeRespVO.setVersion( app.getVersion() );
        appUpgradeRespVO.setUpgradeUrl( app.getUpgradeUrl() );
        appUpgradeRespVO.setUpgradeInfo( app.getUpgradeInfo() );
        appUpgradeRespVO.setUpdateTime( app.getUpdateTime() );

        return appUpgradeRespVO;
    }
}
