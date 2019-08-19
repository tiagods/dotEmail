package dot.demo;

import dot.demo.email.EmailDto;
import dot.demo.email.EmailerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DotEmailApplicationTests {

	@Autowired
	EmailerService emailerService;

	@Test
	public void contextLoads() {
		EmailDto emailDto = new EmailDto();
		emailDto.setFrom("comunicadoprolink@prolinkcontabil.com.br");
		emailDto.setTemplateName("email-inlineimage");
		emailDto.setTemplateName("email-inlineimage");
		emailDto.setSubject("Teste do Spring");
		emailDto.setTo(new String[]{"tiagoice@hotmail.com"});

		Map<String, Object> param = new HashMap<>();
		param.put("name","Tiago");
		emailDto.setParameterMap(param);
		try {
			emailerService.sendHtmlEmail(emailDto);
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
