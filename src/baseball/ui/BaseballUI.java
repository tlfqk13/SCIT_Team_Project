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
//			case 3:	idDelete();	break;
			case 4: ranking(); 	break;
			case 5: hof();		break;
			case 0: System.out.println("게임을 종료합니다."); return;
			default:
			}
		}
	}
	
	void loginMenuPrint() {
		System.out.println("[ 나만의 선수 키우기 야구편 ]");
		System.out.println("1.	회원가입");
		System.out.println("2.	로그인");
		System.out.println("3. 	회원탈퇴");
		System.out.println("4. 	랭킹(올스탯)");
		System.out.println("5. 	은퇴선수");
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
//		System.out.printf("%s  %-6s \t %-2s  %-6s \t %-2s \n","|   ", "번호", "|", "유저 아이디" , "|");
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
//				System.out.println("다시 입력하세요.");
//				continue;
//			}
//		}
		
//		vo = list.get(m-1);
//		int d = dao.deleteId(vo.getUserId());
//		if (d == 1) {
//			System.out.println("아이디가 삭제되었습니다.");
//			presentChar = null;
//		}
//		else {
//			System.out.println("아이디 삭제에 실패했습니다.");
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
				System.out.println("다시 입력하세요.");
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
		System.out.println("[ 랭킹 ]");
		System.out.println("1.	투수 랭킹");
		System.out.println("2.	타자 랭킹");
		System.out.println("0. 	뒤로");
		System.out.print("선택>	");
	}
	
	public void pitcherRanking() {
		ArrayList<UserCharacterVO> list = dao.pitcherRanking();
		System.out.printf("%s", " -------------------------------------------------------------------------------- \n");
		System.out.printf("%s  %-6s \t %-2s  %-6s \t %-2s  %-6s \t %-2s  %-6s \t %-2s  %-7s \t %-2s \n","|   ", "등수", "|", "캐릭터 이름" , "|", "올스탯", "|", "보유금", "|", "연차", "|");
		System.out.printf("%s", " -------------------------------------------------------------------------------- \n");
		for (int i = 0; i < list.size(); i++) {
			UserCharacterVO vo = list.get(i);
			System.out.printf("%s  %-6s \t %-2s  %-6s \t %-2s  %-6d \t %-2s  %-6d \t %-2s  %-7d \t %-2s \n","|   ", ((i+1)+"등"), "|", vo.getCharacterName(), "|", vo.getAllStat(), "|", vo.getGold(), "|",vo.getYear(), "|");
		}
		System.out.printf("%s", " -------------------------------------------------------------------------------- \n");
	}
	
	public void hitterRanking() {
		ArrayList<UserCharacterVO> list = dao.hitterRanking();
		System.out.printf("%s", " -------------------------------------------------------------------------------- \n");
		System.out.printf("%s  %-6s \t %-2s  %-6s \t %-2s  %-6s \t %-2s  %-6s \t %-2s  %-7s \t %-2s \n","|   ", "등수", "|", "캐릭터 이름" , "|", "올스탯", "|", "보유금", "|", "연차", "|");
		System.out.printf("%s", " -------------------------------------------------------------------------------- \n");
		for (int i = 0; i < list.size(); i++) {
			UserCharacterVO vo = list.get(i);
			System.out.printf("%s  %-6s \t %-2s  %-6s \t %-2s  %-6d \t %-2s  %-6d \t %-2s  %-7d \t %-2s \n","|   ", ((i+1)+"등"), "|", vo.getCharacterName(), "|", vo.getAllStat(), "|", vo.getGold(), "|",vo.getYear(), "|");
		}
		System.out.printf("%s", " -------------------------------------------------------------------------------- \n");
	}
	
	
	public void characterMenuPrint() {
		System.out.println("[ 선수 메뉴 ]");
		System.out.println("1.	선수 생성");
		System.out.println("2.	선수 선택");
		System.out.println("3.	선수 삭제");
		System.out.println("4. 	로그아웃");
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
			System.out.println("[ 캐릭터 선택 ]");
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
			System.out.println("[ 캐릭터 삭제 ]");
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
			case 1: pitcherDelete(); 	break;
			case 2: hitterDelete(); 	break;
			case 0: return;
			default:
			}
		}
		
	}
	
	public void selectMenuPrint() {
		System.out.println("1.	투수");
		System.out.println("2.	타자");
		System.out.println("0.	뒤로");
		System.out.print("선택>	");
	}
	
	public void pitcherDelete() {
		pitcherSelect();
		
		int d = dao.deleteCharacter(presentChar.getUserId(), presentChar.getCharacterId());
		if (d == 1) {
			System.out.println("캐릭터가 삭제되었습니다.");
			presentChar = null;
		}
		else {
			System.out.println("캐릭터 삭제에 실패했습니다.");
		}
		
	}
	
	public void hitterDelete() {
		hitterSelect();
		
		int d = dao.deleteCharacter(presentChar.getUserId(), presentChar.getCharacterId());
		if (d == 1) {
			System.out.println("캐릭터가 삭제되었습니다.");
			presentChar = null;
		}
		else {
			System.out.println("캐릭터 삭제에 실패했습니다.");
		}
	}
	
	public void pitcherSelect() {
		int num = 0;
		System.out.printf("%50s", "[ 투수 캐릭터 목록 ] \n");
		
		//투수캐릭터 목록 출력
		ArrayList<UserCharacterVO> list = dao.pitcherSelect(loginId);
		
		if (list.size() == 0) {
			System.out.println("캐릭터가 없습니다.");
			return;
		}
		else {
			UserCharacterVO vo = null;
			System.out.printf("%s", " -------------------------------------------------------------------------------- \n");
			System.out.printf("%s  %-6s \t %-2s  %-6s \t %-2s  %-6s \t %-2s  %-6s \t %-2s  %-7s \t %-2s \n","|   ", "번호", "|", "캐릭터 이름" , "|", "올스탯", "|", "보유금", "|", "연차", "|");
			System.out.printf("%s", " -------------------------------------------------------------------------------- \n");
			for (int i = 0; i < list.size(); i++) {
				vo = list.get(i);
				System.out.printf("%s  %-6d \t %-2s  %-6s \t %-2s  %-6d \t %-2s  %-6d \t %-2s  %-7d \t %-2s \n","|   ", (i+1), "|", vo.getCharacterName(), "|", vo.getAllStat(), "|", vo.getGold(), "|",vo.getYear(), "|");
			}
			System.out.printf("%s", " -------------------------------------------------------------------------------- \n");

			while (true) {
				System.out.print("번호 선택> ");
				try {
					num = keyin.nextInt();
					if (num <= list.size()) {
						break;	
					}
					else {
						System.out.println("다시 입력하세요.");
						continue;
					}
				}
				catch (InputMismatchException e) {
					keyin.nextLine();
					System.out.println("다시 입력하세요.");
					continue;
				}
			}
			
			presentChar = list.get(num-1);
			
		}
	}
	
	public void hitterSelect() {
		int num = 0;
		System.out.printf("%50s", "[ 타자 캐릭터 목록 ] \n");
		
		//타자캐릭터 목록 출력
		ArrayList<UserCharacterVO> list = dao.hitterSelect(loginId);

		if (list.size() == 0) {
			System.out.println("캐릭터가 없습니다.");
			return;
		}
		else {
			UserCharacterVO vo = null;
			System.out.printf("%s", " -------------------------------------------------------------------------------- \n");
			System.out.printf("%s  %-6s \t %-2s  %-6s \t %-2s  %-6s \t %-2s  %-6s \t %-2s  %-7s \t %-2s \n","|   ", "번호", "|", "캐릭터 이름" , "|", "올스탯", "|", "보유금", "|", "연차", "|");
			System.out.printf("%s", " -------------------------------------------------------------------------------- \n");
			for (int i = 0; i < list.size(); i++) {
				vo = list.get(i);
				System.out.printf("%s  %-6d \t %-2s  %-6s \t %-2s  %-6d \t %-2s  %-6d \t %-2s  %-7d \t %-2s \n","|   ", (i+1), "|", vo.getCharacterName(), "|", vo.getAllStat(), "|", vo.getGold(), "|",vo.getYear(), "|");
			}
			System.out.printf("%s", " -------------------------------------------------------------------------------- \n");

			while (true) {
				System.out.print("번호 선택> ");
				try {
					num = keyin.nextInt();
					if (num <= list.size()) {
						break;	
					}
					else {
						System.out.println("다시 입력하세요.");
						continue;
					}
				}
				catch (InputMismatchException e) {
					keyin.nextLine();
					System.out.println("다시 입력하세요.");
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
					System.out.println("다시 입력하세요.");
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
		System.out.println("6. 	퀴즈");
		System.out.println("7. 	캐릭터 선택창");
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
			if (loginId != null && presentChar != null) {
				String charClassName = presentChar.getClassName();
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
		System.out.printf("%25s \n", "[ 스탯창 ]");
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
		System.out.println("[ 경기 하기 ]");
		System.out.println("경기 시작");
		if (presentChar.getYear() < 5) {
			presentChar.setActive(5);
			presentChar.setHealth(100);
			presentChar.setGold(presentChar.getGold()+500);
			presentChar.setYear(presentChar.getYear() + 1);
			System.out.println("행동력이 5로 회복 되었습니다.");
			System.out.println("소지금이 증가 하였습니다.");
			System.out.println("건강이 100으로 회복 되었습니다.");
			System.out.println("연차가 1년 늘었습니다.");
		}
		else {
			int m = dao.hofCharacterInsert(presentChar);
			if (m == 0) {
				System.out.println("명예의 전당에 반영되었습니다.");
			}
			dao.deleteCharacter(loginId, presentChar.getCharacterId());
			presentChar = null;
			return;
		}
		
		
		
		//캐릭터 정보 db에 반영하기
		//dao.     (presentChar);
		
		
//		public void temp() {
//		//5년차 경기 뛰면  명예의전당테이블로 옮기고 삭제
//		UserCharacterVO vo = dao.getCharacter(loginId, presentCharId);
//		if (vo.getYear() < 5) {
//			//경기
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
				System.out.println("다시 입력하세요.");
				continue;
			}	
			switch(m) {		
			case 1: freeRest();			break;
			case 2: premiumRest();			break;
			case 0: System.out.println("메인 메뉴로 돌아갑니다."); return;
			default:
			}
		}
		
	}
	
	public void restMenuPrint() {
		System.out.println("[ 휴식 하기 ]");
		System.out.println("1.	일반 휴식");
		System.out.println("2.	고급 휴식");
		System.out.println("0.	뒤로 가기");
		System.out.print("선택>	");
	}
	
	public void freeRest() {
		if (presentChar.getHealth() < 50) {
			presentChar.setActive(presentChar.getActive()-1);
			presentChar.setHealth(50);
			System.out.println("일반 휴식이 완료 되었습니다.");
		}
		else {
			System.out.println("체력이 충분합니다.");
			return;
		}
		
	//캐릭터 정보 db에 반영하기
	//dao.     (presentChar);

	}
	
	public void premiumRest() {
		
		if (presentChar.getHealth() != 100) {
			presentChar.setActive(presentChar.getActive()-1);
			presentChar.setGold(presentChar.getGold()-100);
			presentChar.setHealth(10);
			System.out.println("프리미엄 휴식이 완료되었습니다.");
			System.out.println("소지금이 감소하였습니다.");
		}
		else {
			System.out.println("체력이 충분합니다.");
			return;
		}
		
		//캐릭터 정보 db에 반영하기
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
				System.out.println("다시 입력하세요.");
				continue;
			}	
			switch(m) {					
			case 1: hitterHofCharacter();		break;
			case 2: pitcherHofCharacter();		break;
			case 3: hitterKickCharacter();		break;
			case 4: pitcherKickCharacter(); 	break;
			case 0: System.out.println("메인메뉴로 돌아갑니다."); 	return;
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
		System.out.printf("%35s \n", "[ 일반 은퇴 선수 ]");
		System.out.println();
		System.out.printf("%33s \n", "- 타자 -");
		System.out.printf("%s", " ---------------------------------------------------------------- \n");
		System.out.printf("%s  %-10s \t %-4s \t %-12s \t %-4s \t %-5s \t %s \n","|   ", "유저ID", "|", "캐릭터 이름" , "|", "올스탯", "|");
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
		System.out.printf("%35s \n", "[ 일반 은퇴 선수 ]");
		System.out.println();
		System.out.printf("%33s \n", "- 투수 -");
		System.out.printf("%s", " ---------------------------------------------------------------- \n");
		System.out.printf("%s  %-10s \t %-4s \t %-12s \t %-4s \t %-5s \t %s \n","|   ", "유저ID", "|", "캐릭터 이름" , "|", "올스탯", "|");
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
				System.out.println("다시 입력하세요.");
				continue;
			}	
			switch(m) {					
			case 1: quizQuestion();		break;
			case 2: quizScore();		break;
			case 3: quizRanking(); 			break;
			case 0: System.out.println("메인메뉴로 돌아갑니다."); 	return;
			default:
			}
		}	
	}
	
	public void quizMenuPrint() {
		System.out.println("[ 퀴즈 ]");
		System.out.println("1.	퀴즈 풀기");
		System.out.println("2.	캐릭터별 퀴즈 정답률");
		System.out.println("3.	정답률 순위");
		System.out.println("0.	뒤로 가기");
		System.out.print("선택>	");
	}
	
	public void quizEndMenuPrint() {
		System.out.println("1.	한번 더");
		System.out.println("2.	끝내기");
		System.out.print("선택>	");
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
				System.out.println((i+1) + "번 문제");
				System.out.println(vo.getQuestion());
				if (vo.getExample1()==null && vo.getExample2()==null && vo.getExample3()==null && vo.getExample4()==null) {
					while (true) {
						try {
							keyin.nextLine();
							System.out.print("정답 : ");
							answer = keyin.nextLine();
							if (answer.equalsIgnoreCase(vo.getCorrect())) {
								System.out.println("맞았습니다.");
								scoreVo.setCorrectAnswer(scoreVo.getCorrectAnswer() + 1);
								break;
							}
							else {
								System.out.print("틀렸습니다. ");
								System.out.println("답 : " + vo.getCorrect());
								scoreVo.setWrongAnswer(scoreVo.getWrongAnswer() + 1);
								break;
							}
						}
						catch (Exception e) {
							e.printStackTrace();
							System.out.println("다시 입력하세요.");
							continue;
						}
					}
				}
				else {
					//퀴즈 보기 섞기
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
						System.out.print("정답 : ");
						try {
							m = keyin.nextInt();
							if (m > 4 || m <= 0) {
								System.out.println("다시 입력하세요.");
								continue;
							}
							else if (m == 1) {
								if (vo.getExample1().equals(vo.getCorrect())) {
									System.out.println("맞았습니다.");
									scoreVo.setCorrectAnswer(scoreVo.getCorrectAnswer() + 1);
									break;
								}
								else {
									System.out.print("틀렸습니다. ");
									if (vo.getExample2().equals(vo.getCorrect())) {
										System.out.println("답 : 2 - " + vo.getExample2());
									}
									else if (vo.getExample3().equals(vo.getCorrect())) {
										System.out.println("답 : 3 - " + vo.getExample3());
									}
									else if (vo.getExample4().equals(vo.getCorrect())) {
										System.out.println("답 : 4 - " + vo.getExample4());
									}
									scoreVo.setWrongAnswer(scoreVo.getWrongAnswer() + 1);
									break;
								}
							}
							else if (m == 2) {
								if (vo.getExample2().equals(vo.getCorrect())) {
									System.out.println("맞았습니다.");
									scoreVo.setCorrectAnswer(scoreVo.getCorrectAnswer() + 1);
									break;
								}
								else {
									System.out.print("틀렸습니다. ");
									if (vo.getExample1().equals(vo.getCorrect())) {
										System.out.println("답 : 1 - " + vo.getExample1());
									}
									else if (vo.getExample3().equals(vo.getCorrect())) {
										System.out.println("답 : 3 - " + vo.getExample3());
									}
									else if (vo.getExample4().equals(vo.getCorrect())) {
										System.out.println("답 : 4 - " + vo.getExample4());
									}
									scoreVo.setWrongAnswer(scoreVo.getWrongAnswer() + 1);
									break;
								}
							}
							else if (m == 3) {
								if (vo.getExample3().equals(vo.getCorrect())) {
									System.out.println("맞았습니다.");
									scoreVo.setCorrectAnswer(scoreVo.getCorrectAnswer() + 1);
									break;
								}
								else {
									System.out.print("틀렸습니다. ");
									if (vo.getExample1().equals(vo.getCorrect())) {
										System.out.println("답 : 1 - " + vo.getExample1());
									}
									else if (vo.getExample2().equals(vo.getCorrect())) {
										System.out.println("답 : 2 - " + vo.getExample2());
									}
									else if (vo.getExample4().equals(vo.getCorrect())) {
										System.out.println("답 : 4 - " + vo.getExample4());
									}
									scoreVo.setWrongAnswer(scoreVo.getWrongAnswer() + 1);
									break;
								}
							}
							else if (m == 4) {
								if (vo.getExample4().equals(vo.getCorrect())) {
									System.out.println("맞았습니다.");
									scoreVo.setCorrectAnswer(scoreVo.getCorrectAnswer() + 1);
									break;
								}
								else {
									System.out.print("틀렸습니다. ");
									if (vo.getExample1().equals(vo.getCorrect())) {
										System.out.println("답 : 1 - " + vo.getExample1());
									}
									else if (vo.getExample2().equals(vo.getCorrect())) {
										System.out.println("답 : 2 - " + vo.getExample2());
									}
									else if (vo.getExample3().equals(vo.getCorrect())) {
										System.out.println("답 : 3 - " + vo.getExample3());
									}
									scoreVo.setWrongAnswer(scoreVo.getWrongAnswer() + 1);
									break;
								}
							}
						}
						catch (InputMismatchException e) {
							keyin.nextLine();
							System.out.println("다시 입력하세요.");
							continue;
						}
				}
					
			}
				
		}
			scoreVo.setCorrectPercent((int)Math.round((scoreVo.getCorrectAnswer()/5.0)* 100));
			System.out.println("맞은 문제수 : " + scoreVo.getCorrectAnswer() + "개");
			System.out.println("틀린 문제수 : " + scoreVo.getWrongAnswer() + "개");
			System.out.println("정답률 : " + scoreVo.getCorrectPercent() + "%");
			dao.quizScroeInsert(scoreVo);
			quizEndMenuPrint();
			while (true) {
				int n;
				try {
					
					n = keyin.nextInt();
				}
				catch (InputMismatchException e) {
					keyin.nextLine();
					System.out.println("다시 입력하세요.");
					continue;
				}	
				switch(n) {					
				case 1: System.out.println("한번 더 시작합니다.");continue quiz1;
				case 2: System.out.println("퀴즈를 끝냅니다.");break quiz1;
				default: System.out.println("다시 입력하세요.");  	System.out.print("선택>	");
				}
			}	

		}
		
	}
	
//	public void quizScore() {
//		ArrayList<QuizScoreVO> scoreList = dao.quizScroeAll();
//		QuizScoreVO vo = null;
//		System.out.printf("%s \n", "[ 퀴즈 점수 ]");
//		System.out.println();
//		System.out.printf("%s", " ---------------------------------------------------------------------------------------- \n");
//		System.out.printf("%s  %-10s \t %-2s  %-6s \t %-2s  %-6s \t %-2s  %-6s \t %-2s  %-7s \t %-2s \n","|   ", "유저ID", "|", "캐릭터 이름" , "|", "맞은 문제", "|", "틀린 문제", "|", "정답률", "|");
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
		System.out.printf("%s \n", "[ 퀴즈 점수 ]");
		score:
		while (true) {
			if (quizScoreNum != 0) {
				if (firstScoreSize > quizScoreNum) {
					System.out.println("1.	다음 페이지");
					System.out.println("2.	뒤로");
					System.out.print("선택>	");
				}
				else {
					System.out.println("마지막 페이지입니다.");
					System.out.println("1.	종료");
					System.out.print("선택>	");
				}
				
				try {
					m = keyin.nextInt();
				}
				catch (InputMismatchException e) {
					keyin.nextLine();
					System.out.println("다시 입력하세요.");
					continue;
				}	
			}
		
			if (firstScoreSize > quizScoreNum) {
				switch(m) {					
				case 1: quizScoreList(); break;
				case 2: return;
				default: System.out.println("다시 입력하세요.");  	System.out.print("선택>	");
				}
			}
			else {
				switch(m) {					
				case 1: return;
				default: System.out.println("다시 입력하세요.");  	System.out.print("선택>	");
				}
			}
		}
	}
	
	public void quizScoreList() {
			QuizScoreVO vo = null;

			ArrayList<QuizScoreVO> scoreList = dao.quizScroeAll2(quizScoreNum,10);
			

				System.out.printf("%s", " ---------------------------------------------------------------------------------------- \n");
				System.out.printf("%s  %-10s \t %-2s  %-6s \t %-2s  %-6s \t %-2s  %-6s \t %-2s  %-7s \t %-2s \n","|   ", "유저ID", "|", "캐릭터 이름" , "|", "맞은 문제", "|", "틀린 문제", "|", "정답률", "|");
				System.out.printf("%s", " ---------------------------------------------------------------------------------------- \n");

				for (int i = 0; i <= scoreList.size()-1; i++) {
					vo = scoreList.get(i);
					System.out.printf("%s  %-10s \t %-2s  %-6s \t %-2s  %-6d \t %-2s  %-6d \t %-2s  %-7d \t %-2s \n","|   ", vo.getUserId(), "|", vo.getCharacterName() , "|", vo.getCorrectAnswer(), "|", vo.getWrongAnswer(), "|", vo.getCorrectPercent(), "|");
				}
				System.out.printf("%s", " ---------------------------------------------------------------------------------------- \n");
				System.out.println((((int)Math.floor(quizScoreNum/10))+1) + "페이지" );
				quizScoreNum +=10;
	}
	
	public void quizRanking() {
		ArrayList<QuizScoreVO> rankingList = dao.quizRanking();
		System.out.printf("%37s", "[ 퀴즈 정답률 순위 ] \n");
		System.out.printf("%s", " ---------------------------------------------------------------- \n");
		System.out.printf("%s  %-10s \t %-2s  %-8s \t %-2s  %-6s \t %-2s  %-7s \t %-2s \n","|   ", "등수", "|", "유저ID" , "|", "캐릭터 이름", "|", "정답률", "|");
		System.out.printf("%s", " ---------------------------------------------------------------- \n");
		for (int i = 0; i < rankingList.size(); i++) {
			QuizScoreVO quizRankingVo = rankingList.get(i);
			System.out.printf("%s  %-10s \t %-2s  %-8s \t %-2s  %-6s \t %-2s  %-7s \t %-2s \n","|   ", ((i+1)+"등"), "|", quizRankingVo.getUserId() , "|", quizRankingVo.getCharacterName(), "|", quizRankingVo.getCorrectPercent(), "|");

		}
		System.out.printf("%s", " ---------------------------------------------------------------- \n");

	}
	
}
	
	
	
	
	

