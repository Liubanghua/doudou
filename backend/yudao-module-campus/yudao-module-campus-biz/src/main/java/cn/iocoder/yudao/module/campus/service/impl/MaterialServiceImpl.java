package cn.iocoder.yudao.module.campus.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.campus.controller.admin.vo.material.*;
import cn.iocoder.yudao.module.campus.convert.MaterialConvert;
import cn.iocoder.yudao.module.campus.dal.dataobject.MaterialDO;
import cn.iocoder.yudao.module.campus.dal.mysql.MaterialMapper;
import cn.iocoder.yudao.module.campus.service.MaterialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.campus.enums.ErrorCodeConstants.MATERIAL_EXISTSED;
import static cn.iocoder.yudao.module.campus.enums.ErrorCodeConstants.MATERIAL_NOT_EXISTSED;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/7/6
 */
@Service
@Slf4j
public class MaterialServiceImpl implements MaterialService {

    @Resource
    private MaterialMapper materialMapper;


    @Override
    public PageResult<MaterialDO> getMaterialPage(MaterialPageReqVO reqVO) {
        LambdaQueryWrapperX<MaterialDO> queryWrapper = new LambdaQueryWrapperX<MaterialDO>();
        queryWrapper.eqIfPresent(MaterialDO::getDeptId, reqVO.getSchoolId());
        return materialMapper.selectPage(reqVO, queryWrapper);
    }

    @Override
    public Long createMaterial(Long deptId, MaterialCreateReqVO reqVO) {
        checkMaterialUnique(reqVO.getName(), deptId);

        MaterialDO materialDO = MaterialConvert.INSTANCE.convert(reqVO);
        materialDO.setDeptId(deptId);

        materialMapper.insert(materialDO);
        return materialDO.getId();
    }

    /**
     * 校验是否已存在此物资的记录
     *
     * @param name
     * @param deptId
     */
    private void checkMaterialUnique(String name, Long deptId) {
        MaterialDO materialDO = materialMapper.selectByNameAndDeptId(name, deptId);
        if (materialDO != null) {
            throw exception(MATERIAL_EXISTSED);
        }
        return;
    }

    @Override
    public void updateMaterial(MaterialUpdateReqVO reqVO) {
        if (materialMapper.selectById(reqVO.getId()) == null) {
            throw exception(MATERIAL_NOT_EXISTSED);
        }
        MaterialDO materialDO = MaterialConvert.INSTANCE.convert(reqVO);
        materialMapper.updateById(materialDO);
    }

    @Override
    public List<MaterialSchoolRespVO> getMaterialSchoolList(List<Long> schoolDeptIds) {
        return materialMapper.selectMaterialSchoolList(schoolDeptIds);
    }

    @Override
    public void updateSchoolMaterial(MaterialSchoolUpdateReqVO reqVO) {
        List<MaterialDO> updateMaterialDos = new ArrayList<>();
        List<MaterialDO> newMaterialDos = new ArrayList<>();
        List<MaterialDO> dbMaterials = materialMapper.selectList("dept_id", reqVO.getDeptId());
        Map<String, MaterialDO> dbMaterialMap = dbMaterials.stream().collect(Collectors.toMap(MaterialDO::getName, material -> material));

        Map<String, Integer> updateMaterialMap = new HashMap<>();
        updateMaterialMap.put("口罩", reqVO.getMaskTotal());
        updateMaterialMap.put("酒精", reqVO.getAlcoholTotal());
        updateMaterialMap.put("洗手液", reqVO.getHandwashTotal());
        updateMaterialMap.put("帐篷", reqVO.getTentTotal());

        for (Map.Entry<String, Integer> entry : updateMaterialMap.entrySet()) {
            String materialName = entry.getKey();
            Integer quantity = entry.getValue();
            MaterialDO dbMaterial = dbMaterialMap.get(materialName);
            if (dbMaterial != null) {
                dbMaterial.setQuantity(quantity);
                dbMaterial.setUpdateTime(new Date());
                updateMaterialDos.add(dbMaterial);
            } else {
                MaterialDO materialDO = new MaterialDO();
                materialDO.setDeptId(reqVO.getDeptId());
                materialDO.setName(materialName);
                materialDO.setQuantity(quantity);
                newMaterialDos.add(materialDO);
            }
        }

        if (CollUtil.isNotEmpty(updateMaterialDos)) {
            materialMapper.updateBatchByIds(updateMaterialDos);
        }

        if (CollUtil.isNotEmpty(newMaterialDos)) {
            materialMapper.insertBatch(newMaterialDos);
        }
        return;
    }
}
