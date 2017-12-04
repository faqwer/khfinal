package finaltp.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		System.out.println("++++"+id+","+pwd);
		String user_pwd = sqlMap.selectOne("memberPWD", id);
		System.out.println(user_pwd);
		
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
	
	
	
	//mypage 정보 가져오기
	public List<MemberDTO> getInfo(String id){
		List<MemberDTO> list = sqlMap.selectList("memberINFO", id);
		return list;
	}
	
	//회원 탈퇴
	public int memberOut(String id) {
		int count = sqlMap.delete("memberOut", id);
		return count;
	}

}
