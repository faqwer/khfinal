package finaltp.ask.model;

public class AskDTO {

	private int bbs_idx;
	private int writer_idx;
	private String ask_status;
	private String secret;
	private int ref;
	private int lev;
	private int sunbun;

	public AskDTO() {
		super();
	}

	public AskDTO(int bbs_idx, int writer_idx, String ask_status, String secret, int ref, int lev, int sunbun) {
		super();
		this.bbs_idx = bbs_idx;
		this.writer_idx = writer_idx;
		this.ask_status = ask_status;
		this.secret = secret;
		this.ref = ref;
		this.lev = lev;
		this.sunbun = sunbun;
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

	public String getAsk_status() {
		return ask_status;
	}

	public void setAsk_status(String ask_status) {
		this.ask_status = ask_status;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getLev() {
		return lev;
	}

	public void setLev(int lev) {
		this.lev = lev;
	}

	public int getSunbun() {
		return sunbun;
	}

	public void setSunbun(int sunbun) {
		this.sunbun = sunbun;
	}

}
