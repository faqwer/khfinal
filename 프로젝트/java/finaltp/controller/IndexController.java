package finaltp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import finaltp.main.model.MainDAO;
import finaltp.main.model.MainDTO;
import finaltp.mainBbs.model.MainBbsDAO;

@Controller
public class IndexController {


	@Autowired
	private MainBbsDAO mainBbsDao;
	
	@Autowired
	private MainDAO mainDao;

//	@RequestMapping("/index.do")
//	public String indexForm() {
//		return "indexMain";
//	}
	
	@RequestMapping("/index.do")
	public ModelAndView indexForm() {
		
		ModelAndView mav=new ModelAndView();
		List<MainDTO> reviewRanking=mainDao.reviewRanking();
		int test = reviewRanking.size();
		System.out.println(test);
		System.out.println(reviewRanking.get(0).getBbs_idx());
		System.out.println(reviewRanking.get(0).getCoverimg());
		System.out.println(reviewRanking.get(0).getName());
		System.out.println(reviewRanking.get(0).getRecommendnum());
		mav.addObject("reviewRanking",reviewRanking);
		mav.setViewName("indexMain");
		return mav;
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
