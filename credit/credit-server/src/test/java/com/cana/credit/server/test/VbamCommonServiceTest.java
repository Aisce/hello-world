package com.cana.credit.server.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.vbam.common.credit.enums.Institution;
import com.cana.vbam.common.service.impl.VbamCommonService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/spring/*.xml")
public class VbamCommonServiceTest {

    @Resource
    private VbamCommonService vbamCommonService;

    @Test
    public void test() {
    	vbamCommonService.generateRSAKey(Institution.cana.name());
    }
}