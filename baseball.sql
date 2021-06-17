 --계정--

CREATE TABLE users (
    userid    VARCHAR2(20) PRIMARY KEY,
    password  VARCHAR2(40) NOT NULL
);
 
 --캐릭터-

CREATE TABLE usercharacter (
    characterid    NUMBER PRIMARY KEY,
    userid         VARCHAR2(20) NOT NULL,
    charactername  VARCHAR2(40) NOT NULL,
    active         NUMBER(1) DEFAULT 5 NOT NULL,
    gold           NUMBER(5) DEFAULT 0 NOT NULL,
    health         NUMBER(3) DEFAULT 100 NOT NULL,
    year           NUMBER(2) DEFAULT 1 NOT NULL,
    classname            VARCHAR2(10) NOT NULL,
    hitterpower          NUMBER(2) DEFAULT 0,
    hitterhit            NUMBER(2) DEFAULT 0,
    hitterrunspeed       NUMBER(2) DEFAULT 0,
    pitcherballspeed     NUMBER(2) DEFAULT 0,
    pitcherballcontrol  NUMBER(2) DEFAULT 0,
    pitchermentality     NUMBER(2) DEFAULT 0,
    CONSTRAINT usercharacterfk FOREIGN KEY ( userid )
        REFERENCES users ( userid )
            ON DELETE CASCADE
);
 
 --아이템소유정보--

CREATE TABLE itemhaveinfo (
    characterid  NUMBER,
    itemid       NUMBER,
    quantity     NUMBER,
    CONSTRAINT itemhaveinfo PRIMARY KEY ( characterid,itemid ),
    CONSTRAINT itemhaveinfofk1 FOREIGN KEY ( characterid )
        REFERENCES usercharacter ( characterid )
            ON DELETE CASCADE,
    CONSTRAINT itemhaveinfofk2 FOREIGN KEY ( itemid )
        REFERENCES item ( itemid )
            ON DELETE CASCADE
);
 
 --아이템장착정보--

CREATE TABLE itemequipinfo (
    characterid   NUMBER PRIMARY KEY,
    equipitemid1  NUMBER DEFAULT 0 NOT NULL,
    equipitemid2  NUMBER DEFAULT 0 NOT NULL,
    equipitemid3  NUMBER DEFAULT 0 NOT NULL,
    CONSTRAINT itemequipinfofk1 FOREIGN KEY ( equipitemid1 )
        REFERENCES item ( itemid )
            ON DELETE CASCADE,
    CONSTRAINT itemequipinfofk2 FOREIGN KEY ( equipitemid2 )
        REFERENCES item ( itemid )
            ON DELETE CASCADE,
    CONSTRAINT itemequipinfofk3 FOREIGN KEY ( equipitemid3 )
        REFERENCES item ( itemid )
            ON DELETE CASCADE
);
 
 --아이템--

CREATE TABLE item (
    itemid       NUMBER PRIMARY KEY,
    itemclass    VARCHAR2(20) NOT NULL,
    itemname     VARCHAR2(40) NOT NULL,
    summary      VARCHAR2(100) NOT NULL,
    price        NUMBER NOT NULL
);
 
 --트레이닝--

CREATE TABLE training (
    trainingid     NUMBER PRIMARY KEY,
    trainingname   VARCHAR2(20) NOT NULL,
    trainingclass  VARCHAR2(20) NOT NULL
);

--트레이닝정보

CREATE TABLE traininginfo (
    coachid     NUMBER,
    trainingid  NUMBER,
    CONSTRAINT traininginfo PRIMARY KEY ( coachid,
                                          trainingid ),
    CONSTRAINT traininginfofk1 FOREIGN KEY ( trainingid )
        REFERENCES training ( trainingid )
            ON DELETE CASCADE,
    CONSTRAINT traininginfofk2 FOREIGN KEY ( coachid )
        REFERENCES coach ( coachid )
            ON DELETE CASCADE
);

--코치--

CREATE TABLE coach (
    coachid        NUMBER PRIMARY KEY,
    coachname      VARCHAR2(20) NOT NULL,
    coacheffect1   NUMBER NOT NULL,
    coacheffect2   NUMBER DEFAULT 0,
    coachdecrease  NUMBER NOT NULL
);

--명예의 전당--

CREATE TABLE hallofframe (
    hallofframeid  NUMBER PRIMARY KEY,
    userid         VARCHAR2(20) NOT NULL,
    charactername  VARCHAR2(40) NOT NULL,
    allstat        NUMBER NOT NULL,
    classname      VARCHAR2(10) NOT NULL,
    CONSTRAINT hallofframefk FOREIGN KEY ( userid )
        REFERENCES users ( userid )
            ON DELETE CASCADE
);

--히스토리 테이블--

--장비아이템 소유 정보 --
CREATE TABLE itemequiphaveinfo (
    itemequipid  NUMBER PRIMARY KEY,
    characterid  NUMBER NOT NULL,
    itemid       NUMBER NOT NULL,
    itemlevel        NUMBER NOT NULL,
    CONSTRAINT itemequiphaveinfofk1 FOREIGN KEY ( characterid )
        REFERENCES usercharacter ( characterid )
            ON DELETE CASCADE,
    CONSTRAINT itemequiphaveinfofk2 FOREIGN KEY ( itemid )
        REFERENCES item ( itemid )
            ON DELETE CASCADE
);
            
--아이템 강화--

CREATE TABLE itemlevel (
    itemid       NUMBER,
    itemlevel    NUMBER,
    itemeffect1  NUMBER NOT NULL,
    itemeffect2  NUMBER DEFAULT 0 NOT NULL,
    CONSTRAINT itemlevelfk1 PRIMARY KEY ( itemid,
                                       itemlevel ),
    CONSTRAINT itemlevelfk2 FOREIGN KEY ( itemid )
        REFERENCES item ( itemid )
            ON DELETE CASCADE
);






--sequence-

CREATE SEQUENCE characteridseq INCREMENT BY 1 MINVALUE 1;

CREATE SEQUENCE itemidseq INCREMENT BY 1 MINVALUE 101;

CREATE SEQUENCE itemequiphaveidseq INCREMENT BY 1 MINVALUE 201;

CREATE SEQUENCE trainingidseq INCREMENT BY 1 MINVALUE 301;

CREATE SEQUENCE coachidseq INCREMENT BY 1 MINVALUE 401;

CREATE SEQUENCE hallofframeidseq INCREMENT BY 1 MINVALUE 501;

CREATE SEQUENCE kickcharacteridseq INCREMENT BY 1 MINVALUE 601;


--훈련 종류 insert--
insert into training values ( trainingidseq.nextval, '파워훈련', '타자');
insert into training values ( trainingidseq.nextval, '타격훈련', '타자');
insert into training values ( trainingidseq.nextval, '주루훈련', '타자');
insert into training values ( trainingidseq.nextval, '볼스피드훈련', '투수');
insert into training values ( trainingidseq.nextval, '볼컨트롤훈련', '투수');
insert into training values ( trainingidseq.nextval, '정신력훈련', '투수');
--코치 insert--
insert into coach (coachid, coachname, coacheffect1, coachdecrease) values (coachidseq.nextval, '파워고정', 5, -25);
insert into coach (coachid, coachname, coacheffect1, coachdecrease) values (coachidseq.nextval, '파워랜덤', 0, -25);
insert into coach (coachid, coachname, coacheffect1, coachdecrease) values (coachidseq.nextval, '타격고정', 5, -25);
insert into coach (coachid, coachname, coacheffect1, coachdecrease) values (coachidseq.nextval, '타격랜덤', 0, -25);
insert into coach (coachid, coachname, coacheffect1, coachdecrease) values (coachidseq.nextval, '주루고정', 5, -25);
insert into coach (coachid, coachname, coacheffect1, coachdecrease) values (coachidseq.nextval, '주루랜덤', 0, -25);
insert into coach (coachid, coachname, coacheffect1, coachdecrease) values (coachidseq.nextval, '볼스피드고정', 5, -25);
insert into coach (coachid, coachname, coacheffect1, coachdecrease) values (coachidseq.nextval, '볼스피드랜덤', 0, -25);
insert into coach (coachid, coachname, coacheffect1, coachdecrease) values (coachidseq.nextval, '볼컨트롤고정', 5, -25);
insert into coach (coachid, coachname, coacheffect1, coachdecrease) values (coachidseq.nextval, '볼컨트롤랜덤', 0, -25);
insert into coach (coachid, coachname, coacheffect1, coachdecrease) values (coachidseq.nextval, '정신력고정', 5, -25);
insert into coach (coachid, coachname, coacheffect1, coachdecrease) values (coachidseq.nextval, '정신력랜덤', 0, -25);




--퀴즈 문제--
CREATE TABLE quiz (
    quiznumber  NUMBER PRIMARY KEY,
    question    VARCHAR2(300) NOT NULL,
    example1    VARCHAR2(60),
    example2    VARCHAR2(60),
    example3    VARCHAR2(60),
    example4    VARCHAR2(60),
    correct     VARCHAR2(60) NOT NULL
);

CREATE SEQUENCE quizseq INCREMENT BY 1 MINVALUE 1001;
CREATE SEQUENCE quizscoreseq INCREMENT BY 1 MINVALUE 1101;

--사용자의 퀴즈 정답률--

CREATE TABLE quizscore (
    quizscorenumber  NUMBER PRIMARY KEY,
    userid           VARCHAR2(20) NOT NULL,
    characterid      NUMBER NOT NULL,
    charactername    VARCHAR2(40) NOT NULL,
    correctanswer    NUMBER NOT NULL,
    wronganswer      NUMBER NOT NULL,
    correctpercent   NUMBER NOT NULL,
    CONSTRAINT quizscorefk1 FOREIGN KEY ( userid )
        REFERENCES users ( userid )
            ON DELETE CASCADE,
    CONSTRAINT quizscorefk2 FOREIGN KEY ( characterid )
        REFERENCES usercharacter ( characterid )
            ON DELETE CASCADE
);

insert
    INTO quiz values (
    quizseq.NEXTVAL,
    'KBO리그 역대 최초로, 16년 연속 10홈런을 기록한 선수는?',
    '양준혁', 
    '박경완', 
    '장종훈', 
    '최정', 
    '최정'
    );
insert 
    INTO quiz values (
    quizseq.nextval, 
    '삼성라이온즈 팬들에게 삼성왕조라고 불리는 정규시즌, 한국시리즈 4년연속 우승 달성년도는 언제일까요?', 
    '1986년부터 1989년',
    '1997년부터 2000년',
    '2004년부터 2007년',
    '2011년부터 2014년',
    '2011년부터 2014년'
    );
insert
    INTO quiz (quiznumber, question, correct) values (
    quizseq.NEXTVAL,
    '야구 전광판에서 RHEB중 H의 의미하는 바는?',
    '안타'
    );
insert
    INTO quiz values (
    quizseq.NEXTVAL,
    '2021년 첫 데뷔전임에도 불구하고, 4월 7일, 3이닝동안 퍼펙트 피칭을 보여주며 데뷔전 승리를 거머쥔 기아의 신인투수는?',
    '장재영',
    '김진욱',
    '이의리',
    '이승재',
    '이승재'
    );
insert
    INTO quiz values (
    quizseq.NEXTVAL,
    '다음은 투수기록 기록용어이다. 숫자가 작을수록 좋은 투수라고 평가하는 기록 용어는?',
    'ERA(방어율)',
    'K/9(9이닝당 탈삼진수)',
    '승률',
    'WAR(대체선수대비 기여승수)',
    'ERA(방어율)'
    );
insert
    INTO quiz (quiznumber, question, correct) values (
    quizseq.NEXTVAL,
    'KBO에서는 정규시즌에서는 보통 동일한 팀과 3일 연속경기를 가지게 되는데, 이 3연전 중 3번을 모두 승리할 경우 쓰는 용어는?',
    '스윕'
    );
insert
    INTO quiz (quiznumber, question, correct) values (
    quizseq.NEXTVAL,
    '21시즌 KBO 올스타전이 개최되는 곳은 어디일까요?',
    '고척'
    );
insert
    INTO quiz values (
    quizseq.NEXTVAL,
    '보기의 KBO구단 중, 연고지가 다른 구단은 어디일까요?',
    '두산 베어스',
    'LG 트윈스',
    'KT 위즈',
    '키움 히어로즈',
    'KT 위즈'
    );
insert
    INTO quiz (quiznumber, question, correct) values (
    quizseq.NEXTVAL,
    '2021년 6월 4일, 통산 300번째 도루를 달성한 삼성라이온즈의 박해민 선수의 기록은, KBO리그 역대 몇 번째 기록이었을까요?(숫자만 적으세요.)',
    '12'
    );
insert
    INTO quiz values (
    quizseq.NEXTVAL,
    '2021 신한은행 SOL KBO 리그 5월 MVP로 선정된 선수는?',
    '최정(SSG)',
    '우규민(삼성)',
    '강백호(KT)',
    '이정후(키움)',
    '이정후(키움)'
    );
insert
    INTO quiz values (
    quizseq.NEXTVAL,
    'KBO리그 2021시즌, 두번째 완봉승을 달성한 선수는?',
    '데이비드 뷰캐넌(삼성 라이온즈)',
    '애런 브룩스(기아 타이거즈)',
    '최채흥(삼성 라이온즈)',
    '박세웅(롯데 자이언츠)',
    '박세웅(롯데 자이언츠)'
    );
insert
    INTO quiz values (
    quizseq.NEXTVAL,
    '2021년 6월 6일, KBO리그 야구 경기 중 역전승이 일이나지 않은 경기는?',
    '롯데-KT',
    'SSG-두산',
    '삼성-키움',
    '한화-NC',
    'SSG-두산'
    );
insert
    INTO quiz values (
    quizseq.NEXTVAL,
    'KBO 야구선수 중 1982년에 태어나지 않은 선수는?',
    '추신수(SSG 랜더스)',
    '이성우(LG 트윈스)',
    '오승환(삼성 라이온즈)',
    '이대호(롯데 자이언츠)',
    '이성우(LG 트윈스)'
    );
insert
    INTO quiz values (
    quizseq.NEXTVAL,
    '2021년 5월 27일, NC 다이노스의 양의지 선수는 시즌 10번째 홈런을 기록했습니다. 양의지 선수는 이 기록을 포함 연속 몇년 두 자릿수 홈런을 기록했을까요?',
    '8년',
    '9년',
    '10년',
    '11년',
    '8년'
    );
insert
    INTO quiz values (
    quizseq.NEXTVAL,
    'KBO에서 2022년(2023년 입단)부터 도입하는 프로야구 얼리드래프트 제도는, 대학교 몇학년부터 KBO 신인드래프트에 참가할 수 있을까요?',
    '1학년',
    '2학년',
    '3학년',
    '4학년',
    '2학년'
    );
insert
    INTO quiz values (
    quizseq.NEXTVAL,
    '야구에서 중견수를 뜻하는 CF는 어떤 단어의 줄임말일까요?',
    'Cash Flow',
    'Commercial Film',
    'Center Fielder',
    'Center Foward',
    'Center Fielder'
    );
insert
    INTO quiz values (
    quizseq.NEXTVAL,
    '야구에서 주자가 3루에 있을 때, 득점을 하기 위해 번트를 대는 작전을 의미하는 야구 용어는?',
    '드래그번트',
    '스퀴즈번트',
    '위장번트',
    '기습번트',
    '스퀴즈번트'
    );
insert
    INTO quiz values (
    quizseq.NEXTVAL,
    '한화 이글스가 공식발표한 구단 4번째 영구결번지정 김태균 선수의 등번호는?',
    '21번',
    '23번',
    '35번',
    '52번',
    '52번'
    );
insert
    INTO quiz (quiznumber, question, correct) values (
    quizseq.NEXTVAL,
    'KBO리그 역대 최초로, 16년 연속 10홈런을 기록한 선수는?',
    '최정'
    );
insert
    INTO quiz values (
    quizseq.NEXTVAL,
    'KBO리그에 외국인선수 제대로 최초 도입된 년도는?',
    '1988년',
    '1998년',
    '2008년',
    '2018년',
    '1998년'
    );
    