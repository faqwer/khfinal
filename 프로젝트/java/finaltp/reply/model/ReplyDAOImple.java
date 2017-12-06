package finaltp.reply.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

public class ReplyDAOImple implements ReplyDAO {

	private SqlSessionTemplate sqlMap;

	public ReplyDAOImple(SqlSessionTemplate sqlMap) {
		super();
		this.sqlMap = sqlMap;
	}

	public int getCommentCount(int bbs_idx) {
		int result = sqlMap.selectOne("getCommentCount", bbs_idx);
		return result;
	}

	// maxRef 가져오는 메서드
	public int getMaxRef(int bbs_idx) {
		int result = sqlMap.selectOne("getMaxRef", bbs_idx);
		return result;
	}

	// 댓글 작성 메서드
	public int commentWrite(ReplyDTO dto) {
		int result = sqlMap.insert("commentWrite", dto);
		return result;
	}

	// 댓글 가져오는 메서드
	public List<ReplyDTO> commentList(int bbs_idx) {
		List<ReplyDTO> dto = sqlMap.selectList("commentList", bbs_idx);
		return dto;
	}
}
