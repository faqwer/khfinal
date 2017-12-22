package finaltp.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import finaltp.acc.model.AccDAO;
import finaltp.acc.model.AccDTO;
import finaltp.mainBbs.model.MainBbsDAO;
import finaltp.mainBbs.model.MainBbsDTO;
import finaltp.member.model.*;
import finaltp.mypage.model.MypageDAO;
import finaltp.plan.model.PlanDAO;
import finaltp.plan.model.PlanDTO;
import finaltp.recommend.model.RecommendDTO;
import finaltp.reply.model.ReplyDTO;
import finaltp.review.model.ReviewDAO;
import finaltp.review.model.ReviewDTO;
import finaltp.route.model.RouteDAO;
import finaltp.route.model.RouteDTO;

@Controller
public class MypageController {

	@Autowired
	private MemberDAO memberDao;

	@Autowired
	private MainBbsDAO mainBbsDao;

	@Autowired
	private ReviewDAO reviewDao;

	@Autowired
	private AccDAO accDao;

	@Autowired
	private PlanDAO planDao;
	
	@Autowired
	private MypageDAO mypageDao;

	// 마이페이지
	@RequestMapping(value = "mypage.do", method = RequestMethod.GET)
	public ModelAndView goMypage(@RequestParam(value = "id") String id, HttpSession session,
			@RequestParam(value = "cp", defaultValue = "1") int cp) {

		ModelAndView mav = new ModelAndView();
		System.out.println("test");
		int totalCnt = mypageDao.planTotalCnt();
		int listSize = 5;
		int pageSize = 5;

		String userid = (String) session.getAttribute("userid");
		System.out.println(userid);
		int w_idx = mypageDao.getMemberIdx(userid);
		MemberDTO loginUser = mainBbsDao.getLoginUserInfo(userid); // 멤버 테이블에서 현재 접속중인 사용자의 정보를 가져옴

		List<PlanDTO> planList = mypageDao.myPlanList(cp, listSize, w_idx); // 게시물 가져옴

		mav.addObject("planList", planList);
		mav.addObject("loginUser", loginUser);
		String pageStr = finaltp.paging.PageModule.makePage("mypage.do", totalCnt, listSize, pageSize, cp);
		mav.addObject("pageStr", pageStr);

		MemberDTO member = mypageDao.getInfo(id);
		System.out.println("memberIng : " + member.getProfile_img());
		mav.addObject("member", member);
		mav.setViewName("mypage/mypage");
		return mav;
	}

	// 회원탈퇴 yes/no
	@RequestMapping(value = "memberoutForm.do", method = RequestMethod.GET)
	public ModelAndView memberoutForm() {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("mypage/memberoutForm");
		return mav;
	}

	// 회원 탈퇴
	@RequestMapping(value = "memberOut.do", method = RequestMethod.GET)
	public ModelAndView memberOut(@RequestParam(value = "id") String id, HttpServletRequest req) {

		int count = mypageDao.memberOut(id);
		String msg, gopage;
		msg = count > 0 ? "정상적으로 탈퇴 되었습니다. 이용해주셔서 감사합니다." : "탈퇴 오류";
		gopage = count > 0 ? "index.do" : "memberoutForm.do";

		HttpSession session = req.getSession();
		session.invalidate();

		ModelAndView mav = new ModelAndView();
		mav.setViewName("mypage/memberMsg");
		mav.addObject("msg", msg);
		mav.addObject("gopage", gopage);
		return mav;
	}

	// 회원 정보 수정
	@RequestMapping(value = "memberEdit.do", method = RequestMethod.POST)
	public ModelAndView memberEdit(@RequestParam(value = "ppwd") String ppwd,
			@RequestParam(value = "npwd") String npwd, @RequestParam(value = "npwd2") String npwd2,
			HttpServletRequest request, HttpSession session) throws Exception{
		String fileName="";
		 String id = (String) session.getAttribute("userid");
		 System.out.println("사진저장 : "+id); // 사용자 아이디 
	      String resultMsg ="";
	      MultipartHttpServletRequest mr;
	      String imgName="";
	         mr = (MultipartHttpServletRequest) request;
			 System.out.println("mr="+mr.getFile("file"));
	         MultipartFile imgfile = mr.getFile("file");
	         System.out.println("imgfile="+imgfile.getOriginalFilename());
	         if(!imgfile.getOriginalFilename().equals("")) {
			 System.out.println("imgfile="+imgfile);
			 System.out.println("test1"+imgfile.getOriginalFilename());
	         imgName = imgfile.getOriginalFilename().trim();   // 사진 이름 저장
	         String filepath = "C:\\eclipse\\finaltp\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\finaltp" + "/UPF/";   // 사진을 넣어줄 폴더 경로 + 사용자 아이디는 아직 안 넣음

	         // 파일 확장자 체크
	         int pathPoing = imgName.lastIndexOf(".");
	         String filePoint = imgName.substring(pathPoing + 1, imgName.length());
	         String fileType = filePoint.toLowerCase();

			 System.out.println("test3");
	         if (!fileType.equals("jpg") && !fileType.equals("png") && !fileType.equals("jpeg")) {
	            resultMsg = "이미지 파일만 업로드 가능합니다.";
	         }
	         

			 System.out.println("test4");
	         File f = new File(filepath+id); // filepath에 해당 아이디의 폴더 존재 확인 
	         System.out.println(f.exists());
	         if (!f.exists()) {
	            System.out.println("폴더없음");
	            f.mkdirs(); // 폴더 생성
	         }
			 System.out.println("test");
	         // 폴더가 존재한다면 폴더 내의 파일들 없애는 구문 추가 > 폴더 비움
	         String finalImg = filepath+id+File.separator+imgName;   // filepath+사용자 아이디(폴더명)+/+사진 이름 > 총경로
	         // 디비에 업데이트 문 사용해서 프로필 이미지 경로 finalImg 로 수정해주기.
	         System.out.println("/finaltp/UPF/"+id+File.separator+imgName);
	         System.out.println(finalImg+"---------");
	         imgfile.transferTo(new File(finalImg));   // 사진 파일 폴더에 추가
	         resultMsg = "정상적으로 업로드 하였습니다.";
	         System.out.println(resultMsg);

	 		fileName=finalImg;
	         }
		String msg, gopage;
		System.out.println(fileName);
		System.out.println("asdasdasdasdasdas");
		int result = mypageDao.memberEdit(id, ppwd, npwd, npwd2,"/finaltp/UPF/"+id+File.separator+imgName);
		
		if (result == memberDao.NOT_PWD) {
			msg = "현재 비밀번호를 입력해주세요";
		} else if (result == memberDao.DISCORD) {
			msg = "현재 비밀번호가 일치하지 않습니다.";
		} else if (result == memberDao.PN_CONCORD) {
			msg = "현재 비밀번호와 새 비밀번호가 일치합니다. 다르게 변경 해주세요";
		} else if (result == memberDao.NN_DISCORD) {
			msg = "새 비밀번호와 새 비밀번호 확인이 일치하지 않습니다. 다시 확인 해주세요.";
		} else if (result == memberDao.EDIT_OK) {
			msg = "회원 정보가 정상적으로 변경되었습니다.";
		} else
		{
			msg = "알 수 없는 오류가 발생했습니다.";
		}

		gopage = "mypage.do?id=" + id;

		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", msg);
		mav.addObject("gopage", gopage);
		mav.setViewName("mypage/memberMsg");
		return mav;

	}

	// 리뷰 게시글 목록
	@RequestMapping(value = "myWriting.do")
	public ModelAndView mypageReviewList(HttpSession session, @RequestParam(value = "cp", defaultValue = "1") int cp,
			@RequestParam(value = "w_idx") int w_idx) {
		System.out.println("test");
		int totalCnt = mainBbsDao.getTotalCnt("review");
		int listSize = 5;
		int pageSize = 5;

		String userid = (String) session.getAttribute("userid");
		MemberDTO loginUser = mainBbsDao.getLoginUserInfo(userid); // 멤버 테이블에서 현재 접속중인 사용자의 정보를 가져옴
		ModelAndView mav = new ModelAndView();
		List<MainBbsDTO> mainList = mypageDao.myReviewMainBbsList(cp, listSize, "review", w_idx); // total_bbs에서 acc
																									// 게시물 가져옴
		System.out.println("size : " + mainList.size());
		List<ReviewDTO> reviewList = mypageDao.myReviewList(cp, listSize, mainList); // acc테이블에서 acc 게시물 가져옴
		System.out.println("mainList" + mainList);
		for (int i = 0; i < mainList.size(); i++) {
			mainList.get(i).setContent(mainList.get(i).getContent().replaceAll("\n", "<br>"));
			mainList.get(i).setReviewdto(reviewList.get(i));
			mainList.get(i).setUserid(mainBbsDao.getUserId(mainList.get(i).getWriter_idx()));
			mainList.get(i).setProfileImg(mainBbsDao.getWriterProfileImg(mainList.get(i).getWriter_idx()));
		}
		mav.addObject("mainList", mainList);
		// mav.addObject("reviewList", reviewList);
		mav.addObject("loginUser", loginUser);
		String pageStr = finaltp.paging.PageModule.makePage("myWriting.do", totalCnt, listSize, pageSize, cp);
		mav.addObject("pageStr", pageStr);
		mav.setViewName("mypage/myWriting");
		return mav;
	}

	// 동행 게시글 목록
	@RequestMapping(value = "myAccWriting.do")
	public ModelAndView mypageAccList(HttpSession session, @RequestParam(value = "cp", defaultValue = "1") int cp,
			@RequestParam(value = "w_idx") int w_idx) {
		System.out.println("test");
		int totalCnt = mainBbsDao.getTotalCnt("review");
		int listSize = 5;
		int pageSize = 5;

		String userid = (String) session.getAttribute("userid");
		MemberDTO loginUser = mainBbsDao.getLoginUserInfo(userid); // 멤버 테이블에서 현재 접속중인 사용자의 정보를 가져옴
		ModelAndView mav = new ModelAndView();
		List<MainBbsDTO> mainList = mypageDao.myAccMainBbsList(cp, listSize, "acc", w_idx); // total_bbs에서 acc
																								// 게시물 가져옴
		System.out.println("size : " + mainList.size());
		List<AccDTO> accList = mypageDao.myAccList(cp, listSize, mainList);// acc테이블에서 acc 게시물 가져옴
		System.out.println("mainList" + mainList);
		for (int i = 0; i < mainList.size(); i++) {
			mainList.get(i).setContent(mainList.get(i).getContent().replaceAll("\n", "<br>"));
			mainList.get(i).setAccdto(accList.get(i));
			mainList.get(i).setUserid(mainBbsDao.getUserId(mainList.get(i).getWriter_idx()));
			mainList.get(i).setProfileImg(mainBbsDao.getWriterProfileImg(mainList.get(i).getWriter_idx()));
		}
		mav.addObject("mainList", mainList);
		// mav.addObject("reviewList", reviewList);
		mav.addObject("loginUser", loginUser);
		String pageStr = finaltp.paging.PageModule.makePage("myAccWriting.do", totalCnt, listSize, pageSize, cp);
		mav.addObject("pageStr", pageStr);
		mav.setViewName("mypage/myAccWriting");
		return mav;
	}
	
	//팔로잉
	@RequestMapping(value="myFollowing.do")
	public ModelAndView myFollowing() {
	
		ModelAndView mav=new ModelAndView();
		mav.setViewName("mypage/myFollowing");
		return mav;
	}
	//팔로워
	@RequestMapping(value="myFollower.do")
	public ModelAndView myFollower() {
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("mypage/myFollower");
		return mav;
	}

}
