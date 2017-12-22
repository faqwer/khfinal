package finaltp.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {


   // 사진 저장 테스트
   @RequestMapping(value = "pimg.do", method = RequestMethod.GET)
   public ModelAndView pimg() {
      ModelAndView mav = new ModelAndView();
      mav.setViewName("test/pimg");

      return mav;
   }


   
   @RequestMapping(value = "pimg.do", method = RequestMethod.POST)
   public ModelAndView save_pimg(HttpServletRequest request, HttpSession session) throws Exception {

	   String id = (String) session.getAttribute("userid");
      System.out.println("사진저장 : "+id); // 사용자 아이디 

      String resultMsg ="";
      MultipartHttpServletRequest mr;
         mr = (MultipartHttpServletRequest) request;
         MultipartFile imgfile = mr.getFile("file");

         final String imgName = imgfile.getOriginalFilename().trim();   // 사진 이름 저장
         String filepath = "C:\\eclipse\\finaltp\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\finaltp" + "/UPF/";   // 사진을 넣어줄 폴더 경로 + 사용자 아이디는 아직 안 넣음
         System.out.println("imgName="+imgName);
         // 파일 확장자 체크
         int pathPoing = imgName.lastIndexOf(".");
         String filePoint = imgName.substring(pathPoing + 1, imgName.length());
         String fileType = filePoint.toLowerCase();

         if (!fileType.equals("jpg") && !fileType.equals("png") && !fileType.equals("jpeg")) {
            resultMsg = "이미지 파일만 업로드 가능합니다.";
         }
         

         File f = new File(filepath+id); // filepath에 해당 아이디의 폴더 존재 확인 
         System.out.println(f.exists());
         if (!f.exists()) {
            System.out.println("폴더없음");
            f.mkdirs(); // 폴더 생성
         }
         // 폴더가 존재한다면 폴더 내의 파일들 없애는 구문 추가 > 폴더 비움
         String finalImg = filepath+id+File.separator+imgName;   // filepath+사용자 아이디(폴더명)+/+사진 이름 > 총경로
         // 디비에 업데이트 문 사용해서 프로필 이미지 경로 finalImg 로 수정해주기.
         
         System.out.println(finalImg+"---------");
         imgfile.transferTo(new File(finalImg));   // 사진 파일 폴더에 추가
         resultMsg = "정상적으로 업로드 하였습니다.";

         ModelAndView mav = new ModelAndView();
         mav.setViewName("test/pimg_result");
         mav.addObject("resultMsg", resultMsg);
         return mav;
   }
}