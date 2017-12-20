package finaltp.ask.model;

import java.util.List;

import finaltp.mainBbs.model.MainBbsDTO;

public interface AskDAO {

	public List<MainBbsDTO> askList(int cp, int listSize, String status);
	public List<MainBbsDTO> adminAskList(int cp, int listSize, String status);
	public int mainAskWrite(MainBbsDTO dto);
	public int askWrite(MainBbsDTO dto, String secret);
	public AskDTO askContent(int bbs_idx);
	public int askReWrite(AskDTO dto);
	public void updateSun(int ref, int sunbun);
}
