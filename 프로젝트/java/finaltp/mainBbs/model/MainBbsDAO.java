package finaltp.mainBbs.model;

import java.util.List;

import finaltp.acc.model.AccDTO;
import finaltp.reply.model.ReplyDTO;

public interface MainBbsDAO {
	public List<MainBbsDTO> noticeList(int cp, int ls);
	public int getTotalCnt();
	public List<MainBbsDTO> noticeContent(int idx);
	public int getMemberIdx(String userid);
	public int getBbsMemberIdx(int bbs_idx);
	public int accWrite(MainBbsDTO dto, String nation);
	public List<MainBbsDTO> mainAccList(int cp, int ls);
	public List<AccDTO> accList(int cp, int ls);
	public String getUserId(int member_idx);
	
}
