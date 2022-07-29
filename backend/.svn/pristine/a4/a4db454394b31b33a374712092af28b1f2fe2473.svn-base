package cn.iocoder.yudao.module.campus.controller.admin.vo.student;

import com.alibaba.excel.annotation.ExcelProperty;

import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import cn.iocoder.yudao.module.system.enums.DictTypeConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 学生 Excel VO
 *
 * @author chenWenXiang
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = false) // 设置 chain = false，避免信息导入有问题
public class StudentExcelVo {
	
	@ExcelProperty("姓名")
	private String name;
	
	@ExcelProperty(value = "性别", converter = DictConvert.class)
	@DictFormat(DictTypeConstants.USER_SEX)
	private Integer sex;
	
	@ExcelProperty("年级")
	private String campusGradeName;
	
	@ExcelProperty("班级")
	private String campusClassName;
	
	@ExcelProperty("学号")
	private String studentNo;
	
	@ExcelProperty("身份证号")
	private String idCard;
	
	@ExcelProperty("照片")
	private String photo;
}
