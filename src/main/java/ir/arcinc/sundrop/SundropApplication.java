package ir.arcinc.sundrop;

import ir.arcinc.sundrop.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SundropApplication {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = SpringApplication.run(SundropApplication.class, args);

//		String[] beans = context.getBeanDefinitionNames();
//		Arrays.sort(beans);
//		for (String b : beans)
//			System.out.println(b);

		UserService service = context.getBean(UserService.class);
		service.createUser("taha", "man");
	}
}
