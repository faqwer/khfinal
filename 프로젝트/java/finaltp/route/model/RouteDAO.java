package finaltp.route.model;

import java.util.List;

public interface RouteDAO {

	public List<RouteDTO> routeList(int cp, int ls); // ��Ʈ �Խñ� ���
	public RouteDTO routeContent(int idx, int cp,int ls);//��Ʈ ���� ����
}
