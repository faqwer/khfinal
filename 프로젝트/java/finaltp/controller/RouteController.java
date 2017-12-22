package finaltp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import finaltp.acc.model.AccDTO;
import finaltp.mainBbs.model.MainBbsDAO;
import finaltp.mainBbs.model.MainBbsDTO;
import finaltp.member.model.MemberDTO;
import finaltp.recommend.model.RecommendDAO;
import finaltp.recommend.model.RecommendDTO;
import finaltp.reply.model.ReplyDAO;
import finaltp.reply.model.ReplyDTO;
import finaltp.route.model.RouteDAO;
import finaltp.route.model.RouteDTO;

@Controller
public class RouteController {

	@Autowired
	private RouteDAO routeDao;

	@Autowired
	private MainBbsDAO mainBbsDao;

	@Autowired
	private ReplyDAO replyDao;
	
	@Autowired
	private RecommendDAO recommendDao;

	// 루트 게시글 목록
	@RequestMapping(value = "routeList.do", method = RequestMethod.GET)
	public ModelAndView routeList(HttpSession session,@RequestParam(value = "cp", defaultValue = "1") int cp) {
		int totalCnt = mainBbsDao.getTotalCnt("route");
		int listSize = 5;
		int pageSize = 5;

		MemberDTO loginUser=null;
		RecommendDTO dto=null;
		
		String userid=(String) session.getAttribute("userid");
		ModelAndView mav = new ModelAndView();
		List<MainBbsDTO> mainList = mainBbsDao.mainBbsList(cp, listSize, "route");
		List<RouteDTO> routeList = routeDao.routeList(cp, listSize, mainList);
		List<ReplyDTO> replyList = null;

		for (int i = 0; i < mainList.size(); i++) {
			mainList.get(i).setContent(mainList.get(i).getContent().replaceAll("\n", "<br>"));
			mainList.get(i).setRoutedto(routeList.get(i));
			mainList.get(i).setUserid(mainBbsDao.getUserId(mainList.get(i).getWriter_idx()));

			if((String) session.getAttribute("userid") != null) {
				loginUser = mainBbsDao.getLoginUserInfo(userid); // 로그인 중인 사용자 정보
				
				// 로그인 중인 사용자 해당 게시물 추천여부 확인
				dto = new RecommendDTO(mainList.get(i).getBbs_idx(), mainList.get(i).getWriter_idx(), mainBbsDao.getMemberIdx(userid));
				mainList.get(i).setRecommend(recommendDao.recommendCheck(dto));
			}
			
			replyList = replyDao.commentList(mainList.get(i).getBbs_idx());

			if (replyList.size() != 0) {
				mainList.get(i).setReplylist(replyList);
			} else {
				mainList.get(i).setReplylist(null);
			}
		}
		mav.addObject("mainList", mainList);
		mav.addObject("loginUser", loginUser);
		mav.addObject("routeList", routeList);
		String pageStr = finaltp.paging.PageModule.makePage("routeList.do", totalCnt, listSize, pageSize, cp);
		mav.addObject("pageStr", pageStr);
		mav.setViewName("route/routeList");

		return mav;
	}

	// 루트 본문 보기
	@RequestMapping(value = "routeContent.do", method = RequestMethod.GET)
	public ModelAndView routeContent(@RequestParam(value = "cp", defaultValue = "1") int cp, @RequestParam("bbs_idx") int bbs_idx) {
		int totalCnt = mainBbsDao.getTotalCnt("route");
		int listSize = 5;
		int pageSize = 5;

		ModelAndView mav = new ModelAndView();
		MainBbsDTO mainList = mainBbsDao.bbsContent(bbs_idx);
		RouteDTO routeContent = routeDao.routeContent(bbs_idx);
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
