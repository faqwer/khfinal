package finaltp.acc.model;

import org.mybatis.spring.SqlSessionTemplate;

public class AccDAOImple implements AccDAO {

	private SqlSessionTemplate sqlMap;

	public AccDAOImple(SqlSessionTemplate sqlMap) {
		super();
		this.sqlMap = sqlMap;
	}

	
	public int accWrite(String nation, String content) {
		
		return 0;
	}
}
