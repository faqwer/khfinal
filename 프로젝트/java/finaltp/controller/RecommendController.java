package finaltp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import finaltp.recommend.model.RecommendDAO;
import finaltp.recommend.model.RecommendDTO;

@Controller
public class RecommendController {

	@Autowired
	private RecommendDAO recommendDao;
	
	@RequestMapping("/recommendUp.do")
	public ModelAndView recommendUp(@RequestParam("bbs_idx") int bbs_idx, @RequestParam("writer_idx") int writer_idx, @RequestParam("user_idx") int user_idx) {
		ModelAndView mav = new ModelAndView();
		RecommendDTO dto = new RecommendDTO(bbs_idx, writer_idx, user_idx);
		int result = recommendDao.recommendUp(dto);
		mav.setViewName("acc/accList");
		return mav;
	}
	
	@RequestMapping("/recommendDown.do")
	public ModelAndView recommendDown(@RequestParam("bbs_idx") int bbs_idx, @RequestParam("writer_idx") int writer_idx, @RequestParam("user_idx") int user_idx) {
		ModelAndView mav = new ModelAndView();
		RecommendDTO dto = new RecommendDTO(bbs_idx, writer_idx, user_idx);
		int result = recommendDao.recommendDown(dto);
		mav.setViewName("acc/accList");
		return mav;
	}
	
}
