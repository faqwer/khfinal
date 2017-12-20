package finaltp.reply.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		int result = sqlMap.selectOne("getCommentMaxRef", bbs_idx);
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
		for (int i = 0; i < dto.size(); i++) {
			String writerid = sqlMap.selectOne("getWriterId", dto.get(i).getUser_idx());
			String profileImg = sqlMap.selectOne("getProfileImg", dto.get(i).getUser_idx());
			dto.get(i).setWriterid(writerid);
			dto.get(i).setProfileImg(profileImg);
		}
		return dto;
	}

	// 댓글 전체 삭제 메서드
	public int commentAllDelete(int bbs_idx) {
		int result = sqlMap.delete("commentAllDelete", bbs_idx);
		return result;
	}
	
	// 댓글 수정 메서드
	public int commentRevise(int reply_idx, String content) {
		Map map = new HashMap();
		map.put("reply_idx", reply_idx);
		map.put("content", content);
		int result = sqlMap.update("commentRevise", map);
		return result;
	}
	
	// 댓글 삭제
	public int commentDelete(int reply_idx) {
		int result = sqlMap.delete("commentDelete", reply_idx);
		return result;
	}
}
