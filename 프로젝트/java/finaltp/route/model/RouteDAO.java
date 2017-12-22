package finaltp.route.model;

import java.util.List;

import finaltp.mainBbs.model.MainBbsDTO;


public interface RouteDAO {

	public List<RouteDTO> routeList(int cp, int ls, List<MainBbsDTO> mainList); // 루트 게시글 목록
	public RouteDTO routeContent(int bbs_idx);//루트 본문 보기

}
