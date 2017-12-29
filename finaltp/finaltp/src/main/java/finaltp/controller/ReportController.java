package finaltp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import finaltp.report.model.ReportDAO;
import finaltp.report.model.ReportDTO;

@Controller
public class ReportController {
	
	@Autowired
	private ReportDAO reportDao;

	@RequestMapping("/reportForm.do")
	public String reportForm() {
		return "report/reportForm";
	}

	@RequestMapping("/report.do")
	public ModelAndView report(@RequestParam("bbs_idx") int bbs_idx, @RequestParam("writer_idx") int writer_idx,
			@RequestParam("userid") String userid, @RequestParam("reportReasonSelect") String reportReasonSelect,
			@RequestParam("reportReasonTextArea") String reportReasonTextArea) {
		ModelAndView mav = new ModelAndView();
		int user_idx = reportDao.getUserIdx(userid);
		String reason = reportReasonSelect + "<br>" + reportReasonTextArea;
		ReportDTO dto = new ReportDTO(bbs_idx, writer_idx, user_idx, reason, "no");
		int result = reportDao.report(dto);
		String msg = result > 0 ? "신고 완료" : "신고 실패";
		mav.addObject("msg", msg);
		mav.setViewName("report/msg");
		return mav;
	}
}
