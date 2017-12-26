package finaltp.recommend.model;

public class RecommendDTO {

	private int bbs_idx;
	private int writer_idx;
	private int user_idx;

	public RecommendDTO() {
		super();
	}

	public RecommendDTO(int bbs_idx, int writer_idx, int user_idx) {
		super();
		this.bbs_idx = bbs_idx;
		this.writer_idx = writer_idx;
		this.user_idx = user_idx;
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

}
