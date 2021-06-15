package baseball.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

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
			ss.commit();
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

	//���� ĳ���� ���� ��������
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


	
	

}
