package org.megion.xapp.core.aop;

import org.megion.xapp.core.aop.interceptor.NoTransactionException;
import org.megion.xapp.core.aop.interceptor.YesTransactionException;
import org.megion.xapp.test.context.beans.AopBean;
import org.megion.xapp.test.context.CommonContext;
import org.megion.xapp.test.context.DatasourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
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
public class AopTest  extends AbstractTestNGSpringContextTests {

    @Autowired
    private AopBean aopBean;

    @Test(expectedExceptions = NoTransactionException.class)
    public void testCheckRequiredTransaction() {
        aopBean.requiredTransaction();
    }


    @Test(expectedExceptions = YesTransactionException.class)
    public void testCheckWithoutTransaction() {
        aopBean.withoutTransaction();
    }
}
