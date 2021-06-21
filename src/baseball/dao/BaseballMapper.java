package baseball.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;

import baseball.vo.HofVO;
import baseball.vo.QuizScoreVO;
import baseball.vo.QuizVO;
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
	public ArrayList<UserVO> getId();
	//아이디 삭제하기
	public int deleteId(String userId);
	
	

}
