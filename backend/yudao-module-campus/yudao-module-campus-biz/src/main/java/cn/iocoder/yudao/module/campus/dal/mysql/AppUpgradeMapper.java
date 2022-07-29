package cn.iocoder.yudao.module.campus.dal.mysql;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.campus.dal.dataobject.AppUpgradeDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AppUpgradeMapper extends BaseMapperX<AppUpgradeDO> {

    @Select("select * from campus_app_upgrade\n" +
            "where id = (select max(id) from campus_app_upgrade where deleted = 0)")
    AppUpgradeDO selectNewestOne();
}
