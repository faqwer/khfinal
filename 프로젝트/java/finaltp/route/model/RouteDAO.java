package finaltp.route.model;

import java.util.List;

import finaltp.mainBbs.model.MainBbsDTO;

public interface RouteDAO {
	public List<RouteDTO> routeList(List<MainBbsDTO> mainList);
	public int routeWrite(MainBbsDTO mainDto, RouteDTO routeDto);
	public MainBbsDTO routeMainContent(int bbs_idx);
	public RouteDTO routeContent(int bbs_idx);
	public int routeRevise(int bbs_idx, String thema, String coverimg);
}
