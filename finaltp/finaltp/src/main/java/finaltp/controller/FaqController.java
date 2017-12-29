package finaltp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import finaltp.faq.model.FaqDAO;
import finaltp.faq.model.FaqDTO;

@Controller
public class FaqController {

	@Autowired
	private FaqDAO faqDao;
	
	@RequestMapping("/faqList.do")
	public ModelAndView faqList() {
		ModelAndView mav = new ModelAndView();
		List<FaqDTO> dto = faqDao.faqList();
		mav.addObject("faqList", dto);
		mav.setViewName("faq/faqList");
		return mav;
	}
}
