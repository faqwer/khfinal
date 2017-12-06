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
	
	//회원 정보 수정
	public int memberEdit(String id,String ppwd,String npwd,String npwd2) {
		String user_pwd = sqlMap.selectOne("pwdCheck",id);
		System.out.println("user_pwd : " + user_pwd);
		System.out.println("ppwd : " + ppwd);
		if(ppwd==null||ppwd=="") {
			return NOT_PWD;
		}else {
			if(user_pwd.equals(ppwd)) {
				if(ppwd.equals(npwd)) {
					return PN_CONCORD;
				}else {
					if(npwd.equals(npwd2)) {
						HashMap map=new HashMap();
						map.put("id", id);
						map.put("npwd",npwd);
						int result=sqlMap.update("memberEdit",map);
						return EDIT_OK;
					}else {
						return NN_DISCORD;
					}
				}
			}else {
				return DISCORD;
			}
		}
	}
}
