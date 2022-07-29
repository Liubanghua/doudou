package cn.iocoder.yudao.module.member.convert.auth;

import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.module.member.controller.app.auth.vo.AppAuthResetPasswordReqVO;
import cn.iocoder.yudao.module.member.controller.app.auth.vo.AppAuthSmsLoginReqVO;
import cn.iocoder.yudao.module.member.controller.app.auth.vo.AppAuthSmsSendReqVO;
import cn.iocoder.yudao.module.member.controller.app.auth.vo.AppAuthSocialBindLoginReqVO;
import cn.iocoder.yudao.module.member.controller.app.auth.vo.AppAuthSocialQuickLoginReqVO;
import cn.iocoder.yudao.module.member.controller.app.social.vo.AppSocialUserUnbindReqVO;
import cn.iocoder.yudao.module.member.dal.dataobject.user.MemberUserDO;
import cn.iocoder.yudao.module.system.api.sms.dto.code.SmsCodeSendReqDTO;
import cn.iocoder.yudao.module.system.api.sms.dto.code.SmsCodeUseReqDTO;
import cn.iocoder.yudao.module.system.api.social.dto.SocialUserBindReqDTO;
import cn.iocoder.yudao.module.system.api.social.dto.SocialUserUnbindReqDTO;
import cn.iocoder.yudao.module.system.enums.sms.SmsSceneEnum;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-29T07:49:26+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_331 (Oracle Corporation)"
)
public class AuthConvertImpl implements AuthConvert {

    @Override
    public LoginUser convert0(MemberUserDO bean) {
        if ( bean == null ) {
            return null;
        }

        LoginUser loginUser = new LoginUser();

        loginUser.setUsername( bean.getMobile() );
        loginUser.setId( bean.getId() );
        loginUser.setUpdateTime( bean.getUpdateTime() );
        loginUser.setPassword( bean.getPassword() );
        loginUser.setStatus( bean.getStatus() );
        loginUser.setTenantId( bean.getTenantId() );

        return loginUser;
    }

    @Override
    public SocialUserBindReqDTO convert(Long userId, Integer userType, AppAuthSocialBindLoginReqVO reqVO) {
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
    public SocialUserBindReqDTO convert(Long userId, Integer userType, AppAuthSocialQuickLoginReqVO reqVO) {
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
    public SocialUserUnbindReqDTO convert(Long userId, Integer userType, AppSocialUserUnbindReqVO reqVO) {
        if ( userId == null && userType == null && reqVO == null ) {
            return null;
        }

        SocialUserUnbindReqDTO socialUserUnbindReqDTO = new SocialUserUnbindReqDTO();

        if ( userId != null ) {
            socialUserUnbindReqDTO.setUserId( userId );
        }
        if ( userType != null ) {
            socialUserUnbindReqDTO.setUserType( userType );
        }
        if ( reqVO != null ) {
            socialUserUnbindReqDTO.setType( reqVO.getType() );
        }

        return socialUserUnbindReqDTO;
    }

    @Override
    public SmsCodeSendReqDTO convert(AppAuthSmsSendReqVO reqVO) {
        if ( reqVO == null ) {
            return null;
        }

        SmsCodeSendReqDTO smsCodeSendReqDTO = new SmsCodeSendReqDTO();

        smsCodeSendReqDTO.setMobile( reqVO.getMobile() );
        smsCodeSendReqDTO.setScene( reqVO.getScene() );

        return smsCodeSendReqDTO;
    }

    @Override
    public SmsCodeUseReqDTO convert(AppAuthResetPasswordReqVO reqVO, SmsSceneEnum scene, String usedIp) {
        if ( reqVO == null && scene == null && usedIp == null ) {
            return null;
        }

        SmsCodeUseReqDTO smsCodeUseReqDTO = new SmsCodeUseReqDTO();

        if ( reqVO != null ) {
            smsCodeUseReqDTO.setMobile( reqVO.getMobile() );
            smsCodeUseReqDTO.setCode( reqVO.getCode() );
        }
        if ( scene != null ) {
            smsCodeUseReqDTO.setScene( scene.getScene() );
        }
        if ( usedIp != null ) {
            smsCodeUseReqDTO.setUsedIp( usedIp );
        }

        return smsCodeUseReqDTO;
    }

    @Override
    public SmsCodeUseReqDTO convert(AppAuthSmsLoginReqVO reqVO, Integer scene, String usedIp) {
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
