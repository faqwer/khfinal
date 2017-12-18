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
		
		int count = replyDao.commentWrite(dto);
		mav.setViewName("acc/accList");
		return mav;
	}
	
	@RequestMapping("/commentRevise.do")
	public ModelAndView commentRevise(@RequestParam("reply_idx") int reply_idx, @RequestParam("content") String content) {
		ModelAndView mav = new ModelAndView();
		int count = replyDao.commentRevise(reply_idx, content);
		mav.setViewName("acc/accList");
		return mav;
	}
	
	@RequestMapping("/commentDelete.do")
	public ModelAndView commentDelete(@RequestParam("reply_idx") int reply_idx) {
		ModelAndView mav = new ModelAndView();
		int count = replyDao.commentDelete(reply_idx);
		mav.setViewName("acc/accList");
		return mav;
	}
}
