package cn.iocoder.yudao.module.infra.convert.logger;

import cn.iocoder.yudao.framework.apilog.core.service.dto.ApiAccessLogCreateReqDTO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.infra.controller.admin.logger.vo.apiaccesslog.ApiAccessLogExcelVO;
import cn.iocoder.yudao.module.infra.controller.admin.logger.vo.apiaccesslog.ApiAccessLogRespVO;
import cn.iocoder.yudao.module.infra.dal.dataobject.logger.ApiAccessLogDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-29T07:50:10+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_331 (Oracle Corporation)"
)
public class ApiAccessLogConvertImpl implements ApiAccessLogConvert {

    @Override
    public ApiAccessLogRespVO convert(ApiAccessLogDO bean) {
        if ( bean == null ) {
            return null;
        }

        ApiAccessLogRespVO apiAccessLogRespVO = new ApiAccessLogRespVO();

        apiAccessLogRespVO.setTraceId( bean.getTraceId() );
        apiAccessLogRespVO.setUserId( bean.getUserId() );
        apiAccessLogRespVO.setUserType( bean.getUserType() );
        apiAccessLogRespVO.setApplicationName( bean.getApplicationName() );
        apiAccessLogRespVO.setRequestMethod( bean.getRequestMethod() );
        apiAccessLogRespVO.setRequestUrl( bean.getRequestUrl() );
        apiAccessLogRespVO.setRequestParams( bean.getRequestParams() );
        apiAccessLogRespVO.setUserIp( bean.getUserIp() );
        apiAccessLogRespVO.setUserAgent( bean.getUserAgent() );
        apiAccessLogRespVO.setBeginTime( bean.getBeginTime() );
        apiAccessLogRespVO.setEndTime( bean.getEndTime() );
        apiAccessLogRespVO.setDuration( bean.getDuration() );
        apiAccessLogRespVO.setResultCode( bean.getResultCode() );
        apiAccessLogRespVO.setResultMsg( bean.getResultMsg() );
        apiAccessLogRespVO.setId( bean.getId() );
        apiAccessLogRespVO.setCreateTime( bean.getCreateTime() );

        return apiAccessLogRespVO;
    }

    @Override
    public List<ApiAccessLogRespVO> convertList(List<ApiAccessLogDO> list) {
        if ( list == null ) {
            return null;
        }

        List<ApiAccessLogRespVO> list1 = new ArrayList<ApiAccessLogRespVO>( list.size() );
        for ( ApiAccessLogDO apiAccessLogDO : list ) {
            list1.add( convert( apiAccessLogDO ) );
        }

        return list1;
    }

    @Override
    public PageResult<ApiAccessLogRespVO> convertPage(PageResult<ApiAccessLogDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<ApiAccessLogRespVO> pageResult = new PageResult<ApiAccessLogRespVO>();

        pageResult.setList( convertList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    @Override
    public List<ApiAccessLogExcelVO> convertList02(List<ApiAccessLogDO> list) {
        if ( list == null ) {
            return null;
        }

        List<ApiAccessLogExcelVO> list1 = new ArrayList<ApiAccessLogExcelVO>( list.size() );
        for ( ApiAccessLogDO apiAccessLogDO : list ) {
            list1.add( apiAccessLogDOToApiAccessLogExcelVO( apiAccessLogDO ) );
        }

        return list1;
    }

    @Override
    public ApiAccessLogDO convert(ApiAccessLogCreateReqDTO bean) {
        if ( bean == null ) {
            return null;
        }

        ApiAccessLogDO apiAccessLogDO = new ApiAccessLogDO();

        apiAccessLogDO.setTraceId( bean.getTraceId() );
        apiAccessLogDO.setUserId( bean.getUserId() );
        apiAccessLogDO.setUserType( bean.getUserType() );
        apiAccessLogDO.setApplicationName( bean.getApplicationName() );
        apiAccessLogDO.setRequestMethod( bean.getRequestMethod() );
        apiAccessLogDO.setRequestUrl( bean.getRequestUrl() );
        apiAccessLogDO.setRequestParams( bean.getRequestParams() );
        apiAccessLogDO.setUserIp( bean.getUserIp() );
        apiAccessLogDO.setUserAgent( bean.getUserAgent() );
        apiAccessLogDO.setBeginTime( bean.getBeginTime() );
        apiAccessLogDO.setEndTime( bean.getEndTime() );
        apiAccessLogDO.setDuration( bean.getDuration() );
        apiAccessLogDO.setResultCode( bean.getResultCode() );
        apiAccessLogDO.setResultMsg( bean.getResultMsg() );

        return apiAccessLogDO;
    }

    protected ApiAccessLogExcelVO apiAccessLogDOToApiAccessLogExcelVO(ApiAccessLogDO apiAccessLogDO) {
        if ( apiAccessLogDO == null ) {
            return null;
        }

        ApiAccessLogExcelVO apiAccessLogExcelVO = new ApiAccessLogExcelVO();

        apiAccessLogExcelVO.setId( apiAccessLogDO.getId() );
        apiAccessLogExcelVO.setTraceId( apiAccessLogDO.getTraceId() );
        apiAccessLogExcelVO.setUserId( apiAccessLogDO.getUserId() );
        apiAccessLogExcelVO.setUserType( apiAccessLogDO.getUserType() );
        apiAccessLogExcelVO.setApplicationName( apiAccessLogDO.getApplicationName() );
        apiAccessLogExcelVO.setRequestMethod( apiAccessLogDO.getRequestMethod() );
        apiAccessLogExcelVO.setRequestUrl( apiAccessLogDO.getRequestUrl() );
        apiAccessLogExcelVO.setRequestParams( apiAccessLogDO.getRequestParams() );
        apiAccessLogExcelVO.setUserIp( apiAccessLogDO.getUserIp() );
        apiAccessLogExcelVO.setUserAgent( apiAccessLogDO.getUserAgent() );
        apiAccessLogExcelVO.setBeginTime( apiAccessLogDO.getBeginTime() );
        apiAccessLogExcelVO.setEndTime( apiAccessLogDO.getEndTime() );
        apiAccessLogExcelVO.setDuration( apiAccessLogDO.getDuration() );
        apiAccessLogExcelVO.setResultCode( apiAccessLogDO.getResultCode() );
        apiAccessLogExcelVO.setResultMsg( apiAccessLogDO.getResultMsg() );

        return apiAccessLogExcelVO;
    }
}
