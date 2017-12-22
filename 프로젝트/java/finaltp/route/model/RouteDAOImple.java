package finaltp.route.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import finaltp.acc.model.AccDTO;
import finaltp.mainBbs.model.MainBbsDTO;
import finaltp.review.model.ReviewDTO;


public class RouteDAOImple implements RouteDAO {

	private SqlSessionTemplate sqlMap;
	
	public RouteDAOImple(SqlSessionTemplate sqlMap) {
		super();
		this.sqlMap = sqlMap;
	}

	//루트 게시판 목록
	public List<RouteDTO> routeList(int cp, int ls, List<MainBbsDTO> mainList) {

		Map<String, Integer> data = new HashMap<String, Integer>();
		List<RouteDTO> dto = new ArrayList<RouteDTO>();
		for(int i=0;i<mainList.size();i++) {
			data.put("bbs_idx", mainList.get(i).getBbs_idx());
			data.put("writer_idx", mainList.get(i).getWriter_idx());
			RouteDTO route=sqlMap.selectOne("routeList", data);
			dto.add(route);
		}
		return dto;
	}
		
	//루트 게시판 본문 보기
	public RouteDTO routeContent(int bbs_idx) {

		RouteDTO dto = sqlMap.selectOne("routeContent", bbs_idx);
		return dto;
	}
	

}
