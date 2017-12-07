package finaltp.reply.model;

import java.util.List;

public interface ReplyDAO {
	public int commentWrite(ReplyDTO dto); // 댓글 작성
	public int getMaxRef(int bbs_idx); // maxRef 조회
	public int getCommentCount(int bbs_idx); // 댓글 갯수 조회
	public List<ReplyDTO> commentList(int bbs_idx); // 댓글 리스트
	public int commentAllDelete(int bbs_idx); // 댓글 삭제
}
