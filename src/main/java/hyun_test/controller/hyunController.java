package hyun_test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class hyunController {
	
	@RequestMapping("/index.do")
	public String indexForm() {
		return "indexMain";
	}
	
	@RequestMapping("/jqueryForm.do")
	public String jqueryForm() {
		return "jQuery-Test/jQuery_Test";
	}
	
	@RequestMapping("/ajax.do")
	public ModelAndView ajaxForm(@RequestParam(value="test",defaultValue="test")String test) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("test",test);
		mav.setViewName("jQuery-Test/ajax_Test");
		return mav;
	}
	@RequestMapping("/ajax2.do")
	public ModelAndView ajaxForm() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("jQuery-Test/ajax_Test2");
		return mav;
	}
	@RequestMapping("/notice.do")
	public ModelAndView noticeForm() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("jQuery-Test/getNotice");
		return mav;
	}
	@RequestMapping("/test.do")
	public ModelAndView test() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("jQuery-Test/test");
		return mav;
	}
	@RequestMapping("/writeForm.do")
	public ModelAndView write() {
		System.out.println("test");
		ModelAndView mav=new ModelAndView();
		mav.setViewName("bbs/SE2/write");
		return mav;
	}
	@RequestMapping("/insertForm.do")
	public ModelAndView insert(@RequestParam("title")String title,@RequestParam("content")String content) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("bbs/SE2/insert");
		mav.addObject("title",title);
		mav.addObject("content",content);
		return mav;
	}
	@RequestMapping("/writeForm2.do")
	public ModelAndView write2() {
		System.out.println("test");
		ModelAndView mav=new ModelAndView();
		mav.setViewName("bbs/SE2_img/write");
		return mav;
	}
	@RequestMapping("/insertForm.2do")
	public ModelAndView insert2(@RequestParam("title")String title,@RequestParam("content")String content) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("bbs/SE2_img/insert");
		mav.addObject("title",title);
		mav.addObject("content",content);
		return mav;
	}
}
