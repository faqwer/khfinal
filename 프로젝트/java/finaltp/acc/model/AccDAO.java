package finaltp.acc.model;

import java.util.List;

import finaltp.mainBbs.model.MainBbsDTO;

public interface AccDAO {

	public int accWrite(MainBbsDTO dto, String nation); // 동행게시글 작성
	public List<AccDTO> accList(int cp, int ls); // 동행 게시글 목록
	public int accStatusChange(int bbs_idx); // 동행 게시글 상태 변경
}
