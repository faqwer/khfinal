package finaltp.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import finaltp.mail.MailService;


@Controller
public class MailController {
	@Autowired
	private MailService mailService;
	
	@RequestMapping(value = "mail_test.do", method = RequestMethod.GET)
	public ModelAndView test() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sendMail/mail");

		return mav;
	}
	
	@RequestMapping(value="sendMail.do", method = RequestMethod.POST, produces="application/json" )
	public ModelAndView send(HttpSession session, @RequestParam String email) {
		
		boolean re = sendMail(session, email);
		System.out.println(re);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sendMail/mail");
		mav.addObject("id", email);
		
		return mav;
	}
	
	
	@ResponseBody
	private boolean sendMail(HttpSession session, String email) {
		System.out.println("들어오긴했나");
		int randomCode = new Random().nextInt(100000)+10000;
		String joinCode = String.valueOf(randomCode);
		session.setAttribute("joinCode", joinCode);
		
		String subject = "회원가입 승인 번호 입니다.";
		StringBuilder sb = new StringBuilder();
		sb.append("회원가입 승인번호는 ").append(joinCode).append(" 입니다.");
		
		return mailService.send(subject, sb.toString(), "20140313s@gmail.com", email);
	}
	
	

	
}
