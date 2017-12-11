package finaltp.mainBbs.model;

import java.util.List;

import finaltp.acc.model.AccDTO;
import finaltp.member.model.MemberDTO;
import finaltp.reply.model.ReplyDTO;

public interface MainBbsDAO {
	public List<MainBbsDTO> mainBbsList(int cp, int ls, String category);
	public int getTotalCnt(String category); // 총 게시물 조회
	public MainBbsDTO bbsContent(int idx); // 게시판 본문
	public int getMemberIdx(String userid); //
	public int getBbsWriterIdx(int bbs_idx); // 게시판 작성자 idx
	public String getUserId(int member_idx); // UserId 조회
	public int mainBbsStatusChange(int bbs_idx, String category); // 게시판 상태변경
	public int getWriterMemberIdx(String userid); // 작성자 idx 조회
	public String getWriterProfileImg(int writer_idx); // 작성자 프로필사진 조회
	public MemberDTO getLoginUserInfo(String userid); // 로그인중인 유저 정보 조회
	
}
