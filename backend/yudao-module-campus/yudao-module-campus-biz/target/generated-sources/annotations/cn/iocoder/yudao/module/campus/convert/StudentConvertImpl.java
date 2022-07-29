package cn.iocoder.yudao.module.campus.convert;

import cn.iocoder.yudao.module.campus.controller.admin.vo.student.StudentCreateReqVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.student.StudentExcelVo;
import cn.iocoder.yudao.module.campus.controller.admin.vo.student.StudentRespVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.student.StudentTempPreviewRespVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.student.StudentTempRecordRespVO;
import cn.iocoder.yudao.module.campus.controller.admin.vo.student.StudentUpdateReqVO;
import cn.iocoder.yudao.module.campus.dal.dataobject.StudentDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-29T07:50:30+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_331 (Oracle Corporation)"
)
public class StudentConvertImpl implements StudentConvert {

    @Override
    public List<StudentRespVO> convertDO2RespVO(List<StudentDO> list) {
        if ( list == null ) {
            return null;
        }

        List<StudentRespVO> list1 = new ArrayList<StudentRespVO>( list.size() );
        for ( StudentDO studentDO : list ) {
            list1.add( convertDo( studentDO ) );
        }

        return list1;
    }

    @Override
    public StudentDO convert(StudentCreateReqVO reqVO) {
        if ( reqVO == null ) {
            return null;
        }

        StudentDO studentDO = new StudentDO();

        studentDO.setName( reqVO.getName() );
        studentDO.setSex( reqVO.getSex() );
        studentDO.setStudentNo( reqVO.getStudentNo() );
        studentDO.setAvatar( reqVO.getAvatar() );
        studentDO.setIdCard( reqVO.getIdCard() );
        studentDO.setDeptId( reqVO.getDeptId() );

        return studentDO;
    }

    @Override
    public StudentTempRecordRespVO convert(StudentDO studentDO) {
        if ( studentDO == null ) {
            return null;
        }

        StudentTempRecordRespVO studentTempRecordRespVO = new StudentTempRecordRespVO();

        studentTempRecordRespVO.setName( studentDO.getName() );
        studentTempRecordRespVO.setSex( studentDO.getSex() );
        studentTempRecordRespVO.setStudentNo( studentDO.getStudentNo() );
        studentTempRecordRespVO.setAvatar( studentDO.getAvatar() );
        studentTempRecordRespVO.setIdCard( studentDO.getIdCard() );
        studentTempRecordRespVO.setDeptId( studentDO.getDeptId() );
        studentTempRecordRespVO.setId( studentDO.getId() );

        return studentTempRecordRespVO;
    }

    @Override
    public StudentRespVO convertDo(StudentDO studentDO) {
        if ( studentDO == null ) {
            return null;
        }

        StudentRespVO studentRespVO = new StudentRespVO();

        studentRespVO.setName( studentDO.getName() );
        studentRespVO.setSex( studentDO.getSex() );
        studentRespVO.setStudentNo( studentDO.getStudentNo() );
        studentRespVO.setAvatar( studentDO.getAvatar() );
        studentRespVO.setIdCard( studentDO.getIdCard() );
        studentRespVO.setDeptId( studentDO.getDeptId() );
        studentRespVO.setId( studentDO.getId() );

        return studentRespVO;
    }

    @Override
    public StudentDO convertUpdate(StudentUpdateReqVO reqVO) {
        if ( reqVO == null ) {
            return null;
        }

        StudentDO studentDO = new StudentDO();

        studentDO.setId( reqVO.getId() );
        studentDO.setName( reqVO.getName() );
        studentDO.setSex( reqVO.getSex() );
        studentDO.setStudentNo( reqVO.getStudentNo() );
        studentDO.setAvatar( reqVO.getAvatar() );
        studentDO.setIdCard( reqVO.getIdCard() );
        studentDO.setDeptId( reqVO.getDeptId() );

        return studentDO;
    }

    @Override
    public StudentDO convert(StudentExcelVo bean) {
        if ( bean == null ) {
            return null;
        }

        StudentDO studentDO = new StudentDO();

        studentDO.setName( bean.getName() );
        studentDO.setSex( bean.getSex() );
        studentDO.setStudentNo( bean.getStudentNo() );
        studentDO.setIdCard( bean.getIdCard() );

        return studentDO;
    }

    @Override
    public List<StudentTempPreviewRespVO> convertDO2TempRespVO(List<StudentDO> list) {
        if ( list == null ) {
            return null;
        }

        List<StudentTempPreviewRespVO> list1 = new ArrayList<StudentTempPreviewRespVO>( list.size() );
        for ( StudentDO studentDO : list ) {
            list1.add( studentDOToStudentTempPreviewRespVO( studentDO ) );
        }

        return list1;
    }

    protected StudentTempPreviewRespVO studentDOToStudentTempPreviewRespVO(StudentDO studentDO) {
        if ( studentDO == null ) {
            return null;
        }

        StudentTempPreviewRespVO studentTempPreviewRespVO = new StudentTempPreviewRespVO();

        studentTempPreviewRespVO.setName( studentDO.getName() );
        studentTempPreviewRespVO.setSex( studentDO.getSex() );
        studentTempPreviewRespVO.setStudentNo( studentDO.getStudentNo() );
        studentTempPreviewRespVO.setAvatar( studentDO.getAvatar() );
        studentTempPreviewRespVO.setIdCard( studentDO.getIdCard() );
        studentTempPreviewRespVO.setDeptId( studentDO.getDeptId() );
        studentTempPreviewRespVO.setId( studentDO.getId() );

        return studentTempPreviewRespVO;
    }
}
