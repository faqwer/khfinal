﻿package finaltp.controller;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import finaltp.member.model.MemberDAO;
import finaltp.member.model.MemberDTO;
import finaltp.plan.model.PlanDAO;
import finaltp.plan.model.PlanDAOImple;
import finaltp.plan.model.PlanDTO;
import finaltp.preply.model.PReplyDAO;
import finaltp.preply.model.PReplyDTO;

@Controller
public class PlanController {

	@Autowired
	private PlanDAO planDao;
	
	@Autowired
	private MemberDAO memberDao;

	@Autowired
	private PReplyDAO preplyDao;
	
	@RequestMapping("/planList.do")
	public ModelAndView planList(@RequestParam(value = "cp", defaultValue = "1") int cp) {
		int listSize = 10;
		int pageSize = 5;
		int totalCnt = planDao.getPlanTotalCnt();
		String status="normal";
		ModelAndView mav = new ModelAndView();
		mav.setViewName("planner/planList");
		List<PlanDTO> plist = planDao.planList(cp, listSize, status);
		ArrayList<MemberDTO> mlist=new ArrayList<MemberDTO>();
		for(int i=0;i<plist.size();i++) {
			System.out.println(plist.get(i).getWriter_idx());
			MemberDTO mdto=memberDao.getUserInfo(plist.get(i).getWriter_idx());
			mlist.add(mdto);
		}
		String paging=finaltp.paging.PageModule.makePage("planList.do", totalCnt, listSize, pageSize, cp);
		mav.addObject("plist",plist);
		mav.addObject("mlist",mlist);
		mav.addObject("paging",paging);
		
		return mav;
	}
	
	@RequestMapping("/routesearch.do")
	public ModelAndView routeSearchForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("planner/routeSearch");
		return mav;
	}
	@RequestMapping("/addReply.do")
	public ModelAndView addReply(PReplyDTO prdto) {
		ModelAndView mav = new ModelAndView();
		int result=preplyDao.addPReply(prdto);
		List<PReplyDTO> list=preplyDao.getPReply(prdto.getPlanner_idx());
		for(int i=0;i<list.size();i++) {
			list.get(i).setUser_id(planDao.getUesrid(list.get(i).getUser_idx()));
		}
		mav.addObject("list",list);
		mav.setViewName("planner/reply");
		return mav;
	}
	@RequestMapping("/getReply.do")
	public ModelAndView getReply(@RequestParam(value="planner_idx")int planner_idx) {
		ModelAndView mav = new ModelAndView();
		List<PReplyDTO> list=preplyDao.getPReply(planner_idx);
		for(int i=0;i<list.size();i++) {
			list.get(i).setUser_id(planDao.getUesrid(list.get(i).getUser_idx()));
		}
		mav.addObject("list",list);
		mav.setViewName("planner/reply");
		return mav;
	}
	@RequestMapping("/getRecommend.do")
	public ModelAndView getRecommend(@RequestParam(value="planner_idx")int planner_idx) {
		ModelAndView mav = new ModelAndView();
		int cnt=planDao.getPlanRecommendCnt(planner_idx);
		mav.addObject("recommendCnt",cnt);
		mav.setViewName("planner/getRecommend");
		return mav;
	}
	
	@RequestMapping("/planwrite.do")
	public ModelAndView plannerWrite(MultipartHttpServletRequest multi, HttpSession session){
		ModelAndView mav=new ModelAndView();
		String id = "faqwer";
		/*String id = (String) session.getAttribute("userid");*/
        String path = "C:\\Users\\Hyun\\Desktop\\finaltp\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\finaltp\\img\\plancover\\"+id+"\\";
        
        String newFileName = "normal"; // 업로드 되는 파일명
        
        File dir = new File(path);
        if(!dir.isDirectory()){
            dir.mkdir();
        }
        Iterator<String> files = multi.getFileNames();
        while(files.hasNext()){
            String uploadFile = files.next();
            newFileName="";             
            MultipartFile mFile = multi.getFile(uploadFile);
            String fileName = mFile.getOriginalFilename();
            System.out.println("실제 파일 이름 : " +fileName);
            newFileName = System.currentTimeMillis()+"."
                    +fileName.substring(fileName.lastIndexOf(".")+1);
            
            try {
                mFile.transferTo(new File(path+newFileName));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
		/*int writer_idx=(Integer)session.getAttribute("useridx");*/
		int writer_idx=22;
		String thema=multi.getParameter("thema");
		String subject=multi.getParameter("subject");
		java.sql.Date startday=java.sql.Date.valueOf(multi.getParameter("startdaytext"));
		int day=Integer.parseInt(multi.getParameter("day"));
		String plan_story_subject=multi.getParameter("plan_story_subject");
		String plan_story_content=multi.getParameter("plan_story_content");
		String schedule=multi.getParameter("schedule");
		String coverimg;
		if(newFileName.equals("normal")) {
			coverimg="/finaltp/img/plancover/"+newFileName+".png";
		}else {
			coverimg="/finaltp/img/plancover/"+id+'/'+newFileName;
		}
		PlanDTO pdto=new PlanDTO(writer_idx, thema, subject, startday, day, coverimg, plan_story_subject, plan_story_content, schedule);
		
		int planner_idx=planDao.planWrite(pdto);
		mav.addObject("planner_idx", planner_idx);
		mav.setViewName("planner/planSaveResult");
		return mav;
	}
	@RequestMapping("/recommendImgCk.do")
	public ModelAndView recommendImgCk(HttpSession session
			,@RequestParam(value="planner_idx")int planner_idx
			,@RequestParam(value="writer_idx")int writer_idx
			,@RequestParam(value="user_idx")int user_idx) {
		ModelAndView mav=new ModelAndView();
		int result=planDao.ckRecommend(planner_idx,user_idx);
		String img="";
		if(result==0) {
			int set=planDao.addRecommend(planner_idx, writer_idx, user_idx);
			img="/finaltp/img/follow2.PNG";
		}else {
			int set=planDao.delRecommend(planner_idx, writer_idx, user_idx);
			img="/finaltp/img/follow1.PNG";
		}
		mav.setViewName("planner/recommendImgCk");
		mav.addObject("img",img);
		return mav;
	}
	@RequestMapping("/recommendImg.do")
	public ModelAndView recommendImg(HttpSession session
			,@RequestParam(value="planner_idx")int planner_idx
			,@RequestParam(value="user_idx")int user_idx) {
		ModelAndView mav=new ModelAndView();
		int result=planDao.ckRecommend(planner_idx,user_idx);
		String img="";
		if(result==0) {
			img="/finaltp/img/follow1.PNG";
		}else {
			img="/finaltp/img/follow2.PNG";
		}
		mav.setViewName("planner/recommendImgCk");
		mav.addObject("img",img);
		return mav;
	}
	@RequestMapping("/planContent.do")
	public ModelAndView planContentForm(HttpSession session,@RequestParam(value="planner_idx")int planner_idx) {
		ModelAndView mav=new ModelAndView();
		PlanDTO pdto=planDao.getPlanContent(planner_idx);
		mav.addObject("pdto",pdto);
		java.sql.Date startday=pdto.getStartday();
		String startdaystr= startday.toString();
		String[] startdayarr=startdaystr.split("-");
		mav.addObject("year",startdayarr[0]);
		mav.addObject("month",startdayarr[1]);
		mav.addObject("day",startdayarr[2]);
		mav.setViewName("planner/planContent");
		int planRecommend=planDao.getPlanRecommendCnt(pdto.getPlanner_idx());
		mav.addObject("planRecommend",planRecommend);
		/*String user_idx_s=(String)session.getAttribute("useridx");
		int user_idx=-1;
		if(user_idx_s==null||user_idx_s=="") {
			user_idx=-1;
		}else {
			user_idx=Integer.parseInt(user_idx_s);
		}*/
		int user_idx=45;
		mav.addObject("user_idx",user_idx);
		MemberDTO mdto=memberDao.getUserInfo(pdto.getWriter_idx());
		mav.addObject("mdto",mdto);
		return mav;
	}
	@RequestMapping("/planDelete.do")
	public ModelAndView plandelete(@RequestParam(value="planner_idx")int planner_idx) {
		ModelAndView mav=new ModelAndView();
		int result=planDao.planDelete(planner_idx);
		mav.setViewName("indexMain");
		return mav;
	}
	@RequestMapping("/planUpdataForm.do")
	public ModelAndView planUpdataForm(@RequestParam(value="planner_idx")int planner_idx) {
		ModelAndView mav=new ModelAndView();
		PlanDTO pdto=planDao.getPlanContent(planner_idx);
		mav.addObject("pdto",pdto);
		java.sql.Date startday=pdto.getStartday();
		String startdaystr= startday.toString();
		String[] startdayarr=startdaystr.split("-");
		mav.addObject("year",startdayarr[0]);
		mav.addObject("month",startdayarr[1]);
		mav.addObject("day",startdayarr[2]);
		mav.setViewName("planner/planUpdata");
		return mav;
	}
	@RequestMapping("/planUpdata.do")
	public ModelAndView planUpdata(MultipartHttpServletRequest multi, HttpSession session){
		ModelAndView mav=new ModelAndView();
		String id = "faqwer";
		/*String id = (String) session.getAttribute("userid");*/
        String path = "C:\\Users\\Hyun\\Desktop\\finaltp\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\finaltp\\img\\plancover\\"+id+"\\";
        
        String newFileName = "no"; // 업로드 되는 파일명
        
        File dir = new File(path);
        if(!dir.isDirectory()){
            dir.mkdir();
        }
        Iterator<String> files = multi.getFileNames();
        while(files.hasNext()){
            String uploadFile = files.next();
            newFileName="";             
            MultipartFile mFile = multi.getFile(uploadFile);
            String fileName = mFile.getOriginalFilename();
            System.out.println("실제 파일 이름 : " +fileName);
            newFileName = System.currentTimeMillis()+"."
                    +fileName.substring(fileName.lastIndexOf(".")+1);
            
            try {
                mFile.transferTo(new File(path+newFileName));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

		int planner_idx=Integer.parseInt(multi.getParameter("planner_idx"));
		/*int writer_idx=(Integer)session.getAttribute("useridx");*/
		int writer_idx=22;
		String thema=multi.getParameter("thema");
		String subject=multi.getParameter("subject");
		java.sql.Date startday=java.sql.Date.valueOf(multi.getParameter("startdaytext"));
		int day=Integer.parseInt(multi.getParameter("day"));
		String plan_story_subject=multi.getParameter("plan_story_subject");
		String plan_story_content=multi.getParameter("plan_story_content");
		String schedule=multi.getParameter("schedule");
		String coverimg;
		if(newFileName.equals("no")) {
			coverimg=newFileName;
		}else {
			coverimg="/finaltp/img/plancover/"+id+'/'+newFileName;
		}
		PlanDTO pdto=new PlanDTO(planner_idx, writer_idx, thema, subject, startday, day, coverimg, plan_story_subject, plan_story_content, schedule);
		int update=planDao.planUpdate(pdto);
		mav.setViewName("planner/planSaveResult");
		mav.addObject("planner_idx", planner_idx);
		return mav;
	}
	@RequestMapping("/planwriteForm.do")
	public ModelAndView plannerWriteForm(@RequestParam(value = "subject") String subject,
			@RequestParam(value = "startday_year") String startday_year,
			@RequestParam(value = "startday_month") String startday_month,
			@RequestParam(value = "startday_day") String startday_day,
			@RequestParam(value = "thema") String thema) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("planner/planWrite");
		mav.addObject("subject",subject);
		mav.addObject("startday_year",startday_year);
		mav.addObject("startday_month",startday_month);
		mav.addObject("startday_day",startday_day);
		mav.addObject("thema",thema);
		return mav;
	}
	
	@RequestMapping("/calendar.do")
	public ModelAndView calendar(@RequestParam(value = "year", defaultValue = "-1") int _year,
			@RequestParam(value = "month", defaultValue = "-1") int _month) {
		ModelAndView mav = new ModelAndView();
		
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1;

		if (_year != -1) {
			year = _year;
		}
		if (_month != -1) {
			month = _month;
		}
		now.set(year, month - 1, 1); // 출력할 년도, 월로 설정

		year = now.get(Calendar.YEAR); // 변화된 년, 월
		month = now.get(Calendar.MONTH) + 1;
		
		int end = now.getActualMaximum(Calendar.DAY_OF_MONTH); // 해당월의 마지막 날짜
		int w = now.get(Calendar.DAY_OF_WEEK); // 1~7(일~토)
		mav.addObject("year",year);
		mav.addObject("month",month);
		mav.addObject("end",end);
		mav.addObject("w",w);
		mav.setViewName("planner/calendar");
		return mav;
	}
}