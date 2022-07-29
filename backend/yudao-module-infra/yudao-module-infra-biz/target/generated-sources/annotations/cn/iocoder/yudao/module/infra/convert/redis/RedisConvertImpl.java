package cn.iocoder.yudao.module.infra.convert.redis;

import cn.iocoder.yudao.framework.redis.core.RedisKeyDefine;
import cn.iocoder.yudao.framework.redis.core.RedisKeyDefine.KeyTypeEnum;
import cn.iocoder.yudao.framework.redis.core.RedisKeyDefine.TimeoutTypeEnum;
import cn.iocoder.yudao.module.infra.controller.admin.redis.vo.RedisKeyRespVO;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-29T07:50:10+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_331 (Oracle Corporation)"
)
public class RedisConvertImpl implements RedisConvert {

    @Override
    public List<RedisKeyRespVO> convertList(List<RedisKeyDefine> list) {
        if ( list == null ) {
            return null;
        }

        List<RedisKeyRespVO> list1 = new ArrayList<RedisKeyRespVO>( list.size() );
        for ( RedisKeyDefine redisKeyDefine : list ) {
            list1.add( redisKeyDefineToRedisKeyRespVO( redisKeyDefine ) );
        }

        return list1;
    }

    protected RedisKeyRespVO redisKeyDefineToRedisKeyRespVO(RedisKeyDefine redisKeyDefine) {
        if ( redisKeyDefine == null ) {
            return null;
        }

        String keyTemplate = null;
        KeyTypeEnum keyType = null;
        Class<?> valueType = null;
        TimeoutTypeEnum timeoutType = null;
        Duration timeout = null;
        String memo = null;

        keyTemplate = redisKeyDefine.getKeyTemplate();
        keyType = redisKeyDefine.getKeyType();
        valueType = redisKeyDefine.getValueType();
        timeoutType = redisKeyDefine.getTimeoutType();
        timeout = redisKeyDefine.getTimeout();
        memo = redisKeyDefine.getMemo();

        RedisKeyRespVO redisKeyRespVO = new RedisKeyRespVO( keyTemplate, keyType, valueType, timeoutType, timeout, memo );

        return redisKeyRespVO;
    }
}
