package baseball.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import baseball.vo.HofVO;
import baseball.vo.QuizScoreVO;
import baseball.vo.QuizVO;
import baseball.vo.UserCharacterVO;
import baseball.vo.UserVO;


public class BaseballDAO {
	private SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();
	
	
	//ȸ������ ����
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

	
	//���̵� �ߺ� Ȯ��
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



	//�α��ν� ���̵�, ��й�ȣ Ȯ��
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

	
	//Ÿ�� ĳ���� ����
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
	
	
	//���� ĳ���� ����
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
	
	//Ÿ�� ĳ���� ��� �ҷ�����
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


	//���� ĳ���� ��� �ҷ�����
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

	//���� ĳ���� ���� �ҷ�����
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

	//���� ���� Ÿ�� ��� �ҷ�����
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

	//���� ���� ���� ��� �ҷ�����
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

	//�Ϲ� ���� ���� �� Ÿ�� ��� �ҷ�����
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
	
	//�Ϲ� ���� ���� �� ���� ��� �ҷ�����
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
	
	
	
	//5���� ���Ŀ� ���𼱼� ������� �̵�
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


	
	//���� ����
	public int deleteCharacter(String userId, int characterId) {
		SqlSession ss = null;
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("characterId", characterId);
		try {
			ss = factory.openSession(); 
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			result = mapper.deleteCharacter(map);
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

	
	//���� ���� �ҷ�����
	public ArrayList<QuizVO> quiz() {
		SqlSession ss = null;
		ArrayList<QuizVO> result = null;
		try {
			ss = factory.openSession(); 
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			result = mapper.quiz();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (ss != null) ss.close();
		}
		return result;
	}

	//���� ���� �Է�
	public int quizScroeInsert(QuizScoreVO scoreVo) {
		SqlSession ss = null;
		int result = 0;
		try {
			ss = factory.openSession(); 
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			result = mapper.quizScoreInsert(scoreVo);
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

	//���� ���� �ҷ�����
	public ArrayList<QuizScoreVO> quizScroeAll() {
		SqlSession ss = null;
		ArrayList<QuizScoreVO> result = null;
		try {
			ss = factory.openSession(); 
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			result = mapper.quizScoreAll();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (ss != null) ss.close();
		}
		return result;
	}

	//���� ���� 10���� �ҷ�����
	public ArrayList<QuizScoreVO> quizScroeAll2(int startRecore, int countPerPage) {
		SqlSession ss = null;
		ArrayList<QuizScoreVO> result = null;
		try {
			ss = factory.openSession(); 
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			RowBounds rb = new RowBounds(startRecore, countPerPage);
			result = mapper.quizScoreAll2(rb);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (ss != null) ss.close();
		}
		return result;
	}

	//���� ����� ���� �ҷ�����
	public ArrayList<QuizScoreVO> quizRanking() {
		SqlSession ss = null;
		ArrayList<QuizScoreVO> result = null;
		try {
			ss = factory.openSession(); 
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			result = mapper.quizRanking();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (ss != null) ss.close();
		}
		return result;
	}

	//���� ��ŷ ��� �ҷ�����
	public ArrayList<UserCharacterVO> pitcherRanking() {
		SqlSession ss = null;
		ArrayList<UserCharacterVO> result = null;
		try {
			ss = factory.openSession(); 
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			result = mapper.pitcherRanking();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (ss != null) ss.close();
		}
		return result;
	}
	
	//Ÿ�� ��ŷ ��� �ҷ�����
		public ArrayList<UserCharacterVO> hitterRanking() {
			SqlSession ss = null;
			ArrayList<UserCharacterVO> result = null;
			try {
				ss = factory.openSession(); 
				BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
				result = mapper.hitterRanking();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				if (ss != null) ss.close();
			}
			return result;
		}

		//���̵� ��� �ҷ�����
		public ArrayList<UserVO> getId() {
			SqlSession ss = null;
			ArrayList<UserVO> result = null;
			try {
				ss = factory.openSession(); 
				BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
				result = mapper.getId();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				if (ss != null) ss.close();
			}
			return result;
		}

		//���̵� ����
		public int deleteId(String userId) {
			SqlSession ss = null;
			int result = 0;
			try {
				ss = factory.openSession(); 
				BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
				result = mapper.deleteId(userId);
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
