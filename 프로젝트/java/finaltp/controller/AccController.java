package finaltp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import finaltp.acc.model.AccDAO;
import finaltp.acc.model.AccDTO;
import finaltp.mainBbs.model.MainBbsDAO;
import finaltp.mainBbs.model.MainBbsDTO;
import finaltp.member.model.MemberDTO;
import finaltp.recommend.model.RecommendDAO;
import finaltp.recommend.model.RecommendDTO;
import finaltp.reply.model.ReplyDAO;
import finaltp.reply.model.ReplyDTO;

@Controller
public class AccController {

	@Autowired
	private AccDAO accDao;

	@Autowired
	private MainBbsDAO mainBbsDao;

	@Autowired
	private ReplyDAO replyDao;
	
	@Autowired
	private RecommendDAO recommendDao;

	// 동행 게시글 작성
	@RequestMapping("/accWrite.do")
	public ModelAndView accWrite(HttpSession session, @RequestParam("nation") String nation,
			@RequestParam("content") String content) {
		ModelAndView mav = new ModelAndView();
		String userid = (String) session.getAttribute("userid");
		int writer_idx = mainBbsDao.getMemberIdx(userid);
		MainBbsDTO dto = new MainBbsDTO(writer_idx, "acc", "normal", content);
		int result = accDao.accWrite(dto, nation);
		mav.setViewName("acc/accList");
		return mav;
	}

	// 동행 게시글 목록
	@RequestMapping("/accList.do")
	public ModelAndView accList(HttpSession session, @RequestParam(value = "cp", defaultValue = "1") int cp) {
		int totalCnt = mainBbsDao.getTotalCnt("acc");
		int listSize = 5;
		int pageSize = 5;

		String userid = (String) session.getAttribute("userid");
		MemberDTO loginUser = mainBbsDao.getLoginUserInfo(userid); // 멤버 테이블에서 현재 접속중인 사용자의 정보를 가져옴
		ModelAndView mav = new ModelAndView();
		List<MainBbsDTO> mainList = mainBbsDao.mainBbsList(cp, listSize, "acc"); // total_bbs에서 acc 게시물 가져옴
		List<AccDTO> accList = accDao.accList(cp, listSize, mainList); // acc테이블에서 acc 게시물 가져옴
		List<ReplyDTO> replyList = null;

		for (int i = 0; i < mainList.size(); i++) {
			mainList.get(i).setContent(mainList.get(i).getContent().replaceAll("\n", "<br>"));
			mainList.get(i).setAccdto(accList.get(i));
			mainList.get(i).setUserid(mainBbsDao.getUserId(mainList.get(i).getWriter_idx()));
			mainList.get(i).setProfileImg(mainBbsDao.getWriterProfileImg(mainList.get(i).getWriter_idx()));
			
			RecommendDTO dto = new RecommendDTO(mainList.get(i).getBbs_idx(), mainList.get(i).getWriter_idx(), mainBbsDao.getMemberIdx(userid));
			mainList.get(i).setRecommend(recommendDao.recommendCheck(dto));
			
			replyList = replyDao.commentList(mainList.get(i).getBbs_idx());

			if (replyList.size() != 0) {
				mainList.get(i).setReplylist(replyList);
			} else {
				mainList.get(i).setReplylist(null);
			}
		}
		mav.addObject("mainList", mainList);
		mav.addObject("accList", accList);
		mav.addObject("loginUser", loginUser);
		String pageStr = finaltp.paging.PageModule.makePage("accList.do", totalCnt, listSize, pageSize, cp);
		mav.addObject("pageStr", pageStr);
		mav.setViewName("acc/accList");
		return mav;
	}
	
	@RequestMapping("/accRevise.do")
	public ModelAndView accRevise(@RequestParam("bbs_idx") int bbs_idx, @RequestParam("content") String content) {
		ModelAndView mav = new ModelAndView();
		int count = accDao.accRevise(bbs_idx, content);
		mav.setViewName("acc/accList");
		return mav;
	}

	// 동행 게시글 삭제
	@RequestMapping("/accDelete.do")
	public ModelAndView accDelete(@RequestParam("bbs_idx") int bbs_idx) {
		ModelAndView mav = new ModelAndView();
		int accTableResult = accDao.accStatusChange(bbs_idx);
		mav.setViewName("acc/accList");
		return mav;
	}

}
