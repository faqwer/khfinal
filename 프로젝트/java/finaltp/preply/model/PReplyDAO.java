package finaltp.preply.model;

import java.util.List;

public interface PReplyDAO {
	public List<PReplyDTO> getPReply(int planner_idx);
	public int addPReply(PReplyDTO prdto);
}
