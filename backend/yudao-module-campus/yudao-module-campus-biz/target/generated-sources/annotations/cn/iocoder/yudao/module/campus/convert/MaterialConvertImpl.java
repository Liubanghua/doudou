package cn.iocoder.yudao.module.campus.convert;

import cn.iocoder.yudao.module.campus.controller.admin.vo.material.MaterialCreateReqVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.material.MaterialRespVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.material.MaterialUpdateReqVO;
import cn.iocoder.yudao.module.campus.dal.dataobject.MaterialDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-29T07:50:30+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_331 (Oracle Corporation)"
)
public class MaterialConvertImpl implements MaterialConvert {

    @Override
    public List<MaterialRespVO> convert(List<MaterialDO> list) {
        if ( list == null ) {
            return null;
        }

        List<MaterialRespVO> list1 = new ArrayList<MaterialRespVO>( list.size() );
        for ( MaterialDO materialDO : list ) {
            list1.add( materialDOToMaterialRespVO( materialDO ) );
        }

        return list1;
    }

    @Override
    public MaterialDO convert(MaterialCreateReqVO reqVO) {
        if ( reqVO == null ) {
            return null;
        }

        MaterialDO materialDO = new MaterialDO();

        materialDO.setName( reqVO.getName() );
        if ( reqVO.getQuantity() != null ) {
            materialDO.setQuantity( reqVO.getQuantity().intValue() );
        }
        materialDO.setRemarks( reqVO.getRemarks() );

        return materialDO;
    }

    @Override
    public MaterialDO convert(MaterialUpdateReqVO reqVO) {
        if ( reqVO == null ) {
            return null;
        }

        MaterialDO materialDO = new MaterialDO();

        materialDO.setId( reqVO.getId() );
        materialDO.setName( reqVO.getName() );
        if ( reqVO.getQuantity() != null ) {
            materialDO.setQuantity( reqVO.getQuantity().intValue() );
        }
        materialDO.setRemarks( reqVO.getRemarks() );

        return materialDO;
    }

    protected MaterialRespVO materialDOToMaterialRespVO(MaterialDO materialDO) {
        if ( materialDO == null ) {
            return null;
        }

        MaterialRespVO materialRespVO = new MaterialRespVO();

        materialRespVO.setName( materialDO.getName() );
        if ( materialDO.getQuantity() != null ) {
            materialRespVO.setQuantity( materialDO.getQuantity().longValue() );
        }
        materialRespVO.setRemarks( materialDO.getRemarks() );
        materialRespVO.setId( materialDO.getId() );

        return materialRespVO;
    }
}
