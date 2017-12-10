package finaltp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import finaltp.mainBbs.model.MainBbsDAO;
import finaltp.mainBbs.model.MainBbsDTO;
import finaltp.member.model.MemberDTO;
import finaltp.review.model.ReviewDAO;
import finaltp.review.model.ReviewDTO;

@Controller
public class ReviewController {

	@Autowired
	private MainBbsDAO mainBbsDao;

	@Autowired
	private ReviewDAO reviewDao;

	@RequestMapping("/reviewList.do")
	public ModelAndView reviewList(HttpSession session, @RequestParam(value = "cp", defaultValue = "1") int cp) {
		int totalCnt = mainBbsDao.getTotalCnt("review");
		int listSize = 6;
		int pageSize = 5;

		String userid = (String) session.getAttribute("userid");
		MemberDTO loginUser = mainBbsDao.getLoginUserInfo(userid); // 멤버 테이블에서 현재 접속중인 사용자의 정보를 가져옴
		ModelAndView mav = new ModelAndView();
		List<MainBbsDTO> mainList = mainBbsDao.mainBbsList(cp, listSize, "review"); // total_bbs에서 review 게시물 가져옴
		List<ReviewDTO> reviewList = reviewDao.reviewList(cp, listSize, mainList);

		for (int i = 0; i < mainList.size(); i++) {
			
		}
		mav.setViewName("review/reviewList");
		return mav;
	}

	@RequestMapping(value = "/reviewWrite.do", method = RequestMethod.GET)
	public ModelAndView reviewForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("review/reviewWrite");
		return mav;
	}

	@RequestMapping(value = "/reviewWrite.do", method = RequestMethod.POST)
	public ModelAndView reviewWrite(HttpSession session, @RequestParam("subject") String subject,
			@RequestParam("textAreaContent") String content, @RequestParam("coverimg") String coverimg,
			@RequestParam("thema") String thema) {
		ModelAndView mav = new ModelAndView();
		String userid = (String) session.getAttribute("userid");
		int writer_idx = mainBbsDao.getMemberIdx(userid);

		MainBbsDTO mainDto = new MainBbsDTO(writer_idx, "review", subject, content);
		ReviewDTO reviewDto = new ReviewDTO(0, writer_idx, thema, 0, coverimg);

		int result = reviewDao.reviewWrite(mainDto, reviewDto);
		mav.addObject("msg", result > 0 ? "작성 완료" : "작성 실패");
		mav.addObject("gopage", "reviewList.do");
		mav.setViewName("review/reviewMsg");
		return mav;
	}

}
