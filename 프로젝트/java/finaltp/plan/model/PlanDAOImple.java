package finaltp.plan.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

public class PlanDAOImple implements PlanDAO {

	private static int SUCESS=1;
	private static int ERROR=1;
	
	
	private SqlSessionTemplate sqlMap;

	public PlanDAOImple(SqlSessionTemplate sqlMap) {
		super();
		this.sqlMap = sqlMap;
	}
	public List<PlanDTO> planList(int cp, int ls, String status) {
		int startnum = (cp - 1) * ls + 1;
		int endnum = cp * ls;
		Map data = new HashMap();
		data.put("startnum", startnum);
		data.put("endnum", endnum);
		data.put("status", status);
		List<PlanDTO> dto = sqlMap.selectList("planList", data);
		return dto;
	}
	public int planDelete(int planner_idx) {
		int delete=sqlMap.update("plannerStatusDefer", planner_idx);
		return delete;
	}
	public int planUpdate(PlanDTO pdto) {
		int update;
		if(pdto.getCoverimg().equals("no")) {
			update=sqlMap.update("planUpdate", pdto);
		}else {
			update=sqlMap.update("planUpdateWithImg", pdto);
		}
		
		return update;
	}
	public int planWrite(PlanDTO pdto) {
		// TODO Auto-generated method stub
		int write=sqlMap.insert("planWrite", pdto);
		return write;
	}

	public PlanDTO getPlanContent(int planner_idx) {
		// TODO Auto-generated method stub
		PlanDTO pdto=sqlMap.selectOne("planContent", planner_idx);
		return pdto;
	}
	public String getUesrid(int member_idx) {
		String userid=sqlMap.selectOne("getUserId",member_idx);
		return userid;
	}
	public int getPlanRecommendCnt(int planner_idx) {
	      int result=sqlMap.selectOne("PlanRecommendCnt",planner_idx);
	      return result;
	   }

	public int ckRecommend(int planner_idx, int user_idx) {
		// TODO Auto-generated method stub
		HashMap map=new HashMap();
		map.put("planner_idx",planner_idx );
		map.put("user_idx",user_idx );
		int result=sqlMap.selectOne("ckRecommend",map);
		return result;
	}

	public int addRecommend(int planner_idx, int writer_idx, int user_idx) {
		// TODO Auto-generated method stub
		HashMap map=new HashMap();
		map.put("planner_idx",planner_idx );
		map.put("writer_idx",writer_idx );
		map.put("user_idx",user_idx );
		int result=sqlMap.insert("addRecommend",map);
		return result;
	}

	public int delRecommend(int planner_idx, int writer_idx, int user_idx) {
		// TODO Auto-generated method stub
		HashMap map=new HashMap();
		map.put("planner_idx",planner_idx );
		map.put("writer_idx",writer_idx );
		map.put("user_idx",user_idx );
		int result=sqlMap.delete("delRecommend",map);
		return result;
	}
	
}
