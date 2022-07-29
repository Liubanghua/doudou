package cn.iocoder.yudao.module.campus.controller.admin;

import cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.campus.controller.admin.vo.student.*;
import cn.iocoder.yudao.module.campus.dal.dataobject.StudentDO;
import cn.iocoder.yudao.module.campus.service.StudentService;
import cn.iocoder.yudao.module.system.dal.dataobject.dept.DeptDO;
import cn.iocoder.yudao.module.system.service.dept.DeptService;
import io.netty.util.internal.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.module.infra.enums.ErrorCodeConstants.FILE_IS_EMPTY;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/7/28
 */
@Api(tags = "智慧校园 - 校长视角 - 学生管理")
@RestController
@RequestMapping("/smart-campus/school/student")
@Validated
public class StudentController {

    @Resource
    private StudentService studentService;

    @Resource
    private DeptService deptService;


    @PostMapping("/create")
    @ApiOperation("新增学生")
    public CommonResult<Long> createStudent(@Valid @RequestBody StudentCreateReqVO reqVO) {
        Long id = studentService.createStudent(reqVO);
        return success(id);
    }

    @DeleteMapping("/delete/{ids}")
    @ApiOperation("删除学生")
    public CommonResult<Boolean> deleteStudent(@PathVariable Long[] ids) {
        for (Long id : ids) {
            studentService.deleteStudent(id);
        }
        return success(true);
    }

    @PutMapping("/update")
    @ApiOperation("更新学生")
    public CommonResult<Boolean> updateStudent(@Valid @RequestBody StudentUpdateReqVO reqVO) {
        studentService.updateStudent(reqVO);
        return success(true);
    }

    @PutMapping("/update-dept")
    @ApiOperation("更新学生的部门信息")
    public CommonResult<Boolean> updateStudentDept(@Valid @RequestBody StudentUpdateDeptReqVO reqVO) {
        studentService.updateStudentsDept(reqVO);
        return success(true);
    }

    @PutMapping("/update-avatar")
    @ApiOperation("上传学生头像")
    public CommonResult<String> updateStudentAvatar(@RequestParam("avatarFile") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw ServiceExceptionUtil.exception(FILE_IS_EMPTY);
        }
        String avatar = studentService.updateStudentAvatar(file.getInputStream());
        return success(avatar);
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出学生 Excel")
    @OperateLog(type = EXPORT)
    public void exportTenantExcel(@Valid StudentExportReqVO exportReqVO,
                                  HttpServletResponse response) throws IOException {
        List<StudentDO> students = studentService.getStudentList(exportReqVO);
        List<StudentExcelVo> datas = new ArrayList<StudentExcelVo>();
        if (students != null && students.size() > 0) {
            Collection<Long> classDeptIds = CollectionUtils.convertList(students, StudentDO::getDeptId);
            List<DeptDO> classDepts = deptService.getDepts(classDeptIds);
            Map<Long, DeptDO> classDeptMap = CollectionUtils.convertMap(classDepts, DeptDO::getId);

            Collection<Long> gradeDeptIds = CollectionUtils.convertList(classDepts, DeptDO::getParentId);
            List<DeptDO> gradeDepts = deptService.getDepts(gradeDeptIds);
            Map<Long, DeptDO> gradeDeptMap = CollectionUtils.convertMap(gradeDepts, DeptDO::getId);

            for (StudentDO studentDO : students) {
                StudentExcelVo studentExcelVo = new StudentExcelVo();
                studentExcelVo.setName(studentDO.getName());
                studentExcelVo.setSex(studentDO.getSex());
                studentExcelVo.setStudentNo(studentDO.getStudentNo());
                studentExcelVo.setCampusClassName(classDeptMap.get(studentDO.getDeptId()).getName());
                studentExcelVo.setCampusGradeName(gradeDeptMap.get(classDeptMap.get(studentDO.getDeptId()).getParentId()).getName());
                if (!StringUtil.isNullOrEmpty(studentDO.getAvatar())) {
                    studentExcelVo.setPhoto("已上传");
                } else {
                    studentExcelVo.setPhoto("未上传");
                }
                datas.add(studentExcelVo);
            }
        }
        ExcelUtils.write(response, "学生.xls", "数据", StudentExcelVo.class, datas);
    }

    @GetMapping("/get-import-template")
    @ApiOperation("获得导入学生模板")
    public void importTemplate(HttpServletResponse response) throws IOException {
        // 手动创建导出 demo
        List<StudentExcelVo> list = Arrays
                .asList(StudentExcelVo.builder().name("张三").sex(1).campusClassName("1班")
                        .campusGradeName("一年级").studentNo("6704110302").idCard("420381199911057561").build());
        // 输出
        ExcelUtils.write(response, "学生导入模板.xls", "学生列表", StudentExcelVo.class, list);
    }

    @PostMapping("/import")
    @ApiOperation("导入学生信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "Excel 文件", required = true, dataTypeClass = MultipartFile.class),
            @ApiImplicitParam(name = "updateSupport", value = "是否支持更新，默认为 false", example = "true", dataTypeClass = Boolean.class)
    })
    public CommonResult<StudentImportRespVO> importExcel(@RequestParam("file") MultipartFile file,
                                                         @RequestParam(value = "updateSupport", required = false, defaultValue = "false") Boolean updateSupport) throws Exception {
        List<StudentExcelVo> list = ExcelUtils.read(file, StudentExcelVo.class);
        return success(studentService.importStudent(list, updateSupport));
    }


}
