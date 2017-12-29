package finaltp.follow.model;

import java.util.List;

import finaltp.member.model.MemberDTO;

public interface FollowDAO {
	public int follow(int follow_idx,int follower_idx);
	public int unfollow(int follow_idx,int follower_idx);
	public FollowDTO getfollow(int follow_idx,int follower_idx);
	public List<FollowDTO> getfollow_idxs(int user_idx);
	public List<FollowDTO> getfollower_idxs(int user_idx);
	public MemberDTO getfollowMemberInfo(int user_idx);
	public MemberDTO getfollowerMemberInfo(int user_idx);
	public int followCnt(int user_idx);
	public int followerCnt(int user_idx);
}
