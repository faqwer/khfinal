package finaltp.controller;

import java.util.Calendar;

import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PlanController {

	@RequestMapping("/routesearch.do")
	public ModelAndView routeSearchForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("planner/routeSearch");
		return mav;
	}
	
	@RequestMapping("/planwriteForm.do")
	public ModelAndView plannerWrite(@RequestParam(value = "subject") String subject,
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
