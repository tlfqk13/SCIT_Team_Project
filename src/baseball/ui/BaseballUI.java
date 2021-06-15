package baseball.ui;

import java.util.ArrayList;
import java.util.Scanner;

import baseball.dao.BaseballDAO;
import baseball.vo.ItemInventoryVO;
import baseball.vo.ItemVO;
import baseball.vo.ItemequipinfoVO;
import baseball.vo.TrainerVO;
import baseball.vo.UserCharacterVO;
import baseball.vo.UserVO;



public class BaseballUI {
	private BaseballDAO dao = new BaseballDAO();
	Scanner keyin = new Scanner(System.in);
	private UserCharacterVO presentChar = null;
	private ItemVO item_presentChar=null;
	private String loginId = null;
	
	private int purchaseResult=0;
	private int purchaseId=0;
	
	private int purchaseBat=0;
	private int purchaseGlove=0;
	private int purchaseShoes=0;
	private int purchaseCloth=0;
	private int purchaseHelmet=0;
	
	
	public BaseballUI() {
		while (true) {
			loginMenuPrint();				//�޴� ���
			int m = keyin.nextInt();	//��ȣ �Է¹���
			switch(m) {					//�� ��ȣ�� ���� ó��, 0�� ���� ����
			case 1: join();		break;
			case 2: login();	break;
//			case 3: hof();	break;
			case 0: System.out.println("������ �����մϴ�."); return;
			default:
			}
		}
	}
	
	void loginMenuPrint() {
		System.out.println("[ ������ ���� Ű��� �߱��� ]");
		System.out.println("1.	ȸ������");
		System.out.println("2.	�α���");
		System.out.println("3. 	���� ����");
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
				characterMenuPrint();				
				int m = keyin.nextInt();
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
	//aaaaaaa
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
		System.out.print("Ÿ�� or ���� > ");
		className = keyin.next();
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
			selectMenuPrint();
			int m = keyin.nextInt();
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
			System.out.print("��ȣ ����> ");
			int num = keyin.nextInt();
			presentChar = list.get(num-1);
			mainMenu();
		}
	}
	
	public void hitterSelect() {
		System.out.println("[ Ÿ�� ĳ���� ��� ]");
		
		//Ÿ��ĳ���� ��� ���
		ArrayList<UserCharacterVO> list = dao.hitterSelect(loginId);

		if (list.size() == 0) {
			System.out.println("ĳ���Ͱ� �����ϴ�.");
			return;
		}
		else {
			UserCharacterVO vo = null;
			System.out.printf("%-5s \t %-10s \t %-10s \t %-10s \t %-10s \t %-10s \t %-10s \n", "��ȣ", "ĳ���� �̸�","hitterpower","hitterhit","hitterrunspeed","������", "����");
			for (int i = 0; i < list.size(); i++) {
				vo = list.get(i);
				System.out.printf("%-5d \t %-10s \t %-10d \t %-10d \t %-10d \t %-10d \t %-10d \n", (i+1), vo.getCharacterName(),vo.getHitterPower(),vo.getHitterHit(),vo.getHitterRunSpeed(),vo.getGold(), vo.getYear());
			}
			System.out.print("��ȣ ����> ");
			int num = keyin.nextInt();
			presentChar = list.get(num-1);
			mainMenu();
		}
		
	}
	
	
	public void mainMenu() {
		if (loginId != null && presentChar != null) {
			while (true) {
				mainMenuPrint();
				int m = keyin.nextInt();	
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
			case 1: clothSelect();			break;
			case 2: helmetSelect();			break;
			case 3: batSelect();			break;
			case 4: shoesSelect(); 			break;
			case 5: gloveSelect();			break;
//			case 6: foodSelect(); 			break;
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
		System.out.println("5. 	�۷���");
		System.out.println("6.  ����");
		System.out.println("0.	�ڷΰ���");
		System.out.print("����>	");
	}

	public void clothSelect() {
		System.out.println(" [ �� ��� ��� ] ");
		//Ÿ��ĳ���� ��� ���
		ArrayList<ItemVO> list = dao.clothSelect();
		ItemVO vo=null;
		System.out.println(" ������ �̸� " + " ������ ���� "+" ���� ");
		for(int i=0;i<list.size();i++) {
			vo=list.get(i);
			System.out.println(
					(i+1) + " : " + 
					vo.getItemName()+"\t"
					+vo.getSummary()+"\t"
					+vo.getPrice());
		}
		System.out.print("��ȣ ����> ");
		int num = keyin.nextInt();
		item_presentChar=list.get(num-1);
		clothPurchase(item_presentChar);
		System.out.println(presentChar.getGold());

	}
	
	public void clothPurchase(ItemVO item_presentChar) {
		
		
		if(presentChar.getGold()>=300) {
			if(item_presentChar.getItemName().contentEquals("������ ������")) {
				System.out.println("������ ������ ���ſϷ�");
				presentChar.setGold(presentChar.getGold()-300);
			}
			else if(item_presentChar.getItemName().contentEquals("���� ������")) {
				System.out.println("������ ������ ���ſϷ�");
				presentChar.setGold(presentChar.getGold()-450);
			}
			else if(item_presentChar.getItemName().contentEquals("�¸��� ������")) {
				System.out.println("������ ������ ���ſϷ�");
				presentChar.setGold(presentChar.getGold()-600);
			}
		}
		else {
			System.out.println("���� �����");
		}
	}
	
	public void helmetSelect() {
		System.out.println(" [ ��� ��� ��� ] ");
		//Ÿ��ĳ���� ��� ���
		ArrayList<ItemVO> list = dao.helmetSelect();
		ItemVO vo=null;
		System.out.println(" ������ �̸� " + " ������ ���� "+" ���� ");
		for(int i=0;i<list.size();i++) {
			vo=list.get(i);
			System.out.println(
					vo.getItemName()+"\t"
					+vo.getSummary()+"\t"
					+vo.getPrice());
		}
		System.out.print("��ȣ ����> ");
		int num = keyin.nextInt();
		item_presentChar=list.get(num-1);
		helmetPurchase(item_presentChar);
		System.out.println(presentChar.getGold());
	}
	private void helmetPurchase(ItemVO item_presentChar2) {
			
		if(presentChar.getGold()>=300 || presentChar.getGold()>=200) {
			if(item_presentChar.getItemName().contentEquals("������ ���")) {
				System.out.println("������ ��� ���ſϷ�");
				presentChar.setGold(presentChar.getGold()-200);
			}
			else if(item_presentChar.getItemName().contentEquals("���� ���")) {
				System.out.println("������ ��� ���ſϷ�");
				presentChar.setGold(presentChar.getGold()-450);
			}
			else if(item_presentChar.getItemName().contentEquals("�¸��� ���")) {
				System.out.println("������ ��� ���ſϷ�");
				presentChar.setGold(presentChar.getGold()-650);
			}
		}
		else {
			System.out.println("���� �����");
		}
		
		//������ �޾Ƽ� ���̵� where �Ἥ �ϸ� �׳� �����Ϸ� 
		purchaseResult=presentChar.getGold();
		purchaseId=presentChar.getCharacterId();
		purchaseHelmet=presentChar.getHitterHit();
		dao.test(purchaseResult);
		dao.purchaseHelmet(purchaseHelmet);
		
		//UserCharacterVO vo = new UserCharacterVO(loginId, name, className);
		UserCharacterVO vo= new UserCharacterVO(purchaseResult,purchaseId);
		dao.ttest(vo);
		
	}

	public void batSelect() {
		System.out.println(" [ ����� ��� ��� ] ");
		//Ÿ��ĳ���� ��� ���
		ArrayList<ItemVO> list = dao.batSelect();
		ItemVO vo=null;
		System.out.println(" ������ �̸� " + " ������ ���� "+" ���� ");
		for(int i=0;i<list.size();i++) {
			vo=list.get(i);
			System.out.println(
					vo.getItemName()+"\t"
					+vo.getSummary()+"\t"
					+vo.getPrice());
		}
		System.out.print("��ȣ ����> ");
		int num = keyin.nextInt();
		item_presentChar=list.get(num-1);
		batPurchase(item_presentChar);
		System.out.println(presentChar.getGold());
	}
	private void batPurchase(ItemVO item_presentChar2) {
		
		if(presentChar.getGold()>=300) {
			if(item_presentChar.getItemName().contentEquals("������ �����")) {
				System.out.println("������ ����� ���ſϷ�");
				presentChar.setGold(presentChar.getGold()-600);
				presentChar.setHitterPower(presentChar.getHitterPower()+3);
			}
			else if(item_presentChar.getItemName().contentEquals("���� �����")) {
				System.out.println("������ ����� ���ſϷ�");
				presentChar.setGold(presentChar.getGold()-900);
				presentChar.setHitterPower(presentChar.getHitterPower()+5);
			}
			else if(item_presentChar.getItemName().contentEquals("�¸��� �����")) {
				System.out.println("������ ����� ���ſϷ�");
				presentChar.setGold(presentChar.getGold()-1200);
				presentChar.setHitterPower(presentChar.getHitterPower()+7);
			}
		}
		else {
			System.out.println("���� �����");
		}
		purchaseResult=presentChar.getGold();
		purchaseBat=presentChar.getHitterPower();
		dao.test(purchaseResult);
		dao.purchaseBat(purchaseBat);
	}

	public void shoesSelect() {
		System.out.println(" [ �Ź� ��� ��� ] ");
		//Ÿ��ĳ���� ��� ���
		ArrayList<ItemVO> list = dao.shoesSelect();
		ItemVO vo=null;
		System.out.println(" ������ �̸� " + " ������ ���� "+" ���� ");
		for(int i=0;i<list.size();i++) {
			vo=list.get(i);
			System.out.println(
					vo.getItemName()+"\t"
					+vo.getSummary()+"\t"
					+vo.getPrice());
		}
		System.out.print("��ȣ ����> ");
		int num = keyin.nextInt();
		item_presentChar=list.get(num-1);
		shoesPurchase(item_presentChar);
		System.out.println(presentChar.getGold());
	}
	private void shoesPurchase(ItemVO item_presentChar2) {
		if(presentChar.getGold()>=300) {
			if(item_presentChar.getItemName().contentEquals("������ �Ź�")) {
				System.out.println("������ �Ź� ���ſϷ�");
				presentChar.setGold(presentChar.getGold()-300);
				presentChar.setHitterRunSpeed(presentChar.getHitterRunSpeed()+3);
			}
			else if(item_presentChar.getItemName().contentEquals("���� �Ź�")) {
				System.out.println("������ �Ź� ���ſϷ�");
				presentChar.setGold(presentChar.getGold()-400);
				presentChar.setHitterRunSpeed(presentChar.getHitterRunSpeed()+5);
			}
			else if(item_presentChar.getItemName().contentEquals("�¸��� �Ź�")) {
				System.out.println("������ �Ź� ���ſϷ�");
				presentChar.setGold(presentChar.getGold()-500);
				presentChar.setHitterRunSpeed(presentChar.getHitterRunSpeed()+7);
			}
		}
		else {
			System.out.println("���� ����� !!!");
		}
		purchaseResult=presentChar.getGold();
		purchaseShoes=presentChar.getHitterRunSpeed();
		dao.test(purchaseResult);
		dao.purchaseShoes(purchaseShoes);
	}

	public void gloveSelect() {
		System.out.println(" [ �۷��� ��� ��� ] ");
		//Ÿ��ĳ���� ��� ���
		ArrayList<ItemVO> list = dao.gloveSelect();
		
		
		ItemVO vo=null;
		System.out.println(" ������ �̸� " + " ������ ���� "+" ���� ");
		for(int i=0;i<list.size();i++) {
			vo=list.get(i);
			System.out.println(
					vo.getItemName()+"\t"
					+vo.getSummary()+"\t"
					+vo.getPrice());
		}
		System.out.print("��ȣ ����> ");
		int num = keyin.nextInt();
		item_presentChar=list.get(num-1);
		glovePurchase(item_presentChar);
		
	}
	private void glovePurchase(ItemVO item_presentChar2) {
		
		if(presentChar.getGold()>=300) {
			if(item_presentChar.getItemName().contentEquals("������ �۷���")) {
				System.out.println("������ �۷��� ���ſϷ�");
				presentChar.setGold(presentChar.getGold()-300);
			}
			else if(item_presentChar.getItemName().contentEquals("���� �۷���")) {
				System.out.println("������ �۷��� ���ſϷ�");
				presentChar.setGold(presentChar.getGold()-550);
			}
			else if(item_presentChar.getItemName().contentEquals("�¸��� �۷���")) {
				System.out.println("������ �۷��� ���ſϷ�");
				presentChar.setGold(presentChar.getGold()-700);
			}
		}
		else {
			System.out.println("���� ����� ");
		}
		
		purchaseResult=presentChar.getGold();
		purchaseGlove=presentChar.getPitcherBallControl();
		dao.test(purchaseResult);
		dao.purchaseGlove(purchaseGlove);
	}

	public void foodSelect() {
		System.out.println(" [ ���� ������ ��� ] ");
		//Ÿ��ĳ���� ��� ���
		ArrayList<ItemVO> list = dao.foodSelect();
		ItemVO vo=null;
		System.out.println(" ������ �̸� " + " ������ ���� "+" ���� ");
		for(int i=0;i<list.size();i++) {
			vo=list.get(i);
			System.out.println(
					vo.getItemName()+"\t"
					+vo.getSummary()+"\t"
					+vo.getPrice());
		}
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
			case 2: itemInfo();			break;
			case 3: itemEquipHaveInfo(); break;
			case 0: return;
			default:
			}
		}
	}
	
	public void characterInfoMenuPrint() {
		System.out.println("[ ĳ���� ���� ]");
		System.out.println("1.	����");
		System.out.println("2.	�Һ� ������");
		System.out.println("3.  ��� ������");
		System.out.println("0.	�ڷΰ���");
		System.out.print("����>	");
	}
	
	public void statInfo() {
		System.out.println("[ ����â ]");
		System.out.println();
		System.out.printf("%s", "---------------------------- \n");
		System.out.printf("%5s \t %-10s \t %-4s \t %-5s \t %s \n","|", "ĳ���� �̸�", "|", presentChar.getCharacterName(), "|");
		System.out.printf("%-10s \t %-4s \t %s \n", "Ŭ����", "|", presentChar.getClassName());
		System.out.printf("%-10s \t %-4s \t %d \n", "����", "|", presentChar.getYear());
		System.out.printf("%-10s \t %-4s \t %d \n", "�ý���", "|", presentChar.getAllStat() );
		if (presentChar.getClassName().equals("Ÿ��")) {
			System.out.printf("%-10s \t %-4s \t %d \n", "Power", "|", presentChar.getHitterPower() );
			System.out.printf("%-10s \t %-4s \t %d \n", "Hit", "|", presentChar.getHitterHit() );
			System.out.printf("%-10s \t %-4s \t %d \n", "RunSpeed", "|", presentChar.getHitterRunSpeed() );

		}
		else if (presentChar.getClassName().equals("����")) {
			System.out.printf("%-10s \t %-4s \t %d \n", "BallSpeed", "|", presentChar.getPitcherBallSpeed() );
			System.out.printf("%-10s \t %-4s \t %d \n", "BallControl", "|", presentChar.getPitcherBallControl());
			System.out.printf("%-10s \t %-4s \t %d \n", "Mentality", "|", presentChar.getPitcherMentality() );
		}
		System.out.printf("%-10s \t %-4s \t %d \n", "ü�� ", "|", presentChar.getHealth());
		System.out.printf("%-10s \t %-4s \t %d \n", "�ൿ��", "|", presentChar.getActive());
		
		
	}
	
	public void itemInfo() {
		System.out.println(" [ �� ������ ��� ] ");
		//Ÿ��ĳ���� ��� ���
		ArrayList<ItemInventoryVO> list = dao.itemInfo();
		ItemInventoryVO vo=null;
		System.out.println("--------------------------------------------------");
		System.out.println("��� : " + presentChar.getGold());
		System.out.println("--------------------------------------------------");
		System.out.println(" ������ �̸� " + " ������ ���� "+" ���� ");
		System.out.println(list.size());
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
		}
	}
	
	public void itemEquipHaveInfo() {
		System.out.println("[ �� ��� ������ ��� ] ");
		ArrayList<ItemequipinfoVO> list = dao.itemequiphaveInfo();
		ItemInventoryVO vo=null;
		System.out.println("--------------------------------------------------");
		System.out.println("��� : " + presentChar.getGold());
		System.out.println("--------------------------------------------------");
		System.out.println(" ������ �̸� " + " ������ ���� "+" ���� ");
		System.out.println(list.size());
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
		}
	}
	
	public void goldInfo() {
		
	}

	
	public void match() {
		
	}
	
	public void rest() {
		
	}
	
	
}
	
	
	
	
	

