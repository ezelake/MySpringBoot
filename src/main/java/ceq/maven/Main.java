package ceq.maven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.FileTemplateResolver;

@RestController
@SpringBootApplication
public class Main {

	static TemplateEngine templateEngine = null;
	
	static {
		templateEngine = new TemplateEngine();
		FileTemplateResolver fileTemplateResolver = new FileTemplateResolver();
		fileTemplateResolver.setSuffix(".html");
		fileTemplateResolver.setPrefix("src/main/resources/templates/");
		fileTemplateResolver.setTemplateMode("HTML");
		fileTemplateResolver.setCharacterEncoding("UTF-8");
		fileTemplateResolver.setCacheable(false);
		templateEngine.setTemplateResolver(fileTemplateResolver);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@RequestMapping("/")
	public String hola() {
		return "Hola";
	}

	@RequestMapping("/thyme")
	public String thyme() {
		Context context = new Context();
		context.setVariable("mytext", "nuevo");
		return templateEngine.process("Index", context);
	}
}
