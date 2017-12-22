package finaltp.main.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

public class MainDAOImple implements MainDAO {

	private SqlSessionTemplate sqlMap;

	public MainDAOImple(SqlSessionTemplate sqlMap) {
		super();
		this.sqlMap = sqlMap;
	}
	
	public List<MainDTO> reviewRanking() {
		
		List<MainDTO> dto=sqlMap.selectList("reviewRanking");
		return dto;
	}
}
