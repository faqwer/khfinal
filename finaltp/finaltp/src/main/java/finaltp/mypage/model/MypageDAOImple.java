package finaltp.mypage.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import finaltp.acc.model.AccDTO;
import finaltp.mainBbs.model.MainBbsDTO;
import finaltp.member.model.MemberDTO;
import finaltp.plan.model.PlanDTO;
import finaltp.review.model.ReviewDTO;

public class MypageDAOImple implements MypageDAO {

	private SqlSessionTemplate sqlMap;
		
	public MypageDAOImple(SqlSessionTemplate sqlMap) {
		super();
		this.sqlMap = sqlMap;
	}


	//mypage 정보 가져오기
	public MemberDTO getInfo(String id){
		MemberDTO list = sqlMap.selectOne("memberINFO", id);
		return list;
	}
	
	//회원 탈퇴
	public int memberOut(String id) {
		int count = sqlMap.delete("memberOut", id);
		return count;
	}
	
	//회원 정보 수정
	public int memberEdit(String id,String ppwd,String npwd,String npwd2, String fileName) {
		String user_pwd = sqlMap.selectOne("memberPWD",id);
		String user_img=sqlMap.selectOne("memberImgCheck", id);
		
		if(!fileName.equals("")) {
			if(!fileName.equals(user_img)) {
				HashMap map=new HashMap();
				map.put("id", id);
				map.put("fileName",fileName);
				int result=sqlMap.update("memberImgEdit", map);
			}
		}
		if(ppwd==null||ppwd=="") {
			return NOT_PWD;	
		}else {
			if(user_pwd.equals(ppwd)) {
				if(ppwd.equals(npwd)) {
					return PN_CONCORD;
				}else {
					System.out.println("npwd="+npwd+"asdasd");
					if(npwd.equals(npwd2)) {
						if(!npwd.equals("")) {
							System.out.println("npwd=notnull");
							HashMap map=new HashMap();
							map.put("id", id);
							map.put("npwd",npwd);
							int result=sqlMap.update("memberPwdEdit",map);
							return EDIT_OK;
						}else {
							System.out.println("npwd=null");
							return EDIT_OK;
						}
					}else {
						return NN_DISCORD;
					}
				}
			}else {
				return DISCORD;
			}
		}
	}
	
	public int getMemberIdx(String userid) {
		System.out.println("id = " + userid);
		int w_idx=sqlMap.selectOne("memberIdx", userid);
		System.out.println("w_idx : " + w_idx);
		return w_idx;
	}
	
	//마이페이지 후기  리스트
	public List<MainBbsDTO> myReviewMainBbsList(int cp, int ls, String category, int w_idx) {
		int startnum = (cp - 1) * ls + 1;
		int endnum = cp * ls;
		
		Map data=new HashMap();
		data.put("startnum", startnum);
		data.put("endnum", endnum);
		data.put("category", category);
		data.put("writer_idx", w_idx);
		System.out.println("writer_idx = " + w_idx);
		List<MainBbsDTO> dto=sqlMap.selectList("myMainBbsList", data);
		System.out.println("size : " + dto.size());
		
		return dto;
	}
	
	//마이페이지 동행  메인 리스트
	public List<MainBbsDTO> myAccMainBbsList(int cp, int ls, String category, int w_idx) {
		int startnum = (cp - 1) * ls + 1;
		int endnum = cp * ls;
		
		Map data=new HashMap();
		data.put("startnum", startnum);
		data.put("endnum", endnum);
		data.put("category", category);
		data.put("writer_idx", w_idx);
		System.out.println("writer_idx = " + w_idx);
		List<MainBbsDTO> dto=sqlMap.selectList("myMainBbsList", data);
		System.out.println("size : " + dto.size());
		
		return dto;
	}

	//마이페이지 플래너 리스트
	public List<PlanDTO> myPlanList(int cp, int ls, int w_idx) {
		int startnum = (cp - 1) * ls + 1;
		int endnum = cp * ls;

		Map data = new HashMap();
		data.put("startnum", startnum);
		data.put("endnum", endnum);
		data.put("writer_idx", w_idx);
		System.out.println("writer_idx test = " + w_idx);
		List<PlanDTO> dto = sqlMap.selectList("myPlanner", data);

		return dto;
	}

	// 플래너 총 개시물 수 구하기
	public int planTotalCnt() {
		int count = sqlMap.selectOne("planTotalCnt");
		return count == 0 ? 1 : count;
	}

	//마이페이지 동행 리스트
	public List<AccDTO> myAccList(int cp, int ls, List<MainBbsDTO> mainList) {
		int startnum = (cp - 1) * ls + 1;
		int endnum = cp * ls;
		Map<String, Integer> data = new HashMap<String, Integer>();
		List<AccDTO> dto= new ArrayList<AccDTO>();
		for(int i=0;i<mainList.size();i++) {
			data.put("startnum", startnum);
			data.put("endnum", endnum);
			data.put("bbs_idx", mainList.get(i).getBbs_idx());
			data.put("writer_idx", mainList.get(i).getWriter_idx());
			AccDTO acc=sqlMap.selectOne("accList", data);
			dto.add(acc);
		}
		return dto;
	}
	
	//마이페이지 여행기 리스트
	public List<ReviewDTO> myReviewList(int cp, int ls, List<MainBbsDTO> mainList) {
		int startnum = (cp - 1) * ls + 1;
		int endnum = cp * ls;
		Map<String, Integer> data = new HashMap<String, Integer>();
		List<ReviewDTO> dto= new ArrayList<ReviewDTO>();
		for(int i=0;i<mainList.size();i++) {
			data.put("startnum", startnum);
			data.put("endnum", endnum);
			data.put("bbs_idx", mainList.get(i).getBbs_idx());
			data.put("writer_idx", mainList.get(i).getWriter_idx());
			ReviewDTO acc=sqlMap.selectOne("reviewList", data);
			dto.add(acc);
		}
		return dto;
	}
}
