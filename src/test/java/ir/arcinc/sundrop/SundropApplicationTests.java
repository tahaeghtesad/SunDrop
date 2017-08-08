package ir.arcinc.sundrop;

import ir.arcinc.sundrop.model.User;
import ir.arcinc.sundrop.repository.UserRepository;
import ir.arcinc.sundrop.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SundropApplicationTests {

	@Autowired
	private ApplicationContext context;

	@Test
	public void contextLoads(){
		assertTrue(context.getBean(DataSource.class) != null);
	}

	@Test
	public void addUser() throws Exception {
		UserRepository service = context.getBean(UserRepository.class);
		User u = new User();
		u.setUsername("taha3");
		u.setPassword("me");
		service.save(u);
		assertTrue(service.findUserByUsername("taha3").getPassword().equals("me"));
	}

	@Test
	public void findPreviousUser() throws Exception {

		UserRepository service = context.getBean(UserRepository.class);
		assertTrue(service.findUserByUsername("taha3") == null);
	}

	@Test(expected = UsernameNotFoundException.class)
	public void shouldNotFindPreviousUser() throws Exception {
		UserService service = context.getBean(UserService.class);
		assertTrue(service.loadUserByUsername("taha4") == null);
	}

}
