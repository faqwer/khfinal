package finaltp.report.model;

public interface ReportDAO {

	public int getUserIdx(String userid); // 신고자 idx 가져오는 메서드
	public int report(ReportDTO dto); // 신고 테이블 추가
}
