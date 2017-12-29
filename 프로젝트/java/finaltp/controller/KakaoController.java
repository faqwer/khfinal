package finaltp.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import finaltp.member.model.MemberDAO;
import finaltp.member.model.MemberDTO;

@Controller
public class KakaoController {


	@Autowired
	private MemberDAO memberDao;
	
	private MemberDTO dto;
	
	
	// 로그인 버튼
	@RequestMapping(value = "kakao.do", method = RequestMethod.GET)
	public ModelAndView ka() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("k_login/login");

		return mav;
	}

	// 아이디 존재 여부 확인
	@RequestMapping(value = "result.do")
	public ModelAndView kakao(
			HttpSession session,
			@RequestParam(value = "PROFILE_IMG") String pimg,
			@ModelAttribute("join") MemberDTO dto) {
		/*
		 * 이메일 가지고 가입된 이메일이 있는지 확인 있으면 그대로 로그인 진행 세션에 값 저장 없으면 디비에 회원 정보 (아이디-이메일,
		 * 비밀번효-null, 이름, 사진) 저장
		 */
		String pwd = "";
		dto.getProfile_img();
		dto.setProfile_img(pimg);
		dto.setPwd(pwd);
		System.out.println(dto.getProfile_img());
		
		int result = memberDao.idCheck(dto.getId());
		// result 가 0이면 아이디 존재하지 않음. 디비에 회원정보 저장 후 로그인. 
		// result 가 1이면 아이디 존재. 바로 로그인.
		System.out.println("아이디 존재여부 : "+result);

		ModelAndView mav = new ModelAndView();
		String gopage="";
		
		
		if(result == 0) {
			int res_join = memberDao.join(dto);
			System.out.println("가입결과"+res_join+"/1 가입 : 0 실패?");

			mav.addObject("id", dto.getId());
			mav.addObject("name", dto.getName());
			mav.addObject("pimg", dto.getProfile_img());
			
			// 고페이지, addObj 나중에 지우기. 
		}
		

		// idx 받아오는 부분 추가 
		int idx = memberDao.getIdx(dto.getId());
		session.setAttribute("useridx", idx);
		// 제대로 실행되는지 확인하기
		
		
		
		String msg = dto.getName() + "님 환영합니다.";
		
		session.setAttribute("userid", dto.getId());
		session.setAttribute("name", dto.getName());
		gopage = "index.do";
		
		// 세션 저장 및 로그인 부분 추가 
		// gopage="index"; > 로그인 후 세션 들고 가는 고 페이지. 

		mav.addObject("msg", msg);
		mav.addObject("gopage", gopage);
		mav.setViewName("member/login");


		return mav;
	}

}
