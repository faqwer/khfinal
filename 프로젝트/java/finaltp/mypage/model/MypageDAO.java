package finaltp.mypage.model;

import java.util.ArrayList;
import java.util.List;

import finaltp.acc.model.AccDTO;
import finaltp.mainBbs.model.MainBbsDTO;
import finaltp.member.model.MemberDTO;
import finaltp.plan.model.PlanDTO;
import finaltp.review.model.ReviewDTO;

public interface MypageDAO {

	public static final int NOT_ID = 1;// 사용자가 변경 불가능한 상수
	public static final int NOT_PWD = 2;
	public static final int LOGIN_OK = 3;
	public static final int ERROR = -1;
	public static final int DISCORD = 4;
	public static final int PN_CONCORD = 5;
	public static final int NN_DISCORD = 6;
	public static final int EDIT_OK = 7;
	public static final int CONCORD = 8;
	public static final int IMG_CHANGE = 9;
	
	// mypage
	public MemberDTO getInfo(int member_idx);
	public int memberOut(int member_idx);
	public ArrayList<PlanDTO> getMybookmark(int cp, int ls,int user_idx);
	public int memberEdit(String id,String ppwd,String npwd,String npwd2, String fileName);
	public List<MainBbsDTO> myReviewMainBbsList(int cp, int ls, String category,int w_idx);
	public List<ReviewDTO> myReviewList(int cp, int ls, List<MainBbsDTO> mainList);//마이페이지 내가쓴리뷰
	public List<MainBbsDTO> myAccMainBbsList(int cp, int ls, String category,int w_idx);
	public List<AccDTO> myAccList(int cp, int ls, List<MainBbsDTO> mainList);//마이페이지 내가쓴동행
	public List<PlanDTO> myPlanList(int cp, int ls,int w_idx);
	public int planTotalCnt();
	public int myBookmarkCnt(int user_idx);
	public int myPlannerCnt(int user_idx);
	
}
