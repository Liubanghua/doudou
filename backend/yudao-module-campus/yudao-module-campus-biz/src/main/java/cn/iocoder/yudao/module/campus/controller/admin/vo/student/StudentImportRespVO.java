package cn.iocoder.yudao.module.campus.controller.admin.vo.student;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
/**
 * 学生信息导入 Response VO
 * @author chenwenxiang 2022/7/7
 *
 */
@ApiModel("学生信息导入 Response VO")
@Data
@Builder
public class StudentImportRespVO {
	
	@ApiModelProperty(value = "创建成功的学生数组", required = true)
    private List<String> createStudentNames;

    @ApiModelProperty(value = "更新成功的学生数组", required = true)
    private List<String> updateStudentNames;

    @ApiModelProperty(value = "导入失败的学生集合", required = true, notes = "key 为学生姓名，value 为失败原因")
    private Map<String, String> failureStudentNames;
}
