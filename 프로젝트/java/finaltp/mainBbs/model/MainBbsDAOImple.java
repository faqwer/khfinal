package finaltp.mainBbs.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import finaltp.member.model.MemberDTO;

public class MainBbsDAOImple implements MainBbsDAO {

	private SqlSessionTemplate sqlMap;

	public MainBbsDAOImple(SqlSessionTemplate sqlMap) {
		super();
		this.sqlMap = sqlMap;
	}

	// 전체 게시물 조회
	public List<MainBbsDTO> mainBbsList(int cp, int ls, String category) {
		int startnum = (cp - 1) * ls + 1;
		int endnum = cp * ls;
		Map data = new HashMap();
		data.put("startnum", startnum);
		data.put("endnum", endnum);
		data.put("category", category);
		List<MainBbsDTO> dto = sqlMap.selectList("mainBbsList", data);
		return dto;
	}

	// 전체 게시물 수 조회
	public int getTotalCnt(String category) {
		int count = sqlMap.selectOne("totalCnt", category);
		return count == 0 ? 1 : count;
	}

	// 게시물 본문 조회
	public MainBbsDTO bbsContent(int idx) {
		MainBbsDTO dto = sqlMap.selectOne("bbsContent", idx);
		return dto;
	}

	// 멤버 번호 조회
	public int getMemberIdx(String userid) {
		int member_idx = sqlMap.selectOne("memberIdx", userid);
		return member_idx;
	}

	// 게시물 멤버 번호 조회
	public int getBbsWriterIdx(int bbs_idx) {
		int writer_idx = sqlMap.selectOne("bbsWriterIdx", bbs_idx);
		return writer_idx;
	}

	// 작성자 ID 가져오는 메서드
	public String getUserId(int member_idx) {
		String userid = sqlMap.selectOne("getUserId", member_idx);
		return userid;
	}
	
	// 메인 게시판 게시글 삭제
	public int mainBbsStatusDefer(int bbs_idx) {
		int result = sqlMap.delete("mainBbsStatusDefer", bbs_idx);
		return result;
	}
	
	// 작성자 idx 가져오는 메서드
	public int getWriterMemberIdx(String writerid) {
		int result = sqlMap.selectOne("getWriterMemberIdx", writerid);
		return result;
	}
	
	// 게시글 작성자 프로필 사진 가져오는 메서드
	public String getWriterProfileImg(int writer_idx) {
		String result = sqlMap.selectOne("getProfileImg", writer_idx);
		return result;
	}
	
	// 로그인 중인 사용자 정보 조회
	public MemberDTO getLoginUserInfo(String userid) {
			MemberDTO dto = sqlMap.selectOne("memberINFO", userid);
			return dto;
	}
	// 후기 게시글 메인 테이블 수정
	public int reviewMainBbsRevise(int bbs_idx, String subject, String content) {
		Map data = new HashMap();
		data.put("bbs_idx", bbs_idx);
		data.put("subject", subject);
		data.put("content", content);
		int result = sqlMap.update("reviewMainBbsRevise", data);
		return result;
	}

	// 조회수 증가
	public int setReadNum(int bbs_idx, String category) {
		int result = 0;
		if(category.equals("review")) {
			result = sqlMap.update("setReviewReadNum", bbs_idx);
		}
		return result;
	}
	
}
