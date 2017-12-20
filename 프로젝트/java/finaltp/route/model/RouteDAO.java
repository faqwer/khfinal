package finaltp.route.model;

import java.util.List;

public interface RouteDAO {

	public List<RouteDTO> routeList(int cp, int ls); // 루트 게시글 목록
	public RouteDTO routeContent(int idx, int cp,int ls);//루트 본문 보기
}
