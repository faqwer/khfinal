package finaltp.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

public class MemberDAOimple implements MemberDAO {

	private SqlSessionTemplate sqlMap;
	
	public MemberDAOimple(SqlSessionTemplate sqlMap) {
		super();
		this.sqlMap = sqlMap;
	}
	

	public int idCheck(String id) {
		int result = sqlMap.selectOne("memberId", id);
		return result;
	}
	
	public int join(MemberDTO dto) {
		int count = sqlMap.insert("memberJoin", dto);
		return count;
	}
	
	
	public int login(String id, String pwd) {
		String user_pwd = sqlMap.selectOne("memberPWD", id);
		System.out.println("비밀번호 : "+user_pwd);
		
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
	
	public int updatePwd(String id, String pwd) {
		Map data = new HashMap();
		data.put("id", id);
		data.put("new_pwd", pwd);
		
		int result = sqlMap.update("updatePwd", data);
		
		return result;
		
	}
	
	public int getIdx(String id) {
		int idx = sqlMap.selectOne("memberIdx", id);
		return idx;
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
