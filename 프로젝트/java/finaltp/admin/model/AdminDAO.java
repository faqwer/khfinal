package finaltp.admin.model;

import java.util.*;

import finaltp.mainBbs.model.MainBbsDTO;
import finaltp.member.model.*;

public interface AdminDAO {

	public List<MemberDTO> memberList(int cp, int ls, String type); // 멤버 목록 조회
	public List<MemberDTO> memberSearch(String sel, String text, String type); // 멤버 검색
	public int memberOut(String[] id); // 멤버 탈퇴
	public int normalBlackMove(String[] id); // 블랙리스트 전환
	public int blackNormalMove(String[] id); // 일반으로 전환
	public int getMemberTotalCnt(String type); // 관리자 총 회원 조회
	public int getAdminBbsTotalCnt(String category, String status); // 관리자 총 게시물 조회
	public int getAdminAskTotalCnt(String category, String status); // 관리자 ask 게시판 총 게시물 조회
	public List<MainBbsDTO> getMainBbsList(int cp, int ls, String category, String status); // 게시판 메인 테이블 정보 조회
	public int normalDeferMove(int[] bbs_idx); // 일반 -> 보류 상태 전환
	public int deferNormalMove(int[] bbs_idx); // 보류 -> 일반 상태 전환
	public int bbsDelete(int[] bbs_idx); // 게시글 삭제
}
