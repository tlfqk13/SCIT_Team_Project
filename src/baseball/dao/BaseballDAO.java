package baseball.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import baseball.vo.ItemHaveInfoVO;
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
	

	public ArrayList<ItemHaveInfoVO> itemInfo() {
		SqlSession ss= null;
		ArrayList<ItemHaveInfoVO> result=null;
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
	
	public void itemehaveInfo(ItemHaveInfoVO ihivo, ItemHaveInfoVO ihivo1) {
		SqlSession ss= null;
		try {
			ss=factory.openSession();
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			mapper.itemehaveInfo(ihivo);
			ss.commit();
			mapper.addQuantity(ihivo1);
			ss.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(ss!=null)ss.close();
		}

	}
	public void purchaseBat(UserCharacterVO vo) {
		SqlSession ss = null;
		try {
			ss = factory.openSession(); 
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			mapper.purchaseBat(vo);
			ss.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (ss != null) ss.close();
		}
		
	}
	
	public void purchaseHelmet(UserCharacterVO vo) {
		SqlSession ss = null;
		try {
			ss = factory.openSession(); 
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			mapper.purchaseHelmet(vo);
			ss.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (ss != null) ss.close();
		}
		
	}


	public void purchaseShoes(UserCharacterVO vo) {
		SqlSession ss = null;
		try {
			ss = factory.openSession(); 
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			mapper.purchaseShoes(vo);
			ss.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (ss != null) ss.close();
		}
		
	}


	public void purchaseGlove(UserCharacterVO vo) {
		SqlSession ss = null;
		try {
			ss = factory.openSession(); 
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			mapper.purchaseGlove(vo);
			ss.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (ss != null) ss.close();
		}
		
	}


	public void purchaseCloth(UserCharacterVO vo) {
		SqlSession ss = null;
		try {
			ss = factory.openSession(); 
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			mapper.purchaseCloth(vo);
			ss.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (ss != null) ss.close();
		}
		
	}


	public ArrayList<ItemequipinfoVO> itemequiphaveInfo(int purchaseId) {
		SqlSession ss= null;
		ArrayList<ItemequipinfoVO> result=null;
		try {
			ss=factory.openSession();
			BaseballMapper mapper = ss.getMapper(BaseballMapper.class);
			result=mapper.itemequiphaveInfo(purchaseId);
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


}
