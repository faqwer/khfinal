package finaltp.admin.model;

import finaltp.member.model.*;
import finaltp.acc.model.*;
import finaltp.mainBbs.model.MainBbsDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import org.mybatis.spring.SqlSessionTemplate;

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

	public int normalDeferMove(int[] bbs_idx) {
		int result = 1;
		Map map = new HashMap();
		map.put("status", "defer");
		for (int i = 0; i < bbs_idx.length; i++) {
			map.put("bbs_idx", bbs_idx[i]);
			result *= sqlMap.update("statusChange", map);
		}
		return result;
	}
	
	public int deferNormalMove(int[] bbs_idx) {
		int result = 1;
		Map map = new HashMap();
		map.put("status", "normal");
		for (int i = 0; i < bbs_idx.length; i++) {
			map.put("bbs_idx", bbs_idx[i]);
			result *= sqlMap.update("statusChange", map);
		}
		return result;
	}
	
	public int bbsDelete(int[] bbs_idx) {
		int result = 1;
		for (int i = 0; i < bbs_idx.length; i++) {
			result *= sqlMap.update("bbsDelete", bbs_idx[i]);
		}
		return result;
	}
}
