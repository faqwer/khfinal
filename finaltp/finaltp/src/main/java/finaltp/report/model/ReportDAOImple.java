package finaltp.report.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import finaltp.mainBbs.model.MainBbsDTO;
import finaltp.review.model.ReviewDTO;

public class ReportDAOImple implements ReportDAO{

	private SqlSessionTemplate sqlMap;

	public ReportDAOImple(SqlSessionTemplate sqlMap) {
		super();
		this.sqlMap = sqlMap;
	}

	// 신고자 idx 가져오는 메서드
	public int getUserIdx(String userid) {
		int user_idx = sqlMap.selectOne("getUserIdx", userid);
		return user_idx;
	}
	
	// 신고 테이블 추가
	public int report(ReportDTO dto) {
		int result = sqlMap.insert("reportAdd", dto);
		return result;
	}
	
	// 신고목록 가져오는 메서드
	public List<ReportDTO> reportList(int cp, int ls) {
		int startnum = (cp - 1) * ls + 1;
		int endnum = cp * ls;
		Map data = new HashMap();
		data.put("startnum", startnum);
		data.put("endnum", endnum);
		List<ReportDTO> dto = sqlMap.selectList("reportList", data);
		return dto;
	}
	
	public int adminReportProcess(int bbs_idx, int writer_idx, String report, String admin_content) {
		Map data = new HashMap();
		int updateReportNum = 1;
		if(report.equals("yes")) {
			data.put("bbs_idx", bbs_idx);
			data.put("admin_content", admin_content);
			updateReportNum = sqlMap.update("updateReportNum", writer_idx);
			
		} else {
			data.put("bbs_idx", bbs_idx);
			data.put("admin_content", "No Report");
		}
		int result = sqlMap.update("ReportProcess", data);
		int updateStatus = sqlMap.update("reportUpdateStatus", bbs_idx);
		return updateReportNum * result;
	}
}
