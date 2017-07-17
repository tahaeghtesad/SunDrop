package ir.arcinc.sundrop;

import ir.arcinc.sundrop.model.Directory;
import ir.arcinc.sundrop.model.User;
import ir.arcinc.sundrop.repository.UserRepository;
import ir.arcinc.sundrop.service.UserService;
import org.h2.server.web.WebServlet;
import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.SQLException;
import java.util.Arrays;

@SpringBootApplication
public class SundropApplication {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = SpringApplication.run(SundropApplication.class, args);

//		String[] beans = context.getBeanDefinitionNames();
//		Arrays.sort(beans);
//		for (String b : beans)
//			System.out.println(b);

		UserService service = context.getBean(UserService.class);
		System.out.println(service.createUser("taha", "man"));
	}
}
