package cn.iocoder.yudao.module.system.convert.tenant;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.tenant.vo.tenant.TenantCreateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.tenant.vo.tenant.TenantExcelVO;
import cn.iocoder.yudao.module.system.controller.admin.tenant.vo.tenant.TenantRespVO;
import cn.iocoder.yudao.module.system.controller.admin.tenant.vo.tenant.TenantUpdateReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.tenant.TenantDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-29T07:49:39+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_331 (Oracle Corporation)"
)
public class TenantConvertImpl implements TenantConvert {

    @Override
    public TenantDO convert(TenantCreateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        TenantDO tenantDO = new TenantDO();

        tenantDO.setName( bean.getName() );
        tenantDO.setContactName( bean.getContactName() );
        tenantDO.setContactMobile( bean.getContactMobile() );
        tenantDO.setStatus( bean.getStatus() );
        tenantDO.setDomain( bean.getDomain() );
        tenantDO.setPackageId( bean.getPackageId() );
        tenantDO.setExpireTime( bean.getExpireTime() );
        tenantDO.setAccountCount( bean.getAccountCount() );

        return tenantDO;
    }

    @Override
    public TenantDO convert(TenantUpdateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        TenantDO tenantDO = new TenantDO();

        tenantDO.setId( bean.getId() );
        tenantDO.setName( bean.getName() );
        tenantDO.setContactName( bean.getContactName() );
        tenantDO.setContactMobile( bean.getContactMobile() );
        tenantDO.setStatus( bean.getStatus() );
        tenantDO.setDomain( bean.getDomain() );
        tenantDO.setPackageId( bean.getPackageId() );
        tenantDO.setExpireTime( bean.getExpireTime() );
        tenantDO.setAccountCount( bean.getAccountCount() );

        return tenantDO;
    }

    @Override
    public TenantRespVO convert(TenantDO bean) {
        if ( bean == null ) {
            return null;
        }

        TenantRespVO tenantRespVO = new TenantRespVO();

        tenantRespVO.setName( bean.getName() );
        tenantRespVO.setContactName( bean.getContactName() );
        tenantRespVO.setContactMobile( bean.getContactMobile() );
        tenantRespVO.setStatus( bean.getStatus() );
        tenantRespVO.setDomain( bean.getDomain() );
        tenantRespVO.setPackageId( bean.getPackageId() );
        tenantRespVO.setExpireTime( bean.getExpireTime() );
        tenantRespVO.setAccountCount( bean.getAccountCount() );
        tenantRespVO.setId( bean.getId() );
        tenantRespVO.setCreateTime( bean.getCreateTime() );

        return tenantRespVO;
    }

    @Override
    public List<TenantRespVO> convertList(List<TenantDO> list) {
        if ( list == null ) {
            return null;
        }

        List<TenantRespVO> list1 = new ArrayList<TenantRespVO>( list.size() );
        for ( TenantDO tenantDO : list ) {
            list1.add( convert( tenantDO ) );
        }

        return list1;
    }

    @Override
    public PageResult<TenantRespVO> convertPage(PageResult<TenantDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<TenantRespVO> pageResult = new PageResult<TenantRespVO>();

        pageResult.setList( convertList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    @Override
    public List<TenantExcelVO> convertList02(List<TenantDO> list) {
        if ( list == null ) {
            return null;
        }

        List<TenantExcelVO> list1 = new ArrayList<TenantExcelVO>( list.size() );
        for ( TenantDO tenantDO : list ) {
            list1.add( tenantDOToTenantExcelVO( tenantDO ) );
        }

        return list1;
    }

    protected TenantExcelVO tenantDOToTenantExcelVO(TenantDO tenantDO) {
        if ( tenantDO == null ) {
            return null;
        }

        TenantExcelVO tenantExcelVO = new TenantExcelVO();

        tenantExcelVO.setId( tenantDO.getId() );
        tenantExcelVO.setName( tenantDO.getName() );
        tenantExcelVO.setContactName( tenantDO.getContactName() );
        tenantExcelVO.setContactMobile( tenantDO.getContactMobile() );
        tenantExcelVO.setStatus( tenantDO.getStatus() );
        tenantExcelVO.setCreateTime( tenantDO.getCreateTime() );

        return tenantExcelVO;
    }
}
