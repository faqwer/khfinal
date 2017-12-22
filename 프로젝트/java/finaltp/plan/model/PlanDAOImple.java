package finaltp.plan.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

public class PlanDAOImple implements PlanDAO {

	private SqlSessionTemplate sqlMap;

	public PlanDAOImple(SqlSessionTemplate sqlMap) {
		super();
		this.sqlMap = sqlMap;
	}
}
