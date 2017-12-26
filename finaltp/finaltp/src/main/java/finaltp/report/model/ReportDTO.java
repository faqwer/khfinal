package finaltp.report.model;

public class ReportDTO {

	private int bbs_idx;
	private int writer_idx;
	private int user_idx;
	private String writer_subject;
	private String userid;
	private String reportid;
	private String reason;
	private String status;

	public ReportDTO() {
		super();
	}

	public ReportDTO(int bbs_idx, int writer_idx, int user_idx, String writer_subject, String userid, String reportid,
			String reason, String status) {
		super();
		this.bbs_idx = bbs_idx;
		this.writer_idx = writer_idx;
		this.user_idx = user_idx;
		this.writer_subject = writer_subject;
		this.userid = userid;
		this.reportid = reportid;
		this.reason = reason;
		this.status = status;
	}

	// 신고 작성
	public ReportDTO(int bbs_idx, int writer_idx, int user_idx, String reason, String status) {
		super();
		this.bbs_idx = bbs_idx;
		this.writer_idx = writer_idx;
		this.user_idx = user_idx;
		this.reason = reason;
		this.status = status;
	}

	public int getBbs_idx() {
		return bbs_idx;
	}

	public void setBbs_idx(int bbs_idx) {
		this.bbs_idx = bbs_idx;
	}

	public int getWriter_idx() {
		return writer_idx;
	}

	public void setWriter_idx(int writer_idx) {
		this.writer_idx = writer_idx;
	}

	public int getUser_idx() {
		return user_idx;
	}

	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}

	public String getWriter_subject() {
		return writer_subject;
	}

	public void setWriter_subject(String writer_subject) {
		this.writer_subject = writer_subject;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getReportid() {
		return reportid;
	}

	public void setReportid(String reportid) {
		this.reportid = reportid;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
