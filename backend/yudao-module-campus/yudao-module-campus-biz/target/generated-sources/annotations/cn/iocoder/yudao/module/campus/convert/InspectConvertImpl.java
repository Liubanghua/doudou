package cn.iocoder.yudao.module.campus.convert;

import cn.iocoder.yudao.module.campus.controller.admin.vo.inspect.InspectCreateReqVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.inspect.InspectRespVO;
import cn.iocoder.yudao.module.campus.dal.dataobject.InspectDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-29T07:50:30+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_331 (Oracle Corporation)"
)
public class InspectConvertImpl implements InspectConvert {

    @Override
    public List<InspectRespVO> convert(List<InspectDO> inspectDOS) {
        if ( inspectDOS == null ) {
            return null;
        }

        List<InspectRespVO> list = new ArrayList<InspectRespVO>( inspectDOS.size() );
        for ( InspectDO inspectDO : inspectDOS ) {
            list.add( inspectDOToInspectRespVO( inspectDO ) );
        }

        return list;
    }

    @Override
    public InspectDO convert(InspectCreateReqVO reqVO) {
        if ( reqVO == null ) {
            return null;
        }

        InspectDO inspectDO = new InspectDO();

        inspectDO.setCreateTime( reqVO.getCreateTime() );
        if ( reqVO.getGrade() != null ) {
            inspectDO.setGrade( reqVO.getGrade() );
        }
        inspectDO.setRemarks( reqVO.getRemarks() );

        return inspectDO;
    }

    protected InspectRespVO inspectDOToInspectRespVO(InspectDO inspectDO) {
        if ( inspectDO == null ) {
            return null;
        }

        InspectRespVO inspectRespVO = new InspectRespVO();

        inspectRespVO.setGrade( inspectDO.getGrade() );
        inspectRespVO.setRemarks( inspectDO.getRemarks() );
        inspectRespVO.setCreateTime( inspectDO.getCreateTime() );

        return inspectRespVO;
    }
}
