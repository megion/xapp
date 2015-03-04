package org.megion.xapp.core.aop;

import org.megion.xapp.core.entity.Member;
import org.megion.xapp.core.service.UserService;
import org.megion.xapp.test.beans.AopTestBean;
import org.megion.xapp.test.context.CommonContext;
import org.megion.xapp.test.context.DatasourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

@Test
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class
})
@DirtiesContext
@DbUnitConfiguration(databaseConnection = "databaseConnection")
@ContextConfiguration(classes = {
        DatasourceContext.class, CommonContext.class
})
public class AopTest {

	@Autowired
	private AopTestBean aopTestBean;
	
    public void testCheckTransaction() {
    	aopTestBean.requiredTransaction();
    }
}
