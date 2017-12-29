package finaltp.plan.model;

import java.util.List;

public interface PlanDAO {
<<<<<<< HEAD

=======
	public int planWrite(PlanDTO pdto);
	public int planUpdate(PlanDTO pdto);
	public int planDelete(int planner_idx);
	public PlanDTO getPlanContent(int planner_idx);
	public int getPlanRecommendCnt(int planner_idx);
	public int ckRecommend(int planner_idx,int user_idx);
	public int addRecommend(int planner_idx,int writer_idx,int user_idx);
	public int delRecommend(int planner_idx,int writer_idx,int user_idx);
	public String getUesrid(int member_idx);
	public List<PlanDTO> planList(int cp, int ls, String status); // �÷��� ����Ʈ
	
>>>>>>> 02b1868adc5fbcb3219d735b2ad072a50a787d45
}
