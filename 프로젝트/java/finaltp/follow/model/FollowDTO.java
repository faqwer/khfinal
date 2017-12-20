package finaltp.follow.model;

public class FollowDTO {

	private int follow_idx;
	private int follower_idx;
	
	public FollowDTO() {
		super();
	}

	public FollowDTO(int follow_idx, int follower_idx) {
		super();
		this.follow_idx = follow_idx;
		this.follower_idx = follower_idx;
	}

	public int getFollow_idx() {
		return follow_idx;
	}

	public void setFollow_idx(int follow_idx) {
		this.follow_idx = follow_idx;
	}

	public int getFollower_idx() {
		return follower_idx;
	}

	public void setFollower_idx(int follower_idx) {
		this.follower_idx = follower_idx;
	}
	
	
}
