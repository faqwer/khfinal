package finaltp.report.model;

public class ReportDTO {

	private int bbs_idx;
	private int member_idx;
	private int user_idx;
	private String reason;
	private String status;

	public ReportDTO() {
		super();
	}

	public ReportDTO(int bbs_idx, int member_idx, int user_idx, String reason, String status) {
		super();
		this.bbs_idx = bbs_idx;
		this.member_idx = member_idx;
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

	public int getMember_idx() {
		return member_idx;
	}

	public void setMember_idx(int member_idx) {
		this.member_idx = member_idx;
	}

	public int getUser_idx() {
		return user_idx;
	}

	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
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
