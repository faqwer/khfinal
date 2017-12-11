package finaltp.review.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import finaltp.acc.model.AccDTO;
import finaltp.mainBbs.model.MainBbsDTO;

public class ReviewDAOImple implements ReviewDAO {

	private SqlSessionTemplate sqlMap;

	public ReviewDAOImple(SqlSessionTemplate sqlMap) {
		super();
		this.sqlMap = sqlMap;
	}

	// 후기 게시판 글 작성
	public int reviewWrite(MainBbsDTO mainDto, ReviewDTO reviewDto) {
		int mainBbsResult = sqlMap.insert("mainBbsWrite", mainDto);
		int reviewResult = sqlMap.insert("reviewWrite", reviewDto);
		return mainBbsResult * reviewResult;
	}
	
	public List<ReviewDTO> reviewList(int cp, int ls, List<MainBbsDTO> mainList) {
		int startnum = (cp - 1) * ls + 1;
		int endnum = cp * ls;
		Map<String, Integer> data = new HashMap<String, Integer>();
		List<ReviewDTO> dto = new ArrayList<ReviewDTO>();
		for(int i = 0;i<mainList.size();i++) {
			data.put("startnum", startnum);
			data.put("endnum", endnum);
			data.put("bbs_idx", mainList.get(i).getBbs_idx());
			data.put("writer_idx", mainList.get(i).getWriter_idx());
			ReviewDTO acc = sqlMap.selectOne("reviewList", data);
			dto.add(acc);
		}
		return dto;
	}
	
	public ReviewDTO reviewContent(int bbs_idx) {
		ReviewDTO dto = sqlMap.selectOne("reviewContent", bbs_idx);
		return dto;
	}

}
