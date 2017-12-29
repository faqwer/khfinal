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
import finaltp.recommend.model.RecommendDAO;
import finaltp.recommend.model.RecommendDTO;
import finaltp.reply.model.ReplyDAO;
import finaltp.reply.model.ReplyDTO;
import finaltp.review.model.ReviewDAO;
import finaltp.review.model.ReviewDTO;

@Controller
public class ReviewController {

	@Autowired
	private MainBbsDAO mainBbsDao;

	@Autowired
	private ReviewDAO reviewDao;
	
	@Autowired
	private RecommendDAO recommendDao;
	
	@Autowired
	private ReplyDAO replyDao;
	

	// 후기 게시판 리스트
	@RequestMapping("/reviewList.do")
	public ModelAndView reviewList(HttpSession session, @RequestParam(value = "cp", defaultValue = "1") int cp) {
		int totalCnt = mainBbsDao.getTotalCnt("review");
		int listSize = 5;
		int pageSize = 5;

		ModelAndView mav = new ModelAndView();
		List<MainBbsDTO> mainList = mainBbsDao.mainBbsList(cp, listSize, "review"); // total_bbs에서 review 게시물 가져옴
		List<ReviewDTO> reviewList = reviewDao.reviewList(cp, listSize, mainList); // review 테이블에서 reviewDTO 가져옴

		for (int i = 0; i < mainList.size(); i++) {
			mainList.get(i).setReviewdto(reviewList.get(i)); // 메인DTO에 리뷰DTO 추가
			mainList.get(i).setRecommendNum(recommendDao.recommendNum(mainList.get(i).getBbs_idx())); // 메인DTO에 추천수 추가
			mainList.get(i).setUserid(mainBbsDao.getUserId(mainList.get(i).getWriter_idx())); // 메인DTO에 작성자 ID 추가
			mainList.get(i).setProfileImg(mainBbsDao.getWriterProfileImg(mainList.get(i).getWriter_idx())); // 메인DTO에 작성자 프로필사진 추가
		}

		String pageStr = finaltp.paging.PageModule.makePage("reviewList.do", totalCnt, listSize, pageSize, cp); // 페이징
		mav.addObject("mainList", mainList);
		mav.addObject("pageStr", pageStr);
		mav.setViewName("review/reviewList");
		return mav;
	}

	// 후기 작성 페이지로 이동
	@RequestMapping(value = "/reviewWrite.do", method = RequestMethod.GET)
	public ModelAndView reviewForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("review/reviewWrite");
		return mav;
	}

	// 후기 작성
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
		mav.setViewName("msg");
		return mav;
	}
	
	// 후기 게시글 본문
	@RequestMapping("/reviewContent.do")
	public ModelAndView reviewContent(HttpSession session, @RequestParam("bbs_idx") int bbs_idx) {
		ModelAndView mav = new ModelAndView();
		MemberDTO loginUser = null;
		
		
		String userid = (String) session.getAttribute("userid"); // 현재 로그인 중인 아이디
		MainBbsDTO mainList = mainBbsDao.bbsContent(bbs_idx); // 메인 테이블에 있는 정보
		ReviewDTO reviewList = reviewDao.reviewContent(bbs_idx); // 후기 테이블에 있는 정보
		
		List<ReplyDTO> replyList = replyDao.commentList(bbs_idx); // 해당 게시물의 댓글 목록

		mainList.setUserid(mainBbsDao.getUserId(mainList.getWriter_idx())); // 메인DTO에 작성자 ID 추가
		mainList.setProfileImg(mainBbsDao.getWriterProfileImg(mainList.getWriter_idx())); // 메인DTO에 작성자 프로필사진 추가
		
		if((String) session.getAttribute("userid") != null) {
			loginUser = mainBbsDao.getLoginUserInfo(userid); // 로그인 중인 사용자 정보
			
			// 로그인중인 사용자 해당 게시물 추천여부 확인
			RecommendDTO dto = new RecommendDTO(mainList.getBbs_idx(), mainList.getWriter_idx(), mainBbsDao.getMemberIdx(userid));
			mainList.setRecommend(recommendDao.recommendCheck(dto));
		}
		mainList.setReviewdto(reviewList);
		mainList.setReplylist(replyList);
		mav.addObject("loginUser", loginUser);
		mav.addObject("mainList", mainList);
		mav.setViewName("review/reviewContent");
		return mav;
	}

	// 수정 Form 페이지로 이동
	@RequestMapping(value = "/reviewRevise.do", method = RequestMethod.GET)
	public ModelAndView reviewReviseForm(@RequestParam("bbs_idx") int bbs_idx) {
		ModelAndView mav = new ModelAndView();
		MainBbsDTO mainDto = mainBbsDao.bbsContent(bbs_idx);
		ReviewDTO reviewDto = reviewDao.reviewContent(bbs_idx);
		mainDto.setReviewdto(reviewDto);
		mav.addObject("mainList", mainDto);
		mav.setViewName("review/reviewRevise");
		return mav;
	}

	// 후기 수정
	@RequestMapping(value = "/reviewRevise.do", method = RequestMethod.POST)
	public ModelAndView reviewRevise(@RequestParam("bbs_idx") int bbs_idx, @RequestParam("subject") String subject,
			@RequestParam("textAreaContentRevise") String content, @RequestParam("thema") String thema, @RequestParam("coverimg") String coverimg) {
		ModelAndView mav = new ModelAndView();
		int reviewMainBbsResult = mainBbsDao.reviewMainBbsRevise(bbs_idx, subject, content);
		int reviewBbsResult = reviewDao.reviewRevise(bbs_idx, thema, coverimg);
		int result = reviewMainBbsResult * reviewBbsResult;
		mav.addObject("msg", result > 0 ? "수정 완료" : "수정 실패");
		mav.addObject("gopage", "reviewContent.do?bbs_idx=" + bbs_idx);
		mav.setViewName("msg");
		return mav;
	}

	// 후기 삭제
	@RequestMapping("/reviewDelete.do")
	public ModelAndView reviewDelete(@RequestParam("bbs_idx") int bbs_idx) {
		ModelAndView mav = new ModelAndView();
		int result = mainBbsDao.mainBbsStatusDefer(bbs_idx);
		mav.addObject("msg", result > 0 ? "삭제 완료" : "삭제 실패");
		mav.addObject("gopage", "reviewList.do");
		mav.setViewName("msg");
		return mav;
	}
}
