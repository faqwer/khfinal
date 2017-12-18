package finaltp.report.model;

import org.mybatis.spring.SqlSessionTemplate;

public class ReportDAOImple implements ReportDAO{

	private SqlSessionTemplate sqlMap;

	public ReportDAOImple(SqlSessionTemplate sqlMap) {
		super();
		this.sqlMap = sqlMap;
	}

	// 신고자 idx 가져오는 메서드
	public int getUserIdx(String userid) {
		int user_idx = sqlMap.selectOne("getUserIdx", userid);
		return user_idx;
	}
	
	// 신고 테이블 추가
	public int report(ReportDTO dto) {
		int result = sqlMap.insert("reportAdd", dto);
		return result;
	}
	
}
