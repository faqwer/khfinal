package finaltp.plan.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

public class PlanDAOImple implements PlanDAO {

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
}
