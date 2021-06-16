package baseball.dao;

import java.util.ArrayList;
import java.util.HashMap;

import baseball.vo.ItemHaveInfoVO;
import baseball.vo.ItemVO;
import baseball.vo.ItemequipinfoVO;
import baseball.vo.UserCharacterVO;
import baseball.vo.UserVO;

public interface BaseballMapper {

	//ȸ������ ����
	public int join(UserVO vo);
	//���̵� �ߺ� Ȯ��
	public UserVO getId(String id);
	//�α��ν� ���̵�,��й�ȣ Ȯ��
	public UserVO getPassword(HashMap<String, String> map);
	//Ÿ�� ĳ���� ����
	public int hitterCharacterCreate(UserCharacterVO vo);
	//���� ĳ���� ����
	public int pitcherCharacterCreate(UserCharacterVO vo);
	//Ÿ�� ĳ���� ��� �ҷ�����
	public ArrayList<UserCharacterVO> hitterSelect(String s);
	//���� ĳ���� ��� �ҷ�����
	public ArrayList<UserCharacterVO> pitcherSelect(String s);
	// ������ ��� �ҷ����� 
	// ������ �۷��� ��� �ҷ�����
	public ArrayList<ItemVO> gloveSelect();
	// ������ �� ��� �ҷ����� 
	public ArrayList<ItemVO> clothSelect();
	// ������ ��� ��� �ҷ����� 
	public ArrayList<ItemVO> helmetSelect();
	// ������ ����� ��� �ҷ����� 
	public ArrayList<ItemVO> batSelect();
	// ������ �Ź� ��� �ҷ����� 
	public ArrayList<ItemVO> shoesSelect();
	// ������ ���� ��� �ҷ����� 
	public ArrayList<ItemVO> foodSelect();
	// �� ������ ��� �ҷ����� 
	public ArrayList<ItemHaveInfoVO> itemInfo();
	// ��� ������Ʈ 
	public ArrayList<UserCharacterVO> goldUpdate();
	// ���� ���� ��� ������ ����
	public void itemehaveInfo(ItemHaveInfoVO vo);
	
	// ������ ���Ž� ��� �� �ɷ�ġ �����ϴ� �޼��� 
	public void purchaseBat(UserCharacterVO vo);
	public void purchaseHelmet(UserCharacterVO vo);
	public void purchaseShoes(UserCharacterVO vo);
	public void purchaseGlove(UserCharacterVO vo);
	public void purchaseCloth(UserCharacterVO vo);
	
	// ���� ���� ������ ��� �ҷ����� 
	public ArrayList<ItemequipinfoVO> itemequiphaveInfo(int purchaseItemId);
	
	//���ż� 1����
	int addQuantity(ItemHaveInfoVO ihivo1);
}
