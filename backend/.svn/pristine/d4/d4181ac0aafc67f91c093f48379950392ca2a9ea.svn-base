package cn.iocoder.yudao.module.campus.convert;

import cn.iocoder.yudao.module.campus.controller.admin.vo.material.MaterialCreateReqVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.material.MaterialRespVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.material.MaterialUpdateReqVO;
import cn.iocoder.yudao.module.campus.dal.dataobject.MaterialDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MaterialConvert {

    MaterialConvert INSTANCE = Mappers.getMapper(MaterialConvert.class);

    List<MaterialRespVO> convert(List<MaterialDO> list);

    MaterialDO convert(MaterialCreateReqVO reqVO);

    MaterialDO convert(MaterialUpdateReqVO reqVO);

}
