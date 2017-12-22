package finaltp.acc.model;

import java.util.List;

import finaltp.mainBbs.model.MainBbsDTO;
import finaltp.review.model.ReviewDTO;

public interface AccDAO {

	public int accWrite(MainBbsDTO dto, String nation); // 동행게시글 작성
	public List<AccDTO> accList(int cp, int ls, List<MainBbsDTO> mainList); // 동행 게시글 목록
	public int accStatusChange(int bbs_idx); // 동행 게시글 상태 변경
	public int accRevise(int bbs_idx, String content); // 동행 게시글 수정

}
