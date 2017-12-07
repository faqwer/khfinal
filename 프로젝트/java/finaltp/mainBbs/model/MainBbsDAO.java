package finaltp.mainBbs.model;

import java.util.List;

import finaltp.acc.model.AccDTO;
import finaltp.reply.model.ReplyDTO;

public interface MainBbsDAO {
	public List<MainBbsDTO> mainBbsList(int cp, int ls, String category);
	public int getTotalCnt(String category); // 총 게시물 조회
	public List<MainBbsDTO> noticeContent(int idx); // 공지사항 본문
	public int getMemberIdx(String userid); //
	public int getBbsWriterIdx(int bbs_idx); // 게시판 작성자 idx
	public String getUserId(int member_idx); // UserId 조회
	public int mainBbsStatusChange(int bbs_idx, String category); // 게시판 상태변경
	public int getWriterMemberIdx(String userid); // 작성자 idx 조회
	
}
