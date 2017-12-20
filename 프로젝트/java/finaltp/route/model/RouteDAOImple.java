package finaltp.route.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

public class RouteDAOImple implements RouteDAO {

	private SqlSessionTemplate sqlMap;
	
	public RouteDAOImple(SqlSessionTemplate sqlMap) {
		super();
		this.sqlMap = sqlMap;
	}
	public List<RouteDTO> routeList(int cp, int ls) {
		int startnum = (cp - 1) * ls + 1;
		int endnum = cp * ls;
		Map<String, Integer> data = new HashMap<String, Integer>();
		data.put("startnum", startnum);
		data.put("endnum", endnum);
		List<RouteDTO> dto = sqlMap.selectList("routeList2", data);
		return dto;
	}
	
	public RouteDTO routeContent(int idx, int cp, int ls) {
		int startnum = (cp - 1) * ls + 1;
		int endnum = cp * ls;
		Map<String, Integer> data = new HashMap<String, Integer>();
		data.put("startnum", startnum);
		data.put("endnum", endnum);
		data.put("idx", idx);
		RouteDTO dto = sqlMap.selectOne("routeContent", data);
		return dto;
	}
	
}
