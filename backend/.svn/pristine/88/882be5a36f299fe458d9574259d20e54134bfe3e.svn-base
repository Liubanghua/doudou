package cn.iocoder.yudao.module.campus.controller.admin;

import cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.campus.controller.admin.vo.device.AppUpgradeRespVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.device.DeviceRegisterReqVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.device.MeasureDataUploadReqVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.student.StudentCreateReqVO;
import cn.iocoder.yudao.module.campus.convert.AppUpgradeConvert;
import cn.iocoder.yudao.module.campus.dal.dataobject.*;
import cn.iocoder.yudao.module.campus.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.error;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.module.campus.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.module.infra.enums.ErrorCodeConstants.FILE_IS_EMPTY;

/**
 * @program: 智慧校园
 * @description: 智慧校园--设备管理类
 * @author: pengzhenlong
 * @date: 2022/7/27
 */
@Api(tags = "智慧校园 - 设备管理")
@RestController
@RequestMapping("/smart-campus/device")
@Validated
public class DeviceController {

    @Resource
    private DeviceService deviceService;

    @Resource
    private StudentService studentService;

    @Resource
    private AppUpgradeService appUpgradeService;

    @Resource
    private MeasureDataUploadService measureDataUploadService;

    @Resource
    private StudentHeightService studentHeightService;

    @Resource
    private StudentWeightService studentWeightService;

    @Resource
    private TempRecordService tempRecordService;

    @PostMapping("/register")
    @ApiOperation("设备注册")
    public CommonResult<Boolean> deviceRegister(@Valid @RequestBody DeviceRegisterReqVO reqVO) {
        DeviceDO deviceDO = deviceService.getDeviceByDeviceId(reqVO.getDeviceId());
        if (deviceDO != null) {
            return error(GlobalErrorCodeConstants.BAD_REQUEST.getCode(), "该设备已存在");
        }
        deviceService.deviceRegister(reqVO);
        return success(true);
    }

    @PostMapping("/student/upload")
    @ApiOperation("设备上传学生信息")
    public CommonResult<Long> createStudentByUpload(@RequestParam("avatarFile") MultipartFile file, @RequestParam("deviceId") String deviceId) throws Exception {
        //1.参数校验
        if (file.isEmpty()) {
            throw exception(FILE_IS_EMPTY);
        }

        DeviceDO deviceDO = deviceService.getDeviceByDeviceId(deviceId);
        if (deviceDO == null) {
            throw exception(DEVICE_NOT_REGISTER);
        }

        String[] fileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf(".")).split("_");
        if (fileName.length != 2) {
            throw exception(DEVICE_UPLOAD_FILE_NAME_ERROR);
        }

        //2.处理后保存到数据库
        String avatar = studentService.updateStudentAvatar(file.getInputStream());
        StudentCreateReqVO createReqVO = new StudentCreateReqVO();
        createReqVO.setName(fileName[0]);
        createReqVO.setStudentNo(fileName[1]);
        createReqVO.setDeptId(deviceDO.getDeptId());
        createReqVO.setAvatar(avatar);

        Long id = studentService.uploadStudent(createReqVO);
        return success(id);
    }

    @PostMapping("/measure-data/upload")
    @ApiOperation("测量数据上传")
    public CommonResult uploadStudentInfo(@Valid @RequestBody MeasureDataUploadReqVO reqVO) {
        //1. 参数校验
        StudentDO student = studentService.getStudentByStudentNo(reqVO.getStudentNo());
        if (student == null) {
            throw exception(STUDENT_NOT_EXISTSED);
        }

        StudentHeightDO studentHeightDO = new StudentHeightDO();
        StudentWeightDO studentWeightDO = new StudentWeightDO();
        TempRecordDO tempRecord = new TempRecordDO();

        Float height = reqVO.getHeight();
        if (height != null || height != 0) {
            if (height < 50 || height > 250) {
                throw exception(HEIGHT_ABNORMAL);
            }
            studentHeightDO.setStudentId(student.getId());
            studentHeightDO.setHeight(height);
        }

        Float weight = reqVO.getWeight();
        if (weight != null || weight != 0) {
            if (weight < 20 || weight > 300) {
                throw exception(WEIGHT_ABNORMAL);
            }
            studentWeightDO.setStudentId(student.getId());
            studentWeightDO.setWeight(weight);
        }


        Float temperature = reqVO.getTemperature();
        if (temperature != null || temperature != 0) {
            if (temperature < 35 || temperature > 42) {
                throw exception(TEMPERATURE_ABNORMAL);
            }
            tempRecord.setStudentId(student.getId());
            tempRecord.setTemperature(temperature);
        }

        //2. 保存到对应的数据库中
        studentHeightService.create(studentHeightDO);
        studentWeightService.create(studentWeightDO);
        tempRecordService.create(tempRecord);
        return success(true);
    }

    @GetMapping("/upgrade-app/get")
    @ApiOperation("app更新")
    public CommonResult<AppUpgradeRespVO> getUpgradeAppInfo() {
        AppUpgradeDO app = appUpgradeService.getNewestApp();
        return success(AppUpgradeConvert.INSTANCE.convert(app));
    }


}
