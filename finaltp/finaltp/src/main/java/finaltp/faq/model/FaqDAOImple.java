package finaltp.faq.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

public class FaqDAOImple implements FaqDAO {

	private SqlSessionTemplate sqlMap;

	public FaqDAOImple(SqlSessionTemplate sqlMap) {
		super();
		this.sqlMap = sqlMap;
	}

	public List<FaqDTO> faqList() {
		List<FaqDTO> dto = sqlMap.selectList("faqList");
		return dto;
	}
}
