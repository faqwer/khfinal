package finaltp.reply.model;

import java.util.List;

public interface ReplyDAO {
	public int commentWrite(ReplyDTO dto);
	public int getMaxRef(int bbs_idx);
	public int getCommentCount(int bbs_idx);
	public List<ReplyDTO> commentList(int bbs_idx);
}
