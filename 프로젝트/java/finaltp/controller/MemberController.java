package finaltp.controller;

import java.sql.Date;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import finaltp.mail.MailService;
import finaltp.member.model.MemberDAO;
import finaltp.member.model.MemberDTO;

@Controller
public class MemberController {

	@Autowired
	private MailService mailService;

	@Autowired
	private MemberDAO memberDao;

	// 아이디(이메일) 체크 폼
	@RequestMapping(value = "idCheck.do", method = RequestMethod.GET)
	public ModelAndView idCheck() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/idCheck");

		return mav;
	}

	// 아이디(이메일)중복 확인 > 존재하는 아이디일때 이동 창 변경하기
	@RequestMapping(value = "idCheck.do", method = RequestMethod.POST)
	public ModelAndView idCkSubmit(HttpSession session, MemberDTO dto) {

		int result = memberDao.idCheck(dto.getId());

		if (result == 0) {
			sendMail(session, dto.getId());
		}
		String msg = result == 0 ? "사용 가능한 아이디입니다." : "이미 존재하는 아이디입니다.";
		String id = result == 0 ? dto.getId() : null;
		String gopage = result == 0 ? "member/joincode" : "member/msg";

		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", msg);
		mav.addObject("id", id);
		if (result == 0)
			session.setAttribute("id", id);
		mav.setViewName(gopage);

		System.out.println(msg + "," + id);

		return mav;
	}

	// 인증 메일 보내기
	@ResponseBody
	private boolean sendMail(HttpSession session, String email) {
		int randomCode = new Random().nextInt(100000) + 10000;
		String joinCode = String.valueOf(randomCode);
		session.setAttribute("joinCode", joinCode);

		String subject = "회원가입 승인 번호 입니다.";
		StringBuilder sb = new StringBuilder();
		sb.append("회원가입 승인번호는 ").append(joinCode).append(" 입니다.");

		return mailService.send(subject, sb.toString(), "20140313s@gmail.com", email);
	}

	// 이메일 인증 코드 확인
	@RequestMapping(value = "joincode.do", method = RequestMethod.POST)
	public ModelAndView joincode(@RequestParam(value = "joincode") int jc, @RequestParam(value = "ok") int ok) {
		String msg = (jc == ok) ? "이메일 인증 완료." : "인증 번호를 확인하세요.";
		String gopage = (jc == ok) ? "member/joinForm" : "member/joincode";

		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", msg);
		mav.setViewName(gopage);
		return mav;
	}

	// 회원가입 폼 >> 이메일 인증 확인 에서 바로 이동하므로 현재 사용 안함
	/*
	 * @RequestMapping(value = "join.do", method = RequestMethod.GET) public
	 * ModelAndView joinForm() { ModelAndView mav = new ModelAndView();
	 * mav.setViewName("member/joinForm");
	 * 
	 * return mav; }
	 */

	// 회원가입 실행
	@RequestMapping(value = "join.do", method = RequestMethod.POST)
	public ModelAndView joinSubmit(@ModelAttribute("join") MemberDTO dto) {

		String pimg = "/img/base.png"; // 기본 회원가입자는 프로필 이미지가 없기에 ""로 지정
		dto.setProfile_img(pimg);
		int result = memberDao.join(dto);
		String msg = result > 0 ? "가입성공" : "가입실패";

		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", msg);
		mav.setViewName("member/join");

		return mav;
	}

	// 로그인 폼
	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public ModelAndView loginForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/loginForm");

		return mav;
	}

	// 로그인 실행
	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public ModelAndView loginSubmit(HttpServletResponse resp, HttpSession session,
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "pwd", defaultValue = "") String pwd,
			@RequestParam(value = "saveid", defaultValue = "") String saveck) {

		int result = 0;
		String msg = "";
		String gopage = "";

		ModelAndView mav = new ModelAndView();

		if (id == "" || id == null || pwd == "" || pwd == null) {

			msg = "아이디 혹은 비밀번호를 입력해주세요.";
			gopage = "login.do";
			mav.addObject("msg", msg);
			mav.addObject("gopage", gopage);

		} else {

			result = memberDao.login(id, pwd);

			if (result == memberDao.LOGIN_OK) {

				int idx = memberDao.getIdx(id);
				String name = memberDao.getName(id);

				msg = name + "님 환영합니다.";
				session.setAttribute("name", name);
				session.setAttribute("userid", id);
				session.setAttribute("useridx", idx);
				gopage = "index.do";

				if (saveck == null || saveck.equals("")) {
					Cookie ck = new Cookie("saveid", id);
					ck.setMaxAge(0);
					resp.addCookie(ck);
				} else {
					Cookie ck = new Cookie("saveid", id);
					ck.setMaxAge(60 * 5); // 5분
					resp.addCookie(ck);
				}

			} else if (result == memberDao.NOT_ID || result == memberDao.NOT_PWD) {
				msg = "아이디 혹은 비밀번호를 확인해주세요.";
				gopage = "login.do";
			} else {
				msg = "오류.";
				gopage = "index.do";
			}

			mav.addObject("msg", msg);
			mav.addObject("gopage", gopage);

		}

		mav.setViewName("member/login");
		return mav;
	}

	// 로그아웃
	@RequestMapping("logout.do")
	public ModelAndView logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();

		ModelAndView mav = new ModelAndView();
		// mav.setViewName("member/logout");
		mav.setViewName("redirect:/index.do"); // 메시지 페이지를 거칠 필요 없음

		return mav;
	}

	// 비밀번호 찾기
	@RequestMapping("findPwd.do")
	public ModelAndView find() {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/findPwd");
		return mav;
	}

	// 비밀번호 찾기
	@RequestMapping(value = "findPwd.do", method = RequestMethod.POST)
	public ModelAndView findPwd(HttpSession session, @RequestParam("email") String email,
			@RequestParam("name") String name) {

		ModelAndView mav = new ModelAndView();

		String uname = memberDao.getName(email); // 이메일로 이름을 검색

		if (uname == null || uname == "") {

		} else {
			// 검색된 이름이 입력한 이름과 같다면 0 틀리면 1
			int result = uname.equals(name) ? 0 : 1;

			if (result == 0) {

				StringBuffer buffer = new StringBuffer();
				Random random = new Random();

				String chars[] = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,0,1,2,3,4,5,6,7,8,9"
						.split(",");

				for (int i = 0; i < 8; i++) {

					buffer.append(chars[random.nextInt(chars.length)]);

				}

				String new_pwd = buffer.toString();
				System.out.println(new_pwd);

				// 임시 비밀번호 db에 저장

				int upPwd = memberDao.updatePwd(email, new_pwd);

				String msg="";
				if(upPwd==1) {
					
				sendPwd(session, email, new_pwd);
				msg = "임시 비밀번호가 " + email + "로 전송되었습니다.";
				// 임시 비밀번호가 입력한 이메일로 전송되었습니다. [확인] 있는 페이지(findmsg)로 이동.
				}else {
				msg= "Error. 고객센터에 문의 바랍니다.";	
				}
				mav.addObject("msg", msg);
				
			} else if (result == 1) {
				// 일치하는 계정이 존재하지 않습니다.

				String msg = "일치하는 계정을 찾을 수 없습니다.";
				mav.addObject("msg", msg);
			}
		}

		mav.setViewName("member/findmsg");

		return mav;
	}

	// 인증 메일 보내기
	@ResponseBody
	private boolean sendPwd(HttpSession session, String email, String new_pwd) {

		String subject = "임시 비밀번호입니다.";
		StringBuilder sb = new StringBuilder();
		sb.append("임시 비밀번호는 ").append(new_pwd).append(" 입니다.");

		return mailService.send(subject, sb.toString(), "TP", email);
	}
	
	// -------------- ^ 로그인, 회원가입

}
