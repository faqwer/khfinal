package finaltp.controller;

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

	// 동행 게시글 작성
	@RequestMapping("/accWrite.do")
	public ModelAndView accWrite(HttpSession session, @RequestParam("nation") String nation,
			@RequestParam("content") String content) {
		ModelAndView mav = new ModelAndView();
		String userid = (String) session.getAttribute("userid");
		int writer_idx = mainBbsDao.getMemberIdx(userid);
		MainBbsDTO dto = new MainBbsDTO(writer_idx, "acc", "normal", content);
		int result = accDao.accWrite(dto, nation);
		String msg = result > 0 ? "작성 성공" : "작성 실패";
		mav.addObject("msg", msg);
		mav.setViewName("acc/accList");
		return mav;
	}

	// 동행 게시글 목록
	@RequestMapping("/accList.do")
	public ModelAndView accList(@RequestParam(value = "cp", defaultValue = "1") int cp) {
		int totalCnt = mainBbsDao.getTotalCnt("acc");
		int listSize = 5;
		int pageSize = 5;

		ModelAndView mav = new ModelAndView();
		List<MainBbsDTO> mainList = mainBbsDao.mainBbsList(cp, listSize, "acc");
		List<AccDTO> accList = accDao.accList(cp, listSize);
		List<ReplyDTO> replyList = null;

		for (int i = 0; i < mainList.size(); i++) {
			mainList.get(i).setContent(mainList.get(i).getContent().replaceAll("\n", "<br>"));
			mainList.get(i).setAccdto(accList.get(i));
			mainList.get(i).setUserid(mainBbsDao.getUserId(mainList.get(i).getWriter_idx()));

			replyList = replyDao.commentList(mainList.get(i).getBbs_idx());

			if (replyList.size() != 0) {
				mainList.get(i).setReplylist(replyList);
			} else {
				mainList.get(i).setReplylist(null);
			}
		}
		mav.addObject("mainList", mainList);
		mav.addObject("accList", accList);
		String pageStr = finaltp.paging.PageModule.makePage("accList.do", totalCnt, listSize, pageSize, cp);
		mav.addObject("pageStr", pageStr);
		mav.setViewName("acc/accList");
		return mav;
	}

	// 동행 게시글 삭제
	@RequestMapping("/accdelete.do")
	public ModelAndView accDelete(@RequestParam("bbs_idx") int bbs_idx) {
		ModelAndView mav = new ModelAndView();
		int accTableResult = accDao.accStatusChange(bbs_idx);
		int commentCount = replyDao.getCommentCount(bbs_idx);

		int replyTableResult = 1;

		if (commentCount > 0) {
			replyTableResult = replyDao.commentAllDelete(bbs_idx);
		}
		int mainTableResult = mainBbsDao.mainBbsStatusChange(bbs_idx, "acc");
		System.out.println("accTableResult : " + accTableResult);
		System.out.println("mainTableResult : " + mainTableResult);
		System.out.println("replyTableResult : " + replyTableResult);
		mav.addObject("result", accTableResult * mainTableResult * replyTableResult);
		mav.setViewName("acc/accList");
		return mav;
	}

}
