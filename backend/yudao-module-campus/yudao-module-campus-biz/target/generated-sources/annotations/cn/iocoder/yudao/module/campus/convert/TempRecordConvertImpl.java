package cn.iocoder.yudao.module.campus.convert;

import cn.iocoder.yudao.module.campus.controller.admin.vo.temprecord.TempRecordRespVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.temprecord.TempRecordSimpleRespVO;
import cn.iocoder.yudao.module.campus.dal.dataobject.TempRecordDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-29T07:50:30+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_331 (Oracle Corporation)"
)
public class TempRecordConvertImpl implements TempRecordConvert {

    @Override
    public List<TempRecordRespVO> convertList(List<TempRecordDO> list) {
        if ( list == null ) {
            return null;
        }

        List<TempRecordRespVO> list1 = new ArrayList<TempRecordRespVO>( list.size() );
        for ( TempRecordDO tempRecordDO : list ) {
            list1.add( convert( tempRecordDO ) );
        }

        return list1;
    }

    @Override
    public TempRecordRespVO convert(TempRecordDO recordDO) {
        if ( recordDO == null ) {
            return null;
        }

        TempRecordRespVO tempRecordRespVO = new TempRecordRespVO();

        tempRecordRespVO.setStudentId( recordDO.getStudentId() );
        tempRecordRespVO.setCreateTime( recordDO.getCreateTime() );
        tempRecordRespVO.setTemperature( recordDO.getTemperature() );

        return tempRecordRespVO;
    }

    @Override
    public List<TempRecordSimpleRespVO> convert(List<TempRecordDO> records) {
        if ( records == null ) {
            return null;
        }

        List<TempRecordSimpleRespVO> list = new ArrayList<TempRecordSimpleRespVO>( records.size() );
        for ( TempRecordDO tempRecordDO : records ) {
            list.add( tempRecordDOToTempRecordSimpleRespVO( tempRecordDO ) );
        }

        return list;
    }

    protected TempRecordSimpleRespVO tempRecordDOToTempRecordSimpleRespVO(TempRecordDO tempRecordDO) {
        if ( tempRecordDO == null ) {
            return null;
        }

        TempRecordSimpleRespVO tempRecordSimpleRespVO = new TempRecordSimpleRespVO();

        tempRecordSimpleRespVO.setCreateTime( tempRecordDO.getCreateTime() );
        tempRecordSimpleRespVO.setTemperature( tempRecordDO.getTemperature() );

        return tempRecordSimpleRespVO;
    }
}
