package finaltp.acc.model;

import java.util.ArrayList;
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
		int result = sqlMap.insert("mainBbsWrite", dto);
		Map map = new HashMap();
		map.put("writer_idx", dto.getWriter_idx());
		map.put("nation", nation);
		int result2 = sqlMap.insert("accWrite", map);
		return result * result2;
	}

	// 동행 게시판 목록
	public List<AccDTO> accList(int cp, int ls, List<MainBbsDTO> mainList) {
		int startnum = (cp - 1) * ls + 1;
		int endnum = cp * ls;
		Map<String, Integer> data = new HashMap<String, Integer>();
		List<AccDTO> dto = new ArrayList<AccDTO>();
		for(int i = 0;i<mainList.size();i++) {
			data.put("startnum", startnum);
			data.put("endnum", endnum);
			data.put("bbs_idx", mainList.get(i).getBbs_idx());
			data.put("writer_idx", mainList.get(i).getWriter_idx());
			AccDTO acc = sqlMap.selectOne("accList", data);
			dto.add(acc);
		}
		return dto;
	}
	
	// 동행 게시글 상태 변경
	public int accStatusChange(int bbs_idx) {
		int result = sqlMap.delete("accStatusChange", bbs_idx);
		return result;
	}
	
	// 동행 게시글 수정
	public int accRevise(int bbs_idx, String content) {
		Map map = new HashMap();
		map.put("bbs_idx", bbs_idx);
		map.put("content", content);
		int result = sqlMap.update("accRevise", map);
		return result;
	}
}
