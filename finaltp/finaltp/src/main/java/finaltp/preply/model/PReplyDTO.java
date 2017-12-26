package finaltp.preply.model;

import java.sql.Date;

public class PReplyDTO {

	private int reply_idx;
	private int planner_idx;
	private int writer_idx;
	private int user_idx;
	private String user_id;
	private String content;
	private Date writedate;
	private int ref;
	private int sunbun; 
	private int lev;
	public PReplyDTO() {
		super();
	}
	public PReplyDTO(int reply_idx, int planner_idx, int writer_idx, int user_idx, String content, Date writedate,
			int ref, int sunbun, int lev) {
		super();
		this.reply_idx = reply_idx;
		this.planner_idx = planner_idx;
		this.writer_idx = writer_idx;
		this.user_idx = user_idx;
		this.content = content;
		this.writedate = writedate;
		this.ref = ref;
		this.sunbun = sunbun;
		this.lev = lev;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getReply_idx() {
		return reply_idx;
	}
	public void setReply_idx(int reply_idx) {
		this.reply_idx = reply_idx;
	}
	public int getPlanner_idx() {
		return planner_idx;
	}
	public void setPlanner_idx(int planner_idx) {
		this.planner_idx = planner_idx;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getWritedate() {
		return writedate;
	}
	public void setWritedate(Date writerdate) {
		this.writedate = writerdate;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getSunbun() {
		return sunbun;
	}
	public void setSunbun(int sunbun) {
		this.sunbun = sunbun;
	}
	public int getLev() {
		return lev;
	}
	public void setLev(int lev) {
		this.lev = lev;
	} 
	
}