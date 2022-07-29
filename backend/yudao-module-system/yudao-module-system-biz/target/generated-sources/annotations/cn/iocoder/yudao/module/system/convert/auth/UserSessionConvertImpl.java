package cn.iocoder.yudao.module.system.convert.auth;

import cn.iocoder.yudao.module.system.controller.admin.auth.vo.session.UserSessionPageItemRespVO;
import cn.iocoder.yudao.module.system.dal.dataobject.auth.UserSessionDO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-29T07:49:39+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_331 (Oracle Corporation)"
)
public class UserSessionConvertImpl implements UserSessionConvert {

    @Override
    public UserSessionPageItemRespVO convert(UserSessionDO session) {
        if ( session == null ) {
            return null;
        }

        UserSessionPageItemRespVO userSessionPageItemRespVO = new UserSessionPageItemRespVO();

        if ( session.getId() != null ) {
            userSessionPageItemRespVO.setId( String.valueOf( session.getId() ) );
        }
        userSessionPageItemRespVO.setUserIp( session.getUserIp() );
        userSessionPageItemRespVO.setUserAgent( session.getUserAgent() );
        userSessionPageItemRespVO.setCreateTime( session.getCreateTime() );
        userSessionPageItemRespVO.setUsername( session.getUsername() );

        return userSessionPageItemRespVO;
    }
}
