package cn.iocoder.yudao.module.campus.framework.datapermission.config;

import cn.iocoder.yudao.framework.datapermission.core.dept.rule.DeptDataPermissionRuleCustomizer;
import cn.iocoder.yudao.module.campus.dal.dataobject.InspectDO;
import cn.iocoder.yudao.module.campus.dal.dataobject.StudentDO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: 智慧校园
 * @description: campus 模块的数据权限 Configuration
 * @author: pengzhenlong
 * @date: 2022/7/5
 */
@Configuration(proxyBeanMethods = false)
public class CampusDataPermissionConfiguration {

    @Bean
    public DeptDataPermissionRuleCustomizer campusDeptDataPermissionRuleCustomizer() {
        return rule -> {
            //学生类
            rule.addDeptColumn(StudentDO.class);
            //检查记录类
            rule.addDeptColumn(InspectDO.class);
//            rule.addUserColumn(InspectDO.class);
        };
    }

}
