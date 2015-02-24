package org.megion.xapp.core.repository;

import org.megion.xapp.core.entity.Member;
import org.megion.xapp.test.context.DatasourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.testng.annotations.Test;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

@Test
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class
})
@DirtiesContext
@DbUnitConfiguration(databaseConnection = "databaseConnection")
@DatabaseSetup("tests-dataset.xml")
@ContextConfiguration(classes = {
        DatasourceContext.class
})
public class MemberRepositoryTests {
    
	@Autowired
	private MemberRepository memberRepository;
	
    public void testFindByEmail() {
    	Member member = memberRepository.findByEmail("john.smith@mailinator.com");
    	System.out.println("member: " + member);
    }
}
