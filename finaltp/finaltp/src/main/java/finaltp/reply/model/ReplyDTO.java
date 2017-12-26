package finaltp.reply.model;

import java.sql.Date;

public class ReplyDTO {

	private int reply_idx;
	private int writer_idx;
	private int bbs_idx;
	private int user_idx;
	private String content;
	private Date writedate;
	private int ref;
	private int lev;
	private int sunbun;
	private String writerid;
	private String profileImg;

	public ReplyDTO() {
		super();
	}

	public ReplyDTO(int reply_idx, int writer_idx, int bbs_idx, int user_idx, String content, Date writedate, int ref,
			int lev, int sunbun, String writerid, String profileImg) {
		super();
		this.reply_idx = reply_idx;
		this.writer_idx = writer_idx;
		this.bbs_idx = bbs_idx;
		this.user_idx = user_idx;
		this.content = content;
		this.writedate = writedate;
		this.ref = ref;
		this.lev = lev;
		this.sunbun = sunbun;
		this.writerid = writerid;
		this.profileImg = profileImg;
	}

	// 댓글 작성
	public ReplyDTO(int writer_idx, int bbs_idx, int user_idx, String content, int ref, int lev, int sunbun) {
		super();
		this.writer_idx = writer_idx;
		this.bbs_idx = bbs_idx;
		this.user_idx = user_idx;
		this.content = content;
		this.ref = ref;
		this.lev = lev;
		this.sunbun = sunbun;
	}

	public int getReply_idx() {
		return reply_idx;
	}

	public void setReply_idx(int reply_idx) {
		this.reply_idx = reply_idx;
	}

	public int getWriter_idx() {
		return writer_idx;
	}

	public void setWriter_idx(int writer_idx) {
		this.writer_idx = writer_idx;
	}

	public int getBbs_idx() {
		return bbs_idx;
	}

	public void setBbs_idx(int bbs_idx) {
		this.bbs_idx = bbs_idx;
	}

	public int getUser_idx() {
		return user_idx;
	}

	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getWritedate() {
		return writedate;
	}

	public void setWritedate(Date writedate) {
		this.writedate = writedate;
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

	public String getWriterid() {
		return writerid;
	}

	public void setWriterid(String writerid) {
		this.writerid = writerid;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

}
