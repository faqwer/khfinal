package finaltp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import finaltp.mainBbs.model.MainBbsDAO;

@Controller
public class IndexController {

	@Autowired
	private MainBbsDAO mainBbsDao;
	
	@RequestMapping("/index.do")
	public String indexMain() {
		return "index";
	}
	
	@RequestMapping("/readNum.do")
	public ModelAndView setReadNum(@RequestParam("bbs_idx") int bbs_idx, @RequestParam("category") String category) {
		ModelAndView mav = new ModelAndView();
		mainBbsDao.setReadNum(bbs_idx, category);
		if(category.equals("review")) {
			mav.addObject("gopage", "reviewContent.do?bbs_idx=" + bbs_idx);
		}
		mav.setViewName("readNum");
		return mav;
	}
}
