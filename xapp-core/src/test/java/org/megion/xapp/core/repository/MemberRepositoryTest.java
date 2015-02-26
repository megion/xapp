package org.megion.xapp.core.repository;

import java.util.List;

import org.megion.xapp.core.entity.Member;
import org.megion.xapp.core.entity.User;
import org.megion.xapp.core.service.UserService;
import org.megion.xapp.test.context.CommonContext;
import org.megion.xapp.test.context.DatasourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
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
@DatabaseSetup("classpath:tests-dataset.xml")
@ContextConfiguration(classes = {
        DatasourceContext.class, CommonContext.class
})
public class MemberRepositoryTest extends AbstractTestNGSpringContextTests {
    
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	
    public void testFindByEmail() {
    	Member member = memberRepository.findByEmail("john.smith@mailinator.com");
    	Assert.assertNotNull(member);
    	
    	//Member nullMember = memberRepository.findByEmail("null@null.com");
    	//Assert.assertNotNull(nullMember);
    }
    
    public void testUserRepository() {
    	List<User> users = userService.findAllOrderedByUsername();
    	Assert.assertTrue(users.size()>0);
    	
    	users = userRepository.findAllWithRolesOrderedByUsername();
    	for(User user: users) {
    		System.out.println("user: " + user + " roles: " + user.getRoles());
    	}
    	
    	User user1 = userRepository.findById(1l);
    	Assert.assertNotNull(user1);
    }
}
