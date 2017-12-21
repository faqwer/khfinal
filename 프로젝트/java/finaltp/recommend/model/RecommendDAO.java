package finaltp.recommend.model;

public interface RecommendDAO {

	public int recommendUp(RecommendDTO dto); // 추천 증가
	public int recommendDown(RecommendDTO dto); // 추천 삭제
	public String recommendCheck(RecommendDTO dto); // 추천 여부 체크
	public int recommendNum(int bbs_idx); // 추천수 확인
	public int updateRecommendNum(int recommendNum, int bbs_idx); // 추천수 메인 테이블 추가
}
