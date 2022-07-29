package cn.iocoder.yudao.module.campus.service.impl;

import cn.iocoder.yudao.framework.mybatis.core.query.QueryWrapperX;
import cn.iocoder.yudao.module.campus.dal.dataobject.StudentWeightDO;
import cn.iocoder.yudao.module.campus.dal.mysql.StudentWeightMapper;
import cn.iocoder.yudao.module.campus.service.StudentWeightService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/7/28
 */
@Service
public class StudentWeightServiceImpl implements StudentWeightService {

    @Resource
    private StudentWeightMapper studentWeightMapper;

    @Override
    public List<StudentWeightDO> getStudentWeightList(List<Long> studentIds) {
        QueryWrapperX queryWrapper = new QueryWrapperX();
        queryWrapper.inIfPresent("student_id", studentIds);
        return studentWeightMapper.selectList(queryWrapper);
    }

    @Override
    public Long create(StudentWeightDO studentWeightDO) {
        if(studentWeightDO != null){
            studentWeightMapper.insert(studentWeightDO);
            return studentWeightDO.getId();
        }
        return null;
    }
}
