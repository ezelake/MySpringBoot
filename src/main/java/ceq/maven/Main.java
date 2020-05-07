package ceq.maven;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import ceq.maven.h2.Connector;

@RestController
@SpringBootApplication
public class Main {
	
	@Value("${ceq.text:ZZZ}")
	private String text;

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
		SpringApplication application = new SpringApplication(Main.class);
		application.run();
	}

	@RequestMapping("/")
	public String hola() {
		return "Hola";
	}

	@RequestMapping("/thyme")
	public String thyme() {
		Context context = new Context();
		context.setVariable("mytext", text);
		return templateEngine.process("Index", context);
	}

	@RequestMapping("/dbmem")
	public boolean dbMem() {
		try {
			return ! Connector.getConnection().isClosed();
		} catch (SQLException e) {
			return false;
		}
	}
}
