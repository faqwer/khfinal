package finaltp.route.model;

public class RouteDTO {

	private int bbs_idx;
	private int writer_idx;
	private String thema;
	private int readnum;
	private String coverimg;

	public RouteDTO() {
		super();
	}

	public RouteDTO(int bbs_idx, int writer_idx, String thema, int readnum,String coverimg) {
		super();
		this.bbs_idx = bbs_idx;
		this.writer_idx = writer_idx;
		this.thema = thema;
		this.readnum = readnum;
		this.coverimg = coverimg;
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

	public String getCoverimg() {
		return coverimg;
	}

	public void setCoverimg(String coverimg) {
		this.coverimg = coverimg;
	}

}
