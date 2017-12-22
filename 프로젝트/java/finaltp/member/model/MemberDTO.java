package finaltp.member.model;

import java.sql.Date;

import finaltp.follow.model.FollowDTO;

public class MemberDTO {

	private int member_idx;
	private String name;
	private String id;
	private int lev;
	private Date joindate;
	private int reportnum;
	private String bookmark;
	private String pwd;
	private String profile_img;
	private String reason;
	
	private FollowDTO followdto;
	
	public FollowDTO getFollowdto() {
		return followdto;
	}

	public void setFollowdto(FollowDTO followdto) {
		this.followdto = followdto;
	}

	public MemberDTO(int member_idx, String name, String id, int lev, Date joindate, int reportnum, String bookmark,
			String pwd, String profile_img, String reason, FollowDTO followdto) {
		super();
		this.member_idx = member_idx;
		this.name = name;
		this.id = id;
		this.lev = lev;
		this.joindate = joindate;
		this.reportnum = reportnum;
		this.bookmark = bookmark;
		this.pwd = pwd;
		this.profile_img = profile_img;
		this.reason = reason;
		this.followdto = followdto;
	}

	public MemberDTO() {
		// TODO Auto-generated constructor stub
	}

	public MemberDTO(int member_idx, String name, String id, int lev, Date joindate, int reportnum, String bookmark,
			String pwd, String profile_img, String reason) {
		super();
		this.member_idx = member_idx;
		this.name = name;
		this.id = id;
		this.lev = lev;
		this.joindate = joindate;
		this.reportnum = reportnum;
		this.bookmark = bookmark;
		this.pwd = pwd;
		this.profile_img = profile_img;
		this.reason = reason;
	}



	public int getMember_idx() {
		return member_idx;
	}

	public void setMember_idx(int member_idx) {
		this.member_idx = member_idx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getLev() {
		return lev;
	}

	public void setLev(int lev) {
		this.lev = lev;
	}

	public Date getJoindate() {
		return joindate;
	}

	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}

	public int getReportnum() {
		return reportnum;
	}

	public void setReportnum(int reportnum) {
		this.reportnum = reportnum;
	}

	public String getBookmark() {
		return bookmark;
	}

	public void setBookmark(String bookmark) {
		this.bookmark = bookmark;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getProfile_img() {
		return profile_img;
	}

	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
	
}
