package finaltp.plan.model;

import java.util.List;

public interface PlanDAO {
	public List<PlanDTO> planList(int cp, int ls, String status); // 플래너 리스트
}
