package baseball.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import baseball.vo.HofVO;
import baseball.vo.UserCharacterVO;
import baseball.vo.UserVO;


public class BaseballDAO {
	private SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();
	
	
	//회원정보 저장
	public int join(UserVO vo) {
		SqlSession ss = null;
		int result = 0;
		try {
			ss = factory.openSession(); 
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			result = mapper.join(vo);
			ss.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (ss != null) ss.close();
		}
		return result;
	}

	
	//아이디 중복 확인
	public UserVO getId(String id) {
		SqlSession ss = null;
		UserVO vo = null;
		try {
			ss = factory.openSession(); 
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			vo = mapper.getId(id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (ss != null) ss.close();
		}
		return vo;
	}



	//로그인시 아이디, 비밀번호 확인
	public UserVO getPassword(String userId, String password) {
		SqlSession ss = null;
		UserVO vo = null;
		HashMap<String, String> map = new HashMap<>();
		map.put("userId", userId);
		map.put("password", password);
		try {
			ss = factory.openSession(); 
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			vo = mapper.getPassword(map);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (ss != null) ss.close();
		}
		return vo;
	}

	
	//타자 캐릭터 생성
	public int hitterCharacterCreate(UserCharacterVO vo) {
		SqlSession ss = null;
		int result = 0;
		try {
			ss = factory.openSession(); 
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			result = mapper.hitterCharacterCreate(vo);
			ss.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (ss != null) ss.close();
		}
		return result;
	}
	
	
	//투수 캐릭터 생성
	public int pitcherCharacterCreate(UserCharacterVO vo) {
		SqlSession ss = null;
		int result = 0;
		try {
			ss = factory.openSession(); 
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			result = mapper.pitcherCharacterCreate(vo);
			ss.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (ss != null) ss.close();
		}
		return result;
	}
	
	//타자 캐릭터 목록 불러오기
	public ArrayList<UserCharacterVO> hitterSelect(String s) {
		SqlSession ss = null;
		ArrayList<UserCharacterVO> result = null;
		try {
			ss = factory.openSession(); 
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			result = mapper.hitterSelect(s);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (ss != null) ss.close();
		}
		return result;
	}


	//투수 캐릭터 목록 불러오기
	public ArrayList<UserCharacterVO> pitcherSelect(String s) {
		SqlSession ss = null;
		ArrayList<UserCharacterVO> result = null;
		try {
			ss = factory.openSession(); 
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			result = mapper.pitcherSelect(s);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (ss != null) ss.close();
		}
		return result;
	}

	//현재 캐릭터 정보 불러오기
	public UserCharacterVO getCharacter(String loginId, int presentCharId) {
		SqlSession ss = null;
		UserCharacterVO result = null;
		HashMap<String, Object> map = new HashMap<>();
		map.put("loginId", loginId);
		map.put("characterId", presentCharId);
		try {
			ss = factory.openSession(); 
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			result = mapper.getCharacter(map);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (ss != null) ss.close();
		}
		return result;
	}

	//명예의 전당 타자 목록 불러오기
	public ArrayList<HofVO> getHitterHofCharacter() {
		SqlSession ss = null;
		ArrayList<HofVO> result = null;
		try {
			ss = factory.openSession(); 
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			result = mapper.getHitterHofCharacter();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (ss != null) ss.close();
		}
		return result;
	}

	//명예의 전당 투수 목록 불러오기
	public ArrayList<HofVO> getPitcherHofCharacter() {
		SqlSession ss = null;
		ArrayList<HofVO> result = null;
		try {
			ss = factory.openSession(); 
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			result = mapper.getPitcherHofCharacter();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (ss != null) ss.close();
		}
		return result;
	}

	//일반 은퇴 선수 중 타자 목록 불러오기
	public ArrayList<HofVO> getHitterKickCharacter() {
		SqlSession ss = null;
		ArrayList<HofVO> result = null;
		try {
			ss = factory.openSession(); 
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			result = mapper.getHitterKickCharacter();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (ss != null) ss.close();
		}
		return result;
	}
	
	//일반 은퇴 선수 중 투수 목록 불러오기
	public ArrayList<HofVO> getPitcherKickCharacter() {
		SqlSession ss = null;
		ArrayList<HofVO> result = null;
		try {
			ss = factory.openSession(); 
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			result = mapper.getPitcherKickCharacter();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (ss != null) ss.close();
		}
		return result;
	}
	
	
	
	//5년차 이후에 은퇴선수 목록으로 이동
	public int hofCharacterInsert(UserCharacterVO vo) {
		SqlSession ss = null;
		int result = 0;
		try {
			ss = factory.openSession(); 
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			result = mapper.hofCharacterInsert(vo);
			ss.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (ss != null) ss.close();
		}
		return result;		
	}


	
	//선수 삭제
	public int deleteCharacter(String loginId, int presentCharId) {
		SqlSession ss = null;
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("loginId", loginId);
		map.put("presentCharId", presentCharId);
		try {
			ss = factory.openSession(); 
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			result = mapper.deleteCharacter(loginId, presentCharId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (ss != null) ss.close();
		}
		return result;
	}



	
	

}
