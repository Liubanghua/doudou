package cn.iocoder.yudao.module.campus.controller.admin;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.date.DateUtils;
import cn.iocoder.yudao.module.campus.controller.admin.vo.covid.CovidClosePageReqVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.covid.CovidCloseRespVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.inspect.InspectCreateReqVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.inspect.InspectPageReqVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.inspect.InspectRespVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.material.MaterialCreateReqVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.material.MaterialPageReqVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.material.MaterialRespVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.material.MaterialUpdateReqVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.student.*;
import cn.iocoder.yudao.module.campus.controller.admin.vo.temprecord.*;
import cn.iocoder.yudao.module.campus.convert.InspectConvert;
import cn.iocoder.yudao.module.campus.convert.MaterialConvert;
import cn.iocoder.yudao.module.campus.convert.StudentConvert;
import cn.iocoder.yudao.module.campus.convert.TempRecordConvert;
import cn.iocoder.yudao.module.campus.dal.dataobject.*;
import cn.iocoder.yudao.module.campus.enums.ErrorCodeConstants;
import cn.iocoder.yudao.module.campus.service.*;
import cn.iocoder.yudao.module.system.controller.admin.dept.vo.dept.DeptCreateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.dept.vo.dept.DeptListReqVO;
import cn.iocoder.yudao.module.system.controller.admin.dept.vo.dept.DeptRespVO;
import cn.iocoder.yudao.module.system.convert.dept.DeptConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.dept.DeptDO;
import cn.iocoder.yudao.module.system.service.dept.DeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants.FORBIDDEN;
import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.error;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUser;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserDeptId;

/**
 * @program: 智慧校园
 * @description: 智慧校园--学校视角请求入口
 * @author: pengzhenlong
 * @date: 2022/6/15
 */
@Api(tags = "智慧校园 - 学校视角")
@RestController
@RequestMapping("/smart-campus/school")
@Validated
public class SchoolController {

    @Resource
    private StudentService studentService;

    @Resource
    private TempRecordService tempRecordService;

    @Resource
    private DeptService deptService;

    @Resource
    private MaterialService materialService;

    @Resource
    private InspectService inspectService;

    @Resource
    private CovidCloseService covidCloseService;

    @Resource
    private StudentHeightService studentHeightService;

    @Resource
    private StudentWeightService studentWeightService;


    @ApiOperation("获得测温预览")
    @GetMapping("/temp-preview")
    public CommonResult<TempChartRespVO> getTempPreview(@RequestParam("createTime") String createTime) throws Exception {
        Date time = new SimpleDateFormat("yyyy-MM-dd").parse(createTime);
        return success(tempRecordService.getTempChart(time));
    }

    @ApiOperation("获得密接人员分页列表")
    @GetMapping("/close-preview/page")
    public CommonResult<PageResult<CovidCloseRespVO>> closeContactsPage(@Validated CovidClosePageReqVO reqVO) {
        List<DeptDO> gradeDepts = new ArrayList<>();
        if (reqVO.getDeptId() != null) {
            DeptDO gradeDept = deptService.getDept(reqVO.getDeptId());
            if (gradeDept == null) {
                throw exception(ErrorCodeConstants.GRADE_NOT_EXISTSED);
            }
            gradeDepts.add(gradeDept);
        } else {
            gradeDepts = deptService.getDeptsByParentIdFromCache(getLoginUserDeptId(), false);
        }

        List<Long> gradeDeptIds = gradeDepts.stream().map(DeptDO::getId).collect(Collectors.toList());
        PageResult<CovidCloseRespVO> pageResult = covidCloseService.getCovidClosePage(reqVO, gradeDeptIds);
        return success(pageResult);
    }

    @ApiOperation("获得检查记录分页列表")
    @GetMapping("/check-record/page")
    public CommonResult checkRecordsPage(@Valid InspectPageReqVO reqVO) {
        PageResult<InspectDO> pageResult = inspectService.getInspectPage(reqVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return success(new PageResult<>(pageResult.getTotal()));
        }

        List<InspectRespVO> respVOs = InspectConvert.INSTANCE.convert(pageResult.getList());
        return success(new PageResult<>(respVOs, pageResult.getTotal()));
    }

    @ApiOperation("新增检查记录")
    @PostMapping("/check-record/create")
    public CommonResult<Long> createCheckRecord(@Valid @RequestBody InspectCreateReqVO reqVO) {
        Long deptId = getLoginUserDeptId();
        return success(inspectService.createInspect(deptId, reqVO));
    }

    @ApiOperation("年级预览列表")
    @GetMapping("/grade-preview/page")
    public CommonResult gradePreview(@Valid TempRecordPreviewReqVO reqVO) {
        List<DeptDO> allGgradeDepts = deptService.getDeptsByParentIdFromCache(getLoginUserDeptId(), false);
        if (CollUtil.isEmpty(allGgradeDepts)) {
            return error(FORBIDDEN);
        }

        //为空，展示所有年级
        List<DeptDO> depts = new ArrayList<>();
        if (reqVO.getDeptId() == null) {
            depts = allGgradeDepts;
        } else {
            DeptDO deptDO = deptService.getDept(reqVO.getDeptId());
            if (deptDO == null) {
                return error(GlobalErrorCodeConstants.BAD_REQUEST.getCode(), "不存在该年级");
            }

            if (CollUtil.isEmpty(deptService.getDeptsByParentIdFromCache(reqVO.getDeptId(), false))) {
                return success(null);
            }
            depts.add(deptDO);
        }

        List<Long> deptIds = depts.stream().map(DeptDO::getId).collect(Collectors.toList());
        List<TempRecordPreviewRespVO> results = tempRecordService.getGradeTempRecordPreviewList(deptIds, reqVO.getDate());

        Map<Long, DeptDO> gradeDeptMap = depts.stream().collect(Collectors.toMap(DeptDO::getId, dept -> dept));
        return success(TempRecordConvert.INSTANCE.convertGrade(results, gradeDeptMap));
    }

    @ApiOperation("班级预览列表")
    @GetMapping("/class-preview/page")
    public CommonResult classPreviewPage(@Valid TempRecordPreviewReqVO reqVO) {
        List<DeptDO> allGradeDepts = deptService.getDeptsByParentIdFromCache(getLoginUserDeptId(), false);
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
    @GetMapping("/student-preview/page")
    public CommonResult<PageResult<StudentTempPreviewRespVO>> studentPreviewPage(@Valid StudentPageReqVO reqVO) {
        List<Long> queryDeptIds = getClassDeptIds(reqVO.getDeptId());
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


    @ApiOperation("获得学生测温记录详情")
    @GetMapping("/temp/get")
    public CommonResult<StudentTempRecordRespVO> getStudentTempRecord(@Valid StudentTempsDetailReqVO reqVO) {
        return success(tempRecordService.getTempRecordByStudentIdAndDate(reqVO.getStudentId(), DateUtils.getDate(reqVO.getMeasureTime())));
    }

    @ApiOperation("获得学生信息")
    @GetMapping("/student/get")
    public CommonResult<StudentRespVO> getStudent(@RequestParam("id") Long id) {
        return success(StudentConvert.INSTANCE.convertDo(studentService.selectById(id)));
    }

    @ApiOperation("获得防疫物资分页列表")
    @GetMapping("/material/page")
    public CommonResult<PageResult<MaterialRespVO>> materialPage(@Validated MaterialPageReqVO reqVO) {
        reqVO.setSchoolId(getLoginUserDeptId());
        PageResult<MaterialDO> pageResult = materialService.getMaterialPage(reqVO);
        List<MaterialRespVO> respVO = MaterialConvert.INSTANCE.convert(pageResult.getList());
        return success(new PageResult<>(respVO, pageResult.getTotal()));
    }

    @ApiOperation("新增防疫物资")
    @PostMapping("/material/create")
    public CommonResult<Long> createMaterial(@Valid @RequestBody MaterialCreateReqVO reqVO) {
        Long deptId = getLoginUserDeptId();
        return success(materialService.createMaterial(deptId, reqVO));
    }

    @ApiOperation("更新防疫物资")
    @PutMapping("/material/update")
    public CommonResult<Boolean> updateMaterial(@Valid @RequestBody MaterialUpdateReqVO reqVO) {
        materialService.updateMaterial(reqVO);
        return success(true);
    }

    @ApiOperation("获取防疫物资变更记录")
    @GetMapping("/material/get")
    public CommonResult getMaterial(@RequestParam("id") Long id) {
        return success("获取防疫物资变更记录 -- 待开发");
    }


    private List<Long> getClassDeptIds(Long deptId) {
        List<DeptDO> allGradeDepts = deptService.getDeptsByParentIdFromCache(getLoginUserDeptId(), false);
        if (CollUtil.isEmpty(allGradeDepts)) {
            throw exception(FORBIDDEN);
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
    @GetMapping("/temp/page")
    public CommonResult<PageResult<TempRecordRespVO>> getTempRecordsPage(@Validated TempRecordPageReqVO reqVO) {
        List<Long> queryDeptIds = getClassDeptIds(reqVO.getDeptId());

        //获取符合条件的测温记录
        PageResult<TempRecordRespVO> pageResult = tempRecordService.getTempRecordPage(reqVO, queryDeptIds);
        return success(pageResult);
    }

    @ApiOperation("获得学生分页列表")
    @GetMapping("/student/page")
    public CommonResult<PageResult<StudentRespVO>> getStudentsPage(@Valid StudentPageReqVO reqVO) {
        List<Long> queryDeptIds = getClassDeptIds(reqVO.getDeptId());
        PageResult<StudentRespVO> pageResult = studentService.studentPage(reqVO, queryDeptIds);
        List<StudentRespVO> students = pageResult.getList();

        List<Long> studentIds = students.stream().map(StudentRespVO::getId).collect(Collectors.toList());
        List<StudentHeightDO> heights = studentHeightService.getStudentHeightList(studentIds);
        List<StudentWeightDO> weights = studentWeightService.getStudentWeightList(studentIds);

        List<StudentRespVO> results = StudentConvert.INSTANCE.convert(students, heights, weights);
        return success(new PageResult<>(results, pageResult.getTotal()));
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

    @GetMapping("/dept/get-parentDept")
    @ApiOperation("获取父部门")
    public CommonResult<DeptRespVO> getParentDept(@RequestParam("deptId") Long deptId) {
        return success(DeptConvert.INSTANCE.convert(deptService.getDept(deptId)));
    }


}
