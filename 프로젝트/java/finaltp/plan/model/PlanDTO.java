package finaltp.plan.model;

import java.sql.Date;

public class PlanDTO {
	
	private int planner_idx;
	private int member_idx;
	private String status;
	private String thema;
	private String subject;
	private Date startday;
	private int day;
	private int readnum; 
	private Date writedate; 
<<<<<<< HEAD
	private String recommend;
=======
>>>>>>> 02b1868adc5fbcb3219d735b2ad072a50a787d45
	private String checklist;
	private String coverimg;
	private String plan_story_subject;
	private String plan_story_content;
	private String schedule;
	public PlanDTO() {
		super();
	}
<<<<<<< HEAD
	public PlanDTO(int planner_idx, int member_idx, String status, String thema, String subject, Date startday, int day,
			int readnum, Date writedate, String recommend, String checklist, String coverimg, String plan_story_subject,
=======
	public PlanDTO(int writer_idx, String thema, String subject, Date startday, int day, String coverimg,
			String plan_story_subject, String plan_story_content, String schedule) {
		super();
		this.writer_idx = writer_idx;
		this.thema = thema;
		this.subject = subject;
		this.startday = startday;
		this.day = day;
		this.coverimg = coverimg;
		this.plan_story_subject = plan_story_subject;
		this.plan_story_content = plan_story_content;
		this.schedule = schedule;
	}
	public PlanDTO(int planner_idx, int writer_idx, String thema, String subject, Date startday, int day,
			String coverimg, String plan_story_subject, String plan_story_content, String schedule) {
		super();
		this.planner_idx = planner_idx;
		this.writer_idx = writer_idx;
		this.thema = thema;
		this.subject = subject;
		this.startday = startday;
		this.day = day;
		this.coverimg = coverimg;
		this.plan_story_subject = plan_story_subject;
		this.plan_story_content = plan_story_content;
		this.schedule = schedule;
	}
	public PlanDTO(int planner_idx, int writer_idx, String status, String thema, String subject, Date startday, int day,
			int readnum, Date writedate, String checklist, String coverimg, String plan_story_subject,
>>>>>>> 02b1868adc5fbcb3219d735b2ad072a50a787d45
			String plan_story_content, String schedule) {
		super();
		this.planner_idx = planner_idx;
		this.member_idx = member_idx;
		this.status = status;
		this.thema = thema;
		this.subject = subject;
		this.startday = startday;
		this.day = day;
		this.readnum = readnum;
		this.writedate = writedate;
		this.recommend = recommend;
		this.checklist = checklist;
		this.coverimg = coverimg;
		this.plan_story_subject = plan_story_subject;
		this.plan_story_content = plan_story_content;
		this.schedule = schedule;
	}
	public int getPlanner_idx() {
		return planner_idx;
	}
	public void setPlanner_idx(int planner_idx) {
		this.planner_idx = planner_idx;
	}
<<<<<<< HEAD
	public int getMember_idx() {
		return member_idx;
	}
	public void setMember_idx(int member_idx) {
		this.member_idx = member_idx;
=======
	public int getWriter_idx() {
		return writer_idx;
	}
	public void setWriter_idx(int writer_idx) {
		this.writer_idx = writer_idx;
>>>>>>> 02b1868adc5fbcb3219d735b2ad072a50a787d45
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getThema() {
		return thema;
	}
	public void setThema(String thema) {
		this.thema = thema;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Date getStartday() {
		return startday;
	}
	public void setStartday(Date startday) {
		this.startday = startday;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getReadnum() {
		return readnum;
	}
	public void setReadnum(int readnum) {
		this.readnum = readnum;
	}
	public Date getWritedate() {
		return writedate;
	}
	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}
<<<<<<< HEAD
	public String getRecommend() {
		return recommend;
	}
	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
=======
>>>>>>> 02b1868adc5fbcb3219d735b2ad072a50a787d45
	public String getChecklist() {
		return checklist;
	}
	public void setChecklist(String checklist) {
		this.checklist = checklist;
	}
	public String getCoverimg() {
		return coverimg;
	}
	public void setCoverimg(String coverimg) {
		this.coverimg = coverimg;
	}
	public String getPlan_story_subject() {
		return plan_story_subject;
	}
	public void setPlan_story_subject(String plan_story_subject) {
		this.plan_story_subject = plan_story_subject;
	}
	public String getPlan_story_content() {
		return plan_story_content;
	}
	public void setPlan_story_content(String plan_story_content) {
		this.plan_story_content = plan_story_content;
	}
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	
}
