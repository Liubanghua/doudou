package cn.iocoder.yudao.module.campus.dal.mysql;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.campus.controller.admin.vo.material.MaterialSchoolRespVO;
import cn.iocoder.yudao.module.campus.dal.dataobject.MaterialDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/7/6
 */
@Mapper
public interface MaterialMapper extends BaseMapperX<MaterialDO> {

    default MaterialDO selectByNameAndDeptId(String name, Long deptId) {
        return selectOne(MaterialDO::getName, name, MaterialDO::getDeptId, deptId);
    }

    @Select({
            "<script>",
                "select dept.id as dept_id, dept.`name` as school_name,",
                " sum(case material.`name` when '口罩' then material.quantity else 0 end) as mask_total,",
                " sum(case material.`name` when '酒精' then material.quantity else 0 end)  as alcohol_total,",
                " sum(case material.`name` when '洗手液' then material.quantity else 0 end)  as handwash_total,",
                " sum(case material.`name` when '帐篷' then material.quantity else 0 end)  as tent_total",
                "from system_dept dept ",
                "left join campus_material material on ",
                "material.dept_id = dept.id ",
                "where dept.id in ",
                "<foreach collection='deptIds' item='id' open='(' separator=',' close=')'>",
                    "#{id}",
                "</foreach>",
                " group by dept.id",
            "</script>"
    })
    List<MaterialSchoolRespVO> selectMaterialSchoolList(@Param("deptIds") List<Long> schoolDeptIds);

}
