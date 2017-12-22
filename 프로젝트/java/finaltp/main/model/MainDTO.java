package finaltp.main.model;

import finaltp.mainBbs.model.MainBbsDTO;
import finaltp.member.model.MemberDTO;
import finaltp.plan.model.PlanDTO;
import finaltp.recommend.model.RecommendDTO;
import finaltp.review.model.ReviewDTO;

public class MainDTO {
	
	private int bbs_idx;
	private int recommendnum;
	private String coverimg;
	private int readnum;
	private String name;
	private int writer_idx;
	private String subject;


	public MainDTO(int bbs_idx, int recommendnum, String coverimg, int readnum, String name, int writer_idx,
			String subject, int member_idx) {
		super();
		this.bbs_idx = bbs_idx;
		this.recommendnum = recommendnum;
		this.coverimg = coverimg;
		this.readnum = readnum;
		this.name = name;
		this.writer_idx = writer_idx;
		this.subject = subject;
		this.member_idx = member_idx;
	}

	private int member_idx;
//	private ReviewDTO reviewdto;
//	private PlanDTO	plandto;
//	private MainBbsDTO mainbbsdto;
//	private RecommendDTO recommenddto;
//	private MemberDTO memberdto;
	
	public MainDTO() {
		super();
	}

	public MainDTO(int bbs_idx, int recommendnum, String coverimg, int readnum, String name) {
		super();
		this.bbs_idx = bbs_idx;
		this.recommendnum = recommendnum;
		this.coverimg = coverimg;
		this.readnum = readnum;
		this.name = name;
	}

	public int getBbs_idx() {
		return bbs_idx;
	}

	public void setBbs_idx(int bbs_idx) {
		this.bbs_idx = bbs_idx;
	}

	public int getRecommendnum() {
		return recommendnum;
	}

	public void setRecommendnum(int recommendnum) {
		this.recommendnum = recommendnum;
	}

	public String getCoverimg() {
		return coverimg;
	}

	public void setCoverimg(String coverimg) {
		this.coverimg = coverimg;
	}

	public int getReadnum() {
		return readnum;
	}

	public void setReadnum(int readnum) {
		this.readnum = readnum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWriter_idx() {
		return writer_idx;
	}

	public void setWriter_idx(int writer_idx) {
		this.writer_idx = writer_idx;
	}

	public int getMember_idx() {
		return member_idx;
	}

	public void setMember_idx(int member_idx) {
		this.member_idx = member_idx;
	}
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}
