package finaltp.follow.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import finaltp.member.model.MemberDTO;

public class FollowDAOImple implements FollowDAO {
	private SqlSessionTemplate sqlMap;

	public FollowDAOImple(SqlSessionTemplate sqlMap) {
		super();
		this.sqlMap = sqlMap;
	}
	public int follow(int follow_idx,int follower_idx) {
		Map map=new HashMap();
		map.put("follow_idx", follow_idx);
		map.put("follower_idx", follower_idx);
		int result=sqlMap.insert("follow", map);
		return result;
	}
	public int unfollow(int follow_idx,int follower_idx) {
		Map map=new HashMap();
		map.put("follow_idx", follow_idx);
		map.put("follower_idx", follower_idx);
		int result=sqlMap.delete("unfollow", map);
		return result;
	}
	public FollowDTO getfollow(int follow_idx,int follower_idx) {
		Map map=new HashMap();
		map.put("follow_idx", follow_idx);
		map.put("follower_idx", follower_idx);
		FollowDTO result=sqlMap.selectOne("getfollow", map);
		return result;
	}
	public List<FollowDTO> getfollow_idxs(int user_idx){
		List<FollowDTO> list=sqlMap.selectList("getfollows_idx", user_idx);
		return list;
	}
	public List<FollowDTO> getfollower_idxs(int user_idx){
		List<FollowDTO> list=sqlMap.selectList("getfollowers_idx", user_idx);
		return list;
	}
	public MemberDTO getfollowMemberInfo(int user_idx) {
		MemberDTO mdto=sqlMap.selectOne("showFollow",user_idx);
		return mdto;
	}
	public MemberDTO getfollowerMemberInfo(int user_idx) {
		MemberDTO mdto=sqlMap.selectOne("showFollower",user_idx);
		return mdto;
	}
	public int followCnt(int user_idx) {
		int count=sqlMap.selectOne("followCnt", user_idx);
		return count == 0 ? 1 : count;	
	}
	public int followerCnt(int user_idx) {
		int count=sqlMap.selectOne("followerCnt", user_idx);
		return count == 0?1:count;
	}
}
