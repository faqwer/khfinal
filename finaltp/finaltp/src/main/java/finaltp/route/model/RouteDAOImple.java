package finaltp.route.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import finaltp.mainBbs.model.MainBbsDTO;

public class RouteDAOImple implements RouteDAO {

	private SqlSessionTemplate sqlMap;

	public RouteDAOImple(SqlSessionTemplate sqlMap) {
		super();
		this.sqlMap = sqlMap;
	}

	// 경로추천 리스트
	public List<RouteDTO> routeList(List<MainBbsDTO> mainList) {
		Map<String, Integer> data = new HashMap<String, Integer>();
		List<RouteDTO> dto = new ArrayList<RouteDTO>();
		System.out.println("mainList : " + mainList);
		for (int i = 0; i < mainList.size(); i++) {
			data.put("bbs_idx", mainList.get(i).getBbs_idx());
			data.put("writer_idx", mainList.get(i).getWriter_idx());
			RouteDTO route = sqlMap.selectOne("routeList", data);
			dto.add(route);
		}
		return dto;
	}

	// 경로추천 작성
	public int routeWrite(MainBbsDTO mainDto, RouteDTO routeDto) {
		System.out.println("writer_idx : " + routeDto.getWriter_idx());
		System.out.println("thema : " + routeDto.getThema());
		System.out.println("coverimg : " + routeDto.getCoverimg());
		int mainBbsResult = sqlMap.insert("mainBbsWrite", mainDto);
		int routeResult = sqlMap.insert("routeWrite", routeDto);
		return mainBbsResult * routeResult;
	}
	
	// 경로추천 메인 테이블 본문
	public MainBbsDTO routeMainContent(int bbs_idx) {
		MainBbsDTO mainList = null;
		try {
			mainList = sqlMap.selectOne("bbsContent", bbs_idx);
			return mainList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// 경로추천 경로 테이블 본문
	public RouteDTO routeContent(int bbs_idx) {
		RouteDTO routeList = null;
		try {
			routeList = sqlMap.selectOne("routeContent", bbs_idx);
			return routeList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int routeRevise(int bbs_idx, String thema, String coverimg) {
		Map data = new HashMap();
		data.put("bbs_idx", bbs_idx);
		data.put("thema", thema);
		data.put("coverimg", coverimg);
		int result = sqlMap.update("routeRevise", data);
		return result;
	}
}
