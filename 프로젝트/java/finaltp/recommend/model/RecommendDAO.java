package finaltp.recommend.model;

public interface RecommendDAO {

	public int recommendUp(RecommendDTO dto); // 추천 증가
	public int recommendDown(RecommendDTO dto); // 추천 삭제
	public String recommendCheck(RecommendDTO dto); // 추천 여부 체크
	public int recommendNum(int bbs_idx);
}
