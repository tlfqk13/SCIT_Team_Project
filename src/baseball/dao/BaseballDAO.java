package baseball.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import baseball.vo.ItemInventoryVO;
import baseball.vo.ItemVO;
import baseball.vo.ItemequipinfoVO;
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


	public ArrayList<ItemVO> gloveSelect() {
		SqlSession ss= null;
		ArrayList<ItemVO> result=null;
		try {
			ss=factory.openSession();
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			result=mapper.gloveSelect();
			ss.commit();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(ss!=null)ss.close();
		}
		return result;
	}


	public ArrayList<ItemVO> clothSelect() {
		SqlSession ss= null;
		ArrayList<ItemVO> result=null;
		
		try {
			ss=factory.openSession();
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			result=mapper.clothSelect();
			ss.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(ss!=null)ss.close();
		}
		return result;
	}


	public ArrayList<ItemVO> helmetSelect() {
		SqlSession ss= null;
		ArrayList<ItemVO> result=null;
		try {
			ss=factory.openSession();
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			result=mapper.helmetSelect();
			ss.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(ss!=null)ss.close();
		}
		return result;
	}


	public ArrayList<ItemVO> batSelect() {
		SqlSession ss= null;
		ArrayList<ItemVO> result=null;
		try {
			ss=factory.openSession();
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			result=mapper.batSelect();
			ss.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(ss!=null)ss.close();
		}
		return result;
	}


	public ArrayList<ItemVO> shoesSelect() {
		SqlSession ss= null;
		ArrayList<ItemVO> result=null;
		try {
			ss=factory.openSession();
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			result=mapper.shoesSelect();
			ss.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(ss!=null)ss.close();
		}
		return result;
	}


	public ArrayList<ItemVO> foodSelect() {
		SqlSession ss= null;
		ArrayList<ItemVO> result=null;
		try {
			ss=factory.openSession();
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			result=mapper.foodSelect();
			ss.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(ss!=null)ss.close();
		}
		return result;
	}
	

	public ArrayList<ItemInventoryVO> itemInfo() {
		SqlSession ss= null;
		ArrayList<ItemInventoryVO> result=null;
		try {
			ss=factory.openSession();
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			result=mapper.itemInfo();
			ss.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(ss!=null)ss.close();
		}
		return result;
	}
	
	public ArrayList<ItemequipinfoVO> itemequiphaveInfo() {
		SqlSession ss= null;
		ArrayList<ItemequipinfoVO> result=null;
		try {
			ss=factory.openSession();
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			result=mapper.itemequiphaveInfo();
			ss.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(ss!=null)ss.close();
		}
		return result;
	}
	
	public void test(int purchaseResult) {
		SqlSession ss = null;
		try {
			ss = factory.openSession(); 
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			mapper.test(purchaseResult);
			ss.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (ss != null) ss.close();
		}
	}


	public void purchaseBat(int purchaseBat) {
		SqlSession ss = null;
		try {
			ss = factory.openSession(); 
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			mapper.purchaseBat(purchaseBat);
			ss.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (ss != null) ss.close();
		}
		
	}
	
	public void purchaseHelmet(int purchaseHelmet) {
		SqlSession ss = null;
		try {
			ss = factory.openSession(); 
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			mapper.purchaseBat(purchaseHelmet);
			ss.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (ss != null) ss.close();
		}
		
	}


	public void purchaseShoes(int purchaseShoes) {
		SqlSession ss = null;
		try {
			ss = factory.openSession(); 
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			mapper.purchaseShoes(purchaseShoes);
			ss.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (ss != null) ss.close();
		}
		
	}


	public void purchaseGlove(int purchaseGlove) {
		SqlSession ss = null;
		try {
			ss = factory.openSession(); 
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			mapper.purchaseGlove(purchaseGlove);
			ss.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (ss != null) ss.close();
		}
		
	}
	
}
