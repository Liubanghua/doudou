package cn.iocoder.yudao.module.campus.convert;

import cn.iocoder.yudao.module.campus.controller.admin.vo.inspect.InspectCreateReqVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.inspect.InspectRespVO;
import cn.iocoder.yudao.module.campus.dal.dataobject.InspectDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface InspectConvert {

    InspectConvert INSTANCE = Mappers.getMapper(InspectConvert.class);


    List<InspectRespVO> convert(List<InspectDO> inspectDOS);

    InspectDO convert(InspectCreateReqVO reqVO);


}
