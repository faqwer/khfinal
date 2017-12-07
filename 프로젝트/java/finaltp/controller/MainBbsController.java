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
		int totalCnt = mainBbsDao.getTotalCnt("notice");
		int listSize = 5;
		int pageSize = 5;

		ModelAndView mav = new ModelAndView();
		List<MainBbsDTO> dto = mainBbsDao.mainBbsList(cp, listSize, "notice");
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

	
}
