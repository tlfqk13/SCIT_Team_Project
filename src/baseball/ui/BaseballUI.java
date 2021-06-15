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
			loginMenuPrint();				//메뉴 출력
			int m = keyin.nextInt();	//번호 입력받음
			switch(m) {					//고른 번호에 따라 처리, 0번 고르면 종료
			case 1: join();		break;
			case 2: login();	break;
//			case 3: hof();	break;
			case 0: System.out.println("게임을 종료합니다."); return;
			default:
			}
		}
	}
	
	void loginMenuPrint() {
		System.out.println("[ 나만의 선수 키우기 야구편 ]");
		System.out.println("1.	회원가입");
		System.out.println("2.	로그인");
		System.out.println("3. 	명예의 전당");
		System.out.println("0.	종료");
		System.out.print("선택>	");
	}
	
	public void join() {
		System.out.println("[ 회원 가입 ]");
		String userId, password;
		

		int cnt = 0;
		System.out.print("아이디 : ");
		userId = keyin.next();

		UserVO v = dao.getId(userId);
		if (v != null) {
			System.out.println("이미 사용중인 아이디입니다.");
			return;
		}
		
		System.out.print("비밀번호 : ");
		password = keyin.next();
		
		UserVO vo = new UserVO(userId, password);
		cnt = dao.join(vo);
		
		if( cnt == 0) {
			System.out.println("가입 실패");
		}
		else {
			System.out.println("가입 완료 되었습니다");
		}
		
	}
	
	
	public void login() {
		
		String userId, password;
		
		System.out.print("아이디 : ");
		userId = keyin.next();
		UserVO v = dao.getId(userId);
		//아이디로 회원정보 검색
		if (v == null) {
			System.out.println("해당 아이디의 회원이 없습니다.");
			return;
		}
		System.out.print("비밀번호 : ");
		password = keyin.next();
		//아이디와 비번이 맞는지 확인
		v = dao.getPassword(userId, password);
		if (v == null) {
			System.out.println("비밀번호가 틀렸습니다.");
			return;
		}
		else {
			loginId = userId;
		}
		
		
		//캐릭터 선택 메뉴
		if (loginId == null) {
			System.out.println("로그인 해주세요.");
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
		System.out.println("[ 선수 메뉴 ]");
		System.out.println("1.	선수 생성");
		System.out.println("2.	선수 선택");
		System.out.println("0.	선수 삭제");
		System.out.print("선택>	");
	}
	
	public void characterCreate() {
		String name, className;
		int cnt = 0;
		System.out.println("[ 캐릭터 생성 ]");
		System.out.print("캐릭터 이름 : ");
		name = keyin.next();
		System.out.print("타자 or 투수 > ");
		className = keyin.next();
		UserCharacterVO vo = new UserCharacterVO(loginId, name, className);
		if (className.equals("타자")) {
			cnt = dao.hitterCharacterCreate(vo);
		}
		else if (className.equals("투수")) {
			cnt = dao.pitcherCharacterCreate(vo);
		}
		
		if (cnt != 0) {
			System.out.println("캐릭터가 생성되었습니다.");
		}
		else {
			System.out.println("캐릭터 생성에 실패했습니다.");
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
		System.out.println("[ 캐릭터 선택 ]");
		System.out.println("1.	투수");
		System.out.println("2.	타자");
		System.out.println("0.	종료");
		System.out.print("선택>	");
	}
	
	public void pitcherSelect() {
		System.out.println("[ 투수 캐릭터 목록 ]");
		
		//투수캐릭터 목록 출력
		ArrayList<UserCharacterVO> list = dao.pitcherSelect(loginId);
		
		if (list.size() == 0) {
			System.out.println("캐릭터가 없습니다.");
			return;
		}
		else {
			UserCharacterVO vo = null;
			System.out.printf("%-5s \t %-10s \t %-10s \t %-10s \t %-10s \t %-10s \t %-10s \n", "번호","캐릭터 이름","BallSpeed","BallControl","Mentality","보유금", "연차");
			for (int i = 0; i < list.size(); i++) {
				vo = list.get(i);
				System.out.printf("%-5d \t %-10s \t %-10d \t %-10d \t %-10d \t %-10d \t %-10d \n", (i+1), vo.getCharacterName(),vo.getPitcherBallSpeed(),vo.getPitcherBallControl(),vo.getPitcherMentality(),vo.getGold(), vo.getYear());
			}
			System.out.print("번호 선택> ");
			int num = keyin.nextInt();
			presentChar = list.get(num-1);
			mainMenu();
		}
	}
	
	public void hitterSelect() {
		System.out.println("[ 타자 캐릭터 목록 ]");
		
		//타자캐릭터 목록 출력
		ArrayList<UserCharacterVO> list = dao.hitterSelect(loginId);

		if (list.size() == 0) {
			System.out.println("캐릭터가 없습니다.");
			return;
		}
		else {
			UserCharacterVO vo = null;
			System.out.printf("%-5s \t %-10s \t %-10s \t %-10s \t %-10s \t %-10s \t %-10s \n", "번호", "캐릭터 이름","hitterpower","hitterhit","hitterrunspeed","보유금", "연차");
			for (int i = 0; i < list.size(); i++) {
				vo = list.get(i);
				System.out.printf("%-5d \t %-10s \t %-10d \t %-10d \t %-10d \t %-10d \t %-10d \n", (i+1), vo.getCharacterName(),vo.getHitterPower(),vo.getHitterHit(),vo.getHitterRunSpeed(),vo.getGold(), vo.getYear());
			}
			System.out.print("번호 선택> ");
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
				case 0: System.out.println("게임을 종료합니다."); System.exit(0);
				default:
				}
			}
		}
		else {
			return;
		}
		
		
	}
	
	public void mainMenuPrint() {
		System.out.println("[ 나만의 선수 키우기 야구편 ]");
		System.out.println("1.	상점");
		System.out.println("2.	훈련");
		System.out.println("3. 	캐릭터 정보");
		System.out.println("4. 	경기");
		System.out.println("5. 	휴식");
		System.out.println("0.	종료");
		System.out.print("선택>	");
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
		System.out.println("[ 상점 ]");
		System.out.println("1.	옷");
		System.out.println("2.	헬멧");
		System.out.println("3. 	배트");
		System.out.println("4. 	신발");
		System.out.println("5. 	글러브");
		System.out.println("6.  음식");
		System.out.println("0.	뒤로가기");
		System.out.print("선택>	");
	}

	public void clothSelect() {
		System.out.println(" [ 옷 장비 목록 ] ");
		//타자캐릭터 목록 출력
		ArrayList<ItemVO> list = dao.clothSelect();
		ItemVO vo=null;
		System.out.println(" 아이템 이름 " + " 아이템 설명 "+" 가격 ");
		for(int i=0;i<list.size();i++) {
			vo=list.get(i);
			System.out.println(
					(i+1) + " : " + 
					vo.getItemName()+"\t"
					+vo.getSummary()+"\t"
					+vo.getPrice());
		}
		System.out.print("번호 선택> ");
		int num = keyin.nextInt();
		item_presentChar=list.get(num-1);
		clothPurchase(item_presentChar);
		System.out.println(presentChar.getGold());

	}
	
	public void clothPurchase(ItemVO item_presentChar) {
		
		
		if(presentChar.getGold()>=300) {
			if(item_presentChar.getItemName().contentEquals("영광의 유니폼")) {
				System.out.println("영광의 유니폼 구매완료");
				presentChar.setGold(presentChar.getGold()-300);
			}
			else if(item_presentChar.getItemName().contentEquals("명예의 유니폼")) {
				System.out.println("영광의 유니폼 구매완료");
				presentChar.setGold(presentChar.getGold()-450);
			}
			else if(item_presentChar.getItemName().contentEquals("승리의 유니폼")) {
				System.out.println("영광의 유니폼 구매완료");
				presentChar.setGold(presentChar.getGold()-600);
			}
		}
		else {
			System.out.println("돈이 없어요");
		}
	}
	
	public void helmetSelect() {
		System.out.println(" [ 헬멧 장비 목록 ] ");
		//타자캐릭터 목록 출력
		ArrayList<ItemVO> list = dao.helmetSelect();
		ItemVO vo=null;
		System.out.println(" 아이템 이름 " + " 아이템 설명 "+" 가격 ");
		for(int i=0;i<list.size();i++) {
			vo=list.get(i);
			System.out.println(
					vo.getItemName()+"\t"
					+vo.getSummary()+"\t"
					+vo.getPrice());
		}
		System.out.print("번호 선택> ");
		int num = keyin.nextInt();
		item_presentChar=list.get(num-1);
		helmetPurchase(item_presentChar);
		System.out.println(presentChar.getGold());
	}
	private void helmetPurchase(ItemVO item_presentChar2) {
			
		if(presentChar.getGold()>=300 || presentChar.getGold()>=200) {
			if(item_presentChar.getItemName().contentEquals("영광의 헬멧")) {
				System.out.println("영광의 헬멧 구매완료");
				presentChar.setGold(presentChar.getGold()-200);
			}
			else if(item_presentChar.getItemName().contentEquals("명예의 헬멧")) {
				System.out.println("영광의 헬멧 구매완료");
				presentChar.setGold(presentChar.getGold()-450);
			}
			else if(item_presentChar.getItemName().contentEquals("승리의 헬멧")) {
				System.out.println("영광의 헬멧 구매완료");
				presentChar.setGold(presentChar.getGold()-650);
			}
		}
		else {
			System.out.println("돈이 없어요");
		}
		
		//맵으로 받아서 아이디 where 써서 하면 그냥 수정완료 
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
		System.out.println(" [ 방망이 장비 목록 ] ");
		//타자캐릭터 목록 출력
		ArrayList<ItemVO> list = dao.batSelect();
		ItemVO vo=null;
		System.out.println(" 아이템 이름 " + " 아이템 설명 "+" 가격 ");
		for(int i=0;i<list.size();i++) {
			vo=list.get(i);
			System.out.println(
					vo.getItemName()+"\t"
					+vo.getSummary()+"\t"
					+vo.getPrice());
		}
		System.out.print("번호 선택> ");
		int num = keyin.nextInt();
		item_presentChar=list.get(num-1);
		batPurchase(item_presentChar);
		System.out.println(presentChar.getGold());
	}
	private void batPurchase(ItemVO item_presentChar2) {
		
		if(presentChar.getGold()>=300) {
			if(item_presentChar.getItemName().contentEquals("영광의 방망이")) {
				System.out.println("영광의 방망이 구매완료");
				presentChar.setGold(presentChar.getGold()-600);
				presentChar.setHitterPower(presentChar.getHitterPower()+3);
			}
			else if(item_presentChar.getItemName().contentEquals("명예의 방망이")) {
				System.out.println("영광의 방망이 구매완료");
				presentChar.setGold(presentChar.getGold()-900);
				presentChar.setHitterPower(presentChar.getHitterPower()+5);
			}
			else if(item_presentChar.getItemName().contentEquals("승리의 방망이")) {
				System.out.println("영광의 방망이 구매완료");
				presentChar.setGold(presentChar.getGold()-1200);
				presentChar.setHitterPower(presentChar.getHitterPower()+7);
			}
		}
		else {
			System.out.println("돈이 없어요");
		}
		purchaseResult=presentChar.getGold();
		purchaseBat=presentChar.getHitterPower();
		dao.test(purchaseResult);
		dao.purchaseBat(purchaseBat);
	}

	public void shoesSelect() {
		System.out.println(" [ 신발 장비 목록 ] ");
		//타자캐릭터 목록 출력
		ArrayList<ItemVO> list = dao.shoesSelect();
		ItemVO vo=null;
		System.out.println(" 아이템 이름 " + " 아이템 설명 "+" 가격 ");
		for(int i=0;i<list.size();i++) {
			vo=list.get(i);
			System.out.println(
					vo.getItemName()+"\t"
					+vo.getSummary()+"\t"
					+vo.getPrice());
		}
		System.out.print("번호 선택> ");
		int num = keyin.nextInt();
		item_presentChar=list.get(num-1);
		shoesPurchase(item_presentChar);
		System.out.println(presentChar.getGold());
	}
	private void shoesPurchase(ItemVO item_presentChar2) {
		if(presentChar.getGold()>=300) {
			if(item_presentChar.getItemName().contentEquals("영광의 신발")) {
				System.out.println("영광의 신발 구매완료");
				presentChar.setGold(presentChar.getGold()-300);
				presentChar.setHitterRunSpeed(presentChar.getHitterRunSpeed()+3);
			}
			else if(item_presentChar.getItemName().contentEquals("명예의 신발")) {
				System.out.println("영광의 신발 구매완료");
				presentChar.setGold(presentChar.getGold()-400);
				presentChar.setHitterRunSpeed(presentChar.getHitterRunSpeed()+5);
			}
			else if(item_presentChar.getItemName().contentEquals("승리의 신발")) {
				System.out.println("영광의 신발 구매완료");
				presentChar.setGold(presentChar.getGold()-500);
				presentChar.setHitterRunSpeed(presentChar.getHitterRunSpeed()+7);
			}
		}
		else {
			System.out.println("돈이 없어요 !!!");
		}
		purchaseResult=presentChar.getGold();
		purchaseShoes=presentChar.getHitterRunSpeed();
		dao.test(purchaseResult);
		dao.purchaseShoes(purchaseShoes);
	}

	public void gloveSelect() {
		System.out.println(" [ 글러브 장비 목록 ] ");
		//타자캐릭터 목록 출력
		ArrayList<ItemVO> list = dao.gloveSelect();
		
		
		ItemVO vo=null;
		System.out.println(" 아이템 이름 " + " 아이템 설명 "+" 가격 ");
		for(int i=0;i<list.size();i++) {
			vo=list.get(i);
			System.out.println(
					vo.getItemName()+"\t"
					+vo.getSummary()+"\t"
					+vo.getPrice());
		}
		System.out.print("번호 선택> ");
		int num = keyin.nextInt();
		item_presentChar=list.get(num-1);
		glovePurchase(item_presentChar);
		
	}
	private void glovePurchase(ItemVO item_presentChar2) {
		
		if(presentChar.getGold()>=300) {
			if(item_presentChar.getItemName().contentEquals("영광의 글러브")) {
				System.out.println("영광의 글러브 구매완료");
				presentChar.setGold(presentChar.getGold()-300);
			}
			else if(item_presentChar.getItemName().contentEquals("명예의 글러브")) {
				System.out.println("영광의 글러브 구매완료");
				presentChar.setGold(presentChar.getGold()-550);
			}
			else if(item_presentChar.getItemName().contentEquals("승리의 글러브")) {
				System.out.println("영광의 글러브 구매완료");
				presentChar.setGold(presentChar.getGold()-700);
			}
		}
		else {
			System.out.println("돈이 없어요 ");
		}
		
		purchaseResult=presentChar.getGold();
		purchaseGlove=presentChar.getPitcherBallControl();
		dao.test(purchaseResult);
		dao.purchaseGlove(purchaseGlove);
	}

	public void foodSelect() {
		System.out.println(" [ 음식 아이템 목록 ] ");
		//타자캐릭터 목록 출력
		ArrayList<ItemVO> list = dao.foodSelect();
		ItemVO vo=null;
		System.out.println(" 아이템 이름 " + " 아이템 설명 "+" 가격 ");
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
			System.out.println("코치 목록");
			
			//코치 목록 출력/타자면 타자코치만 나오고 투수면 투수코치만 나오게 하기.
//			TrainerVO tvo = dao.getTrainer(m, presentChar.get);
//			for(int i = 0; i < tvo.size(); i++) {
//				System.out.println(presentChar.getClassid + " 코치");
//				System.out.printf("%-4d \t %-3 ");
//			}
			
			System.out.print("선택> ");
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
		System.out.println("[ 캐릭터 정보 ]");
		System.out.println("1.	스탯");
		System.out.println("2.	소비 아이템");
		System.out.println("3.  장비 아이템");
		System.out.println("0.	뒤로가기");
		System.out.print("선택>	");
	}
	
	public void statInfo() {
		System.out.println("[ 스탯창 ]");
		System.out.println();
		System.out.printf("%s", "---------------------------- \n");
		System.out.printf("%5s \t %-10s \t %-4s \t %-5s \t %s \n","|", "캐릭터 이름", "|", presentChar.getCharacterName(), "|");
		System.out.printf("%-10s \t %-4s \t %s \n", "클래스", "|", presentChar.getClassName());
		System.out.printf("%-10s \t %-4s \t %d \n", "연차", "|", presentChar.getYear());
		System.out.printf("%-10s \t %-4s \t %d \n", "올스탯", "|", presentChar.getAllStat() );
		if (presentChar.getClassName().equals("타자")) {
			System.out.printf("%-10s \t %-4s \t %d \n", "Power", "|", presentChar.getHitterPower() );
			System.out.printf("%-10s \t %-4s \t %d \n", "Hit", "|", presentChar.getHitterHit() );
			System.out.printf("%-10s \t %-4s \t %d \n", "RunSpeed", "|", presentChar.getHitterRunSpeed() );

		}
		else if (presentChar.getClassName().equals("투수")) {
			System.out.printf("%-10s \t %-4s \t %d \n", "BallSpeed", "|", presentChar.getPitcherBallSpeed() );
			System.out.printf("%-10s \t %-4s \t %d \n", "BallControl", "|", presentChar.getPitcherBallControl());
			System.out.printf("%-10s \t %-4s \t %d \n", "Mentality", "|", presentChar.getPitcherMentality() );
		}
		System.out.printf("%-10s \t %-4s \t %d \n", "체력 ", "|", presentChar.getHealth());
		System.out.printf("%-10s \t %-4s \t %d \n", "행동력", "|", presentChar.getActive());
		
		
	}
	
	public void itemInfo() {
		System.out.println(" [ 내 아이템 목록 ] ");
		//타자캐릭터 목록 출력
		ArrayList<ItemInventoryVO> list = dao.itemInfo();
		ItemInventoryVO vo=null;
		System.out.println("--------------------------------------------------");
		System.out.println("골드 : " + presentChar.getGold());
		System.out.println("--------------------------------------------------");
		System.out.println(" 아이템 이름 " + " 아이템 설명 "+" 가격 ");
		System.out.println(list.size());
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
		}
	}
	
	public void itemEquipHaveInfo() {
		System.out.println("[ 내 장비 아이템 목록 ] ");
		ArrayList<ItemequipinfoVO> list = dao.itemequiphaveInfo();
		ItemInventoryVO vo=null;
		System.out.println("--------------------------------------------------");
		System.out.println("골드 : " + presentChar.getGold());
		System.out.println("--------------------------------------------------");
		System.out.println(" 아이템 이름 " + " 아이템 설명 "+" 가격 ");
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
	
	
	
	
	

