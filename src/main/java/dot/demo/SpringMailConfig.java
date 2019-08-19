package dot.demo;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class SpringMailConfig {

	@Bean
	public JavaMailSender mailSender() {

		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		mailSender.setHost("email-ssl.com.br"); //change or read from properties
		mailSender.setPort(587);
		mailSender.setProtocol("smtp");
		mailSender.setUsername("comunicadoprolink@prolinkcontabil.com.br");
		mailSender.setPassword("plkc2004");
		// create java mail properties
		Properties mailProperties = new Properties();
		mailProperties.put("mail.smtp.auth", false);
		mailProperties.put("mail.smtp.starttls.enable", false);
		mailProperties.put("mail.smtp.timeout", 5000);
		mailProperties.put("mail.smtp.connectiontimeout", 5000);
		mailSender.setJavaMailProperties(mailProperties);

		return mailSender;

	}

	@Bean(name = "textTemplateEngine")
	public TemplateEngine textTemplateEngine() {
		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.addTemplateResolver(textTemplateResolver());
		return templateEngine;
	}

	private ITemplateResolver textTemplateResolver() {
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("/templates/text/");
		templateResolver.setSuffix(".txt");
		templateResolver.setTemplateMode(TemplateMode.TEXT);
		templateResolver.setCharacterEncoding("UTF8");
		templateResolver.setCheckExistence(true);
		templateResolver.setCacheable(false);
		return templateResolver;
	}

	private ITemplateResolver htmlTemplateResolver() {
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("/templates/html/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCharacterEncoding("UTF8");
		templateResolver.setCheckExistence(true);
		templateResolver.setCacheable(false);
		return templateResolver;
	}

	@Bean(name = "htmlTemplateEngine")
	public TemplateEngine htmlTemplateEngine() {
		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.addTemplateResolver(htmlTemplateResolver());
		return templateEngine;
	}

	private ITemplateResolver fileTemplateResolver() {
		FileTemplateResolver templateResolver = new FileTemplateResolver();
		templateResolver.setPrefix("/Emailer/templates/"); //Change based on your environment
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCharacterEncoding("UTF8");
		templateResolver.setCheckExistence(true);
		templateResolver.setCacheable(false);
		return templateResolver;
	}

	@Bean(name = "fileTemplateEngine")
	public TemplateEngine fileTemplateEngine() {
		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.addTemplateResolver(fileTemplateResolver());
		return templateEngine;
	}

}
