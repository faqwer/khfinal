package finaltp.controller;
 
import java.sql.Date;

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
import org.springframework.web.servlet.ModelAndView;

import finaltp.member.model.MemberDAO;
import finaltp.member.model.MemberDTO;

@Controller
public class MemberController {

	@Autowired
	private MemberDAO memberDao;

	// 겟 > 아무런 데이터 없이 join 페이지 이동시
	// 포스트 > join 기능 실행시

	@RequestMapping(value = "idCheck.do", method = RequestMethod.GET)
	public ModelAndView idCkForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/idCkForm");

		return mav;
	}

	@RequestMapping(value = "idCheck.do", method = RequestMethod.POST)
	public ModelAndView idCkSubmit(MemberDTO dto) {
		System.out.println(dto.getId());
		//int result = memberDao.idCheck(dto.getId());

		//String msg = result == 0 ? "사용 가능한 아이디입니다." : "이미 존재하는 아이디입니다.";
		//String id = result == 0 ? dto.getId() : null;

		ModelAndView mav = new ModelAndView();
		//mav.addObject("msg", msg);
		//mav.addObject("id", id);
		mav.setViewName("member/idCkMsg");

		return mav;
	}

	@RequestMapping(value = "join.do", method = RequestMethod.GET)
	public ModelAndView joinForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/joinForm");

		return mav;
	}

	@RequestMapping(value = "join.do", method = RequestMethod.POST)
	public ModelAndView joinSubmit(@ModelAttribute("join") MemberDTO dto) {

		int result = memberDao.join(dto);
		String msg = result > 0 ? "가입성공" : "가입실패";
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", msg);
		mav.setViewName("member/join");

		return mav;
	}

	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public ModelAndView loginForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/loginForm");

		return mav;
	}

	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public ModelAndView loginSubmit(HttpServletResponse resp, HttpSession session,
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "pwd", defaultValue = "") String pwd,
			@RequestParam(value = "saveid", defaultValue = "") String saveck) {

		System.out.println(id + "/" + pwd + "/" + saveck);

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
				String name = memberDao.getName(id);

				msg = name + "님 환영합니다.";
				session.setAttribute("name", name);
				session.setAttribute("userid", id);
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

	@RequestMapping("logout.do")
	public ModelAndView logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
		
		ModelAndView mav = new ModelAndView();
		//mav.setViewName("member/logout");
		mav.setViewName("redirect:/index.do");	// 메시지 페이지를 거칠 필요 없음
		
		return mav;
	}
}
