package finaltp.mainBbs.model;

import java.sql.Date;
import java.util.List;

import finaltp.acc.model.AccDTO;
import finaltp.ask.model.AskDTO;
import finaltp.recommend.model.RecommendDTO;
import finaltp.reply.model.ReplyDTO;
import finaltp.report.model.ReportDTO;
import finaltp.review.model.ReviewDTO;
import finaltp.route.model.RouteDTO;

public class MainBbsDTO {

	private int bbs_idx;
	private int writer_idx;
	private String category;
	private String status;
	private String subject;
	private String content;
	private Date writedate;
	private String nation;
	private String userid;
	private String reportid;
	private String profileImg;
	private String recommend;
	private int recommendNum;
	private String ask_status;
	private int ref;
	private int sunbun;
	private int lev;
	private String secret;

	private AccDTO accdto;
	private ReviewDTO reviewdto;
	private RouteDTO routedto;
	private AskDTO askdto;
	private ReportDTO reportdto;
	private ReplyDTO replydto;
	private RecommendDTO recommenddto;
	private List<ReplyDTO> replylist;

	public MainBbsDTO() {
		super();
	}

	public MainBbsDTO(int bbs_idx, int writer_idx, String category, String status, String subject, String content,
			Date writedate, String nation, String userid, String profileImg, String recommend, int recommendNum,
			String ask_status, int ref, int sunbun, int lev, String secret, AccDTO accdto, ReviewDTO reviewdto,
			RouteDTO routedto, AskDTO askdto, ReportDTO reportdto, ReplyDTO replydto, RecommendDTO recommenddto,
			List<ReplyDTO> replylist) {
		super();
		this.bbs_idx = bbs_idx;
		this.writer_idx = writer_idx;
		this.category = category;
		this.status = status;
		this.subject = subject;
		this.content = content;
		this.writedate = writedate;
		this.nation = nation;
		this.userid = userid;
		this.profileImg = profileImg;
		this.recommend = recommend;
		this.recommendNum = recommendNum;
		this.ask_status = ask_status;
		this.ref = ref;
		this.sunbun = sunbun;
		this.lev = lev;
		this.secret = secret;
		this.accdto = accdto;
		this.reviewdto = reviewdto;
		this.routedto = routedto;
		this.askdto = askdto;
		this.reportdto = reportdto;
		this.replydto = replydto;
		this.recommenddto = recommenddto;
		this.replylist = replylist;
	}

	// 공지사항
	public MainBbsDTO(int bbs_idx, int writer_idx, String category, String status, String subject, String content,
			int ref, int lev, int sunbun, Date writedate) {
		super();
		this.bbs_idx = bbs_idx;
		this.writer_idx = writer_idx;
		this.category = category;
		this.status = status;
		this.subject = subject;
		this.content = content;
		this.writedate = writedate;
	}

	// 게시판 글 작성
	public MainBbsDTO(int writer_idx, String category, String subject, String content) {
		super();
		this.writer_idx = writer_idx;
		this.category = category;
		this.subject = subject;
		this.content = content;
	}

	// 동행 게시판 리스트
	public MainBbsDTO(int bbs_idx, int writer_idx, String category, String status, String content, int ref, int lev,
			int sunbun, Date writedate, String userid, String profileImg, String recommend, AccDTO accdto,
			List<ReplyDTO> replylist) {
		super();
		this.bbs_idx = bbs_idx;
		this.writer_idx = writer_idx;
		this.category = category;
		this.status = status;
		this.content = content;
		this.writedate = writedate;
		this.userid = userid;
		this.profileImg = profileImg;
		this.recommend = recommend;
		this.accdto = accdto;
		this.replylist = replylist;
	}

	// 후기 게시판 리스트
	public MainBbsDTO(int bbs_idx, int writer_idx, String category, String status, String subject, String content,
			Date writedate, String userid, String profileImg, int recommendNum, ReviewDTO reviewdto) {
		super();
		this.bbs_idx = bbs_idx;
		this.writer_idx = writer_idx;
		this.category = category;
		this.status = status;
		this.subject = subject;
		this.content = content;
		this.writedate = writedate;
		this.userid = userid;
		this.profileImg = profileImg;
		this.recommendNum = recommendNum;
		this.reviewdto = reviewdto;
	}

	// 후기 게시판 본문
	public MainBbsDTO(int bbs_idx, int writer_idx, String category, String subject, String content, Date writedate,
			String userid, String profileImg, String recommend, ReviewDTO reviewdto, List<ReplyDTO> replylist) {
		super();
		this.bbs_idx = bbs_idx;
		this.writer_idx = writer_idx;
		this.category = category;
		this.subject = subject;
		this.content = content;
		this.writedate = writedate;
		this.userid = userid;
		this.profileImg = profileImg;
		this.recommend = recommend;
		this.reviewdto = reviewdto;
		this.replylist = replylist;
	}

	// 후기 게시판 수정
	public MainBbsDTO(int bbs_idx, int writer_idx, String category, String subject, String content,
			ReviewDTO reviewdto) {
		super();
		this.bbs_idx = bbs_idx;
		this.writer_idx = writer_idx;
		this.category = category;
		this.subject = subject;
		this.content = content;
		this.reviewdto = reviewdto;
	}

	// 1:1문의 리스트
	public MainBbsDTO(int bbs_idx, int writer_idx, String category, String status, String subject, String content,
			Date writedate, String userid, String ask_status, int ref, int sunbun, int lev, String secret) {
		super();
		this.bbs_idx = bbs_idx;
		this.writer_idx = writer_idx;
		this.category = category;
		this.status = status;
		this.subject = subject;
		this.content = content;
		this.writedate = writedate;
		this.userid = userid;
		this.ask_status = ask_status;
		this.ref = ref;
		this.sunbun = sunbun;
		this.lev = lev;
		this.secret = secret;
	}
	
	// 신고 목록
	public MainBbsDTO(int bbs_idx, int writer_idx, String category, String status, String subject, String content,
			String userid, String reportid, ReportDTO reportdto) {
		super();
		this.bbs_idx = bbs_idx;
		this.writer_idx = writer_idx;
		this.category = category;
		this.status = status;
		this.subject = subject;
		this.content = content;
		this.userid = userid;
		this.reportid = reportid;
		this.reportdto = reportdto;
	}

	// 신고 테이블
	public MainBbsDTO(int bbs_idx, int writer_idx, String category, String status, String subject, String content,
			Date writedate, ReportDTO reportdto) {
		super();
		this.bbs_idx = bbs_idx;
		this.writer_idx = writer_idx;
		this.category = category;
		this.status = status;
		this.subject = subject;
		this.content = content;
		this.writedate = writedate;
		this.reportdto = reportdto;
	}

	// 답변
	public MainBbsDTO(int bbs_idx, int writer_idx, String category, String status, String subject, String content,
			Date writedate, ReplyDTO replydto) {
		super();
		this.bbs_idx = bbs_idx;
		this.writer_idx = writer_idx;
		this.category = category;
		this.status = status;
		this.subject = subject;
		this.content = content;
		this.writedate = writedate;
		this.replydto = replydto;
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

	public Date getWritedate() {
		return writedate;
	}

	public void setWritedate(Date writedate) {
		this.writedate = writedate;
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

	public String getReportid() {
		return reportid;
	}

	public void setReportid(String reportid) {
		this.reportid = reportid;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public int getRecommendNum() {
		return recommendNum;
	}

	public void setRecommendNum(int recommendNum) {
		this.recommendNum = recommendNum;
	}

	public String getAsk_status() {
		return ask_status;
	}

	public void setAsk_status(String ask_status) {
		this.ask_status = ask_status;
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

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
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

	public RecommendDTO getRecommenddto() {
		return recommenddto;
	}

	public void setRecommenddto(RecommendDTO recommenddto) {
		this.recommenddto = recommenddto;
	}

	public ReplyDTO getReplydto() {
		return replydto;
	}

	public void setReplydto(ReplyDTO replydto) {
		this.replydto = replydto;
	}

	public List<ReplyDTO> getReplylist() {
		return replylist;
	}

	public void setReplylist(List<ReplyDTO> replylist) {
		this.replylist = replylist;
	}

}
