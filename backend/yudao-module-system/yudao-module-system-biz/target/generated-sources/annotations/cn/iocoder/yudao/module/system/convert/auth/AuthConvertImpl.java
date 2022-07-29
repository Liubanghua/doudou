package cn.iocoder.yudao.module.system.convert.auth;

import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.module.system.api.sms.dto.code.SmsCodeSendReqDTO;
import cn.iocoder.yudao.module.system.api.sms.dto.code.SmsCodeUseReqDTO;
import cn.iocoder.yudao.module.system.api.social.dto.SocialUserBindReqDTO;
import cn.iocoder.yudao.module.system.controller.admin.auth.vo.auth.AuthMenuRespVO;
import cn.iocoder.yudao.module.system.controller.admin.auth.vo.auth.AuthSmsLoginReqVO;
import cn.iocoder.yudao.module.system.controller.admin.auth.vo.auth.AuthSmsSendReqVO;
import cn.iocoder.yudao.module.system.controller.admin.auth.vo.auth.AuthSocialBindLoginReqVO;
import cn.iocoder.yudao.module.system.controller.admin.auth.vo.auth.AuthSocialQuickLoginReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.permission.MenuDO;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-29T07:49:39+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_331 (Oracle Corporation)"
)
public class AuthConvertImpl implements AuthConvert {

    @Override
    public LoginUser convert0(AdminUserDO bean) {
        if ( bean == null ) {
            return null;
        }

        LoginUser loginUser = new LoginUser();

        loginUser.setId( bean.getId() );
        loginUser.setUsername( bean.getUsername() );
        loginUser.setPassword( bean.getPassword() );
        loginUser.setStatus( bean.getStatus() );
        loginUser.setTenantId( bean.getTenantId() );
        loginUser.setDeptId( bean.getDeptId() );

        return loginUser;
    }

    @Override
    public AuthMenuRespVO convertTreeNode(MenuDO menu) {
        if ( menu == null ) {
            return null;
        }

        AuthMenuRespVO authMenuRespVO = new AuthMenuRespVO();

        authMenuRespVO.setId( menu.getId() );
        authMenuRespVO.setParentId( menu.getParentId() );
        authMenuRespVO.setName( menu.getName() );
        authMenuRespVO.setPath( menu.getPath() );
        authMenuRespVO.setComponent( menu.getComponent() );
        authMenuRespVO.setIcon( menu.getIcon() );
        authMenuRespVO.setVisible( menu.getVisible() );
        authMenuRespVO.setKeepAlive( menu.getKeepAlive() );

        return authMenuRespVO;
    }

    @Override
    public SocialUserBindReqDTO convert(Long userId, Integer userType, AuthSocialBindLoginReqVO reqVO) {
        if ( userId == null && userType == null && reqVO == null ) {
            return null;
        }

        SocialUserBindReqDTO socialUserBindReqDTO = new SocialUserBindReqDTO();

        if ( userId != null ) {
            socialUserBindReqDTO.setUserId( userId );
        }
        if ( userType != null ) {
            socialUserBindReqDTO.setUserType( userType );
        }
        if ( reqVO != null ) {
            socialUserBindReqDTO.setType( reqVO.getType() );
            socialUserBindReqDTO.setCode( reqVO.getCode() );
            socialUserBindReqDTO.setState( reqVO.getState() );
        }

        return socialUserBindReqDTO;
    }

    @Override
    public SocialUserBindReqDTO convert(Long userId, Integer userType, AuthSocialQuickLoginReqVO reqVO) {
        if ( userId == null && userType == null && reqVO == null ) {
            return null;
        }

        SocialUserBindReqDTO socialUserBindReqDTO = new SocialUserBindReqDTO();

        if ( userId != null ) {
            socialUserBindReqDTO.setUserId( userId );
        }
        if ( userType != null ) {
            socialUserBindReqDTO.setUserType( userType );
        }
        if ( reqVO != null ) {
            socialUserBindReqDTO.setType( reqVO.getType() );
            socialUserBindReqDTO.setCode( reqVO.getCode() );
            socialUserBindReqDTO.setState( reqVO.getState() );
        }

        return socialUserBindReqDTO;
    }

    @Override
    public SmsCodeSendReqDTO convert(AuthSmsSendReqVO reqVO) {
        if ( reqVO == null ) {
            return null;
        }

        SmsCodeSendReqDTO smsCodeSendReqDTO = new SmsCodeSendReqDTO();

        smsCodeSendReqDTO.setMobile( reqVO.getMobile() );
        smsCodeSendReqDTO.setScene( reqVO.getScene() );

        return smsCodeSendReqDTO;
    }

    @Override
    public SmsCodeUseReqDTO convert(AuthSmsLoginReqVO reqVO, Integer scene, String usedIp) {
        if ( reqVO == null && scene == null && usedIp == null ) {
            return null;
        }

        SmsCodeUseReqDTO smsCodeUseReqDTO = new SmsCodeUseReqDTO();

        if ( reqVO != null ) {
            smsCodeUseReqDTO.setMobile( reqVO.getMobile() );
            smsCodeUseReqDTO.setCode( reqVO.getCode() );
        }
        if ( scene != null ) {
            smsCodeUseReqDTO.setScene( scene );
        }
        if ( usedIp != null ) {
            smsCodeUseReqDTO.setUsedIp( usedIp );
        }

        return smsCodeUseReqDTO;
    }
}
