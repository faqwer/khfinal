package finaltp.report.model;

import java.util.List;

public interface ReportDAO {

	public int getUserIdx(String userid); // 신고자 idx 가져오는 메서드
	public int report(ReportDTO dto); // 신고 테이블 추가
	public List<ReportDTO> reportList(int cp, int ls); // 신고목록 가져오는 메서드
	public int adminReportProcess(int bbs_idx, int writer_idx, String report, String admin_content); // 관리자 신고 처리
}
