package cn.iocoder.yudao.module.campus.service.impl;

import cn.iocoder.yudao.framework.mybatis.core.query.QueryWrapperX;
import cn.iocoder.yudao.module.campus.dal.dataobject.StudentHeightDO;
import cn.iocoder.yudao.module.campus.dal.mysql.StudentHeightMapper;
import cn.iocoder.yudao.module.campus.service.StudentHeightService;
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
public class StudentHeightServiceImpl implements StudentHeightService {

    @Resource
    private StudentHeightMapper studentHeightMapper;

    @Override
    public List<StudentHeightDO> getStudentHeightList(List<Long> studentIds) {
        QueryWrapperX queryWrapper = new QueryWrapperX();
        queryWrapper.inIfPresent("student_id", studentIds);
        return studentHeightMapper.selectList(queryWrapper);
    }

    @Override
    public Long create(StudentHeightDO studentHeightDO) {
        if(studentHeightDO != null){
            studentHeightMapper.insert(studentHeightDO);
            return studentHeightDO.getId();
        }
        return null;
    }
}
