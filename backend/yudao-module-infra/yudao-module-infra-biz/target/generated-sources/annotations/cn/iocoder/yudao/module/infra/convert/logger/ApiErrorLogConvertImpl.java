package cn.iocoder.yudao.module.infra.convert.logger;

import cn.iocoder.yudao.framework.apilog.core.service.dto.ApiErrorLogCreateReqDTO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.infra.controller.admin.logger.vo.apierrorlog.ApiErrorLogExcelVO;
import cn.iocoder.yudao.module.infra.controller.admin.logger.vo.apierrorlog.ApiErrorLogRespVO;
import cn.iocoder.yudao.module.infra.dal.dataobject.logger.ApiErrorLogDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-29T07:50:10+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_331 (Oracle Corporation)"
)
public class ApiErrorLogConvertImpl implements ApiErrorLogConvert {

    @Override
    public ApiErrorLogRespVO convert(ApiErrorLogDO bean) {
        if ( bean == null ) {
            return null;
        }

        ApiErrorLogRespVO apiErrorLogRespVO = new ApiErrorLogRespVO();

        apiErrorLogRespVO.setTraceId( bean.getTraceId() );
        if ( bean.getUserId() != null ) {
            apiErrorLogRespVO.setUserId( bean.getUserId().intValue() );
        }
        apiErrorLogRespVO.setUserType( bean.getUserType() );
        apiErrorLogRespVO.setApplicationName( bean.getApplicationName() );
        apiErrorLogRespVO.setRequestMethod( bean.getRequestMethod() );
        apiErrorLogRespVO.setRequestUrl( bean.getRequestUrl() );
        apiErrorLogRespVO.setRequestParams( bean.getRequestParams() );
        apiErrorLogRespVO.setUserIp( bean.getUserIp() );
        apiErrorLogRespVO.setUserAgent( bean.getUserAgent() );
        apiErrorLogRespVO.setExceptionTime( bean.getExceptionTime() );
        apiErrorLogRespVO.setExceptionName( bean.getExceptionName() );
        apiErrorLogRespVO.setExceptionMessage( bean.getExceptionMessage() );
        apiErrorLogRespVO.setExceptionRootCauseMessage( bean.getExceptionRootCauseMessage() );
        apiErrorLogRespVO.setExceptionStackTrace( bean.getExceptionStackTrace() );
        apiErrorLogRespVO.setExceptionClassName( bean.getExceptionClassName() );
        apiErrorLogRespVO.setExceptionFileName( bean.getExceptionFileName() );
        apiErrorLogRespVO.setExceptionMethodName( bean.getExceptionMethodName() );
        apiErrorLogRespVO.setExceptionLineNumber( bean.getExceptionLineNumber() );
        apiErrorLogRespVO.setProcessStatus( bean.getProcessStatus() );
        if ( bean.getId() != null ) {
            apiErrorLogRespVO.setId( bean.getId().intValue() );
        }
        apiErrorLogRespVO.setCreateTime( bean.getCreateTime() );
        apiErrorLogRespVO.setProcessTime( bean.getProcessTime() );
        if ( bean.getProcessUserId() != null ) {
            apiErrorLogRespVO.setProcessUserId( bean.getProcessUserId().intValue() );
        }

        return apiErrorLogRespVO;
    }

    @Override
    public PageResult<ApiErrorLogRespVO> convertPage(PageResult<ApiErrorLogDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<ApiErrorLogRespVO> pageResult = new PageResult<ApiErrorLogRespVO>();

        pageResult.setList( apiErrorLogDOListToApiErrorLogRespVOList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    @Override
    public List<ApiErrorLogExcelVO> convertList02(List<ApiErrorLogDO> list) {
        if ( list == null ) {
            return null;
        }

        List<ApiErrorLogExcelVO> list1 = new ArrayList<ApiErrorLogExcelVO>( list.size() );
        for ( ApiErrorLogDO apiErrorLogDO : list ) {
            list1.add( apiErrorLogDOToApiErrorLogExcelVO( apiErrorLogDO ) );
        }

        return list1;
    }

    @Override
    public ApiErrorLogDO convert(ApiErrorLogCreateReqDTO bean) {
        if ( bean == null ) {
            return null;
        }

        ApiErrorLogDO apiErrorLogDO = new ApiErrorLogDO();

        apiErrorLogDO.setUserId( bean.getUserId() );
        apiErrorLogDO.setTraceId( bean.getTraceId() );
        apiErrorLogDO.setUserType( bean.getUserType() );
        apiErrorLogDO.setApplicationName( bean.getApplicationName() );
        apiErrorLogDO.setRequestMethod( bean.getRequestMethod() );
        apiErrorLogDO.setRequestUrl( bean.getRequestUrl() );
        apiErrorLogDO.setRequestParams( bean.getRequestParams() );
        apiErrorLogDO.setUserIp( bean.getUserIp() );
        apiErrorLogDO.setUserAgent( bean.getUserAgent() );
        apiErrorLogDO.setExceptionTime( bean.getExceptionTime() );
        apiErrorLogDO.setExceptionName( bean.getExceptionName() );
        apiErrorLogDO.setExceptionMessage( bean.getExceptionMessage() );
        apiErrorLogDO.setExceptionRootCauseMessage( bean.getExceptionRootCauseMessage() );
        apiErrorLogDO.setExceptionStackTrace( bean.getExceptionStackTrace() );
        apiErrorLogDO.setExceptionClassName( bean.getExceptionClassName() );
        apiErrorLogDO.setExceptionFileName( bean.getExceptionFileName() );
        apiErrorLogDO.setExceptionMethodName( bean.getExceptionMethodName() );
        apiErrorLogDO.setExceptionLineNumber( bean.getExceptionLineNumber() );

        return apiErrorLogDO;
    }

    protected List<ApiErrorLogRespVO> apiErrorLogDOListToApiErrorLogRespVOList(List<ApiErrorLogDO> list) {
        if ( list == null ) {
            return null;
        }

        List<ApiErrorLogRespVO> list1 = new ArrayList<ApiErrorLogRespVO>( list.size() );
        for ( ApiErrorLogDO apiErrorLogDO : list ) {
            list1.add( convert( apiErrorLogDO ) );
        }

        return list1;
    }

    protected ApiErrorLogExcelVO apiErrorLogDOToApiErrorLogExcelVO(ApiErrorLogDO apiErrorLogDO) {
        if ( apiErrorLogDO == null ) {
            return null;
        }

        ApiErrorLogExcelVO apiErrorLogExcelVO = new ApiErrorLogExcelVO();

        if ( apiErrorLogDO.getId() != null ) {
            apiErrorLogExcelVO.setId( apiErrorLogDO.getId().intValue() );
        }
        apiErrorLogExcelVO.setTraceId( apiErrorLogDO.getTraceId() );
        if ( apiErrorLogDO.getUserId() != null ) {
            apiErrorLogExcelVO.setUserId( apiErrorLogDO.getUserId().intValue() );
        }
        apiErrorLogExcelVO.setUserType( apiErrorLogDO.getUserType() );
        apiErrorLogExcelVO.setApplicationName( apiErrorLogDO.getApplicationName() );
        apiErrorLogExcelVO.setRequestMethod( apiErrorLogDO.getRequestMethod() );
        apiErrorLogExcelVO.setRequestUrl( apiErrorLogDO.getRequestUrl() );
        apiErrorLogExcelVO.setRequestParams( apiErrorLogDO.getRequestParams() );
        apiErrorLogExcelVO.setUserIp( apiErrorLogDO.getUserIp() );
        apiErrorLogExcelVO.setUserAgent( apiErrorLogDO.getUserAgent() );
        apiErrorLogExcelVO.setExceptionTime( apiErrorLogDO.getExceptionTime() );
        apiErrorLogExcelVO.setExceptionName( apiErrorLogDO.getExceptionName() );
        apiErrorLogExcelVO.setExceptionMessage( apiErrorLogDO.getExceptionMessage() );
        apiErrorLogExcelVO.setExceptionRootCauseMessage( apiErrorLogDO.getExceptionRootCauseMessage() );
        apiErrorLogExcelVO.setExceptionStackTrace( apiErrorLogDO.getExceptionStackTrace() );
        apiErrorLogExcelVO.setExceptionClassName( apiErrorLogDO.getExceptionClassName() );
        apiErrorLogExcelVO.setExceptionFileName( apiErrorLogDO.getExceptionFileName() );
        apiErrorLogExcelVO.setExceptionMethodName( apiErrorLogDO.getExceptionMethodName() );
        apiErrorLogExcelVO.setExceptionLineNumber( apiErrorLogDO.getExceptionLineNumber() );
        apiErrorLogExcelVO.setCreateTime( apiErrorLogDO.getCreateTime() );
        apiErrorLogExcelVO.setProcessStatus( apiErrorLogDO.getProcessStatus() );
        apiErrorLogExcelVO.setProcessTime( apiErrorLogDO.getProcessTime() );
        if ( apiErrorLogDO.getProcessUserId() != null ) {
            apiErrorLogExcelVO.setProcessUserId( apiErrorLogDO.getProcessUserId().intValue() );
        }

        return apiErrorLogExcelVO;
    }
}
