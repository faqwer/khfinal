package finaltp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

import javax.servlet.http.HttpSession;

import finaltp.acc.model.AccDAO;
import finaltp.acc.model.AccDTO;
import finaltp.admin.model.AdminDAO;
import finaltp.ask.model.AskDAO;
import finaltp.ask.model.AskDTO;
import finaltp.mainBbs.model.MainBbsDAO;
import finaltp.mainBbs.model.MainBbsDTO;
import finaltp.member.model.MemberDTO;
import finaltp.report.model.ReportDAO;
import finaltp.report.model.ReportDTO;
import finaltp.review.model.ReviewDAO;
import finaltp.review.model.ReviewDTO;

@Controller
public class AdminController {

	@Autowired
	private AdminDAO adminDao;

	@Autowired
	private MainBbsDAO mainBbsDao;

	@Autowired
	private AccDAO accDao;

	@Autowired
	private ReviewDAO reviewDao;

	@Autowired
	private AskDAO askDao;

	@Autowired
	private ReportDAO reportDao;

	// 회원관리 페이지로 이동
	@RequestMapping(value = "/adminMember.do")
	public ModelAndView member(@RequestParam(value = "type", defaultValue = "all") String type) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/adminMember");
		return mav;
	}

	// 전체 회원 목록 DB 조회
	@RequestMapping(value = "/adminAllMemberList.do")
	public ModelAndView allMemberList(@RequestParam(value = "cp", defaultValue = "1") int cp,
			@RequestParam(value = "type", defaultValue = "all") String type) {
		int totalCnt = adminDao.getMemberTotalCnt(type);
		int listSize = 5;
		int pageSize = 5;
		ModelAndView mav = new ModelAndView();
		List<MemberDTO> memberList = adminDao.memberList(cp, listSize, type);
		System.out.println("all : " + memberList);
		String pageStr = finaltp.paging.AjaxPageModule.makePage("adminMember.do?type=" + type, totalCnt, listSize,
				pageSize, cp);
		mav.addObject("memberList", memberList);
		mav.addObject("pageStr", pageStr);
		mav.setViewName("admin/adminAllMember");
		return mav;
	}

	// 일반 회원 목록 DB 조회
	@RequestMapping(value = "/adminNormalMemberList.do")
	public ModelAndView normalMemberList(@RequestParam(value = "cp", defaultValue = "1") int cp,
			@RequestParam(value = "type", defaultValue = "all") String type) {
		int totalCnt = adminDao.getMemberTotalCnt(type);
		int listSize = 5;
		int pageSize = 5;

		ModelAndView mav = new ModelAndView();
		List<MemberDTO> memberList = adminDao.memberList(cp, listSize, type);
		System.out.println("normal : " + memberList);
		String pageStr = finaltp.paging.AjaxPageModule.makePage("adminMember.do?type=" + type, totalCnt, listSize,
				pageSize, cp);
		mav.addObject("memberList", memberList);
		mav.addObject("pageStr", pageStr);
		mav.setViewName("admin/adminNormalMember");
		return mav;
	}

	// 블랙리스트 회원 목록 DB 조회
	@RequestMapping(value = "/adminBlackMemberList.do")
	public ModelAndView blackMemberList(@RequestParam(value = "cp", defaultValue = "1") int cp,
			@RequestParam(value = "type", defaultValue = "all") String type) {
		int totalCnt = adminDao.getMemberTotalCnt(type);
		int listSize = 5;
		int pageSize = 5;
		ModelAndView mav = new ModelAndView();
		List<MemberDTO> memberList = adminDao.memberList(cp, listSize, type);
		System.out.println("black : " + memberList);
		String pageStr = finaltp.paging.AjaxPageModule.makePage("adminMember.do?type=" + type, totalCnt, listSize,
				pageSize, cp);
		mav.addObject("memberList", memberList);
		mav.addObject("pageStr", pageStr);
		mav.setViewName("admin/adminBlackMember");
		return mav;
	}

	// 멤버 검색
	@RequestMapping("/adminMemberSearch.do")
	public ModelAndView memberSearch(@RequestParam("sel") String sel, @RequestParam("text") String text,
			@RequestParam("type") String type) {
		ModelAndView mav = new ModelAndView();
		List<MemberDTO> memberList = adminDao.memberSearch(sel, text, type);
		mav.addObject("memberList", memberList);
		if (type.equals("all")) {
			mav.setViewName("admin/adminAllMember");
		} else if (type.equals("normal")) {
			mav.setViewName("admin/adminNormalMember");
		} else {
			mav.setViewName("admin/adminBlackMember");
		}
		return mav;
	}

	// 회원 탈퇴
	@RequestMapping("/adminMemberOut.do")
	public ModelAndView memberOut(@RequestParam(value = "cp", defaultValue = "1") int cp, @RequestParam("id") String id,
			@RequestParam("type") String type) {
		int totalCnt = adminDao.getMemberTotalCnt(type);
		int listSize = 5;
		int pageSize = 5;

		ModelAndView mav = new ModelAndView();
		String arr[] = id.split(",");
		int result = adminDao.memberOut(arr);
		List<MemberDTO> memberList = adminDao.memberList(cp, listSize, type);
		mav.addObject("memberList", memberList);

		if (type.equals("all")) {
			mav.setViewName("admin/adminAllMember");
		} else if (type.equals("normal")) {
			mav.setViewName("admin/adminNormalMember");
		} else {
			mav.setViewName("admin/adminBlackMember");
		}
		return mav;
	}

	// 블랙리스트로 전환
	@RequestMapping("/normalBlackMove.do")
	public ModelAndView adminNormalBlackMove(@RequestParam(value = "cp", defaultValue = "1") int cp,
			@RequestParam("id") String id, @RequestParam("type") String type) {
		int totalCnt = adminDao.getMemberTotalCnt(type);
		int listSize = 5;
		int pageSize = 5;

		ModelAndView mav = new ModelAndView();
		String arr[] = id.split(",");
		int result = adminDao.normalBlackMove(arr);
		List<MemberDTO> memberList = adminDao.memberList(cp, listSize, type);
		mav.addObject("memberList", memberList);
		mav.setViewName("admin/adminNormalMember");
		return mav;
	}

	// 일반회원으로 전환
	@RequestMapping("/blackNormalMove.do")
	public ModelAndView adminBlackNormalMove(@RequestParam(value = "cp", defaultValue = "1") int cp,
			@RequestParam("id") String id, @RequestParam("type") String type) {
		int totalCnt = adminDao.getMemberTotalCnt(type);
		int listSize = 5;
		int pageSize = 5;

		ModelAndView mav = new ModelAndView();
		String arr[] = id.split(",");
		int result = adminDao.blackNormalMove(arr);
		List<MemberDTO> memberList = adminDao.memberList(cp, listSize, type);
		mav.addObject("memberList", memberList);
		mav.setViewName("admin/adminBlackMember");
		return mav;
	}

	// 게시판 관리 페이지로 이동
	@RequestMapping("/adminBbs.do")
	public ModelAndView adminBbs() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/adminBbs");
		return mav;
	}

	// 해당 게시판 리스트
	@RequestMapping("/adminBbsList.do")
	public ModelAndView adminBbsList(@RequestParam(value = "cp", defaultValue = "1") int cp,
			@RequestParam("sel") String sel, @RequestParam("status") String status) {
		int totalCnt = adminDao.getAdminBbsTotalCnt(sel, status);
		int listSize = 5;
		int pageSize = 5;

		ModelAndView mav = new ModelAndView();
		List<MainBbsDTO> mainList = adminDao.getMainBbsList(cp, listSize, sel, status);
		if (sel.equals("planner")) {
			if (status.equals("normal")) {

			} else {

			}
		} else if (sel.equals("acc")) {
			List<AccDTO> accList = accDao.accList(cp, listSize, mainList); // acc테이블에서 acc 게시물 가져옴
			for (int i = 0; i < mainList.size(); i++) {
				mainList.get(i).setAccdto(accList.get(i));
				mainList.get(i).setUserid(mainBbsDao.getUserId(mainList.get(i).getWriter_idx()));
				mainList.get(i).setProfileImg(mainBbsDao.getWriterProfileImg(mainList.get(i).getWriter_idx()));
			}
			String pageStr = finaltp.paging.AjaxPageModule.makePage("adminBbs.do?sel=" + sel + "&status=" + status,
					totalCnt, listSize, pageSize, cp);
			if (mainList.size() != 0) {
				mav.addObject("mainList", mainList);
			}
			mav.addObject("pageStr", pageStr);

			if (status.equals("normal")) {
				mav.setViewName("admin/adminBbsStatusNormal");
			} else {
				mav.setViewName("admin/adminBbsStatusDefer");
			}
		} else if (sel.equals("review")) {
			List<ReviewDTO> reviewList = reviewDao.reviewList(cp, listSize, mainList);
			for (int i = 0; i < mainList.size(); i++) {
				mainList.get(i).setReviewdto(reviewList.get(i));
				mainList.get(i).setUserid(mainBbsDao.getUserId(mainList.get(i).getWriter_idx()));
			}
			String pageStr = finaltp.paging.AjaxPageModule.makePage("adminBbs.do?sel=" + sel + "&status=" + status,
					totalCnt, listSize, pageSize, cp);
			if (mainList.size() != 0) {
				mav.addObject("mainList", mainList);
			}
			mav.addObject("pageStr", pageStr);

			if (status.equals("normal")) {
				mav.setViewName("admin/adminBbsStatusNormal");
			} else {
				mav.setViewName("admin/adminBbsStatusDefer");
			}
		} else if (sel.equals("route")) { // 경로 추천
			if (status.equals("normal")) {

			} else {

			}
		} else if (sel.equals("ask")) {
			totalCnt = adminDao.getAdminAskTotalCnt(sel, status);
			List<MainBbsDTO> askList = askDao.adminAskList(cp, listSize, status);
			for (int i = 0; i < askList.size(); i++) {
				askList.get(i).setUserid(mainBbsDao.getUserId(askList.get(i).getWriter_idx()));
			}
			String pageStr = finaltp.paging.AjaxPageModule.makePage("adminBbs.do?sel=" + sel + "&status=" + status,
					totalCnt, listSize, pageSize, cp);
			if (askList.size() != 0) {
				mav.addObject("askList", askList);
			}
			mav.addObject("pageStr", pageStr);
			if (status.equals("normal")) {
				mav.setViewName("admin/adminAskNormal");
			} else {
				mav.setViewName("admin/adminAskDefer");
			}
		} else if (sel.equals("report")) {
			List<ReportDTO> reportList = reportDao.reportList(cp, listSize);

			for (int i = 0; i < reportList.size(); i++) {
				MainBbsDTO maindto = mainBbsDao.bbsContent(reportList.get(i).getBbs_idx());
				reportList.get(i).setWriter_subject(maindto.getContent().replaceAll("\n", "<br>"));
				reportList.get(i).setReason(reportList.get(i).getReason().replaceAll("\n", "<br>"));
				reportList.get(i).setUserid(mainBbsDao.getUserId(reportList.get(i).getWriter_idx())); // 작성자 id
				reportList.get(i).setReportid(mainBbsDao.getUserId(reportList.get(i).getUser_idx())); // 신고자 id
			}
			String pageStr = finaltp.paging.AjaxPageModule.makePage("adminBbs.do?sel=" + sel + "&status=" + status,
					totalCnt, listSize, pageSize, cp);
			if (reportList.size() != 0) {
				mav.addObject("reportList", reportList);
			}
			mav.addObject("pageStr", pageStr);

			if (status.equals("normal")) {
				mav.setViewName("admin/adminReportNormal");
			} else {
				mav.setViewName("admin/adminReportDefer");
			}
		}
		return mav;
	}

	// 게시글 일반 -> 보류
	@RequestMapping("/normalDeferMove.do")
	public ModelAndView normalDeferMove(@RequestParam(value = "cp", defaultValue = "1") int cp,
			@RequestParam("sel") String sel, @RequestParam("status") String status,
			@RequestParam("statusChange") String statusChange) {
		int totalCnt = adminDao.getAdminBbsTotalCnt(sel, status);
		int listSize = 5;
		int pageSize = 5;

		ModelAndView mav = new ModelAndView();
		String arr[] = statusChange.split(",");
		int bbs_idx[] = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			bbs_idx[i] = Integer.parseInt(arr[i]);
		}
		int result = adminDao.normalDeferMove(bbs_idx);
		List<MainBbsDTO> mainList = adminDao.getMainBbsList(cp, listSize, sel, status);
		if (sel.equals("planner")) {
			if (status.equals("normal")) {

			} else {

			}
		} else if (sel.equals("acc")) {
			List<AccDTO> accList = accDao.accList(cp, listSize, mainList); // acc테이블에서 acc 게시물 가져옴
			for (int i = 0; i < mainList.size(); i++) {
				mainList.get(i).setAccdto(accList.get(i));
				mainList.get(i).setUserid(mainBbsDao.getUserId(mainList.get(i).getWriter_idx()));
				mainList.get(i).setProfileImg(mainBbsDao.getWriterProfileImg(mainList.get(i).getWriter_idx()));
			}
			String pageStr = finaltp.paging.AjaxPageModule.makePage("adminBbs.do?sel=" + sel + "&status=" + status,
					totalCnt, listSize, pageSize, cp);
			if (mainList.size() != 0) {
				mav.addObject("mainList", mainList);
			}
			mav.addObject("pageStr", pageStr);

			if (status.equals("normal")) {
				mav.setViewName("admin/adminBbsStatusNormal");
			} else {
				mav.setViewName("admin/adminBbsStatusDefer");
			}
		} else if (sel.equals("review")) {
			List<ReviewDTO> reviewList = reviewDao.reviewList(cp, listSize, mainList);
			for (int i = 0; i < mainList.size(); i++) {
				mainList.get(i).setReviewdto(reviewList.get(i));
				mainList.get(i).setUserid(mainBbsDao.getUserId(mainList.get(i).getWriter_idx()));
			}
			String pageStr = finaltp.paging.AjaxPageModule.makePage("adminBbs.do?sel=" + sel + "&status=" + status,
					totalCnt, listSize, pageSize, cp);
			if (mainList.size() != 0) {
				mav.addObject("mainList", mainList);
			}
			mav.addObject("pageStr", pageStr);
			if (status.equals("normal")) {
				mav.setViewName("admin/adminBbsStatusNormal");
			} else {
				mav.setViewName("admin/adminBbsStatusDefer");
			}
		} else if (sel.equals("route")) {
			if (status.equals("normal")) {

			} else {

			}
		} else if (sel.equals("ask")) {
			totalCnt = adminDao.getAdminBbsTotalCnt(sel, status);
			List<MainBbsDTO> askList = askDao.adminAskList(cp, listSize, sel);
			for (int i = 0; i < askList.size(); i++) {
				askList.get(i).setUserid(mainBbsDao.getUserId(askList.get(i).getWriter_idx()));
			}
			String pageStr = finaltp.paging.AjaxPageModule.makePage("adminBbs.do?sel=" + sel + "&status=" + status,
					totalCnt, listSize, pageSize, cp);

			if (askList.size() != 0) {
				mav.addObject("askList", askList);
			}
			mav.addObject("pageStr", pageStr);
			if (status.equals("normal")) {
				mav.setViewName("admin/adminAskNormal");
			} else {
				mav.setViewName("admin/adminAskDefer");
			}
		}
		return mav;
	}

	// 보류 -> 일반 전환
	@RequestMapping("/deferNormalMove.do")
	public ModelAndView deferNormalMove(@RequestParam(value = "cp", defaultValue = "1") int cp,
			@RequestParam("sel") String sel, @RequestParam("status") String status,
			@RequestParam("statusChange") String statusChange) {
		int totalCnt = adminDao.getAdminBbsTotalCnt(sel, status);
		int listSize = 5;
		int pageSize = 5;

		ModelAndView mav = new ModelAndView();
		String arr[] = statusChange.split(",");
		int bbs_idx[] = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			bbs_idx[i] = Integer.parseInt(arr[i]);
		}
		int result = adminDao.deferNormalMove(bbs_idx);
		List<MainBbsDTO> mainList = adminDao.getMainBbsList(cp, listSize, sel, status);
		if (sel.equals("planner")) {
			if (status.equals("normal")) {

			} else {

			}
		} else if (sel.equals("acc")) {
			List<AccDTO> accList = accDao.accList(cp, listSize, mainList); // acc테이블에서 acc 게시물 가져옴
			for (int i = 0; i < mainList.size(); i++) {
				mainList.get(i).setAccdto(accList.get(i));
				mainList.get(i).setUserid(mainBbsDao.getUserId(mainList.get(i).getWriter_idx()));
				mainList.get(i).setProfileImg(mainBbsDao.getWriterProfileImg(mainList.get(i).getWriter_idx()));
			}
			String pageStr = finaltp.paging.AjaxPageModule.makePage("adminBbs.do?sel=" + sel + "&status=" + status,
					totalCnt, listSize, pageSize, cp);
			if (mainList.size() != 0) {
				mav.addObject("mainList", mainList);
			}
			mav.addObject("pageStr", pageStr);
			if (status.equals("normal")) {
				mav.setViewName("admin/adminBbsStatusNormal");
			} else {
				mav.setViewName("admin/adminBbsStatusDefer");
			}
		} else if (sel.equals("review")) {
			List<ReviewDTO> reviewList = reviewDao.reviewList(cp, listSize, mainList);
			for (int i = 0; i < mainList.size(); i++) {
				mainList.get(i).setReviewdto(reviewList.get(i));
				mainList.get(i).setUserid(mainBbsDao.getUserId(mainList.get(i).getWriter_idx()));
			}
			String pageStr = finaltp.paging.AjaxPageModule.makePage("adminBbs.do?sel=" + sel + "&status=" + status,
					totalCnt, listSize, pageSize, cp);

			if (mainList.size() != 0) {
				mav.addObject("mainList", mainList);
			}
			mav.addObject("pageStr", pageStr);

			if (status.equals("normal")) {
				mav.setViewName("admin/adminBbsStatusNormal");
			} else {
				mav.setViewName("admin/adminBbsStatusDefer");
			}
		} else if (sel.equals("route")) {
			if (status.equals("normal")) {

			} else {

			}
		} else if (sel.equals("ask")) {
			totalCnt = adminDao.getAdminBbsTotalCnt(sel, status);
			List<MainBbsDTO> askList = askDao.adminAskList(cp, listSize, sel);
			for (int i = 0; i < askList.size(); i++) {
				askList.get(i).setUserid(mainBbsDao.getUserId(askList.get(i).getWriter_idx()));
			}
			String pageStr = finaltp.paging.AjaxPageModule.makePage("adminBbs.do?sel=" + sel + "&status=" + status,
					totalCnt, listSize, pageSize, cp);

			if (askList.size() != 0) {
				mav.addObject("askList", askList);
			}

			mav.addObject("pageStr", pageStr);
			if (status.equals("normal")) {
				mav.setViewName("admin/adminAskNormal");
			} else {
				mav.setViewName("admin/adminAskDefer");
			}
		}
		return mav;
	}

	// 게시물 삭제
	@RequestMapping("/adminBbsDelete.do")
	public ModelAndView bbsDelete(@RequestParam(value = "cp", defaultValue = "1") int cp,
			@RequestParam("sel") String sel, @RequestParam("status") String status,
			@RequestParam("statusChange") String statusChange) {
		int totalCnt = adminDao.getAdminBbsTotalCnt(sel, status);
		int listSize = 5;
		int pageSize = 5;

		ModelAndView mav = new ModelAndView();
		String arr[] = statusChange.split(",");
		int bbs_idx[] = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			bbs_idx[i] = Integer.parseInt(arr[i]);
		}
		int result = adminDao.bbsDelete(bbs_idx);
		List<MainBbsDTO> mainList = adminDao.getMainBbsList(cp, listSize, sel, status);
		if (sel.equals("planner")) {

		} else if (sel.equals("acc")) {
			List<AccDTO> accList = accDao.accList(cp, listSize, mainList); // acc테이블에서 acc 게시물 가져옴
			for (int i = 0; i < mainList.size(); i++) {
				mainList.get(i).setAccdto(accList.get(i));
				mainList.get(i).setUserid(mainBbsDao.getUserId(mainList.get(i).getWriter_idx()));
				mainList.get(i).setProfileImg(mainBbsDao.getWriterProfileImg(mainList.get(i).getWriter_idx()));
			}
			String pageStr = finaltp.paging.AjaxPageModule.makePage("adminBbs.do?sel=" + sel + "&status=" + status,
					totalCnt, listSize, pageSize, cp);
			if (mainList.size() != 0) {
				mav.addObject("mainList", mainList);
			}
			mav.addObject("pageStr", pageStr);
			mav.setViewName("admin/adminBbsStatusDefer");
		} else if (sel.equals("review")) {
			List<ReviewDTO> reviewList = reviewDao.reviewList(cp, listSize, mainList);
			for (int i = 0; i < mainList.size(); i++) {
				mainList.get(i).setReviewdto(reviewList.get(i));
				mainList.get(i).setUserid(mainBbsDao.getUserId(mainList.get(i).getWriter_idx()));
			}
			String pageStr = finaltp.paging.AjaxPageModule.makePage("adminBbs.do?sel=" + sel + "&status=" + status,
					totalCnt, listSize, pageSize, cp);
			if (mainList.size() != 0) {
				mav.addObject("mainList", mainList);
			}
			mav.addObject("pageStr", pageStr);
			mav.setViewName("admin/adminBbsStatusDefer");
		} else if (sel.equals("route")) {

		} else if (sel.equals("ask")) {
			totalCnt = adminDao.getAdminBbsTotalCnt(sel, status);
			List<MainBbsDTO> askList = askDao.adminAskList(cp, listSize, sel);
			for (int i = 0; i < askList.size(); i++) {
				askList.get(i).setUserid(mainBbsDao.getUserId(askList.get(i).getWriter_idx()));
			}
			String pageStr = finaltp.paging.AjaxPageModule.makePage("adminBbs.do?sel=" + sel + "&status=" + status,
					totalCnt, listSize, pageSize, cp);

			if (askList.size() != 0) {
				mav.addObject("askList", askList);
			}
			mav.addObject("pageStr", pageStr);
			mav.setViewName("admin/adminAskDefer");
		} else if(sel.equals("report")) {
			totalCnt = adminDao.getAdminBbsTotalCnt(sel, status);
			List<ReportDTO> reportList = reportDao.reportList(cp, listSize);

			for (int i = 0; i < reportList.size(); i++) {
				MainBbsDTO maindto = mainBbsDao.bbsContent(reportList.get(i).getBbs_idx());
				reportList.get(i).setWriter_subject(maindto.getContent().replaceAll("\n", "<br>"));
				reportList.get(i).setReason(reportList.get(i).getReason().replaceAll("\n", "<br>"));
				reportList.get(i).setUserid(mainBbsDao.getUserId(reportList.get(i).getWriter_idx())); // 작성자 id
				reportList.get(i).setReportid(mainBbsDao.getUserId(reportList.get(i).getUser_idx())); // 신고자 id
			}
			String pageStr = finaltp.paging.AjaxPageModule.makePage("adminBbs.do?sel=" + sel + "&status=" + status,
					totalCnt, listSize, pageSize, cp);
			if (reportList.size() != 0) {
				mav.addObject("reportList", reportList);
			}
			mav.addObject("pageStr", pageStr);

			if (status.equals("normal")) {
				mav.setViewName("admin/adminReportNormal");
			} else {
				mav.setViewName("admin/adminReportDefer");
			}
		}
		return mav;
	}

	// 관리자 답변 페이지 이동
	@RequestMapping(value = "/adminAskReWriteForm.do")
	public ModelAndView askReWriteForm(int bbs_idx, int ref, int lev, int sunbun, String secret) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("bbs_idx", bbs_idx);
		mav.addObject("ref", ref);
		mav.addObject("lev", lev);
		mav.addObject("sunbun", sunbun);
		mav.addObject("secret", secret);
		mav.setViewName("admin/adminAskReWrite");
		return mav;
	}

	// 관리자 답변
	@RequestMapping(value = "/adminAskReWrite.do")
	public ModelAndView askReWrite(HttpSession session, @RequestParam(value = "cp", defaultValue = "1") int cp,
			@RequestParam("sel") String sel, @RequestParam("status") String status,
			@RequestParam("subject") String subject, @RequestParam("content") String content,
			@RequestParam("bbs_idx") int bbs_idx, @RequestParam("ref") int ref, @RequestParam("lev") int lev,
			@RequestParam("sunbun") int sunbun, @RequestParam("secret") String secret) {
		ModelAndView mav = new ModelAndView();
		String userid = (String) session.getAttribute("userid");
		int writer_idx = mainBbsDao.getMemberIdx(userid);
		MainBbsDTO mainDto = new MainBbsDTO(writer_idx, "ask", subject, content);
		int mainResult = askDao.mainAskWrite(mainDto);
		AskDTO askDto = new AskDTO(bbs_idx, writer_idx, "no", secret, ref, lev, sunbun);
		int result = askDao.askReWrite(askDto);

		String msg = mainResult * result > 0 ? "답변 완료" : "답변 실패";
		mav.addObject("msg", msg);
		mav.addObject("gopage", "adminBbs.do?sel=ask&status=normal&cp=1");
		mav.setViewName("msg");
		return mav;
	}

	// 관리자 신고 처리 페이지 이동
	@RequestMapping("adminReportProcessForm.do")
	public ModelAndView adminReportProcessForm(@RequestParam("bbs_idx") int bbs_idx,
			@RequestParam("writer_idx") int writer_idx) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("bbs_idx", bbs_idx);
		mav.addObject("writer_idx", writer_idx);
		mav.setViewName("admin/adminReportProcess");
		return mav;
	}

	// 관리자 신고 처리
	@RequestMapping("adminReportProcess.do")
	public ModelAndView adminReportProcess(@RequestParam("bbs_idx") int bbs_idx, @RequestParam("writer_idx") int writer_idx,
			@RequestParam(value = "report", defaultValue = "no") String report,
			@RequestParam("admin_content") String admin_content) {
		ModelAndView mav = new ModelAndView();
		int result = reportDao.adminReportProcess(bbs_idx, writer_idx, report, admin_content);
		String msg = result > 0 ? "처리 완료" : "처리 실패";
		mav.addObject("msg", msg);
		mav.addObject("gopage", "adminBbs.do?sel=report&status=normal&cp=1");
		mav.setViewName("msg");
		return mav;
	}
}
