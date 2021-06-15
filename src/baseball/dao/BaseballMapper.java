package baseball.dao;

import java.util.ArrayList;
import java.util.HashMap;

import baseball.vo.ItemInventoryVO;
import baseball.vo.ItemVO;
import baseball.vo.ItemequipinfoVO;
import baseball.vo.UserCharacterVO;
import baseball.vo.UserVO;

public interface BaseballMapper {

	//회원정보 저장
	public int join(UserVO vo);
	//아이디 중복 확인
	public UserVO getId(String id);
	//로그인시 아이디,비밀번호 확인
	public UserVO getPassword(HashMap<String, String> map);
	//타자 캐릭터 생성
	public int hitterCharacterCreate(UserCharacterVO vo);
	//투수 캐릭터 생성
	public int pitcherCharacterCreate(UserCharacterVO vo);
	//타자 캐릭터 목록 불러오기
	public ArrayList<UserCharacterVO> hitterSelect(String s);
	//투수 캐릭터 목록 불러오기
	public ArrayList<UserCharacterVO> pitcherSelect(String s);
	// 아이템 목록 불러오기 
	// 아이템 글러브 목록 불러오기
	public ArrayList<ItemVO> gloveSelect();
	// 아이템 옷 목록 불러오기 
	public ArrayList<ItemVO> clothSelect();
	// 아이템 헬멧 목록 불러오기 
	public ArrayList<ItemVO> helmetSelect();
	// 아이템 방망이 목록 불러오기 
	public ArrayList<ItemVO> batSelect();
	// 아이템 신발 목록 불러오기 
	public ArrayList<ItemVO> shoesSelect();
	// 아이템 음식 목록 불러오기 
	public ArrayList<ItemVO> foodSelect();
	// 내 아이템 목록 불러오기 
	public ArrayList<ItemInventoryVO> itemInfo();
	// 골드 업데이트 
	public ArrayList<UserCharacterVO> goldUpdate();
	
	public ArrayList<ItemequipinfoVO> itemequiphaveInfo();

}
