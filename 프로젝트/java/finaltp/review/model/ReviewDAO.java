package finaltp.review.model;

import java.util.List;

import finaltp.mainBbs.model.MainBbsDTO;

public interface ReviewDAO {
	public int reviewWrite(MainBbsDTO mainDto, ReviewDTO reviewDto);
	public List<ReviewDTO> reviewList(int cp, int ls, List<MainBbsDTO> mainList); // 후기 게시글 목록
}
