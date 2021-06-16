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
			loginMenuPrint();				//메뉴 출력
			try {
				m = keyin.nextInt();
			}
			catch (InputMismatchException e) {
				keyin.nextLine();
				System.out.println("다시 입력하세요.");
				continue;
			}
			switch(m) {					//고른 번호에 따라 처리, 0번 고르면 종료
			case 1: join();		break;
			case 2: login();	break;
			case 3: hof();	break;
			case 0: System.out.println("게임을 종료합니다."); return;
			default:
			}
		}
	}
	
	void loginMenuPrint() {
		System.out.println("[ 나만의 선수 키우기 야구편 ]");
		System.out.println("1.	회원가입");
		System.out.println("2.	로그인");
		System.out.println("3. 	은퇴선수");
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
				int m = 0;
				characterMenuPrint();				
				try {
					m = keyin.nextInt();
				}
				catch (InputMismatchException e) {
					keyin.nextLine();
					System.out.println("다시 입력하세요.");
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
		while (true) {
			System.out.print("타자 or 투수 > ");
			className = keyin.next();
			if (className.equals("타자") || className.equals("투수")) {
				break;
			}
			else {
				System.out.println("다시 입력하세요.");
				continue;
			}
		}
		
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
			int m = 0;
			selectMenuPrint();
			try {
				m = keyin.nextInt();
			}
			catch (InputMismatchException e) {
				keyin.nextLine();
				System.out.println("다시 입력하세요.");
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
		System.out.println("[ 캐릭터 선택 ]");
		System.out.println("1.	투수");
		System.out.println("2.	타자");
		System.out.println("0.	종료");
		System.out.print("선택>	");
	}
	
	public void pitcherSelect() {
		int num = 0;
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
			while (true) {
				System.out.print("번호 선택> ");
				try {
					num = keyin.nextInt();
					break;
				}
				catch (InputMismatchException e) {
					keyin.nextLine();
					System.out.println("다시 입력하세요.");
					continue;
				}
			}
			
			presentCharId = list.get(num-1).getCharacterId();
			mainMenu();
		}
	}
	
	public void hitterSelect() {
		int num = 0;
		System.out.println("[ 타자 캐릭터 목록 ]");
		
		//타자캐릭터 목록 출력
		ArrayList<UserCharacterVO> list = dao.hitterSelect(loginId);

		if (list.size() == 0) {
			System.out.println("캐릭터가 없습니다.");
			return;
		}
		else {
			UserCharacterVO vo = null;
			System.out.printf("%-5s \t %-10s \t %-10s \t %-10s \t %-10s \t %-10s \t %-10s \n", "번호", "캐릭터 이름","BallSpeed","BallControl","Mentality","보유금", "연차");
			for (int i = 0; i < list.size(); i++) {
				vo = list.get(i);
				System.out.printf("%-5d \t %-10s \t %-10d \t %-10d \t %-10d \t %-10d \t %-10d \n", (i+1), vo.getCharacterName(),vo.getPitcherBallSpeed(),vo.getPitcherBallControl(),vo.getPitcherMentality(),vo.getGold(), vo.getYear());
			}
			while (true) {
				System.out.print("번호 선택> ");
				try {
					num = keyin.nextInt();
					break;
				}
				catch (InputMismatchException e) {
					keyin.nextLine();
					System.out.println("다시 입력하세요.");
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
					System.out.println("다시 입력하세요.");
					continue;
				}	
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
		System.out.println("[ 상점 ]");
		System.out.println("1.	옷");
		System.out.println("2.	헬멧");
		System.out.println("3. 	배트");
		System.out.println("4. 	신발");
		System.out.println("5. 	음식");
		System.out.println("0.	뒤로가기");
		System.out.print("선택>	");
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
			if (loginId != null && presentCharId != 0) {
				UserCharacterVO vo = dao.getCharacter(loginId, presentCharId);
				String charClassName = vo.getClassName();
				while (true) {
					if (charClassName.equals("타자")) {
						hitterTrainingMenuPrint();
						try {
							m = keyin.nextInt();
						}
						catch (InputMismatchException e) {
							keyin.nextLine();
							System.out.println("다시 입력하세요.");
							continue;
						}	
						switch(m) {		
//						case 1: powerTraining();			break;
//						case 2: hitTraining();			break;
//						case 3: runSpeedTraining();	break;
						case 0: System.out.println("메인 메뉴로 돌아갑니다."); return;
						default:
						}
					}
				
					else if (charClassName.equals("투수")) {
						pitcherTrainingMenuPrint();
						try {
							m = keyin.nextInt();
						}
						catch (InputMismatchException e) {
							keyin.nextLine();
							System.out.println("다시 입력하세요.");
							continue;
						}	
						switch(m) {		
//						case 1: ballSpeedTraining();			break;
//						case 2: ballControlTraining();			break;
//						case 3: mentalityTraining();	break;
						case 0: System.out.println("메인 메뉴로 돌아갑니다."); return;
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
		System.out.println("[ 타자 훈련 ]");
		System.out.println("1.	파워 훈련");
		System.out.println("2.	타격 훈련");
		System.out.println("3. 	주루 훈련");
		System.out.println("0.	뒤로가기");
		System.out.print("선택>	");
	}

	public void pitcherTrainingMenuPrint() {
		System.out.println("[ 투수 훈련 ]");
		System.out.println("1.	볼스피드 훈련");
		System.out.println("2.	볼컨트롤 훈련");
		System.out.println("3. 	정신력 훈련");
		System.out.println("0.	뒤로가기");
		System.out.print("선택>	");
	}
	
	public void coachMenuPrint() {
		System.out.println("[ 코치 선택 ]");
		
		System.out.print("선택>	");
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
		System.out.println("[ 캐릭터 정보 ]");
		System.out.println("1.	스탯");
		System.out.println("2.	아이템");
		System.out.println("0.	뒤로가기");
		System.out.print("선택>	");
	}
	
	public void statInfo() {
		UserCharacterVO presentChar = dao.getCharacter(loginId, presentCharId);
		System.out.printf("%-25s \n", "[ 스탯창 ]");
		System.out.println();
		System.out.printf("%s", " ---------------------------------------- \n");
		System.out.printf("%-3s \t %-10s \t %-4s \t %-5s \t %s \n","|", "캐릭터 이름", "|", presentChar.getCharacterName(), "|");
		System.out.printf("%-3s \t %-10s \t %-4s \t %-5s \t %s \n","|", "클래스", "|", presentChar.getClassName(), "|");
		System.out.printf("%-3s \t %-10s \t %-4s \t %-5d \t %s \n","|", "연차", "|", presentChar.getYear(), "|");
		System.out.printf("%-3s \t %-10s \t %-4s \t %-5d \t %s \n","|", "올스탯", "|", presentChar.getAllStat() , "|");
		if (presentChar.getClassName().equals("타자")) {
			System.out.printf("%-3s \t %-10s \t %-4s \t %-5d \t %s \n","|", "파워", "|", presentChar.getHitterPower(), "|" );
			System.out.printf("%-3s \t %-10s \t %-4s \t %-5d \t %s \n","|", "타격", "|", presentChar.getHitterHit() , "|");
			System.out.printf("%-3s \t %-10s \t %-4s \t %-5d \t %s \n","|", "주루", "|", presentChar.getHitterRunSpeed() , "|");

		}
		else if (presentChar.getClassName().equals("투수")) {
			System.out.printf("%-3s \t %-10s \t %-4s \t %-5d \t %s \n","|", "볼스피드", "|", presentChar.getPitcherBallSpeed() , "|");
			System.out.printf("%-3s \t %-10s \t %-4s \t %-5d \t %s \n","|", "볼컨트롤", "|", presentChar.getPitcherBallControl(), "|");
			System.out.printf("%-3s \t %-10s \t %-4s \t %-5d \t %s \n","|", "정신력", "|", presentChar.getPitcherMentality(), "|" );
		}
		System.out.printf("%-3s \t %-10s \t %-4s \t %-5d \t %s \n","|", "체력 ", "|", presentChar.getHealth(), "|");
		System.out.printf("%-3s \t %-10s \t %-4s \t %-5d \t %s \n","|", "행동력", "|", presentChar.getActive(), "|");
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
				System.out.println("다시 입력하세요.");
				continue;
			}	
			switch(m) {					
			case 1: hitterHofCharacter();		break;
			case 2: pitcherHofCharacter();		break;
			case 3: hitterKickCharacter();		break;
			case 4: pitcherKickCharacter(); 	break;
			case 0: System.out.println("메인메뉴로 돌아갑니다.");; 	break;
			default:
			}
		}
		
		
	}
	
	public void hofMenuPrint() {
		System.out.println("[ 은퇴선수 ]");
		System.out.println("1.	명예의 전당(타자)");
		System.out.println("2.	명예의 전당(투수)");
		System.out.println("3. 	일반 은퇴 선수(타자)");
		System.out.println("4. 	일반 은퇴 선수(투수)");
		System.out.println("0. 	뒤로");
		System.out.print("선택>	");
	}
	
	public void hitterHofCharacter() {
		ArrayList<HofVO> list = null;
		HofVO vo = null;
		System.out.printf("%35s \n", "[ 명예의 전당 ]");
		System.out.println();
		System.out.printf("%33s \n", "- 타자 -");
		System.out.printf("%s", " ---------------------------------------------------------------- \n");
		System.out.printf("%s  %-10s \t %-4s \t %-12s \t %-4s \t %-5s \t %s \n","|   ", "유저ID", "|", "캐릭터 이름" , "|", "올스탯", "|");
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
		System.out.printf("%35s \n", "[ 명예의 전당 ]");
		System.out.println();
		System.out.printf("%33s \n", "- 투수 -");
		System.out.printf("%s", " ---------------------------------------------------------------- \n");
		System.out.printf("%-3s \t %-10s \t %-4s \t %-12s \t %-4s \t %-5s \t %s \n","|", "유저ID", "|", "캐릭터 이름" , "|", "올스탯", "|");
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
		System.out.printf("%35s \n", "- 일반 은퇴 선수 -");
		System.out.println();
		System.out.printf("%33s \n", "- 타자 -");
		System.out.printf("%s", " ---------------------------------------------------------------- \n");
		System.out.printf("%s  %-10s \t %-4s \t %-12s \t %-4s \t %-5s \t %s \n","|   ", "유저ID", "|", "캐릭터 이름" , "|", "올스탯", "|");
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
		System.out.printf("%35s \n", "- 일반 은퇴 선수 -");
		System.out.println();
		System.out.printf("%33s \n", "- 투수 -");
		System.out.printf("%s", " ---------------------------------------------------------------- \n");
		System.out.printf("%s  %-10s \t %-4s \t %-12s \t %-4s \t %-5s \t %s \n","|   ", "유저ID", "|", "캐릭터 이름" , "|", "올스탯", "|");
		list = dao.getPitcherKickCharacter();
		for (int i = 0; i < list.size(); i++) {
			vo = list.get(i);
			System.out.printf("%-3s \t %-10s \t %-4s \t %-12s \t %-4s \t %-5d \t %s \n","|", vo.getUserid(), "|", vo.getCharacterName() , "|", vo.getAllStat(), "|");
		}
		System.out.printf("%s", " ---------------------------------------------------------------- \n");
	}
	
	public void temp() {
		//5년차 경기 뛰면  명예의전당테이블로 옮기고 삭제
		UserCharacterVO vo = dao.getCharacter(loginId, presentCharId);
		if (vo.getYear() < 5) {
			//경기
		}
		else {
			int m = dao.hofCharacterInsert(vo);
			int n = dao.deleteCharacter(loginId, presentCharId);
			presentCharId = 0;
			return;
		}
		
	}
}
	
	
	
	
	

