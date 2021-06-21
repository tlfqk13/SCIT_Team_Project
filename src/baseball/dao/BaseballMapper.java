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
	public void goldUpdate(UserCharacterVO vo);
	// ���� ���� ��� ������ ����
	public void itemehaveInfo(ItemHaveInfoVO vo);
	//���� ĳ���� ���� �ҷ�����
	public UserCharacterVO getCharacter(HashMap<String, Object> map);
	//���� ���� Ÿ�� ��� �ҷ�����
	public ArrayList<HofVO> getHitterHofCharacter();
	//���� ���� ���� ��� �ҷ�����
	public ArrayList<HofVO> getPitcherHofCharacter();
	//�Ϲ� ���� ���� �� Ÿ�� ��� �ҷ�����
	public ArrayList<HofVO> getHitterKickCharacter();
	//�Ϲ� ���� ���� �� ���� ��� �ҷ�����
	public ArrayList<HofVO> getPitcherKickCharacter();
	//5���� ���Ŀ� ���𼱼� ������� �̵�
	public int hofCharacterInsert(UserCharacterVO vo);
	//���� ����
	public int deleteCharacter(HashMap<String, Object> map);
	//�Ϲ� �޽� 
	public int rest1(int result);
	//��� �޽�
	public int rest2(int result);
	//��� 
	public int play(int result);
	//���� ���� �ҷ�����
	public ArrayList<QuizVO> quiz();
	//���� ���� �Է�
	public int quizScoreInsert(QuizScoreVO scoreVo);
	//���� ���� �ҷ�����
	public ArrayList<QuizScoreVO> quizScoreAll();
	//���� ���� 10���� �ҷ�����
	public ArrayList<QuizScoreVO> quizScoreAll2(RowBounds rb);
	//���� ����� ���� �ҷ�����
	public ArrayList<QuizScoreVO> quizRanking();
	//���� ��ŷ ��� �ҷ�����
	public ArrayList<UserCharacterVO> pitcherRanking();
	//Ÿ�� ��ŷ ��� �ҷ�����
	public ArrayList<UserCharacterVO> hitterRanking();
	//���̵� ��� �ҷ�����
	public ArrayList<UserVO> getUser();
	//���̵� �����ϱ�
	public int deleteId(String userId);
	
	
	// ������ ���Ž� ��� �� �ɷ�ġ �����ϴ� �޼��� 
	public void purchaseBat(UserCharacterVO vo);
	public void purchaseHelmet(UserCharacterVO vo);
	public void purchaseShoes(UserCharacterVO vo);
	public void purchaseGlove(UserCharacterVO vo);
	public void purchaseCloth(UserCharacterVO vo);
	
	// ���� ���� ������ ��� �ҷ����� 
	public ArrayList<ItemEquipinfoVO> itemequiphaveInfo();
	
	//���ż� 1����
	int addQuantity(ItemHaveInfoVO ihivo1);
	
	public ArrayList<ItemHaveInfoVO> itemEquipHaveHemletInfo();
	public ArrayList<ItemHaveInfoVO> itemEquipHaveUniformInfo();
	public ArrayList<ItemHaveInfoVO> itemEquipHaveShoesInfo();
	public void test(ItemHaveInfoVO testVo);
	public void testVoDelete(ItemHaveInfoVO testVo);
	//���, �޽� ĳ���� ���� ������Ʈ
	public int matchRestUpdate(UserCharacterVO presentChar);
	//Ʈ���̴� ���� ��������
	public TrainingVO getTraining(HashMap<String, Object> map);
	//�Ʒ� �� db�� �����ϱ�
	public int trainingUpdate(TrainingVO trVo);
	//�Ʒ��� ���� ĳ���� ���� ������Ʈ
	public UserCharacterVO trainingCharacterUpdate(UserCharacterVO presentChar);
	
}
