package finaltp.controller;

import java.io.InputStreamReader;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConDataController {
	
	@RequestMapping("sky.do")
	public ModelAndView sky() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("condata/airport");
		return mav;
	}
	
	// 환율, 날씨 정보
	@RequestMapping("con.do")
	public ModelAndView contry(@RequestParam(value = "contry") String contry) throws Exception {
		/* 공통부분 */

		int num = contry.indexOf("/");

		String exchange = contry.substring(0, num);
		System.out.println(exchange);
		String weather = contry.substring(num + 1);
		System.out.println(weather);

		String exurl = "https://api.manana.kr/exchange/rate/KRW/" + exchange + ".json";
		String wturl = "http://api.openweathermap.org/data/2.5/weather?q=" + weather
				+ "&appid=0e1ce810761aee19fcd49790ece5c5ff";
		URL url1 = new URL(exurl);
		URL url2 = new URL(wturl);

		InputStreamReader isr1 = new InputStreamReader(url1.openConnection().getInputStream(), "UTF-8");
		JSONArray array_ex = (JSONArray) JSONValue.parse(isr1);

		JSONArray ar_ex = (JSONArray) array_ex;

		for (int i = 0; i < ar_ex.size(); i++) {
			JSONObject data = (JSONObject) ar_ex.get(i);
			System.out.println("국가코드: " + data.get("name").toString());
			System.out.println("환율: " + data.get("rate").toString());
		}

		InputStreamReader isr2 = new InputStreamReader(url2.openConnection().getInputStream(), "UTF-8");
		// JSONArray array_wt = (JSONArray)JSONValue.parse(isr2);
		JSONObject object_wt = (JSONObject) JSONValue.parse(isr2);

		JSONArray wt_weather = (JSONArray) object_wt.get("weather");
		JSONObject weahter_main = (JSONObject) wt_weather.get(0);
		String wt = weahter_main.get("main").toString();
		System.out.println("날씨 : " + weahter_main.get("main").toString());
		String icon = weahter_main.get("icon").toString();

		JSONObject wt_main = (JSONObject) object_wt.get("main");
		Double temp = (Double) wt_main.get("temp".toString());
		System.out.println("기온 : " + wt_main.get("temp".toString()));

		ModelAndView mav = new ModelAndView();
		mav.addObject("ar", ar_ex);
		mav.addObject("wt", wt);
		mav.addObject("icon", icon);
		mav.addObject("temp", temp);
		mav.setViewName("condata/condata");

		return mav;
	}

}
