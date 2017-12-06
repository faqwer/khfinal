package finaltp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import finaltp.route.model.RouteDAO;
import finaltp.route.model.RouteDTO;

@Controller
public class RouteController {

	@Autowired
	private RouteDAO routeDao;
	
	@RequestMapping(value="routeList.do",method=RequestMethod.GET)
	public ModelAndView goRouteList(@RequestParam(value="bbs_idx")int bbs_idx) {
		
		List<RouteDTO> list=routeDao.routeList(bbs_idx);
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("route/routeList");
		mav.addObject("bbs_idx", bbs_idx);
		mav.addObject("list", list);
		return mav;
	}
	
}
