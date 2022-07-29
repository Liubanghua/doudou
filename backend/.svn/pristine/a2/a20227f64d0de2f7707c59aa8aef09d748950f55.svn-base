package cn.iocoder.yudao.module.campus.controller.admin.vo.temprecord;

import java.util.List;

import lombok.Data;
/**
 * 温测概览封装类
 * @author chenwenxiang 2022/7/11
 *
 */

@Data
public class TempChartRespVO {
	
	//学生总人数
	private Integer stuTotal;
	
	//全校测温饼状图
	private List<TempPieChartVO> allTempData;
	
	//体温异常饼状图
	private List<TempPieChartVO> tempUnusualData;
	
	//各个年级体温测量柱状图年级名称
	private List<String> gradeNameList;
	
	//各个年级体温测量柱状图体温数据
	private List<List<Integer>> gradeTempList;
}
