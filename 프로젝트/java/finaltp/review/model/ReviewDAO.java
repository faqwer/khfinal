package finaltp.review.model;

import java.util.List;

import finaltp.mainBbs.model.MainBbsDTO;

public interface ReviewDAO {
	public int reviewWrite(MainBbsDTO mainDto, ReviewDTO reviewDto); // 후기 게시판 글 쓰기
	public List<ReviewDTO> reviewList(int cp, int ls, List<MainBbsDTO> mainList); // 후기 게시판 목록
	public ReviewDTO reviewContent(int bbs_idx); // 후기 게시글 본문
}
