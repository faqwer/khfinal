package finaltp.ask.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import finaltp.mainBbs.model.MainBbsDTO;

public class AskDAOImple implements AskDAO {

	private SqlSessionTemplate sqlMap;

	public AskDAOImple(SqlSessionTemplate sqlMap) {
		super();
		this.sqlMap = sqlMap;
	}

	// 1:1문의 게시판 목록
	public List<MainBbsDTO> askList(int cp, int ls, String status) {
		int startnum = (cp - 1) * ls + 1;
		int endnum = cp * ls;
		System.out.println("startnum : " + startnum);
		System.out.println("endnum : " + endnum);
		System.out.println("status : " + status);
		Map data = new HashMap();
		data.put("startnum", startnum);
		data.put("endnum", endnum);
		data.put("status", status);
		List<MainBbsDTO> dto = sqlMap.selectList("askList", data);
		return dto;
	}

	// 1:1문의 관리자 게시판 목록
	public List<MainBbsDTO> adminAskList(int cp, int ls, String status) {
		int startnum = (cp - 1) * ls + 1;
		int endnum = cp * ls;
		System.out.println("startnum : " + startnum);
		System.out.println("endnum : " + endnum);
		System.out.println("status : " + status);
		Map data = new HashMap();
		data.put("startnum", startnum);
		data.put("endnum", endnum);
		data.put("status", status);
		List<MainBbsDTO> dto = sqlMap.selectList("adminAskList", data);
		return dto;
	}

	// 1:1문의 total 테이블 추가
	public int mainAskWrite(MainBbsDTO dto) {
		int ref = 0;
		int result = sqlMap.insert("mainBbsWrite", dto);
		return result;
	}

	// 1:1문의 ask 테이블 추가
	public int askWrite(MainBbsDTO dto, String secret) {
		int ref = 0;
		int askCount = sqlMap.selectOne("askCount");
		if (askCount == 0) {
			ref = 0;
		} else {
			ref = sqlMap.selectOne("getAskMaxRef");
			ref++; // 게시물을 작성할때마다 ref를 1씩 증가
		}
		Map map = new HashMap();
		map.put("writer_idx", dto.getWriter_idx());
		map.put("ref", ref);
		map.put("secret", secret);
		int result = sqlMap.insert("askWrite", map);
		return result;
	}

	// 1:1문의 본문
	public AskDTO askContent(int bbs_idx) {
		AskDTO dto = sqlMap.selectOne("askContent", bbs_idx);
		return dto;
	}

	// 순번 업데이트
	public void updateSun(int ref, int sunbun) {
		System.out.println("ref : " + ref);
		System.out.println("sunbun : " + sunbun);
		Map data = new HashMap();
		data.put("ref", ref);
		data.put("sunbun", sunbun);
		sqlMap.update("updateSun", data);

	}

	// 1:1문의 관리자 답변
	public int askReWrite(AskDTO dto) {
		updateSun(dto.getRef(), dto.getSunbun() + 1);
		dto.setLev(dto.getLev() + 1);
		dto.setSunbun(dto.getSunbun() + 1);
		int result = sqlMap.insert("askReWrite", dto);
		int result2 = sqlMap.update("updateStatus", dto);
		return result * result2;
	}

}
