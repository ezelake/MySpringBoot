package ceq.maven.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import ceq.maven.data.Person;
import ceq.maven.h2.Connector;

@RestController
public class MainController {

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

	@RequestMapping("/hola")
	public String hola() {
		return "Hola";
	}

	@RequestMapping("/thyme")
	public String thyme() {
		Context context = new Context();
		context.setVariable("mytext", text);
		return templateEngine.process("thyme", context);
	}

	@RequestMapping("/dbmem")
	public boolean dbMem() {
		try {
			return !Connector.getConnection().isClosed();
		} catch (SQLException e) {
			return false;
		}
	}

	@RequestMapping("/persons")
	public Set<Person> persons() {
		Set<Person> set = new HashSet<Person>();

		try {
			Connection conn = Connector.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select ID, NAME from PERSON");
			while (rs.next()) {
				set.add(new Person(rs.getString(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return set;
	}

}
