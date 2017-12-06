package finaltp.route.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

public class RouteDAOImple implements RouteDAO {

	private SqlSessionTemplate sqlMap;
	
	public RouteDAOImple(SqlSessionTemplate sqlMap) {
		super();
		this.sqlMap = sqlMap;
	}

	public List<RouteDTO> routeList(int bbs_idx) {
		
		List<RouteDTO> list=sqlMap.selectList("bbs_idx",bbs_idx);
		return list;
	}
}
