package finaltp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import finaltp.mainBbs.model.MainBbsDAO;
import finaltp.reply.model.ReplyDAO;
import finaltp.reply.model.ReplyDTO;

@Controller
public class ReplyController {

	@Autowired
	private MainBbsDAO mainBbsDao;
	
	@Autowired
	private ReplyDAO replyDao;
	
	@RequestMapping("/commentWrite.do")
	public ModelAndView commentWrite(HttpSession session, @RequestParam("content") String content,
			@RequestParam("bbs_idx") int bbs_idx) {
		ModelAndView mav = new ModelAndView();
		ReplyDTO dto = null;
		String writerid = (String) session.getAttribute("userid");
		int writer_idx = mainBbsDao.getBbsWriterIdx(bbs_idx);
		int user_idx = mainBbsDao.getWriterMemberIdx(writerid);
		int commentCount = replyDao.getCommentCount(bbs_idx);
		
		if(commentCount == 0) {
			dto = new ReplyDTO(writer_idx, bbs_idx, user_idx, content, 0, 0, 0);
		} else {
			int ref = replyDao.getMaxRef(bbs_idx) + 1;
			dto = new ReplyDTO(writer_idx, bbs_idx, user_idx, content, ref, 0, 0);
		}
		
		int result = replyDao.commentWrite(dto);
		mav.setViewName("acc/accList");
		return mav;
	}
}
