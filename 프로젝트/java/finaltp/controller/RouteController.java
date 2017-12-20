package finaltp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import finaltp.route.model.RouteDAO;
import finaltp.route.model.RouteDTO;

@Controller
public class RouteController {

	@Autowired
	private RouteDAO routeDao;
	
	// 루트 게시글 목록
	@RequestMapping(value = "routeList.do", method = RequestMethod.GET)
	public ModelAndView routeList(@RequestParam(value = "cp", defaultValue = "1") int cp) {
		int totalCnt = mainBbsDao.getTotalCnt("route");
		int listSize = 5;
		int pageSize = 5;

		ModelAndView mav = new ModelAndView();
		List<MainBbsDTO> mainList = mainBbsDao.mainBbsList(cp, listSize, "route");
		List<RouteDTO> routeList = routeDao.routeList(cp, listSize);
		List<ReplyDTO> replyList = null;

		for (int i = 0; i < mainList.size(); i++) {
			mainList.get(i).setContent(mainList.get(i).getContent().replaceAll("\n", "<br>"));
			mainList.get(i).setRoutedto(routeList.get(i));
			mainList.get(i).setUserid(mainBbsDao.getUserId(mainList.get(i).getWriter_idx()));

			replyList = replyDao.commentList(mainList.get(i).getBbs_idx());

			if (replyList.size() != 0) {
				mainList.get(i).setReplylist(replyList);
			} else {
				mainList.get(i).setReplylist(null);
			}
		}
		mav.addObject("mainList", mainList);
		// System.out.println("subject : " + mainList.get(0).getSubject());
		mav.addObject("routeList", routeList);
		String pageStr = finaltp.paging.PageModule.makePage("routeList.do", totalCnt, listSize, pageSize, cp);
		mav.addObject("pageStr", pageStr);
		mav.setViewName("route/routeList");

		return mav;
	}

	// 루트 본문 보기
	@RequestMapping(value = "routeContent.do", method = RequestMethod.GET)
	public ModelAndView routeContent(@RequestParam(value = "cp", defaultValue = "1") int cp,
			@RequestParam(value = "idx") int idx) {
		int totalCnt = mainBbsDao.getTotalCnt("route");
		int listSize = 5;
		int pageSize = 5;

		ModelAndView mav = new ModelAndView();
		MainBbsDTO mainList = mainBbsDao.mainBbsContent("route", idx);
		RouteDTO routeContent = routeDao.routeContent(idx, cp, listSize);
		List<ReplyDTO> replyList = null;

		mainList.setContent(mainList.getContent().replaceAll("\n", "<br>"));
		mainList.setRoutedto(routeContent);
		mainList.setUserid(mainBbsDao.getUserId(mainList.getWriter_idx()));

		replyList = replyDao.commentList(mainList.getBbs_idx());

		if (replyList.size() != 0) {
			mainList.setReplylist(replyList);
		} else {
			mainList.setReplylist(null);
		}
		mav.addObject("mainList",mainList);
		mav.addObject("routeContent",routeContent);

		String pageStr = finaltp.paging.PageModule.makePage("routeContent.do", totalCnt, listSize, pageSize, cp);
		mav.addObject("pageStr",pageStr);
		mav.setViewName("route/routeContent");

		return mav;
	}

	
}
