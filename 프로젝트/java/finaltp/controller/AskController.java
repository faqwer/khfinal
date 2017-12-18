package finaltp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import finaltp.ask.model.AskDAO;
import finaltp.ask.model.AskDTO;
import finaltp.mainBbs.model.MainBbsDAO;
import finaltp.mainBbs.model.MainBbsDTO;
import finaltp.member.model.MemberDTO;

@Controller
public class AskController {

	@Autowired
	private MainBbsDAO mainBbsDao;

	@Autowired
	private AskDAO askDao;

	// 1:1문의 목록
	@RequestMapping("/askList.do")
	public ModelAndView askList(HttpSession session, @RequestParam(value = "cp", defaultValue = "1") int cp) {
		int totalCnt = mainBbsDao.getTotalCnt("ask");
		int listSize = 5;
		int pageSize = 5;

		MemberDTO loginUser = null;

		String userid = (String) session.getAttribute("userid");
		ModelAndView mav = new ModelAndView();
		List<MainBbsDTO> mainList = askDao.askList(cp, listSize, "normal"); // total_bbs에서 acc 게시물 가져옴
		//List<AskDTO> askList = askDao.askList(cp, listSize, mainList); // ask 테이블에서 ask 게시물 가져옴

		for (int i = 0; i < mainList.size(); i++) {
			mainList.get(i).setContent(mainList.get(i).getContent().replaceAll("\n", "<br>"));
			//mainList.get(i).setAskdto(askList.get(i));
			mainList.get(i).setUserid(mainBbsDao.getUserId(mainList.get(i).getWriter_idx()));
		}

		if ((String) session.getAttribute("userid") != null) {
			loginUser = mainBbsDao.getLoginUserInfo(userid); // 로그인 중인 사용자 정보
		}
		mav.addObject("mainList", mainList);
		mav.addObject("loginUser", loginUser);
		String pageStr = finaltp.paging.PageModule.makePage("askList.do", totalCnt, listSize, pageSize, cp);
		mav.addObject("pageStr", pageStr);
		mav.setViewName("ask/askList");
		return mav;
	}

	// 1:1문의 작성 폼으로 이동
	@RequestMapping(value = "/askWrite.do", method = RequestMethod.GET)
	public ModelAndView askWriteForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("ask/askWrite");
		return mav;
	}

	// 1:1문의 작성내용 DB에 저장
	@RequestMapping(value = "/askWrite.do", method = RequestMethod.POST)
	public ModelAndView askWrite(HttpSession session, @RequestParam("subject") String subject,
			@RequestParam(value = "secret", defaultValue = "no") String secret,
			@RequestParam("content") String content) {
		ModelAndView mav = new ModelAndView();
		String userid = (String) session.getAttribute("userid");
		int writer_idx = mainBbsDao.getMemberIdx(userid);
		MainBbsDTO dto = new MainBbsDTO(writer_idx, "ask", subject, content);
		int mainResult = askDao.mainAskWrite(dto);
		int result = askDao.askWrite(dto, secret);
		mav.addObject("msg", result * mainResult > 0 ? "작성 완료" : "작성 실패");
		mav.addObject("gopage", "askList.do");
		mav.setViewName("msg");
		return mav;
	}
	
	// 1:1문의 본문
	@RequestMapping("/askContent.do")
	public ModelAndView askContent(HttpSession session, @RequestParam("bbs_idx") int bbs_idx) {
		ModelAndView mav = new ModelAndView();
		String userid = (String) session.getAttribute("userid"); // 현재 접속중인 유저 아이디 가져옴
		MainBbsDTO mainList = mainBbsDao.bbsContent(bbs_idx); // 메인 테이블에 있는 정보
		mainList.setUserid(mainBbsDao.getUserId(mainList.getWriter_idx())); // 메인DTO에 작성자 ID 추가
		System.out.println("userid : " + userid);
		AskDTO askList = askDao.askContent(bbs_idx); // ask 테이블에서 정보 가져옴
		
		mainList.setAskdto(askList); // AskDTO 메인DTO에 추가
		mav.addObject("mainList", mainList);
		mav.setViewName("ask/askContent");
		return mav;
	}
	
	// 1:1문의 수정 폼
	@RequestMapping(value = "/askRevise.do", method = RequestMethod.GET)
	public ModelAndView askReviseForm(@RequestParam("bbs_idx") int bbs_idx) {
		ModelAndView mav = new ModelAndView();
		MainBbsDTO mainList = mainBbsDao.bbsContent(bbs_idx);
		AskDTO askList = askDao.askContent(bbs_idx);
		mainList.setAskdto(askList);
		mav.addObject("mainList", mainList);
		mav.setViewName("ask/askRevise");
		return mav;
	}
	
	// 1:1문의 수정
	@RequestMapping(value = "/askRevise.do", method = RequestMethod.POST)
	public ModelAndView askRevise(@RequestParam("bbs_idx") int bbs_idx, @RequestParam("subject") String subject, @RequestParam("content") String content) {
		ModelAndView mav = new ModelAndView();
		int result = mainBbsDao.mainBbsRevise(bbs_idx, subject, content);
		mav.addObject("msg", result > 0 ? "수정 완료" : "수정 실패");
		mav.addObject("gopage", "askContent.do?bbs_idx=" + bbs_idx);
		mav.setViewName("msg");
		return mav;
	}
	
	@RequestMapping("/askDelete.do")
	public ModelAndView askDelete(@RequestParam("bbs_idx") int bbs_idx) {
		ModelAndView mav = new ModelAndView();
		int result = mainBbsDao.mainBbsStatusDefer(bbs_idx);
		mav.addObject("msg", result > 0 ? "삭제 완료" : "삭제 실패");
		mav.addObject("gopage", "askList.do");
		mav.setViewName("msg");
		return mav;
	}
}
