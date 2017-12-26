package finaltp.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

public class MemberDAOimple implements MemberDAO {

	private SqlSessionTemplate sqlMap;
	
	public MemberDAOimple(SqlSessionTemplate sqlMap) {
		super();
		this.sqlMap = sqlMap;
	}
	
	public int join(MemberDTO dto) {
		int count = sqlMap.insert("memberJoin", dto);
		return count;
	}
	
	public int login(String id, String pwd) {
		
		String user_pwd = sqlMap.selectOne("memberPWD", id);
		
		if(user_pwd==null||user_pwd=="") {
			return NOT_ID;
		} else {
			if (user_pwd.equals(pwd)) {
				return LOGIN_OK;
			} else {
				return NOT_PWD;
			}
		}
	}
	
	public String getName(String id) {
		String name=sqlMap.selectOne("memberName", id);
		return name;
	}
	
	public MemberDTO getUserInfo(int user_idx) {
		MemberDTO mdto=sqlMap.selectOne("memberINFOwithIdx",user_idx);
		return mdto;
	}

}
