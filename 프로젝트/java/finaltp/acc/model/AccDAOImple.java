package finaltp.acc.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import finaltp.mainBbs.model.MainBbsDTO;

public class AccDAOImple implements AccDAO {

	private SqlSessionTemplate sqlMap;

	public AccDAOImple(SqlSessionTemplate sqlMap) {
		super();
		this.sqlMap = sqlMap;
	}

	// 동행 게시판 작성
	public int accWrite(MainBbsDTO dto, String nation) {
		int result = sqlMap.insert("accWrite1", dto);
		Map map = new HashMap();
		map.put("writer_idx", dto.getWriter_idx());
		map.put("nation", nation);
		int result2 = sqlMap.insert("accWrite2", map);
		return result * result2;
	}

	// 동행 게시판 목록
	public List<AccDTO> accList(int cp, int ls) {
		int startnum = (cp - 1) * ls + 1;
		int endnum = cp * ls;
		Map<String, Integer> data = new HashMap<String, Integer>();
		data.put("startnum", startnum);
		data.put("endnum", endnum);
		List<AccDTO> dto = sqlMap.selectList("accList2", data);
		return dto;
	}
	
	public int accStatusChange(int bbs_idx) {
		int result = sqlMap.delete("accStatusChange", bbs_idx);
		return result;
	}
}
