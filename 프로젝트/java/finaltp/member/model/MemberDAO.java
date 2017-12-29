package finaltp.member.model;
<<<<<<< HEAD

=======
>>>>>>> 88e5cf3a11eb5b8e2cb655ce7060b2d48d908acf

public interface MemberDAO {

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

	public int join(MemberDTO dto); 
	
	public int idCheck(String id);
	
	public int login(String id, String pwd);
	public int updatePwd(String id, String pwd);
	
	public int getIdx(String id);
	public String getName(String id);
<<<<<<< HEAD
<<<<<<< HEAD



=======
	public MemberDTO getUserInfo(int user_idx);
>>>>>>> 02b1868adc5fbcb3219d735b2ad072a50a787d45
=======
	

	public MemberDTO getUserInfo(int user_idx);

>>>>>>> 88e5cf3a11eb5b8e2cb655ce7060b2d48d908acf
}
