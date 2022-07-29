package cn.iocoder.yudao.module.campus.service.impl;

import cn.iocoder.yudao.module.campus.controller.admin.vo.device.DeviceRegisterReqVO;
import cn.iocoder.yudao.module.campus.convert.DeviceConvert;
import cn.iocoder.yudao.module.campus.dal.dataobject.DeviceDO;
import cn.iocoder.yudao.module.campus.dal.mysql.DeviceMapper;
import cn.iocoder.yudao.module.campus.service.DeviceService;
import cn.iocoder.yudao.module.system.dal.dataobject.dept.DeptDO;
import cn.iocoder.yudao.module.system.dal.mysql.dept.DeptMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.campus.enums.ErrorCodeConstants.*;

/**
 * @program: 智慧校园
 * @description: 设备--业务操作类
 * @author: pengzhenlong
 * @date: 2022/7/15
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    @Resource
    private DeviceMapper deviceMapper;

    @Resource
    private DeptMapper deptMapper;

    @Override
    public DeviceDO getDeviceByDeviceId(String deviceId) {
        return deviceMapper.selectOne(DeviceDO::getDeviceId, deviceId);
    }

    @Override
    public void deviceRegister(DeviceRegisterReqVO registerVO) {
        String schoolName = registerVO.getSchoolName();
        DeptDO schoolDept = deptMapper.selectOne(DeptDO::getName, schoolName);
        if (schoolDept == null) {
            throw exception(SCHOOL_NAME_NOT_EXISTSED);
        }

        String gradeName = registerVO.getGradeName();
        DeptDO gradeDept = deptMapper.selectByParentIdAndName(schoolDept.getId(), gradeName);
        if (gradeDept == null) {
            throw exception(GRADE_NAME_NOT_EXISTSED);
        }

        String className = registerVO.getClassName();
        DeptDO classDept = deptMapper.selectByParentIdAndName(gradeDept.getId(), className);
        if (classDept == null) {
            throw exception(CLASS_NAME_NOT_EXISTSED);
        }

        DeviceDO device = DeviceConvert.INSTANCE.convert(registerVO);
        device.setDeptId(classDept.getId());
        deviceMapper.insert(device);
    }
}
