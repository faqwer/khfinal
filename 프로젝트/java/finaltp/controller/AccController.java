package finaltp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import finaltp.acc.model.AccDAO;

@Controller
public class AccController {

	@Autowired
	private AccDAO accDao;
		
	@ResponseBody
	@RequestMapping("/accComment.do")
	public ModelAndView accWrite() {
		ModelAndView mav = new ModelAndView();
		
		return mav;
	}
	
	
}
