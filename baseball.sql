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
    CONSTRAINT itemlevel PRIMARY KEY ( itemid,
                                       itemlevel ),
    CONSTRAINT itemlevel FOREIGN KEY ( itemid )
        REFERENCES item ( itemid )
            ON DELETE CASCADE
);



--해고 캐릭터 --
CREATE TABLE kickcharacter (
    kickcharacterid  NUMBER PRIMARY KEY,
    userid           VARCHAR2(20) NOT NULL,
    charactername    VARCHAR2(40) NOT NULL,
    allstat          NUMBER default 0 NOT NULL,
    CONSTRAINT kickcharacterfk FOREIGN KEY ( userid )
        REFERENCES users ( userid )
            ON DELETE CASCADE
);



--sequence-

CREATE SEQUENCE characteridseq INCREMENT BY 1 MINVALUE 1;

CREATE SEQUENCE itemidseq INCREMENT BY 1 MINVALUE 101;

CREATE SEQUENCE itemequiphaveidseq INCREMENT BY 1 MINVALUE 201;

CREATE SEQUENCE trainingidseq INCREMENT BY 1 MINVALUE 301;

CREATE SEQUENCE coachidseq INCREMENT BY 1 MINVALUE 401;

CREATE SEQUENCE hallofframeidseq INCREMENT BY 1 MINVALUE 501;

CREATE SEQUENCE kickcharacter INCREMENT BY 1 MINVALUE 601;