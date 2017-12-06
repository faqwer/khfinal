package finaltp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import finaltp.acc.model.AccDTO;
import finaltp.mainBbs.model.MainBbsDAO;
import finaltp.mainBbs.model.MainBbsDTO;
import finaltp.reply.model.ReplyDAO;
import finaltp.reply.model.ReplyDTO;

@Controller
public class MainBbsController {

	@Autowired
	private MainBbsDAO mainBbsDao;

	@Autowired
	private ReplyDAO replyDao;

	@RequestMapping("/noticeList.do")
	public ModelAndView noticeList(@RequestParam(value = "cp", defaultValue = "1") int cp) {
		int totalCnt = mainBbsDao.getTotalCnt();
		int listSize = 5;
		int pageSize = 5;

		ModelAndView mav = new ModelAndView();
		List<MainBbsDTO> dto = mainBbsDao.noticeList(cp, listSize);
		mav.addObject("noticeList", dto);
		String pageStr = finaltp.paging.PageModule.makePage("noticeList.do", totalCnt, listSize, pageSize, cp);
		mav.addObject("pageStr", pageStr);
		mav.setViewName("notice/noticeList");
		return mav;
	}

	@RequestMapping("/noticeContent.do")
	public ModelAndView noticeContent(int idx) {
		ModelAndView mav = new ModelAndView();
		List<MainBbsDTO> dto = mainBbsDao.noticeContent(idx);
		mav.addObject("noticeContent", dto);
		mav.setViewName("notice/noticeContent");
		return mav;
	}

	@RequestMapping("/accWrite.do")
	public ModelAndView accWrite(HttpSession session, @RequestParam("nation") String nation,
			@RequestParam("content") String content) {
		ModelAndView mav = new ModelAndView();
		String userid = (String) session.getAttribute("userid");
		int member_idx = mainBbsDao.getMemberIdx(userid);
		MainBbsDTO dto = new MainBbsDTO(member_idx, "acc", "normal", content, 0, 0, 0);
		int result = mainBbsDao.accWrite(dto, nation);
		String msg = result > 0 ? "작성 성공" : "작성 실패";
		mav.addObject("msg", msg);
		mav.setViewName("acc/accList");
		return mav;
	}

	@RequestMapping("/accList.do")
	public ModelAndView accList(@RequestParam(value = "cp", defaultValue = "1") int cp) {
		int totalCnt = mainBbsDao.getTotalCnt();
		int listSize = 5;
		int pageSize = 5;

		ModelAndView mav = new ModelAndView();
		List<MainBbsDTO> mainList = mainBbsDao.mainAccList(cp, listSize);
		List<AccDTO> accList = mainBbsDao.accList(cp, listSize);
		List<ReplyDTO> replyList = null;

		for (int i = 0; i < mainList.size(); i++) {
			mainList.get(i).setContent(mainList.get(i).getContent().replaceAll("\n", "<br>"));
			mainList.get(i).setAccdto(accList.get(i));
			mainList.get(i).setUserid(mainBbsDao.getUserId(mainList.get(i).getMember_idx()));

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
}
