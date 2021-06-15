package baseball.dao;

import java.util.ArrayList;
import java.util.HashMap;

import baseball.vo.HofVO;
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
	public int deleteCharacter(String loginId, int presentCharId);


	

}
