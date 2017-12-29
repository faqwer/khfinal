package finaltp.mypage.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import finaltp.acc.model.AccDTO;
import finaltp.follow.model.FollowDAO;
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

	public ArrayList<PlanDTO> getMybookmark(int cp, int ls, int user_idx){
		int startnum = (cp - 1) * ls + 1;
		int endnum = cp * ls;
		List<Integer> planner_idxs=sqlMap.selectList("precommendUserNum", user_idx);
		ArrayList<PlanDTO> list=new ArrayList<PlanDTO>();
		for(int i=0;i<planner_idxs.size();i++) {
			PlanDTO pdto=sqlMap.selectOne("planContent",planner_idxs.get(i).intValue());
			list.add(pdto);
		}
		return list;
	}
	//mypage 정보 가져오기
	public MemberDTO getInfo(int user_idx){
		MemberDTO list = sqlMap.selectOne("memberINFOwithIdx", user_idx);
		return list;
	}
	
	//회원 탈퇴
	public int memberOut(int member_idx) {
		int count = sqlMap.delete("memberOut", member_idx);
		return count;
	}
	
	//회원 정보 수정
	public int memberEdit(String id,String ppwd,String npwd,String npwd2, String fileName) {
		String user_pwd = sqlMap.selectOne("memberPWD",id);
		String user_img=sqlMap.selectOne("memberImgCheck", id);
		if(ppwd==null||ppwd=="") {
			return NOT_PWD;	
		}else {
			if(user_pwd.equals(ppwd)) {
				if(!fileName.equals("/finaltp/UPF/"+id+"\\")) {
					if(!fileName.equals(user_img)) {
						HashMap map=new HashMap();
						map.put("id", id);
						map.put("fileName",fileName);
						int result=sqlMap.update("memberImgEdit", map);
					}
				}
				if(ppwd.equals(npwd)) {
					return PN_CONCORD;
				}else {
					if(npwd.equals(npwd2)) {
						if(!npwd.equals("")) {
							HashMap map=new HashMap();
							map.put("id", id);
							map.put("npwd",npwd);
							int result=sqlMap.update("memberPwdEdit",map);
							return EDIT_OK;
						}else {
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
	
	
	//마이페이지 후기  리스트
	public List<MainBbsDTO> myReviewMainBbsList(int cp, int ls, String category, int w_idx) {
		int startnum = (cp - 1) * ls + 1;
		int endnum = cp * ls;
		
		Map data=new HashMap();
		data.put("startnum", startnum);
		data.put("endnum", endnum);
		data.put("category", category);
		data.put("writer_idx", w_idx);
		List<MainBbsDTO> dto=sqlMap.selectList("myMainBbsList", data);
		
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
		List<MainBbsDTO> dto=sqlMap.selectList("myMainBbsList", data);
		
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
		List<PlanDTO> dto = sqlMap.selectList("myPlanner", data);

		return dto;
	}

	// 플래너 총 개시물 수 구하기
	public int planTotalCnt() {
		int count = sqlMap.selectOne("getPlanTotalCnt");
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
	
	//북마크 갯수 세기
	public int myBookmarkCnt(int user_idx) {
		int count=sqlMap.selectOne("myBookmarkCnt",user_idx);
		return count == 0 ? 1 : count;	
		}
	
	//내 플래너 갯수 세기
	public int myPlannerCnt(int user_idx) {
		int count=sqlMap.selectOne("myPlanCnt", user_idx);
		return count == 0 ? 1 : count;	
	}
}
