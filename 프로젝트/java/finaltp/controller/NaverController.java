package finaltp.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.scribejava.core.model.OAuth2AccessToken;

import finaltp.member.model.MemberDAO;
import finaltp.member.model.MemberDTO;
import finaltp.oauth.NaverLoginBO;

@Controller
public class NaverController {

	@Autowired
	private MemberDAO memberDao;
	private MemberDTO dto;
	
	/* NaverLoginBO */
	private NaverLoginBO naverLoginBO;

	/* NaverLoginBO */
	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO){
		this.naverLoginBO = naverLoginBO;
	}
	
    @RequestMapping("/naver.do")
    public ModelAndView login(HttpSession session) {
        /* 네아로 인증 URL을 생성하기 위하여 getAuthorizationUrl을 호출 */
        String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
        
        /* 생성한 인증 URL을 View로 전달 */
        return new ModelAndView("n_login/login", "url", naverAuthUrl);
    }
    
    @RequestMapping("/callback.do")
	public ModelAndView callback(@RequestParam String code, @RequestParam String state, HttpSession session) throws IOException {
		OAuth2AccessToken oauthToken = naverLoginBO.getAccessToken(session, code, state);
		String apiResult = naverLoginBO.getUserProfile(oauthToken);
		
		// apiResult << 에 든 유저 정보를 JSON 파싱을 이용해 조각조각내어 회원정보 조회, 회원가입, 로그인 기능 진행.
		String json = apiResult; 
		System.out.println(apiResult);
		JSONParser jsonparser = new JSONParser();
		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject) jsonparser.parse(json);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MemberDTO dto = new MemberDTO();
		
		JSONObject res = (JSONObject)jsonObject.get("response");
		
		String id = (String) res.get("email");
		String name = (String) res.get("name");
		String pimg = (String) res.get("profile_image");
		String pwd = "";
		
		dto.setId(id);
		dto.setPwd(pwd);
		dto.setName(name);
		dto.setProfile_img(pimg);
		
		int result = memberDao.idCheck(id);
		// result 가 0이면 아이디 존재하지 않음. 디비에 회원정보 저장 후 로그인. 
		// result 가 1이면 아이디 존재. 바로 로그인.
		System.out.println("아이디 존재여부 : "+result);

		ModelAndView mav = new ModelAndView();
		String gopage="";
		
		if(result == 0) {
			int res_join = memberDao.join(dto);
			System.out.println("가입결과"+res_join+"/1 가입 : 0 실패?");

			mav.addObject("id", id);
			mav.addObject("name", name);
			mav.addObject("pimg", pimg);
			
			// 고페이지, addObj 나중에 지우기. 
		}
		
		

		// idx 받아오는 부분 추가 
		int idx = memberDao.getIdx(id);
		session.setAttribute("useridx", idx);
		// 제대로 실행되는지 확인하기
		
		
		
		String msg = dto.getName() + "님 환영합니다.";
		// 세션 idx 추가
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
