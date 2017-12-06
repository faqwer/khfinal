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
		mav.setViewName("mypage/memberMsg");
		mav.addObject("msg",msg);
		mav.addObject("gopage",gopage);
		return mav;
	}
	
	@RequestMapping(value="memberEdit.do",method=RequestMethod.GET)
	public ModelAndView memberEdit(@RequestParam(value="id")String id,@RequestParam(value="ppwd")String ppwd,@RequestParam(value="npwd")String npwd,@RequestParam(value="npwd2")String npwd2) {

		
		String msg,gopage;
		int result=memberDao.memberEdit(id, ppwd, npwd, npwd2);
		
		if(result==memberDao.NOT_PWD) {
			msg="현재 비밀번호를 입력해주세요";
		}else if(result==memberDao.DISCORD) {
			msg="현재 비밀번호가 일치하지 않습니다.";
		}else if(result==memberDao.PN_CONCORD) {
			msg="현재 비밀번호와 새 비밀번호가 일치합니다. 다르게 변경 해주세요";
		}else if(result==memberDao.NN_DISCORD) {
			msg="새 비밀번호와 새 비밀번호 확인이 일치하지 않습니다. 다시 확인 해주세요.";
		}else if(result==memberDao.EDIT_OK){
			msg="회원 정보가 정상적으로 변경되었습니다.";
		}else {
			msg="알 수 없는 오류가 발생했습니다.";
		}
		
		gopage="mypage.do?id="+id;
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("msg",msg);
		mav.addObject("gopage",gopage);
		mav.setViewName("mypage/memberMsg");
		return mav;
		
		
	}
	
}








