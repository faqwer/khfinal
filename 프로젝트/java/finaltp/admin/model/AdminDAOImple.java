package finaltp.admin.model;

import finaltp.member.model.*;
import finaltp.review.model.ReviewDTO;
import finaltp.route.model.RouteDTO;
import finaltp.acc.model.*;
import finaltp.faq.model.FaqDTO;
import finaltp.mainBbs.model.MainBbsDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.web.servlet.ModelAndView;

public class AdminDAOImple implements AdminDAO {

	private SqlSessionTemplate sqlMap; // 리모컨이용

	public AdminDAOImple(SqlSessionTemplate sqlMap) {
		super();
		this.sqlMap = sqlMap;
	}

	// 멤버 목록 조회
	public List<MemberDTO> memberList(int cp, int ls, String type) {
		List<MemberDTO> dto = null;
		int startnum = (cp - 1) * ls + 1;
		int endnum = cp * ls;
		Map map = new HashMap();
		map.put("startnum", startnum);
		map.put("endnum", endnum);
		if (type.equals("all")) {
			dto = sqlMap.selectList("adminAllMemberList", map);
		} else if (type.equals("normal")) {
			map.put("lev", 1);
			dto = sqlMap.selectList("adminMemberList", map);
		} else {
			map.put("lev", 2);
			dto = sqlMap.selectList("adminMemberList", map);
		}
		return dto;
	}

	// 총 멤버 수 가져오는 메서드
	public int getMemberTotalCnt(String type) {
		if (type.equals("all")) {
			return sqlMap.selectOne("adminAllMemberTotalCnt");
		} else if (type.equals("normal")) {
			return sqlMap.selectOne("adminMemberTotalCnt", 1);
		} else {
			return sqlMap.selectOne("adminMemberTotalCnt", 2);
		}
	}

	// 관리자 해당 게시판 총 게시물 가져오는 메서드
	public int getAdminBbsTotalCnt(String category, String status) {
		Map map = new HashMap();
		map.put("category", category);
		map.put("status", status);
		int count = sqlMap.selectOne("adminBbsTotalCnt", map);
		return count;
	}

	// 관리자 ask 게시판 총 게시물 조회
	public int getAdminAskTotalCnt(String category, String status) {
		Map map = new HashMap();
		map.put("category", category);
		map.put("status", status);
		int count = sqlMap.selectOne("adminAskTotalCnt", map);
		return count;
	}

	public int getAdminPlannerTotalCnt(String status) {
		int count = sqlMap.selectOne("adminPlannerTotalCnt", status);
		return count;
	}

	// 멤버 검색
	public List<MemberDTO> memberSearch(String sel, String text, String type) {
		List<MemberDTO> dto = null;
		Map map = new HashMap();
		if (sel.equals("ID")) {
			if (type.equals("all")) {
				dto = sqlMap.selectList("adminAllMemberIDSearch", text);
			} else if (type.equals("normal")) {
				map.put("lev", 1);
				map.put("text", text);
				dto = sqlMap.selectList("adminMemberIDSearch", map);
			} else {
				map.put("lev", 2);
				map.put("text", text);
				dto = sqlMap.selectList("adminMemberIDSearch", map);
			}
		} else {
			if (type.equals("all")) {
				dto = sqlMap.selectList("adminAllMemberNameSearch", text);
			} else if (type.equals("normal")) {
				map.put("lev", 1);
				map.put("text", text);
				dto = sqlMap.selectList("adminMemberNameSearch", map);
			} else {
				map.put("lev", 2);
				map.put("text", text);
				dto = sqlMap.selectList("adminMemberNameSearch", map);
			}
		}
		return dto;
	}

	// 회원 탈퇴
	public int memberOut(String[] id) {
		int count = 0;
		for (int i = 0; i < id.length; i++) {
			count *= sqlMap.delete("adminMemberOut", id[i]);
		}
		return count;
	}

	// 블랙리스트 전환
	public int normalBlackMove(String[] id) {
		int count = 0;
		for (int i = 0; i < id.length; i++) {
			count *= sqlMap.delete("adminNormalBlackMove", id[i]);
		}
		return count;
	}

	// 일반회원 전환
	public int blackNormalMove(String[] id) {
		int count = 0;
		for (int i = 0; i < id.length; i++) {
			count *= sqlMap.delete("adminBlackNormalMove", id[i]);
		}
		return count;
	}

	// 관리자 MainBbs 게시글 가져오는 메서드
	public List<MainBbsDTO> getMainBbsList(int cp, int ls, String category, String status) {
		int startnum = (cp - 1) * ls + 1;
		int endnum = cp * ls;
		Map map = new HashMap();
		map.put("startnum", startnum);
		map.put("endnum", endnum);
		map.put("category", category);
		map.put("status", status);
		List<MainBbsDTO> dto = sqlMap.selectList("adminMainBbsList", map);
		return dto;
	}

	// 일반 -> 보류 이동
	public int normalDeferMove(int[] bbs_idx, String sel) {
		int result = 1;
		Map map = new HashMap();
		map.put("status", "defer");
		if (sel.equals("planner")) {
			for (int i = 0; i < bbs_idx.length; i++) {
				map.put("planner_idx", bbs_idx[i]);
				result *= sqlMap.update("plannerStatusChange", map);
			}
		} else {
			for (int i = 0; i < bbs_idx.length; i++) {
				map.put("bbs_idx", bbs_idx[i]);
				result *= sqlMap.update("statusChange", map);

			}
		}

		return result;
	}

	// 보류 -> 일반 이동
	public int deferNormalMove(int[] bbs_idx, String sel) {
		int result = 1;
		Map map = new HashMap();
		map.put("status", "normal");
		if (sel.equals("planner")) {
			for (int i = 0; i < bbs_idx.length; i++) {
				map.put("planner_idx", bbs_idx[i]);
				result *= sqlMap.update("plannerStatusChange", map);
			}
		} else {
			for (int i = 0; i < bbs_idx.length; i++) {
				map.put("bbs_idx", bbs_idx[i]);
				result *= sqlMap.update("statusChange", map);
			}
		}

		return result;
	}

	// 삭제
	public int bbsDelete(int[] bbs_idx, String sel) {
		int result = 1;
		if (sel.equals("planner")) {
			for (int i = 0; i < bbs_idx.length; i++) {
				result *= sqlMap.update("plannerDelete", bbs_idx[i]);
			}
		} else {
			for (int i = 0; i < bbs_idx.length; i++) {
				result *= sqlMap.update("bbsDelete", bbs_idx[i]);
			}
		}

		return result;
	}

	// 공지사항 작성
	public int noticeWrite(int writer_idx, String subject, String content, String category) {
		Map data = new HashMap();
		data.put("writer_idx", writer_idx);
		data.put("subject", subject);
		data.put("content", content);
		data.put("category", category);
		int result = sqlMap.insert("mainBbsWrite", data);
		return result;
	}

	// 공지사항 본문
	public MainBbsDTO noticeContent(int bbs_idx) {
		MainBbsDTO dto = null;
		try {
			dto = sqlMap.selectOne("bbsContent", bbs_idx);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return dto;
	}

	// 공지사항 수정
	public int noticeRevise(int bbs_idx, String subject, String content) {
		Map data = new HashMap();
		data.put("bbs_idx", bbs_idx);
		data.put("subject", subject);
		data.put("content", content);
		int result = sqlMap.update("mainBbsRevise", data);
		return result;
	}

	// FAQ 리스트
	public List<FaqDTO> faqList(int cp, int ls) {
		int startnum = (cp - 1) * ls + 1;
		int endnum = cp * ls;
		Map map = new HashMap();
		map.put("startnum", startnum);
		map.put("endnum", endnum);
		List<FaqDTO> dto = sqlMap.selectList("faqList", map);
		return dto;
	}

	// FAQ 작성
	public int faqWrite(String question, String answer) {
		Map data = new HashMap();
		data.put("question", question);
		data.put("answer", answer);
		int result = sqlMap.insert("faqWrite", data);
		return result;
	}

	// FAQ 본문
	public FaqDTO faqContent(int faq_idx) {
		FaqDTO dto = sqlMap.selectOne("faqContent", faq_idx);
		return dto;
	}

	// FAQ 삭제
	public int faqDelete(int[] faq_idx) {
		int result = 1;
		for (int i = 0; i < faq_idx.length; i++) {
			result *= sqlMap.delete("faqDelete", faq_idx[i]);
		}
		return result;
	}

	// FAQ 수정
	public int faqRevise(int faq_idx, String question, String answer) {
		Map data = new HashMap();
		data.put("faq_idx", faq_idx);
		data.put("question", question);
		data.put("answer", answer);
		int result = sqlMap.update("faqRevise", data);
		return result;
	}
}
