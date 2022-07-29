package cn.iocoder.yudao.module.campus.controller.admin;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.system.controller.admin.dept.vo.dept.*;
import cn.iocoder.yudao.module.system.convert.dept.DeptConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.dept.DeptDO;
import cn.iocoder.yudao.module.system.service.dept.DeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import java.util.Comparator;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/7/28
 */
@Api(tags = "智慧校园 - 校长视角 - 班级管理")
@RestController
@RequestMapping("/smart-campus/school/class")
@Validated
public class ClassMgmtController {

    @Resource
    private DeptService deptService;

    @ApiOperation("新增班级")
    @PostMapping("/create")
    public CommonResult<Long> createClass(@Valid @RequestBody DeptCreateReqVO reqVO) {
        Long deptId = deptService.createDept(reqVO);
        return success(deptId);
    }

    @ApiOperation("删除班级")
    @DeleteMapping("/delete")
    public CommonResult<Boolean> deleteClass(@RequestParam("deptId") Long id) {
        deptService.deleteDept(id);
        return success(true);
    }

    @PutMapping("/update")
    @ApiOperation("更新班级")
    public CommonResult<Boolean> updateDept(@Valid @RequestBody DeptUpdateReqVO reqVO) {
        deptService.updateDept(reqVO);
        return success(true);
    }

    @GetMapping("/list")
    @ApiOperation("获取部门列表")
    public CommonResult<List<DeptRespVO>> listDepts(DeptListReqVO reqVO) {
        List<DeptDO> list = deptService.getSimpleDepts(reqVO);
        list.sort(Comparator.comparing(DeptDO::getSort));
        return success(DeptConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/list-all-simple")
    @ApiOperation(value = "获取部门精简信息列表", notes = "只包含被开启的部门，主要用于前端的下拉选项")
    public CommonResult<List<DeptSimpleRespVO>> getSimpleDepts() {
        // 获得部门列表，只要开启状态的
        DeptListReqVO reqVO = new DeptListReqVO();
        reqVO.setStatus(CommonStatusEnum.ENABLE.getStatus());
        List<DeptDO> list = deptService.getSimpleDepts(reqVO);
        // 排序后，返回给前端
        list.sort(Comparator.comparing(DeptDO::getSort));
        return success(DeptConvert.INSTANCE.convertList02(list));
    }

    @GetMapping("/get")
    @ApiOperation("获得部门信息")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    public CommonResult<DeptRespVO> getDept(@RequestParam("id") Long id) {
        return success(DeptConvert.INSTANCE.convert(deptService.getDept(id)));
    }


}
