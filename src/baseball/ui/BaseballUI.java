package baseball.ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import baseball.dao.BaseballDAO;
import baseball.vo.HofVO;
import baseball.vo.TrainerVO;
import baseball.vo.UserCharacterVO;
import baseball.vo.UserVO;



public class BaseballUI {
	private BaseballDAO dao = new BaseballDAO();
	Scanner keyin = new Scanner(System.in);
	private int presentCharId = 0;
	private String loginId = null;

	
	public BaseballUI() {
		while (true) {
			int m = 0;
			loginMenuPrint();				//�޴� ���
			try {
				m = keyin.nextInt();
			}
			catch (InputMismatchException e) {
				keyin.nextLine();
				System.out.println("�ٽ� �Է��ϼ���.");
				continue;
			}
			switch(m) {					//�� ��ȣ�� ���� ó��, 0�� ���� ����
			case 1: join();		break;
			case 2: login();	break;
			case 3: hof();	break;
			case 0: System.out.println("������ �����մϴ�."); return;
			default:
			}
		}
	}
	
	void loginMenuPrint() {
		System.out.println("[ ������ ���� Ű��� �߱��� ]");
		System.out.println("1.	ȸ������");
		System.out.println("2.	�α���");
		System.out.println("3. 	���𼱼�");
		System.out.println("0.	����");
		System.out.print("����>	");
	}
	
	public void join() {
		System.out.println("[ ȸ�� ���� ]");
		String userId, password;
		

		int cnt = 0;
		System.out.print("���̵� : ");
		userId = keyin.next();

		UserVO v = dao.getId(userId);
		if (v != null) {
			System.out.println("�̹� ������� ���̵��Դϴ�.");
			return;
		}
		
		System.out.print("��й�ȣ : ");
		password = keyin.next();
		
		UserVO vo = new UserVO(userId, password);
		cnt = dao.join(vo);
		
		if( cnt == 0) {
			System.out.println("���� ����");
		}
		else {
			System.out.println("���� �Ϸ� �Ǿ����ϴ�");
		}
		
	}
	
	
	public void login() {
		
		String userId, password;
		
		System.out.print("���̵� : ");
		userId = keyin.next();
		UserVO v = dao.getId(userId);
		//���̵�� ȸ������ �˻�
		if (v == null) {
			System.out.println("�ش� ���̵��� ȸ���� �����ϴ�.");
			return;
		}
		System.out.print("��й�ȣ : ");
		password = keyin.next();
		//���̵�� ����� �´��� Ȯ��
		v = dao.getPassword(userId, password);
		if (v == null) {
			System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
			return;
		}
		else {
			loginId = userId;
		}
		
		
		//ĳ���� ���� �޴�
		if (loginId == null) {
			System.out.println("�α��� ���ּ���.");
			return;
		}
		else if (loginId != null) {
			while (true) {
				int m = 0;
				characterMenuPrint();				
				try {
					m = keyin.nextInt();
				}
				catch (InputMismatchException e) {
					keyin.nextLine();
					System.out.println("�ٽ� �Է��ϼ���.");
					continue;
				}
				switch(m) {
					case 1: characterCreate(); 	break;
					case 2: characterSelect();	break;
					case 3: characterDrop();	break;
					case 0: return;
					default: 
				}
				
			}
		}
		
		
		
	}
	
	public void characterMenuPrint() {
		System.out.println("[ ���� �޴� ]");
		System.out.println("1.	���� ����");
		System.out.println("2.	���� ����");
		System.out.println("0.	���� ����");
		System.out.print("����>	");
	}
	
	public void characterCreate() {
		String name, className;
		
		int cnt = 0;
		System.out.println("[ ĳ���� ���� ]");
		System.out.print("ĳ���� �̸� : ");
		name = keyin.next();
		while (true) {
			System.out.print("Ÿ�� or ���� > ");
			className = keyin.next();
			if (className.equals("Ÿ��") || className.equals("����")) {
				break;
			}
			else {
				System.out.println("�ٽ� �Է��ϼ���.");
				continue;
			}
		}
		
		UserCharacterVO vo = new UserCharacterVO(loginId, name, className);
		if (className.equals("Ÿ��")) {
			cnt = dao.hitterCharacterCreate(vo);
		}
		else if (className.equals("����")) {
			cnt = dao.pitcherCharacterCreate(vo);
		}
		
		if (cnt != 0) {
			System.out.println("ĳ���Ͱ� �����Ǿ����ϴ�.");
		}
		else {
			System.out.println("ĳ���� ������ �����߽��ϴ�.");
		}
	}
	
	
	public void characterSelect() {
		while (true) {
			int m = 0;
			selectMenuPrint();
			try {
				m = keyin.nextInt();
			}
			catch (InputMismatchException e) {
				keyin.nextLine();
				System.out.println("�ٽ� �Է��ϼ���.");
				continue;
			}
			switch(m) {					
			case 1: pitcherSelect(); 	break;
			case 2: hitterSelect(); 	break;
			case 0: return;
			default:
			}
		}
	}
		
	public void characterDrop() {
		
	}
	
	public void selectMenuPrint() {
		System.out.println("[ ĳ���� ���� ]");
		System.out.println("1.	����");
		System.out.println("2.	Ÿ��");
		System.out.println("0.	����");
		System.out.print("����>	");
	}
	
	public void pitcherSelect() {
		int num = 0;
		System.out.println("[ ���� ĳ���� ��� ]");
		
		//����ĳ���� ��� ���
		ArrayList<UserCharacterVO> list = dao.pitcherSelect(loginId);
		
		if (list.size() == 0) {
			System.out.println("ĳ���Ͱ� �����ϴ�.");
			return;
		}
		else {
			UserCharacterVO vo = null;
			System.out.printf("%-5s \t %-10s \t %-10s \t %-10s \t %-10s \t %-10s \t %-10s \n", "��ȣ","ĳ���� �̸�","BallSpeed","BallControl","Mentality","������", "����");
			for (int i = 0; i < list.size(); i++) {
				vo = list.get(i);
				System.out.printf("%-5d \t %-10s \t %-10d \t %-10d \t %-10d \t %-10d \t %-10d \n", (i+1), vo.getCharacterName(),vo.getPitcherBallSpeed(),vo.getPitcherBallControl(),vo.getPitcherMentality(),vo.getGold(), vo.getYear());
			}
			while (true) {
				System.out.print("��ȣ ����> ");
				try {
					num = keyin.nextInt();
					break;
				}
				catch (InputMismatchException e) {
					keyin.nextLine();
					System.out.println("�ٽ� �Է��ϼ���.");
					continue;
				}
			}
			
			presentCharId = list.get(num-1).getCharacterId();
			mainMenu();
		}
	}
	
	public void hitterSelect() {
		int num = 0;
		System.out.println("[ Ÿ�� ĳ���� ��� ]");
		
		//Ÿ��ĳ���� ��� ���
		ArrayList<UserCharacterVO> list = dao.hitterSelect(loginId);

		if (list.size() == 0) {
			System.out.println("ĳ���Ͱ� �����ϴ�.");
			return;
		}
		else {
			UserCharacterVO vo = null;
			System.out.printf("%-5s \t %-10s \t %-10s \t %-10s \t %-10s \t %-10s \t %-10s \n", "��ȣ", "ĳ���� �̸�","BallSpeed","BallControl","Mentality","������", "����");
			for (int i = 0; i < list.size(); i++) {
				vo = list.get(i);
				System.out.printf("%-5d \t %-10s \t %-10d \t %-10d \t %-10d \t %-10d \t %-10d \n", (i+1), vo.getCharacterName(),vo.getPitcherBallSpeed(),vo.getPitcherBallControl(),vo.getPitcherMentality(),vo.getGold(), vo.getYear());
			}
			while (true) {
				System.out.print("��ȣ ����> ");
				try {
					num = keyin.nextInt();
					break;
				}
				catch (InputMismatchException e) {
					keyin.nextLine();
					System.out.println("�ٽ� �Է��ϼ���.");
					continue;
				}
			}
			presentCharId = list.get(num-1).getCharacterId();
			mainMenu();
		}
		
	}
	
	
	public void mainMenu() {
		int m = 0;
		
		if (loginId != null && presentCharId != 0) {
			while (true) {
				mainMenuPrint();
				try {
					m = keyin.nextInt();
				}
				catch (InputMismatchException e) {
					keyin.nextLine();
					System.out.println("�ٽ� �Է��ϼ���.");
					continue;
				}	
				switch(m) {					
				case 1: storeMenu();			break;
				case 2: trainingMenu();			break;
				case 3: characterInfoMenu();	break;
				case 4: match(); 			break;
				case 5: rest(); 			break;
				case 0: System.out.println("������ �����մϴ�."); System.exit(0);
				default:
				}
			}
		}
		else {
			return;
		}
		
		
	}
	
	public void mainMenuPrint() {
		System.out.println("[ ������ ���� Ű��� �߱��� ]");
		System.out.println("1.	����");
		System.out.println("2.	�Ʒ�");
		System.out.println("3. 	ĳ���� ����");
		System.out.println("4. 	���");
		System.out.println("5. 	�޽�");
		System.out.println("0.	����");
		System.out.print("����>	");
	}

	
	public void storeMenu() {
		while (true) {
			storeMenuPrint();
			int m = keyin.nextInt();	
			switch(m) {					
//			case 1: clothSelect();			break;
//			case 2: helmetSelect();			break;
//			case 3: batSelect();			break;
//			case 4: shoesSelect(); 			break;
//			case 5: foodSelect(); 			break;
			case 0: return;
			default:
			}
		}
	}
	
	
	public void storeMenuPrint() {
		System.out.println("[ ���� ]");
		System.out.println("1.	��");
		System.out.println("2.	���");
		System.out.println("3. 	��Ʈ");
		System.out.println("4. 	�Ź�");
		System.out.println("5. 	����");
		System.out.println("0.	�ڷΰ���");
		System.out.print("����>	");
	}
	
	public void clothSelect() {
		
	}
	public void helmetSelect() {
		
	}
	public void batSelect() {
		
	}
	public void shoesSelect() {
		
	}
	public void foodSelect() {
		
	}
	
	public void trainingMenu() {
		while (true) {
			System.out.println("��ġ ���");
			
			//��ġ ��� ���/Ÿ�ڸ� Ÿ����ġ�� ������ ������ ������ġ�� ������ �ϱ�.
//			TrainerVO tvo = dao.getTrainer(m, presentChar.get);
//			for(int i = 0; i < tvo.size(); i++) {
//				System.out.println(presentChar.getClassid + " ��ġ");
//				System.out.printf("%-4d \t %-3 ");
//			}
			
			System.out.print("����> ");
			int m = keyin.nextInt();
			return;

		}
	}
	
	public void characterInfoMenu() {
		while (true) {
			characterInfoMenuPrint();
			int m = keyin.nextInt();	
			switch(m) {					
			case 1: statInfo();			break;
//			case 2: itemInfo();			break;
			case 0: return;
			default:
			}
		}
	}
	
	public void characterInfoMenuPrint() {
		System.out.println("[ ĳ���� ���� ]");
		System.out.println("1.	����");
		System.out.println("2.	������");
		System.out.println("0.	�ڷΰ���");
		System.out.print("����>	");
	}
	
	public void statInfo() {
		UserCharacterVO presentChar = dao.getCharacter(loginId, presentCharId);
		System.out.printf("%-25s \n", "[ ����â ]");
		System.out.println();
		System.out.printf("%s", " ---------------------------------------- \n");
		System.out.printf("%-3s \t %-10s \t %-4s \t %-5s \t %s \n","|", "ĳ���� �̸�", "|", presentChar.getCharacterName(), "|");
		System.out.printf("%-3s \t %-10s \t %-4s \t %-5s \t %s \n","|", "Ŭ����", "|", presentChar.getClassName(), "|");
		System.out.printf("%-3s \t %-10s \t %-4s \t %-5d \t %s \n","|", "����", "|", presentChar.getYear(), "|");
		System.out.printf("%-3s \t %-10s \t %-4s \t %-5d \t %s \n","|", "�ý���", "|", presentChar.getAllStat() , "|");
		if (presentChar.getClassName().equals("Ÿ��")) {
			System.out.printf("%-3s \t %-10s \t %-4s \t %-5d \t %s \n","|", "�Ŀ�", "|", presentChar.getHitterPower(), "|" );
			System.out.printf("%-3s \t %-10s \t %-4s \t %-5d \t %s \n","|", "Ÿ��", "|", presentChar.getHitterHit() , "|");
			System.out.printf("%-3s \t %-10s \t %-4s \t %-5d \t %s \n","|", "�ַ�", "|", presentChar.getHitterRunSpeed() , "|");

		}
		else if (presentChar.getClassName().equals("����")) {
			System.out.printf("%-3s \t %-10s \t %-4s \t %-5d \t %s \n","|", "�����ǵ�", "|", presentChar.getPitcherBallSpeed() , "|");
			System.out.printf("%-3s \t %-10s \t %-4s \t %-5d \t %s \n","|", "����Ʈ��", "|", presentChar.getPitcherBallControl(), "|");
			System.out.printf("%-3s \t %-10s \t %-4s \t %-5d \t %s \n","|", "���ŷ�", "|", presentChar.getPitcherMentality(), "|" );
		}
		System.out.printf("%-3s \t %-10s \t %-4s \t %-5d \t %s \n","|", "ü�� ", "|", presentChar.getHealth(), "|");
		System.out.printf("%-3s \t %-10s \t %-4s \t %-5d \t %s \n","|", "�ൿ��", "|", presentChar.getActive(), "|");
		System.out.printf("%s", " ---------------------------------------- \n");
		
	}
	
	public void itemInfo() {
		
	}
	
	public void goldInfo() {
		
	}

	
	public void match() {
		
	}
	
	public void rest() {
		
	}
	
	public void hof() {
		int m = 0;
		while (true) {
			hofMenuPrint();
			try {
				m = keyin.nextInt();
			}
			catch (InputMismatchException e) {
				keyin.nextLine();
				System.out.println("�ٽ� �Է��ϼ���.");
				continue;
			}	
			switch(m) {					
			case 1: hitterHofCharacter();		break;
			case 2: pitcherHofCharacter();		break;
			case 3: hitterKickCharacter();		break;
			case 4: pitcherKickCharacter(); 	break;
			case 0: System.out.println("���θ޴��� ���ư��ϴ�.");; 	break;
			default:
			}
		}
		
		
	}
	
	public void hofMenuPrint() {
		System.out.println("[ ���𼱼� ]");
		System.out.println("1.	���� ����(Ÿ��)");
		System.out.println("2.	���� ����(����)");
		System.out.println("3. 	�Ϲ� ���� ����(Ÿ��)");
		System.out.println("4. 	�Ϲ� ���� ����(����)");
		System.out.println("0. 	�ڷ�");
		System.out.print("����>	");
	}
	
	public void hitterHofCharacter() {
		ArrayList<HofVO> list = null;
		HofVO vo = null;
		System.out.printf("%35s \n", "[ ���� ���� ]");
		System.out.println();
		System.out.printf("%33s \n", "- Ÿ�� -");
		System.out.printf("%s", " ---------------------------------------------------------------- \n");
		System.out.printf("%s  %-10s \t %-4s \t %-12s \t %-4s \t %-5s \t %s \n","|   ", "����ID", "|", "ĳ���� �̸�" , "|", "�ý���", "|");
		System.out.printf("%s", " ---------------------------------------------------------------- \n");		
		list = dao.getHitterHofCharacter();
		for (int i = 0; i < list.size(); i++) {
			vo = list.get(i);
			System.out.printf("%s  %-10s \t %-4s \t %-12s \t %-4s \t %-5d \t %s \n","|   ", vo.getUserid(), "|", vo.getCharacterName() , "|", vo.getAllStat(), "|");

		}
		System.out.printf("%s", " ---------------------------------------------------------------- \n");		

	}
	
	public void pitcherHofCharacter() {
		ArrayList<HofVO> list = null;
		HofVO vo = null;
		System.out.printf("%35s \n", "[ ���� ���� ]");
		System.out.println();
		System.out.printf("%33s \n", "- ���� -");
		System.out.printf("%s", " ---------------------------------------------------------------- \n");
		System.out.printf("%-3s \t %-10s \t %-4s \t %-12s \t %-4s \t %-5s \t %s \n","|", "����ID", "|", "ĳ���� �̸�" , "|", "�ý���", "|");
		System.out.printf("%s", " ---------------------------------------------------------------- \n");		
		list = dao.getPitcherHofCharacter();
		for (int i = 0; i < list.size(); i++) {
			vo = list.get(i);
			System.out.printf("%-3s \t %-10s \t %-4s \t %-12s \t %-4s \t %-5d \t %s \n","|", vo.getUserid(), "|", vo.getCharacterName() , "|", vo.getAllStat(), "|");
		}
		System.out.println();
		System.out.printf("%s", " ---------------------------------------------------------------- \n");		

	}
	
	public void hitterKickCharacter() {
		ArrayList<HofVO> list = null;
		HofVO vo = null;
		System.out.printf("%35s \n", "- �Ϲ� ���� ���� -");
		System.out.println();
		System.out.printf("%33s \n", "- Ÿ�� -");
		System.out.printf("%s", " ---------------------------------------------------------------- \n");
		System.out.printf("%s  %-10s \t %-4s \t %-12s \t %-4s \t %-5s \t %s \n","|   ", "����ID", "|", "ĳ���� �̸�" , "|", "�ý���", "|");
		list = dao.getHitterKickCharacter();
		for (int i = 0; i < list.size(); i++) {
			vo = list.get(i);
			System.out.printf("%-3s \t %-10s \t %-4s \t %-12s \t %-4s \t %-5d \t %s \n","|", vo.getUserid(), "|", vo.getCharacterName() , "|", vo.getAllStat(), "|");
		}
		System.out.printf("%s", " ---------------------------------------------------------------- \n");
	}
	
	public void pitcherKickCharacter() {
		ArrayList<HofVO> list = null;
		HofVO vo = null;
		System.out.printf("%35s \n", "- �Ϲ� ���� ���� -");
		System.out.println();
		System.out.printf("%33s \n", "- ���� -");
		System.out.printf("%s", " ---------------------------------------------------------------- \n");
		System.out.printf("%s  %-10s \t %-4s \t %-12s \t %-4s \t %-5s \t %s \n","|   ", "����ID", "|", "ĳ���� �̸�" , "|", "�ý���", "|");
		list = dao.getPitcherKickCharacter();
		for (int i = 0; i < list.size(); i++) {
			vo = list.get(i);
			System.out.printf("%-3s \t %-10s \t %-4s \t %-12s \t %-4s \t %-5d \t %s \n","|", vo.getUserid(), "|", vo.getCharacterName() , "|", vo.getAllStat(), "|");
		}
		System.out.printf("%s", " ---------------------------------------------------------------- \n");
	}
	
	public void temp() {
		//5���� ��� �ٸ�  �����������̺�� �ű�� ����
		UserCharacterVO vo = dao.getCharacter(loginId, presentCharId);
		if (vo.getYear() < 5) {
			//���
		}
		else {
			int m = dao.hofCharacterInsert(vo);
			int n = dao.deleteCharacter(loginId, presentCharId);
			presentCharId = 0;
			return;
		}
		
	}
}
	
	
	
	
	

