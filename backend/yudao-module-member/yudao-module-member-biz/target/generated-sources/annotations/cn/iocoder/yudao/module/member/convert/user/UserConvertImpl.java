package cn.iocoder.yudao.module.member.convert.user;

import cn.iocoder.yudao.module.member.api.user.dto.UserRespDTO;
import cn.iocoder.yudao.module.member.controller.app.user.vo.AppUserInfoRespVO;
import cn.iocoder.yudao.module.member.dal.dataobject.user.MemberUserDO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-29T07:49:26+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_331 (Oracle Corporation)"
)
public class UserConvertImpl implements UserConvert {

    @Override
    public AppUserInfoRespVO convert(MemberUserDO bean) {
        if ( bean == null ) {
            return null;
        }

        AppUserInfoRespVO appUserInfoRespVO = new AppUserInfoRespVO();

        appUserInfoRespVO.setNickname( bean.getNickname() );
        appUserInfoRespVO.setAvatar( bean.getAvatar() );

        return appUserInfoRespVO;
    }

    @Override
    public UserRespDTO convert2(MemberUserDO bean) {
        if ( bean == null ) {
            return null;
        }

        UserRespDTO userRespDTO = new UserRespDTO();

        userRespDTO.setId( bean.getId() );
        userRespDTO.setNickname( bean.getNickname() );
        userRespDTO.setStatus( bean.getStatus() );
        userRespDTO.setMobile( bean.getMobile() );

        return userRespDTO;
    }
}
