package finaltp.route.model;

public class RouteDTO {

	private int bbs_idx;
	private int member_idx;
	private String thema;
	private int readnum;
	private String recommend;
	private String coverimg;

	public RouteDTO() {
		super();
	}

	public RouteDTO(int bbs_idx, int member_idx, String thema, int readnum, String recommend, String coverimg) {
		super();
		this.bbs_idx = bbs_idx;
		this.member_idx = member_idx;
		this.thema = thema;
		this.readnum = readnum;
		this.recommend = recommend;
		this.coverimg = coverimg;
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

	public String getThema() {
		return thema;
	}

	public void setThema(String thema) {
		this.thema = thema;
	}

	public int getReadnum() {
		return readnum;
	}

	public void setReadnum(int readnum) {
		this.readnum = readnum;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public String getCoverimg() {
		return coverimg;
	}

	public void setCoverimg(String coverimg) {
		this.coverimg = coverimg;
	}

}
