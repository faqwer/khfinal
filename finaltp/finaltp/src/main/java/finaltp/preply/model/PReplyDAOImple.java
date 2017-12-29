package finaltp.preply.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

public class PReplyDAOImple implements PReplyDAO {

	
	private SqlSessionTemplate sqlMap;

	public PReplyDAOImple(SqlSessionTemplate sqlMap) {
		super();
		this.sqlMap = sqlMap;
	}
	public List<PReplyDTO> getPReply(int planner_idx) {
		List<PReplyDTO> list=sqlMap.selectList("getPReply", planner_idx);
		return list;
	}
	public int addPReply(PReplyDTO prdto) {
		int result=sqlMap.insert("addPReply", prdto);
		return result;
	}
}
