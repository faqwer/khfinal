package finaltp.recommend.model;

import org.mybatis.spring.SqlSessionTemplate;

public class RecommendDAOImple implements RecommendDAO {

	private SqlSessionTemplate sqlMap;

	public RecommendDAOImple(SqlSessionTemplate sqlMap) {
		super();
		this.sqlMap = sqlMap;
	}

	public int recommendUp(RecommendDTO dto) {
		int result = sqlMap.insert("recommendUp", dto);
		return result;
	}

	public int recommendDown(RecommendDTO dto) {
		int result = sqlMap.delete("recommendDown", dto);
		return result;
	}

	public String recommendCheck(RecommendDTO dto) {
		int result = sqlMap.selectOne("recommendCheck", dto);
		if (result > 0) {
			return "NO";
		} else {
			return "OK";
		}
	}
	
	public int recommendNum(int bbs_idx) {
		int result = sqlMap.selectOne("recommendNum", bbs_idx);
		return result;
	}
}
