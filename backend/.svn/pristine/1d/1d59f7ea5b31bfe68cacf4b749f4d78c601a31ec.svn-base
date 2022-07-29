package cn.iocoder.yudao.module.campus.service.impl;

import cn.iocoder.yudao.module.campus.dal.dataobject.AppUpgradeDO;
import cn.iocoder.yudao.module.campus.dal.mysql.AppUpgradeMapper;
import cn.iocoder.yudao.module.campus.service.AppUpgradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: 智慧校园
 * @description:
 * @author: pengzhenlong
 * @date: 2022/7/28
 */
@Service
@Slf4j
public class AppUpgradeServiceImpl implements AppUpgradeService {

    @Resource
    private AppUpgradeMapper appUpgradeMapper;

    @Override
    public AppUpgradeDO getNewestApp() {
        return appUpgradeMapper.selectNewestOne();
    }
}
