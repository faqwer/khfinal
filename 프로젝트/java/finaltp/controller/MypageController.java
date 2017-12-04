package finaltp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import finaltp.member.model.*;

@Controller
public class MypageController {
	
	@Autowired
	private MemberDAO memberDao;
	

	@RequestMapping(value="mypage.do",method=RequestMethod.GET)
	public ModelAndView goMypage(@RequestParam(value="id") String id) {
		
		List<MemberDTO> member = memberDao.getInfo(id);
		
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("mypage/mypage");
		mav.addObject("id",id);
		mav.addObject("member",member);
		return mav;
	}
	
	@RequestMapping(value="memberoutForm.do",method=RequestMethod.GET)
	public ModelAndView memberoutForm() {
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("mypage/memberoutForm");
		return mav;
	}
	
	@RequestMapping(value="memberOut.do", method=RequestMethod.GET)
	public ModelAndView memberOut(@RequestParam(value="id")String id,HttpServletRequest req) {
		
		int count=memberDao.memberOut(id);
		String msg,gopage;
		msg=count>0?"정상적으로 탈퇴 되었습니다. 이용해주셔서 감사합니다.":"탈퇴 오류";
		gopage=count>0?"index.do":"memberoutForm.do";
		
		HttpSession session = req.getSession();
		session.invalidate();
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("mypage/memberOut");
		mav.addObject("msg",msg);
		mav.addObject("gopage",gopage);
		return mav;
	}
	
}








