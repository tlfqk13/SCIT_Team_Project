package baseball.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

import baseball.dao.BaseballDAO;
import baseball.vo.HofVO;
import baseball.vo.QuizScoreVO;
import baseball.vo.QuizVO;
import baseball.vo.TrainerVO;
import baseball.vo.UserCharacterVO;
import baseball.vo.UserVO;



public class BaseballUI {
	private BaseballDAO dao = new BaseballDAO();
	Scanner keyin = new Scanner(System.in);
	private UserCharacterVO presentChar = null;
	private String loginId = null;
	private int quizScoreNum = 0;
//	int tenNum = 9;

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
//			case 3:	idDelete();	break;
			case 4: ranking(); 	break;
			case 5: hof();		break;
			case 0: System.out.println("������ �����մϴ�."); return;
			default:
			}
		}
	}
	
	void loginMenuPrint() {
		System.out.println("[ ������ ���� Ű��� �߱��� ]");
		System.out.println("1.	ȸ������");
		System.out.println("2.	�α���");
		System.out.println("3. 	ȸ��Ż��");
		System.out.println("4. 	��ŷ(�ý���)");
		System.out.println("5. 	���𼱼�");
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
					case 3: characterDelete();	break;
					case 4: loginId = null; return;
					case 0: return;
					default: 
				}
				
			}
		}
		
	}
	
//	public void idDelete() {
//		int m = 0;
//		ArrayList<UserVO> list = dao.getId();
//		UserVO vo = null;
//		
//		System.out.printf("%s  %-6s \t %-2s  %-6s \t %-2s \n","|   ", "��ȣ", "|", "���� ���̵�" , "|");
//		System.out.printf("%s", " ---------------------- \n");
//		for (int i = 0; i < list.size(); i++) {
//			vo = list.get(i);
//			System.out.printf("%s  %-6d \t %-2s  %-6s \t %-2s \n","|   ", (i+1), "|", vo.getUserId() , "|");
//		}
//		while (true) {
//			try {
//				m = keyin.nextInt();
//				break;
//			}
//			catch (InputMismatchException e) {
//				keyin.nextLine();
//				System.out.println("�ٽ� �Է��ϼ���.");
//				continue;
//			}
//		}
		
//		vo = list.get(m-1);
//		int d = dao.deleteId(vo.getUserId());
//		if (d == 1) {
//			System.out.println("���̵� �����Ǿ����ϴ�.");
//			presentChar = null;
//		}
//		else {
//			System.out.println("���̵� ������ �����߽��ϴ�.");
//		}
//		
//	}
	
	public void ranking() {
		while (true) {
			int m = 0;
			rankingMenuPrint();				
			try {
				m = keyin.nextInt();
			}
			catch (InputMismatchException e) {
				keyin.nextLine();
				System.out.println("�ٽ� �Է��ϼ���.");
				continue;
			}
			switch(m) {
				case 1: pitcherRanking(); 	break;
				case 2: hitterRanking();	break;
				case 0: return;
				default: 
			}
			
		}
	}
	
	public void rankingMenuPrint() {
		System.out.println("[ ��ŷ ]");
		System.out.println("1.	���� ��ŷ");
		System.out.println("2.	Ÿ�� ��ŷ");
		System.out.println("0. 	�ڷ�");
		System.out.print("����>	");
	}
	
	public void pitcherRanking() {
		ArrayList<UserCharacterVO> list = dao.pitcherRanking();
		System.out.printf("%s", " -------------------------------------------------------------------------------- \n");
		System.out.printf("%s  %-6s \t %-2s  %-6s \t %-2s  %-6s \t %-2s  %-6s \t %-2s  %-7s \t %-2s \n","|   ", "���", "|", "ĳ���� �̸�" , "|", "�ý���", "|", "������", "|", "����", "|");
		System.out.printf("%s", " -------------------------------------------------------------------------------- \n");
		for (int i = 0; i < list.size(); i++) {
			UserCharacterVO vo = list.get(i);
			System.out.printf("%s  %-6s \t %-2s  %-6s \t %-2s  %-6d \t %-2s  %-6d \t %-2s  %-7d \t %-2s \n","|   ", ((i+1)+"��"), "|", vo.getCharacterName(), "|", vo.getAllStat(), "|", vo.getGold(), "|",vo.getYear(), "|");
		}
		System.out.printf("%s", " -------------------------------------------------------------------------------- \n");
	}
	
	public void hitterRanking() {
		ArrayList<UserCharacterVO> list = dao.hitterRanking();
		System.out.printf("%s", " -------------------------------------------------------------------------------- \n");
		System.out.printf("%s  %-6s \t %-2s  %-6s \t %-2s  %-6s \t %-2s  %-6s \t %-2s  %-7s \t %-2s \n","|   ", "���", "|", "ĳ���� �̸�" , "|", "�ý���", "|", "������", "|", "����", "|");
		System.out.printf("%s", " -------------------------------------------------------------------------------- \n");
		for (int i = 0; i < list.size(); i++) {
			UserCharacterVO vo = list.get(i);
			System.out.printf("%s  %-6s \t %-2s  %-6s \t %-2s  %-6d \t %-2s  %-6d \t %-2s  %-7d \t %-2s \n","|   ", ((i+1)+"��"), "|", vo.getCharacterName(), "|", vo.getAllStat(), "|", vo.getGold(), "|",vo.getYear(), "|");
		}
		System.out.printf("%s", " -------------------------------------------------------------------------------- \n");
	}
	
	
	public void characterMenuPrint() {
		System.out.println("[ ���� �޴� ]");
		System.out.println("1.	���� ����");
		System.out.println("2.	���� ����");
		System.out.println("3.	���� ����");
		System.out.println("4. 	�α׾ƿ�");
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
			System.out.println("[ ĳ���� ���� ]");
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
			case 1: pitcherSelect(); 	mainMenu();		break;
			case 2: hitterSelect();		mainMenu();	 	break;
			case 0: return;
			default:
			}
		}
	}
		
	public void characterDelete() {
		while (true) {
			int m = 0;
			System.out.println("[ ĳ���� ���� ]");
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
			case 1: pitcherDelete(); 	break;
			case 2: hitterDelete(); 	break;
			case 0: return;
			default:
			}
		}
		
	}
	
	public void selectMenuPrint() {
		System.out.println("1.	����");
		System.out.println("2.	Ÿ��");
		System.out.println("0.	�ڷ�");
		System.out.print("����>	");
	}
	
	public void pitcherDelete() {
		pitcherSelect();
		
		int d = dao.deleteCharacter(presentChar.getUserId(), presentChar.getCharacterId());
		if (d == 1) {
			System.out.println("ĳ���Ͱ� �����Ǿ����ϴ�.");
			presentChar = null;
		}
		else {
			System.out.println("ĳ���� ������ �����߽��ϴ�.");
		}
		
	}
	
	public void hitterDelete() {
		hitterSelect();
		
		int d = dao.deleteCharacter(presentChar.getUserId(), presentChar.getCharacterId());
		if (d == 1) {
			System.out.println("ĳ���Ͱ� �����Ǿ����ϴ�.");
			presentChar = null;
		}
		else {
			System.out.println("ĳ���� ������ �����߽��ϴ�.");
		}
	}
	
	public void pitcherSelect() {
		int num = 0;
		System.out.printf("%50s", "[ ���� ĳ���� ��� ] \n");
		
		//����ĳ���� ��� ���
		ArrayList<UserCharacterVO> list = dao.pitcherSelect(loginId);
		
		if (list.size() == 0) {
			System.out.println("ĳ���Ͱ� �����ϴ�.");
			return;
		}
		else {
			UserCharacterVO vo = null;
			System.out.printf("%s", " -------------------------------------------------------------------------------- \n");
			System.out.printf("%s  %-6s \t %-2s  %-6s \t %-2s  %-6s \t %-2s  %-6s \t %-2s  %-7s \t %-2s \n","|   ", "��ȣ", "|", "ĳ���� �̸�" , "|", "�ý���", "|", "������", "|", "����", "|");
			System.out.printf("%s", " -------------------------------------------------------------------------------- \n");
			for (int i = 0; i < list.size(); i++) {
				vo = list.get(i);
				System.out.printf("%s  %-6d \t %-2s  %-6s \t %-2s  %-6d \t %-2s  %-6d \t %-2s  %-7d \t %-2s \n","|   ", (i+1), "|", vo.getCharacterName(), "|", vo.getAllStat(), "|", vo.getGold(), "|",vo.getYear(), "|");
			}
			System.out.printf("%s", " -------------------------------------------------------------------------------- \n");

			while (true) {
				System.out.print("��ȣ ����> ");
				try {
					num = keyin.nextInt();
					if (num <= list.size()) {
						break;	
					}
					else {
						System.out.println("�ٽ� �Է��ϼ���.");
						continue;
					}
				}
				catch (InputMismatchException e) {
					keyin.nextLine();
					System.out.println("�ٽ� �Է��ϼ���.");
					continue;
				}
			}
			
			presentChar = list.get(num-1);
			
		}
	}
	
	public void hitterSelect() {
		int num = 0;
		System.out.printf("%50s", "[ Ÿ�� ĳ���� ��� ] \n");
		
		//Ÿ��ĳ���� ��� ���
		ArrayList<UserCharacterVO> list = dao.hitterSelect(loginId);

		if (list.size() == 0) {
			System.out.println("ĳ���Ͱ� �����ϴ�.");
			return;
		}
		else {
			UserCharacterVO vo = null;
			System.out.printf("%s", " -------------------------------------------------------------------------------- \n");
			System.out.printf("%s  %-6s \t %-2s  %-6s \t %-2s  %-6s \t %-2s  %-6s \t %-2s  %-7s \t %-2s \n","|   ", "��ȣ", "|", "ĳ���� �̸�" , "|", "�ý���", "|", "������", "|", "����", "|");
			System.out.printf("%s", " -------------------------------------------------------------------------------- \n");
			for (int i = 0; i < list.size(); i++) {
				vo = list.get(i);
				System.out.printf("%s  %-6d \t %-2s  %-6s \t %-2s  %-6d \t %-2s  %-6d \t %-2s  %-7d \t %-2s \n","|   ", (i+1), "|", vo.getCharacterName(), "|", vo.getAllStat(), "|", vo.getGold(), "|",vo.getYear(), "|");
			}
			System.out.printf("%s", " -------------------------------------------------------------------------------- \n");

			while (true) {
				System.out.print("��ȣ ����> ");
				try {
					num = keyin.nextInt();
					if (num <= list.size()) {
						break;	
					}
					else {
						System.out.println("�ٽ� �Է��ϼ���.");
						continue;
					}
				}
				catch (InputMismatchException e) {
					keyin.nextLine();
					System.out.println("�ٽ� �Է��ϼ���.");
					continue;
				}
			}
			presentChar = list.get(num-1);
		}
		
	}
	
	
	public void mainMenu() {
		int m = 0;
		
		if (loginId != null && presentChar != null) {
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
				case 4: match(); 				break;
				case 5: rest(); 				break;
				case 6: quiz();					break;
				case 7: presentChar = null; return;
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
		System.out.println("6. 	����");
		System.out.println("7. 	ĳ���� ����â");
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
			int m = 0;
			if (loginId != null && presentChar != null) {
				String charClassName = presentChar.getClassName();
				while (true) {
					if (charClassName.equals("Ÿ��")) {
						hitterTrainingMenuPrint();
						try {
							m = keyin.nextInt();
						}
						catch (InputMismatchException e) {
							keyin.nextLine();
							System.out.println("�ٽ� �Է��ϼ���.");
							continue;
						}	
						switch(m) {		
//						case 1: powerTraining();			break;
//						case 2: hitTraining();			break;
//						case 3: runSpeedTraining();	break;
						case 0: System.out.println("���� �޴��� ���ư��ϴ�."); return;
						default:
						}
					}
				
					else if (charClassName.equals("����")) {
						pitcherTrainingMenuPrint();
						try {
							m = keyin.nextInt();
						}
						catch (InputMismatchException e) {
							keyin.nextLine();
							System.out.println("�ٽ� �Է��ϼ���.");
							continue;
						}	
						switch(m) {		
//						case 1: ballSpeedTraining();			break;
//						case 2: ballControlTraining();			break;
//						case 3: mentalityTraining();	break;
						case 0: System.out.println("���� �޴��� ���ư��ϴ�."); return;
						default:
						}
					}
					
				}
			}
			else {
				return;
			}
			
	}
	
	public void hitterTrainingMenuPrint() {
		System.out.println("[ Ÿ�� �Ʒ� ]");
		System.out.println("1.	�Ŀ� �Ʒ�");
		System.out.println("2.	Ÿ�� �Ʒ�");
		System.out.println("3. 	�ַ� �Ʒ�");
		System.out.println("0.	�ڷΰ���");
		System.out.print("����>	");
	}

	public void pitcherTrainingMenuPrint() {
		System.out.println("[ ���� �Ʒ� ]");
		System.out.println("1.	�����ǵ� �Ʒ�");
		System.out.println("2.	����Ʈ�� �Ʒ�");
		System.out.println("3. 	���ŷ� �Ʒ�");
		System.out.println("0.	�ڷΰ���");
		System.out.print("����>	");
	}
	
	public void coachMenuPrint() {
		System.out.println("[ ��ġ ���� ]");
		
		System.out.print("����>	");
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
		System.out.printf("%25s \n", "[ ����â ]");
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
		System.out.println("[ ��� �ϱ� ]");
		System.out.println("��� ����");
		if (presentChar.getYear() < 5) {
			presentChar.setActive(5);
			presentChar.setHealth(100);
			presentChar.setGold(presentChar.getGold()+500);
			presentChar.setYear(presentChar.getYear() + 1);
			System.out.println("�ൿ���� 5�� ȸ�� �Ǿ����ϴ�.");
			System.out.println("�������� ���� �Ͽ����ϴ�.");
			System.out.println("�ǰ��� 100���� ȸ�� �Ǿ����ϴ�.");
			System.out.println("������ 1�� �þ����ϴ�.");
		}
		else {
			int m = dao.hofCharacterInsert(presentChar);
			if (m == 0) {
				System.out.println("���� ���翡 �ݿ��Ǿ����ϴ�.");
			}
			dao.deleteCharacter(loginId, presentChar.getCharacterId());
			presentChar = null;
			return;
		}
		
		
		
		//ĳ���� ���� db�� �ݿ��ϱ�
		//dao.     (presentChar);
		
		
//		public void temp() {
//		//5���� ��� �ٸ�  �����������̺�� �ű�� ����
//		UserCharacterVO vo = dao.getCharacter(loginId, presentCharId);
//		if (vo.getYear() < 5) {
//			//���
//		}
//		else {
//			int m = dao.hofCharacterInsert(vo);
//			int n = dao.deleteCharacter(loginId, presentCharId);
//			presentCharId = 0;
//			return;
//		}
//		
//	}
		
	}
	
	public void rest() {
		int m = 0;
		while (true) {
			restMenuPrint();
			try {
				m = keyin.nextInt();
			}
			catch (InputMismatchException e) {
				keyin.nextLine();
				System.out.println("�ٽ� �Է��ϼ���.");
				continue;
			}	
			switch(m) {		
			case 1: freeRest();			break;
			case 2: premiumRest();			break;
			case 0: System.out.println("���� �޴��� ���ư��ϴ�."); return;
			default:
			}
		}
		
	}
	
	public void restMenuPrint() {
		System.out.println("[ �޽� �ϱ� ]");
		System.out.println("1.	�Ϲ� �޽�");
		System.out.println("2.	��� �޽�");
		System.out.println("0.	�ڷ� ����");
		System.out.print("����>	");
	}
	
	public void freeRest() {
		if (presentChar.getHealth() < 50) {
			presentChar.setActive(presentChar.getActive()-1);
			presentChar.setHealth(50);
			System.out.println("�Ϲ� �޽��� �Ϸ� �Ǿ����ϴ�.");
		}
		else {
			System.out.println("ü���� ����մϴ�.");
			return;
		}
		
	//ĳ���� ���� db�� �ݿ��ϱ�
	//dao.     (presentChar);

	}
	
	public void premiumRest() {
		
		if (presentChar.getHealth() != 100) {
			presentChar.setActive(presentChar.getActive()-1);
			presentChar.setGold(presentChar.getGold()-100);
			presentChar.setHealth(10);
			System.out.println("�����̾� �޽��� �Ϸ�Ǿ����ϴ�.");
			System.out.println("�������� �����Ͽ����ϴ�.");
		}
		else {
			System.out.println("ü���� ����մϴ�.");
			return;
		}
		
		//ĳ���� ���� db�� �ݿ��ϱ�
		//dao.     (presentChar);
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
			case 0: System.out.println("���θ޴��� ���ư��ϴ�."); 	return;
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
		System.out.printf("%35s \n", "[ �Ϲ� ���� ���� ]");
		System.out.println();
		System.out.printf("%33s \n", "- Ÿ�� -");
		System.out.printf("%s", " ---------------------------------------------------------------- \n");
		System.out.printf("%s  %-10s \t %-4s \t %-12s \t %-4s \t %-5s \t %s \n","|   ", "����ID", "|", "ĳ���� �̸�" , "|", "�ý���", "|");
		System.out.printf("%s", " ---------------------------------------------------------------- \n");

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
		System.out.printf("%35s \n", "[ �Ϲ� ���� ���� ]");
		System.out.println();
		System.out.printf("%33s \n", "- ���� -");
		System.out.printf("%s", " ---------------------------------------------------------------- \n");
		System.out.printf("%s  %-10s \t %-4s \t %-12s \t %-4s \t %-5s \t %s \n","|   ", "����ID", "|", "ĳ���� �̸�" , "|", "�ý���", "|");
		System.out.printf("%s", " ---------------------------------------------------------------- \n");

		list = dao.getPitcherKickCharacter();
		for (int i = 0; i < list.size(); i++) {
			vo = list.get(i);
			System.out.printf("%-3s \t %-10s \t %-4s \t %-12s \t %-4s \t %-5d \t %s \n","|", vo.getUserid(), "|", vo.getCharacterName() , "|", vo.getAllStat(), "|");
		}
		System.out.printf("%s", " ---------------------------------------------------------------- \n");
	}
	

	
	public void quiz() {
		int m = 0;
		while (true) {
			quizMenuPrint();
			try {
				m = keyin.nextInt();
			}
			catch (InputMismatchException e) {
				keyin.nextLine();
				System.out.println("�ٽ� �Է��ϼ���.");
				continue;
			}	
			switch(m) {					
			case 1: quizQuestion();		break;
			case 2: quizScore();		break;
			case 3: quizRanking(); 			break;
			case 0: System.out.println("���θ޴��� ���ư��ϴ�."); 	return;
			default:
			}
		}	
	}
	
	public void quizMenuPrint() {
		System.out.println("[ ���� ]");
		System.out.println("1.	���� Ǯ��");
		System.out.println("2.	ĳ���ͺ� ���� �����");
		System.out.println("3.	����� ����");
		System.out.println("0.	�ڷ� ����");
		System.out.print("����>	");
	}
	
	public void quizEndMenuPrint() {
		System.out.println("1.	�ѹ� ��");
		System.out.println("2.	������");
		System.out.print("����>	");
	}
	
	public void quizQuestion() {
		
		ArrayList<QuizVO> list = dao.quiz();
		QuizScoreVO scoreVo = new QuizScoreVO(loginId, presentChar.getCharacterId(), presentChar.getCharacterName());
		quiz1:
		while (true) {
			int m = 0;
			scoreVo.setCorrectAnswer(0);
			scoreVo.setCorrectPercent(0);
			scoreVo.setWrongAnswer(0);
			String answer = null;
			for (int i = 0; i < list.size(); i++) {
				QuizVO vo =  list.get(i);
				
				System.out.println();
				System.out.println((i+1) + "�� ����");
				System.out.println(vo.getQuestion());
				if (vo.getExample1()==null && vo.getExample2()==null && vo.getExample3()==null && vo.getExample4()==null) {
					while (true) {
						try {
							keyin.nextLine();
							System.out.print("���� : ");
							answer = keyin.nextLine();
							if (answer.equalsIgnoreCase(vo.getCorrect())) {
								System.out.println("�¾ҽ��ϴ�.");
								scoreVo.setCorrectAnswer(scoreVo.getCorrectAnswer() + 1);
								break;
							}
							else {
								System.out.print("Ʋ�Ƚ��ϴ�. ");
								System.out.println("�� : " + vo.getCorrect());
								scoreVo.setWrongAnswer(scoreVo.getWrongAnswer() + 1);
								break;
							}
						}
						catch (Exception e) {
							e.printStackTrace();
							System.out.println("�ٽ� �Է��ϼ���.");
							continue;
						}
					}
				}
				else {
					//���� ���� ����
					ArrayList<String> exampleAll = new ArrayList<String>();
					exampleAll.add(vo.getExample1());
					exampleAll.add(vo.getExample2());
					exampleAll.add(vo.getExample3());
					exampleAll.add(vo.getExample4());
					Collections.shuffle(exampleAll);
					vo.setExample1(exampleAll.get(0));
					vo.setExample2(exampleAll.get(1));
					vo.setExample3(exampleAll.get(2));
					vo.setExample4(exampleAll.get(3));
					
					
					
					System.out.println("1 - " + vo.getExample1());
					System.out.println("2 - " + vo.getExample2());
					System.out.println("3 - " + vo.getExample3());
					System.out.println("4 - " + vo.getExample4());
					System.out.println();
					
					while (true) {
						System.out.print("���� : ");
						try {
							m = keyin.nextInt();
							if (m > 4 || m <= 0) {
								System.out.println("�ٽ� �Է��ϼ���.");
								continue;
							}
							else if (m == 1) {
								if (vo.getExample1().equals(vo.getCorrect())) {
									System.out.println("�¾ҽ��ϴ�.");
									scoreVo.setCorrectAnswer(scoreVo.getCorrectAnswer() + 1);
									break;
								}
								else {
									System.out.print("Ʋ�Ƚ��ϴ�. ");
									if (vo.getExample2().equals(vo.getCorrect())) {
										System.out.println("�� : 2 - " + vo.getExample2());
									}
									else if (vo.getExample3().equals(vo.getCorrect())) {
										System.out.println("�� : 3 - " + vo.getExample3());
									}
									else if (vo.getExample4().equals(vo.getCorrect())) {
										System.out.println("�� : 4 - " + vo.getExample4());
									}
									scoreVo.setWrongAnswer(scoreVo.getWrongAnswer() + 1);
									break;
								}
							}
							else if (m == 2) {
								if (vo.getExample2().equals(vo.getCorrect())) {
									System.out.println("�¾ҽ��ϴ�.");
									scoreVo.setCorrectAnswer(scoreVo.getCorrectAnswer() + 1);
									break;
								}
								else {
									System.out.print("Ʋ�Ƚ��ϴ�. ");
									if (vo.getExample1().equals(vo.getCorrect())) {
										System.out.println("�� : 1 - " + vo.getExample1());
									}
									else if (vo.getExample3().equals(vo.getCorrect())) {
										System.out.println("�� : 3 - " + vo.getExample3());
									}
									else if (vo.getExample4().equals(vo.getCorrect())) {
										System.out.println("�� : 4 - " + vo.getExample4());
									}
									scoreVo.setWrongAnswer(scoreVo.getWrongAnswer() + 1);
									break;
								}
							}
							else if (m == 3) {
								if (vo.getExample3().equals(vo.getCorrect())) {
									System.out.println("�¾ҽ��ϴ�.");
									scoreVo.setCorrectAnswer(scoreVo.getCorrectAnswer() + 1);
									break;
								}
								else {
									System.out.print("Ʋ�Ƚ��ϴ�. ");
									if (vo.getExample1().equals(vo.getCorrect())) {
										System.out.println("�� : 1 - " + vo.getExample1());
									}
									else if (vo.getExample2().equals(vo.getCorrect())) {
										System.out.println("�� : 2 - " + vo.getExample2());
									}
									else if (vo.getExample4().equals(vo.getCorrect())) {
										System.out.println("�� : 4 - " + vo.getExample4());
									}
									scoreVo.setWrongAnswer(scoreVo.getWrongAnswer() + 1);
									break;
								}
							}
							else if (m == 4) {
								if (vo.getExample4().equals(vo.getCorrect())) {
									System.out.println("�¾ҽ��ϴ�.");
									scoreVo.setCorrectAnswer(scoreVo.getCorrectAnswer() + 1);
									break;
								}
								else {
									System.out.print("Ʋ�Ƚ��ϴ�. ");
									if (vo.getExample1().equals(vo.getCorrect())) {
										System.out.println("�� : 1 - " + vo.getExample1());
									}
									else if (vo.getExample2().equals(vo.getCorrect())) {
										System.out.println("�� : 2 - " + vo.getExample2());
									}
									else if (vo.getExample3().equals(vo.getCorrect())) {
										System.out.println("�� : 3 - " + vo.getExample3());
									}
									scoreVo.setWrongAnswer(scoreVo.getWrongAnswer() + 1);
									break;
								}
							}
						}
						catch (InputMismatchException e) {
							keyin.nextLine();
							System.out.println("�ٽ� �Է��ϼ���.");
							continue;
						}
				}
					
			}
				
		}
			scoreVo.setCorrectPercent((int)Math.round((scoreVo.getCorrectAnswer()/5.0)* 100));
			System.out.println("���� ������ : " + scoreVo.getCorrectAnswer() + "��");
			System.out.println("Ʋ�� ������ : " + scoreVo.getWrongAnswer() + "��");
			System.out.println("����� : " + scoreVo.getCorrectPercent() + "%");
			dao.quizScroeInsert(scoreVo);
			quizEndMenuPrint();
			while (true) {
				int n;
				try {
					
					n = keyin.nextInt();
				}
				catch (InputMismatchException e) {
					keyin.nextLine();
					System.out.println("�ٽ� �Է��ϼ���.");
					continue;
				}	
				switch(n) {					
				case 1: System.out.println("�ѹ� �� �����մϴ�.");continue quiz1;
				case 2: System.out.println("��� �����ϴ�.");break quiz1;
				default: System.out.println("�ٽ� �Է��ϼ���.");  	System.out.print("����>	");
				}
			}	

		}
		
	}
	
//	public void quizScore() {
//		ArrayList<QuizScoreVO> scoreList = dao.quizScroeAll();
//		QuizScoreVO vo = null;
//		System.out.printf("%s \n", "[ ���� ���� ]");
//		System.out.println();
//		System.out.printf("%s", " ---------------------------------------------------------------------------------------- \n");
//		System.out.printf("%s  %-10s \t %-2s  %-6s \t %-2s  %-6s \t %-2s  %-6s \t %-2s  %-7s \t %-2s \n","|   ", "����ID", "|", "ĳ���� �̸�" , "|", "���� ����", "|", "Ʋ�� ����", "|", "�����", "|");
//
//		for (int i  = 0; i < scoreList.size(); i++) {
//			vo = scoreList.get(i);
//			System.out.printf("%s  %-10s \t %-2s  %-6s \t %-2s  %-6d \t %-2s  %-6d \t %-2s  %-7d \t %-2s \n","|   ", vo.getUserId(), "|", vo.getCharacterName() , "|", vo.getCorrectAnswer(), "|", vo.getWrongAnswer(), "|", vo.getCorrectPercent(), "|");
//			
//		}
//		System.out.printf("%s", " ---------------------------------------------------------------------------------------- \n");
//
//	}
	
	public void quizScore() {
		
		int m = 1;
		quizScoreNum = 0;
		ArrayList<QuizScoreVO> scoreList = dao.quizScroeAll();
		int firstScoreSize = scoreList.size();
		System.out.printf("%s \n", "[ ���� ���� ]");
		score:
		while (true) {
			if (quizScoreNum != 0) {
				if (firstScoreSize > quizScoreNum) {
					System.out.println("1.	���� ������");
					System.out.println("2.	�ڷ�");
					System.out.print("����>	");
				}
				else {
					System.out.println("������ �������Դϴ�.");
					System.out.println("1.	����");
					System.out.print("����>	");
				}
				
				try {
					m = keyin.nextInt();
				}
				catch (InputMismatchException e) {
					keyin.nextLine();
					System.out.println("�ٽ� �Է��ϼ���.");
					continue;
				}	
			}
		
			if (firstScoreSize > quizScoreNum) {
				switch(m) {					
				case 1: quizScoreList(); break;
				case 2: return;
				default: System.out.println("�ٽ� �Է��ϼ���.");  	System.out.print("����>	");
				}
			}
			else {
				switch(m) {					
				case 1: return;
				default: System.out.println("�ٽ� �Է��ϼ���.");  	System.out.print("����>	");
				}
			}
		}
	}
	
	public void quizScoreList() {
			QuizScoreVO vo = null;

			ArrayList<QuizScoreVO> scoreList = dao.quizScroeAll2(quizScoreNum,10);
			

				System.out.printf("%s", " ---------------------------------------------------------------------------------------- \n");
				System.out.printf("%s  %-10s \t %-2s  %-6s \t %-2s  %-6s \t %-2s  %-6s \t %-2s  %-7s \t %-2s \n","|   ", "����ID", "|", "ĳ���� �̸�" , "|", "���� ����", "|", "Ʋ�� ����", "|", "�����", "|");
				System.out.printf("%s", " ---------------------------------------------------------------------------------------- \n");

				for (int i = 0; i <= scoreList.size()-1; i++) {
					vo = scoreList.get(i);
					System.out.printf("%s  %-10s \t %-2s  %-6s \t %-2s  %-6d \t %-2s  %-6d \t %-2s  %-7d \t %-2s \n","|   ", vo.getUserId(), "|", vo.getCharacterName() , "|", vo.getCorrectAnswer(), "|", vo.getWrongAnswer(), "|", vo.getCorrectPercent(), "|");
				}
				System.out.printf("%s", " ---------------------------------------------------------------------------------------- \n");
				System.out.println((((int)Math.floor(quizScoreNum/10))+1) + "������" );
				quizScoreNum +=10;
	}
	
	public void quizRanking() {
		ArrayList<QuizScoreVO> rankingList = dao.quizRanking();
		System.out.printf("%37s", "[ ���� ����� ���� ] \n");
		System.out.printf("%s", " ---------------------------------------------------------------- \n");
		System.out.printf("%s  %-10s \t %-2s  %-8s \t %-2s  %-6s \t %-2s  %-7s \t %-2s \n","|   ", "���", "|", "����ID" , "|", "ĳ���� �̸�", "|", "�����", "|");
		System.out.printf("%s", " ---------------------------------------------------------------- \n");
		for (int i = 0; i < rankingList.size(); i++) {
			QuizScoreVO quizRankingVo = rankingList.get(i);
			System.out.printf("%s  %-10s \t %-2s  %-8s \t %-2s  %-6s \t %-2s  %-7s \t %-2s \n","|   ", ((i+1)+"��"), "|", quizRankingVo.getUserId() , "|", quizRankingVo.getCharacterName(), "|", quizRankingVo.getCorrectPercent(), "|");

		}
		System.out.printf("%s", " ---------------------------------------------------------------- \n");

	}
	
}
	
	
	
	
	

