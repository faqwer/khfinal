package finaltp.mainBbs.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import finaltp.acc.model.AccDTO;
import finaltp.reply.model.ReplyDTO;

public class MainBbsDAOImple implements MainBbsDAO {

	private SqlSessionTemplate sqlMap;

	public MainBbsDAOImple(SqlSessionTemplate sqlMap) {
		super();
		this.sqlMap = sqlMap;
	}

	// 전체 게시물 조회
	public List<MainBbsDTO> noticeList(int cp, int ls) {
		int startnum = (cp - 1) * ls + 1;
		int endnum = cp * ls;
		Map<String, Integer> data = new HashMap<String, Integer>();
		data.put("startnum", startnum);
		data.put("endnum", endnum);
		List<MainBbsDTO> dto = sqlMap.selectList("noticeList", data);
		return dto;
	}

	// 전체 게시물 수 조회
	public int getTotalCnt() {
		int count = sqlMap.selectOne("totalCnt");
		return count == 0 ? 1 : count;
	}

	// 게시물 본문 조회
	public List<MainBbsDTO> noticeContent(int idx) {
		List<MainBbsDTO> dto = sqlMap.selectList("noticeContent", idx);
		return dto;
	}

	// 멤버 번호 조회
	public int getMemberIdx(String userid) {
		int member_idx = sqlMap.selectOne("memberIdx", userid);
		return member_idx;
	}
	
	// 게시물 멤버 번호 조회
	public int getBbsMemberIdx(int bbs_idx) {
		int member_idx = sqlMap.selectOne("bbsMemberIdx", bbs_idx);
		return member_idx;
	}

	// 동행 게시판 작성
	public int accWrite(MainBbsDTO dto, String nation) {
		int result = sqlMap.insert("accWrite1", dto);
		Map map = new HashMap();
		map.put("member_idx", dto.getMember_idx());
		map.put("nation", nation);
		int result2 = sqlMap.insert("accWrite2", map);
		return result * result2;
	}

	// 메인 게시판 목록(동행)
	public List<MainBbsDTO> mainAccList(int cp, int ls) {
		int startnum = (cp - 1) * ls + 1;
		int endnum = cp * ls;
		Map<String, Integer> data = new HashMap<String, Integer>();
		data.put("startnum", startnum);
		data.put("endnum", endnum);
		List<MainBbsDTO> dto = sqlMap.selectList("accList1", data);
		return dto;
	}

	// 동행 게시판 목록
	public List<AccDTO> accList(int cp, int ls) {
		int startnum = (cp - 1) * ls + 1;
		int endnum = cp * ls;
		Map<String, Integer> data = new HashMap<String, Integer>();
		data.put("startnum", startnum);
		data.put("endnum", endnum);
		List<AccDTO> dto = sqlMap.selectList("accList2", data);
		return dto;
	}

	// 작성자 ID 가져오는 메서드
	public String getUserId(int member_idx) {
		String userid = sqlMap.selectOne("getUserId", member_idx);
		return userid;
	}

}
