package baseball.dao;

import java.util.ArrayList;
import java.util.HashMap;

import baseball.vo.ItemHaveInfoVO;
import baseball.vo.ItemVO;
import baseball.vo.ItemEquipInventoryVO;
import baseball.vo.ItemEquipinfoVO;
import org.apache.ibatis.session.RowBounds;

import baseball.vo.HofVO;
import baseball.vo.QuizScoreVO;
import baseball.vo.QuizVO;
import baseball.vo.TrainingVO;
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
	public ArrayList<ItemHaveInfoVO> itemInfo();
	// 골드 업데이트 
	public void goldUpdate(UserCharacterVO vo);
	// 내가 가진 장비 아이템 정보
	public void itemehaveInfo(ItemHaveInfoVO vo);
	//현재 캐릭터 정보 불러오기
	public UserCharacterVO getCharacter(HashMap<String, Object> map);
	//명예의 전당 타자 목록 불러오기
	public ArrayList<HofVO> getHitterHofCharacter();
	//명예의 전당 투수 목록 불러오기
	public ArrayList<HofVO> getPitcherHofCharacter();
	//일반 은퇴 선수 중 타자 목록 불러오기
	public ArrayList<HofVO> getHitterKickCharacter();
	//일반 은퇴 선수 중 투수 목록 불러오기
	public ArrayList<HofVO> getPitcherKickCharacter();
	//5년차 이후에 은퇴선수 목록으로 이동
	public int hofCharacterInsert(UserCharacterVO vo);
	//선수 삭제
	public int deleteCharacter(HashMap<String, Object> map);
	//일반 휴식 
	public int rest1(int result);
	//고급 휴식
	public int rest2(int result);
	//경기 
	public int play(int result);
	//퀴즈 문제 불러오기
	public ArrayList<QuizVO> quiz();
	//퀴즈 점수 입력
	public int quizScoreInsert(QuizScoreVO scoreVo);
	//퀴즈 점수 불러오기
	public ArrayList<QuizScoreVO> quizScoreAll();
	//퀴즈 점수 10개씩 불러오기
	public ArrayList<QuizScoreVO> quizScoreAll2(RowBounds rb);
	//퀴즈 정답률 순위 불러오기
	public ArrayList<QuizScoreVO> quizRanking();
	//투수 랭킹 목록 불러오기
	public ArrayList<UserCharacterVO> pitcherRanking();
	//타자 랭킹 목록 불러오기
	public ArrayList<UserCharacterVO> hitterRanking();
	//아이디 목록 불러오기
	public ArrayList<UserVO> getUser();
	//아이디 삭제하기
	public int deleteId(String userId);
	
	
	// 아이템 구매시 골드 와 능력치 갱신하는 메서드 
	public void purchaseBat(UserCharacterVO vo);
	public void purchaseHelmet(UserCharacterVO vo);
	public void purchaseShoes(UserCharacterVO vo);
	public void purchaseGlove(UserCharacterVO vo);
	public void purchaseCloth(UserCharacterVO vo);
	
	// 내가 가진 아이템 목록 불러오기 
	public ArrayList<ItemEquipinfoVO> itemequiphaveInfo();
	
	//구매수 1증가
	int addQuantity(ItemHaveInfoVO ihivo1);
	
	public ArrayList<ItemHaveInfoVO> itemEquipHaveHemletInfo();
	public ArrayList<ItemHaveInfoVO> itemEquipHaveUniformInfo();
	public ArrayList<ItemHaveInfoVO> itemEquipHaveShoesInfo();
	public void test(ItemHaveInfoVO testVo);
	public void testVoDelete(ItemHaveInfoVO testVo);
	//경기, 휴식 캐릭터 정보 업데이트
	public int matchRestUpdate(UserCharacterVO presentChar);
	//트레이닝 정보 가져오기
	public TrainingVO getTraining(HashMap<String, Object> map);
	//훈련 후 db에 적용하기
	public int trainingUpdate(TrainingVO trVo);
	//훈련후 현재 캐릭터 정보 업데이트
	public UserCharacterVO trainingCharacterUpdate(UserCharacterVO presentChar);
	
}
