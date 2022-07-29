package cn.iocoder.yudao.module.campus.controller.admin;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.module.campus.controller.admin.vo.covid.CovidClosePageReqVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.covid.CovidCloseRespVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.covid.CovidCloseSchoolReqVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.covid.CovidCloseSchoolRespVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.inspect.InspectCreateReqVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.inspect.InspectSchoolReqVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.inspect.InspectSchoolRespVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.material.*;
import cn.iocoder.yudao.module.campus.controller.admin.vo.student.StudentPageReqVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.student.StudentTempPreviewRespVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.temprecord.*;
import cn.iocoder.yudao.module.campus.convert.CovidCloseConvert;
import cn.iocoder.yudao.module.campus.convert.MaterialConvert;
import cn.iocoder.yudao.module.campus.convert.StudentConvert;
import cn.iocoder.yudao.module.campus.convert.TempRecordConvert;
import cn.iocoder.yudao.module.campus.dal.dataobject.MaterialDO;
import cn.iocoder.yudao.module.campus.dal.dataobject.StudentDO;
import cn.iocoder.yudao.module.campus.dal.dataobject.TempRecordDO;
import cn.iocoder.yudao.module.campus.enums.ErrorCodeConstants;
import cn.iocoder.yudao.module.campus.service.*;
import cn.iocoder.yudao.module.system.dal.dataobject.dept.DeptDO;
import cn.iocoder.yudao.module.system.service.dept.DeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants.FORBIDDEN;
import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.error;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUser;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserDeptId;
import static cn.iocoder.yudao.module.campus.enums.ErrorCodeConstants.SCHOOL_NOT_EXISTSED;

/**
 * @program: 智慧校园
 * @description: 智慧校园-- 教育局视角请求入口
 * @author: pengzhenlong
 * @date: 2022/7/4
 */
@Api(tags = "智慧校园 - 教育局视角")
@RestController
@RequestMapping("/smart-campus/educate")
@Validated
public class BureauController {

    @Resource
    private DeptService deptService;

    @Resource
    private TempRecordService tempRecordService;

    @Resource
    private CovidCloseService covidCloseService;

    @Resource
    private InspectService inspectService;

    @Resource
    private MaterialService materialService;

    @Resource
    private StudentService studentService;

    @ApiOperation("学校情况预览")
    @GetMapping("/school-preview")
    public CommonResult getSchoolPreview() {
        return success("学校情况预览--待开发");
    }

    @ApiOperation("获得测温统计列表")
    @GetMapping("/temp-record/preview")
    public CommonResult TempRecordsPage(@Valid TempRecordPreviewReqVO reqVO) {
        List<DeptDO> allSchoolDepts = deptService.getDeptsByParentIdFromCache(getLoginUserDeptId(), false);
        if (CollUtil.isEmpty(allSchoolDepts)) {
            return success(true);
        }

        //为空时，展示所有学校
        List<DeptDO> schoolDepts = new ArrayList<>();
        if (reqVO.getDeptId() == null) {
            schoolDepts = allSchoolDepts;
        } else {
            if (CollUtil.isEmpty(deptService.getDeptsByParentIdFromCache(reqVO.getDeptId(), true))) {
                return error(GlobalErrorCodeConstants.BAD_REQUEST.getCode(), "该学校不存在数据");
            }

            DeptDO deptDO = deptService.getDept(reqVO.getDeptId());
            if (deptDO == null) {
                return error(GlobalErrorCodeConstants.BAD_REQUEST.getCode(), "该学校不存在");
            }
            schoolDepts.add(deptDO);
        }

        Map<Long, DeptDO> schoolDeptMap = schoolDepts.stream().collect(Collectors.toMap(DeptDO::getId, dept -> dept));
        List<Long> schoolDeptIds = schoolDepts.stream().map(DeptDO::getId).collect(Collectors.toList());
        List<TempRecordSchoolPreviewRespVO> results = tempRecordService.getSchoolTempRecordPreviewList(schoolDeptIds, reqVO.getDate());
        return success(TempRecordConvert.INSTANCE.convertSchool(results, schoolDeptMap));

    }

    @ApiOperation("获得学校密接预览")
    @GetMapping("/close-preview/preview")
    public CommonResult closeContactsPage(@Valid CovidCloseSchoolReqVO reqVO) {
        List<DeptDO> schoolDepts = getSchoolDepts(reqVO.getDeptId());
        List<Long> schoolDeptIds = schoolDepts.stream().map(DeptDO::getId).collect(Collectors.toList());

        List<CovidCloseSchoolRespVO> results = covidCloseService.getCovidCloseSchoolPreviewList(schoolDeptIds, reqVO.getDate());
        Map<Long, DeptDO> schoolDeptMap = schoolDepts.stream().collect(Collectors.toMap(DeptDO::getId, dept -> dept));

        return success(CovidCloseConvert.INSTANCE.convert(results, schoolDeptMap));
    }

    @ApiOperation("获得检查记录列表")
    @GetMapping("/check-record/page")
    public CommonResult<PageResult<InspectSchoolRespVO>> checkRecordsPage(@Valid InspectSchoolReqVO reqVO) {
        List<DeptDO> schoolDepts = getSchoolDepts(reqVO.getSchoolId());
        List<Long> schoolDeptIds = schoolDepts.stream().map(DeptDO::getId).collect(Collectors.toList());
        return success(inspectService.getSchoolInspects(schoolDeptIds, reqVO.getDate()));
    }

    @ApiOperation("新增检查记录")
    @PostMapping("/check-record/create")
    public CommonResult createCheckRecord(@Valid @RequestBody InspectCreateReqVO reqVO) {
        validateSchoolId(reqVO.getSchoolId());
        return success(inspectService.createInspect(reqVO.getSchoolId(), reqVO));
    }


    @ApiOperation("获得防疫物资列表")
    @GetMapping("/material/list")
    public CommonResult<List<MaterialSchoolRespVO>> materialPage(@Valid MaterialSchoolReqVO reqVO) {
        List<DeptDO> schoolDepts = getSchoolDepts(reqVO.getDeptId());
        List<Long> schoolDeptIds = schoolDepts.stream().map(DeptDO::getId).collect(Collectors.toList());
        return success(materialService.getMaterialSchoolList(schoolDeptIds));
    }


    @ApiOperation("更新防疫物资")
    @PutMapping("/material/update")
    public CommonResult<Boolean> updateMaterial(@Valid @RequestBody MaterialSchoolUpdateReqVO reqVO) {
        if (deptService.getDept(reqVO.getDeptId()) == null) {
            return error(SCHOOL_NOT_EXISTSED);
        }
        materialService.updateSchoolMaterial(reqVO);
        return success(true);
    }

    @ApiOperation("获取防疫物资详情")
    @GetMapping("/material/get")
    public CommonResult getMaterial(@NotNull @RequestParam("schoolId") Long schoolId) {
        DeptDO school = deptService.getDept(schoolId);
        return success(materialService.getMaterialSchoolList(Arrays.asList(school.getId())));
    }

    @GetMapping("/dept/get")
    @ApiOperation("获取子部门")
    public CommonResult<List<DeptDO>> getDepts(@RequestParam("deptId") Long deptId) {
        if (deptId == 0) {
            deptId = getLoginUser().getDeptId();
        }
        List<DeptDO> deptList = deptService.getDeptsByParentIdFromCache(deptId, false);
        return success(deptList);
    }

    @ApiOperation("获得密接人员分页列表")
    @GetMapping("/school/close-preview/list")
    public CommonResult<PageResult<CovidCloseRespVO>> closeContactsPage(@Validated CovidClosePageReqVO reqVO) {
        validateSchoolId(reqVO.getSchoolId());

        List<DeptDO> gradeDepts = new ArrayList<>();
        if (reqVO.getDeptId() != null) {
            DeptDO gradeDept = deptService.getDept(reqVO.getDeptId());
            if (gradeDept == null) {
                throw exception(ErrorCodeConstants.GRADE_NOT_EXISTSED);
            }
            gradeDepts.add(gradeDept);
        } else {
            gradeDepts = deptService.getDeptsByParentIdFromCache(reqVO.getSchoolId(), false);
        }

        List<Long> gradeDeptIds = gradeDepts.stream().map(DeptDO::getId).collect(Collectors.toList());
        PageResult<CovidCloseRespVO> pageResult = covidCloseService.getCovidClosePage(reqVO, gradeDeptIds);
        return success(pageResult);
    }

    @ApiOperation("获得检查记录列表")
    @GetMapping("/school/check-record/page")
    public CommonResult<PageResult<InspectSchoolRespVO>> schoolCheckRecordsPage(@Valid InspectSchoolReqVO reqVO) {
        validateSchoolId(reqVO.getSchoolId());

        List<DeptDO> schoolDepts = getSchoolDepts(reqVO.getSchoolId());
        List<Long> schoolDeptIds = schoolDepts.stream().map(DeptDO::getId).collect(Collectors.toList());
        return success(inspectService.getSchoolInspects(schoolDeptIds, reqVO.getDate()));
    }

    @ApiOperation("年级预览列表")
    @GetMapping("/school/grade-preview/page")
    public CommonResult gradePreview(@Valid TempRecordPreviewReqVO reqVO) {
        Long schoolId = reqVO.getSchoolId();
        validateSchoolId(schoolId);

        List<DeptDO> allGradeDepts = deptService.getDeptsByParentIdFromCache(schoolId, false);
        if (CollUtil.isEmpty(allGradeDepts)) {
            return error(FORBIDDEN);
        }

        //为空，展示所有年级
        List<DeptDO> depts = new ArrayList<>();
        if (reqVO.getDeptId() == null) {
            depts = allGradeDepts;
        } else {
            DeptDO deptDO = deptService.getDept(reqVO.getDeptId());
            if (deptDO == null) {
                return error(GlobalErrorCodeConstants.BAD_REQUEST.getCode(), "不存在该年级");
            }
            depts.add(deptDO);
        }

        List<Long> deptIds = depts.stream().map(DeptDO::getId).collect(Collectors.toList());
        List<TempRecordPreviewRespVO> results = tempRecordService.getGradeTempRecordPreviewList(deptIds, reqVO.getDate());

        Map<Long, DeptDO> gradeDeptMap = depts.stream().collect(Collectors.toMap(DeptDO::getId, dept -> dept));
        return success(TempRecordConvert.INSTANCE.convertGrade(results, gradeDeptMap));
    }

    @ApiOperation("班级预览列表")
    @GetMapping("/school/class-preview/page")
    public CommonResult classPreviewPage(@Valid TempRecordPreviewReqVO reqVO) {
        Long schoolId = reqVO.getSchoolId();
        validateSchoolId(schoolId);
        List<DeptDO> allGradeDepts = deptService.getDeptsByParentIdFromCache(schoolId, false);
        if (CollUtil.isEmpty(allGradeDepts)) {
            return error(FORBIDDEN);
        }
        List<DeptDO> allClassDepts = new ArrayList<>();
        for (DeptDO dept : allGradeDepts) {
            List<DeptDO> classDepts = deptService.getDeptsByParentIdFromCache(dept.getId(), false);
            if (CollUtil.isNotEmpty(classDepts)) {
                allClassDepts.addAll(classDepts);
            }
        }

        List<DeptDO> queryDepts = new ArrayList<>();
        //为空，展示所有班级
        if (reqVO.getDeptId() == null) {
            queryDepts = allClassDepts;
        } else {
            //校验参数是否有效
            deptService.validDepts(Arrays.asList(reqVO.getDeptId()));
            queryDepts = deptService.getDeptsByParentIdFromCache(reqVO.getDeptId(), false);
            if (CollUtil.isEmpty(queryDepts)) { //是否是班级选择
                DeptDO deptDO = deptService.getDept(reqVO.getDeptId());
                queryDepts.add(deptDO);
            }
        }

        List<Long> queryDeptIds = queryDepts.stream().map(DeptDO::getId).collect(Collectors.toList());
        Map<Long, DeptDO> classDeptMap = queryDepts.stream().collect(Collectors.toMap(DeptDO::getId, dept -> dept));
        Map<Long, DeptDO> gradeDeptMap = allGradeDepts.stream().collect(Collectors.toMap(DeptDO::getId, dept -> dept));

        List<TempRecordPreviewRespVO> results = tempRecordService.getClassTempRecordPreviewList(queryDeptIds, reqVO.getDate());
        return success(TempRecordConvert.INSTANCE.convertClass(results, classDeptMap, gradeDeptMap));
    }

    @ApiOperation("学生测温预览分页列表")
    @GetMapping("/school/student-preview/page")
    public CommonResult<PageResult<StudentTempPreviewRespVO>> studentPreviewPage(@Valid StudentPageReqVO reqVO) {
        List<Long> queryDeptIds = getClassDeptIds(reqVO.getSchoolId(), reqVO.getDeptId());
        PageResult<StudentDO> pageResult = studentService.getStudentPage(reqVO, queryDeptIds);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return success(new PageResult<>(pageResult.getTotal()));
        }

        List<Long> studentIds = CollectionUtils.convertList(pageResult.getList(), StudentDO::getId);
        List<TempRecordDO> tempRecords = tempRecordService.getTodayTempRecordsByStudentIdsAndDate(studentIds);
        Map<Long, List<TempRecordDO>> studentTempRecordMap = new HashMap<>();
        for (TempRecordDO tempRecord : tempRecords) {
            Long studentId = tempRecord.getStudentId();
            List<TempRecordDO> studentTempRecordList = studentTempRecordMap.get(studentId);
            if (CollectionUtil.isEmpty(studentTempRecordList)) {
                studentTempRecordList = new ArrayList<>();
            }
            studentTempRecordList.add(tempRecord);
            studentTempRecordMap.put(tempRecord.getStudentId(), studentTempRecordList);
        }

        Collection<Long> classDeptIds = CollectionUtils.convertList(pageResult.getList(), StudentDO::getDeptId);
        Map<Long, DeptDO> deptMap = deptService.getStudentDeptMapByIds(classDeptIds);

        List<StudentTempPreviewRespVO> results = StudentConvert.INSTANCE.convert(pageResult.getList(), studentTempRecordMap, deptMap);
        return success(new PageResult<>(results, pageResult.getTotal()));
    }

    private List<Long> getClassDeptIds(Long schoolId, Long deptId) {
        validateSchoolId(schoolId);
        List<DeptDO> allGradeDepts = deptService.getDeptsByParentIdFromCache(schoolId, false);
        List<DeptDO> allClassDepts = new ArrayList<>();
        for (DeptDO dept : allGradeDepts) {
            List<DeptDO> classDepts = deptService.getDeptsByParentIdFromCache(dept.getId(), false);
            if (CollUtil.isNotEmpty(classDepts)) {
                allClassDepts.addAll(classDepts);
            }
        }

        List<DeptDO> queryDepts = new ArrayList<>();
        //为空，展示所有班级
        if (deptId == null) {
            queryDepts = allClassDepts;
        } else {
            //校验参数是否有效
            deptService.validDepts(Arrays.asList(deptId));
            queryDepts = deptService.getDeptsByParentIdFromCache(deptId, false);
            if (CollUtil.isEmpty(queryDepts)) { //是否是班级选择
                DeptDO deptDO = deptService.getDept(deptId);
                queryDepts.add(deptDO);
            }
        }
        return queryDepts.stream().map(DeptDO::getId).collect(Collectors.toList());
    }

    @ApiOperation("测温记录分页列表")
    @GetMapping("/school/temp/page")
    public CommonResult<PageResult<TempRecordRespVO>> getTempRecordsPage(@Validated TempRecordPageReqVO reqVO) {
        //获取符合条件的测温记录
        List<Long> classDeptIds = getClassDeptIds(reqVO.getSchoolId(), reqVO.getDeptId());
        PageResult<TempRecordRespVO> pageResult = tempRecordService.getTempRecordPage(reqVO, classDeptIds);
        return success(pageResult);
    }

    @ApiOperation("获得防疫物资分页列表")
    @GetMapping("/school/material/page")
    public CommonResult<PageResult<MaterialRespVO>> materialPage(@Validated MaterialPageReqVO reqVO) {
        validateSchoolId(reqVO.getSchoolId());
        PageResult<MaterialDO> pageResult = materialService.getMaterialPage(reqVO);
        List<MaterialRespVO> respVO = MaterialConvert.INSTANCE.convert(pageResult.getList());
        return success(new PageResult<>(respVO, pageResult.getTotal()));
    }

    @ApiOperation("新增防疫物资")
    @PostMapping("/school/material/create")
    public CommonResult<Long> createMaterial(@Valid @RequestBody MaterialCreateReqVO reqVO) {
        validateSchoolId(reqVO.getSchoolId());
        return success(materialService.createMaterial(reqVO.getSchoolId(), reqVO));
    }

    @ApiOperation("更新防疫物资")
    @PutMapping("/school/material/update")
    public CommonResult<Boolean> updateMaterial(@Valid @RequestBody MaterialUpdateReqVO reqVO) {
        validateSchoolId(reqVO.getSchoolId());
        materialService.updateMaterial(reqVO);
        return success(true);
    }

    private void validateSchoolId(Long schoolId) {
        if (schoolId == null) {
            throw exception(ErrorCodeConstants.SCHOOL_NOT_EXISTSED);
        }

        if (deptService.getDept(schoolId) == null) {
            throw exception(ErrorCodeConstants.SCHOOL_NOT_EXISTSED);
        }
    }


    private List<DeptDO> getSchoolDepts(Long reqId) {
        List<DeptDO> schoolDepts = new ArrayList<>();
        if (reqId == null) {
            schoolDepts = deptService.getDeptsByParentIdFromCache(getLoginUserDeptId(), false);
        } else {
            DeptDO deptDO = deptService.getDept(reqId);
            if (deptDO == null) {
                throw exception(SCHOOL_NOT_EXISTSED);
            }
            schoolDepts.add(deptDO);
        }
        return schoolDepts;
    }

}
