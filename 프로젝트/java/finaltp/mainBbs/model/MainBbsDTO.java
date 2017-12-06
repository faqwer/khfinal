package finaltp.mainBbs.model;

import java.sql.Date;
import java.util.List;

import finaltp.acc.model.AccDTO;
import finaltp.ask.model.AskDTO;
import finaltp.reply.model.ReplyDTO;
import finaltp.report.model.ReportDTO;
import finaltp.review.model.ReviewDTO;
import finaltp.route.model.RouteDTO;

public class MainBbsDTO {

	private int bbs_idx;
	private int member_idx;
	private String category;
	private String status;
	private String subject;
	private String content;
	private int ref;
	private int lev;
	private int sunbun;
	private Date writedate;
	private String nation;
	private String userid;

	private AccDTO accdto;
	private ReviewDTO reviewdto;
	private RouteDTO routedto;
	private AskDTO askdto;
	private ReportDTO reportdto;
	private ReplyDTO replydto;
	private List<ReplyDTO> replylist;

	public MainBbsDTO() {
		super();
	}

	public MainBbsDTO(int bbs_idx, int member_idx, String category, String status, String subject, String content,
			int ref, int lev, int sunbun, Date writedate, AccDTO accdto, ReviewDTO reviewdto, RouteDTO routedto,
			AskDTO askdto, ReportDTO reportdto, ReplyDTO replydto) {
		super();
		this.bbs_idx = bbs_idx;
		this.member_idx = member_idx;
		this.category = category;
		this.status = status;
		this.subject = subject;
		this.content = content;
		this.ref = ref;
		this.lev = lev;
		this.sunbun = sunbun;
		this.writedate = writedate;
		this.accdto = accdto;
		this.reviewdto = reviewdto;
		this.routedto = routedto;
		this.askdto = askdto;
		this.reportdto = reportdto;
		this.replydto = replydto;
	}

	// 공지사항
	public MainBbsDTO(int bbs_idx, int member_idx, String category, String status, String subject, String content,
			int ref, int lev, int sunbun, Date writedate) {
		super();
		this.bbs_idx = bbs_idx;
		this.member_idx = member_idx;
		this.category = category;
		this.status = status;
		this.subject = subject;
		this.content = content;
		this.ref = ref;
		this.lev = lev;
		this.sunbun = sunbun;
		this.writedate = writedate;
	}

	// 동행 게시판 글 작성
	public MainBbsDTO(int member_idx, String category, String status, String content, int ref, int lev, int sunbun) {
		super();
		this.member_idx = member_idx;
		this.category = category;
		this.status = status;
		this.content = content;
		this.ref = ref;
		this.lev = lev;
		this.sunbun = sunbun;
	}

	// 동행 게시판 리스트
	public MainBbsDTO(int bbs_idx, int member_idx, String category, String status, String content, int ref, int lev,
			int sunbun, Date writedate, String userid, AccDTO accdto, List<ReplyDTO> replylist) {
		super();
		this.bbs_idx = bbs_idx;
		this.member_idx = member_idx;
		this.category = category;
		this.status = status;
		this.content = content;
		this.ref = ref;
		this.lev = lev;
		this.sunbun = sunbun;
		this.writedate = writedate;
		this.userid = userid;
		this.accdto = accdto;
		this.replylist = replylist;
	}

	// 신고 테이블
	public MainBbsDTO(int bbs_idx, int member_idx, String category, String status, String subject, String content,
			int ref, int lev, int sunbun, Date writedate, ReportDTO reportdto) {
		super();
		this.bbs_idx = bbs_idx;
		this.member_idx = member_idx;
		this.category = category;
		this.status = status;
		this.subject = subject;
		this.content = content;
		this.ref = ref;
		this.lev = lev;
		this.sunbun = sunbun;
		this.writedate = writedate;
		this.reportdto = reportdto;
	}

	// 답변
	public MainBbsDTO(int bbs_idx, int member_idx, String category, String status, String subject, String content,
			int ref, int lev, int sunbun, Date writedate, ReplyDTO replydto) {
		super();
		this.bbs_idx = bbs_idx;
		this.member_idx = member_idx;
		this.category = category;
		this.status = status;
		this.subject = subject;
		this.content = content;
		this.ref = ref;
		this.lev = lev;
		this.sunbun = sunbun;
		this.writedate = writedate;
		this.replydto = replydto;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Date getWritedate() {
		return writedate;
	}

	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}

	public AccDTO getAccdto() {
		return accdto;
	}

	public void setAccdto(AccDTO accdto) {
		this.accdto = accdto;
	}

	public ReviewDTO getReviewdto() {
		return reviewdto;
	}

	public void setReviewdto(ReviewDTO reviewdto) {
		this.reviewdto = reviewdto;
	}

	public RouteDTO getRoutedto() {
		return routedto;
	}

	public void setRoutedto(RouteDTO routedto) {
		this.routedto = routedto;
	}

	public AskDTO getAskdto() {
		return askdto;
	}

	public void setAskdto(AskDTO askdto) {
		this.askdto = askdto;
	}

	public ReportDTO getReportdto() {
		return reportdto;
	}

	public void setReportdto(ReportDTO reportdto) {
		this.reportdto = reportdto;
	}

	public ReplyDTO getReplydto() {
		return replydto;
	}

	public void setReplydto(ReplyDTO replydto) {
		this.replydto = replydto;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public List<ReplyDTO> getReplylist() {
		return replylist;
	}

	public void setReplylist(List<ReplyDTO> replylist) {
		this.replylist = replylist;
	}

}
