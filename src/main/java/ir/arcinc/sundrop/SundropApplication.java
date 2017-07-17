package ir.arcinc.sundrop;

import ir.arcinc.sundrop.model.User;
import ir.arcinc.sundrop.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class SundropApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SundropApplication.class, args);

//		String[] beans = context.getBeanDefinitionNames();
//		Arrays.sort(beans);
//		for (String b : beans)
//			System.out.println(b);

		User taha = new User();
		taha.setUsername("taha");
		taha.setPassword("man");
		UserRepository repository = context.getBean(UserRepository.class);
		repository.save(taha);
	}
}
