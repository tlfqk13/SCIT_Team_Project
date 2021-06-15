 --계정--

CREATE TABLE users (
    userid    VARCHAR2(20) PRIMARY KEY,
    password  VARCHAR2(40) NOT NULL
);
 
 update usercharacter set gold=500 where characterid=1;
 select * from usercharacter;
 

 
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

select * from itemhaveinfo;



--insert into item(itemid,itemclass,itemname,summary,price) values(itemidseq.nextval,'글러브','영광의 글러브','구속+3',300);
 insert into itemhaveinfo(characterid, itemid, quantity) values(1,104,1);
 
 --조인

 select uc.charactername, i.itemname, ihi.quantity from usercharacter uc, itemhaveinfo ihi, item i
 where uc.characterid=ihi.characterid and i.itemid=ihi.itemid;
 
 select ihi.characterid, ihi.itemid, ihi.quantity from item i, itemhaveinfo ihi
where i.itemid = ihi.itemid;
 
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


insert into board(num,id,title,content)values(boardseq.nextval,'aaa','글제목1','글내용 1111');

 
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

drop sequence itemidseq;
delete from item where itemclass='글러브';
select * from item;

insert into item(itemid,itemclass,itemname,summary,price) values(itemidseq.nextval,'글러브','영광의 글러브','구속+3',300);
insert into item(itemid,itemclass,itemname,summary,price) values(itemidseq.nextval,'글러브','명예의 글러브','구속+5',550);
insert into item(itemid,itemclass,itemname,summary,price) values(itemidseq.nextval,'글러브','승리의 글러브','구속+7',750);
----------------------------------------
insert into item(itemid,itemclass,itemname,summary,price) values(itemidseq.nextval,'옷','영광의 유니폼','맨탈+3',300);
insert into item(itemid,itemclass,itemname,summary,price) values(itemidseq.nextval,'옷','명예의 유니폼','맨탈+5',450);
insert into item(itemid,itemclass,itemname,summary,price) values(itemidseq.nextval,'옷','승리의 유니폼','맨탈+7',600);
----------------------------------------
insert into item(itemid,itemclass,itemname,summary,price) values(itemidseq.nextval,'헬멧','영광의 헬멧','체력+3',200);
insert into item(itemid,itemclass,itemname,summary,price) values(itemidseq.nextval,'헬멧','명예의 헬멧','체력+5',450);
insert into item(itemid,itemclass,itemname,summary,price) values(itemidseq.nextval,'헬멧','승리의 헬멧','체력+7',650);
----------------------------------------
insert into item(itemid,itemclass,itemname,summary,price) values(itemidseq.nextval,'방망이','영광의 방망이','파워+3',600);
insert into item(itemid,itemclass,itemname,summary,price) values(itemidseq.nextval,'방망이','명예의 방망이','파워+5',900);
insert into item(itemid,itemclass,itemname,summary,price) values(itemidseq.nextval,'방망이','승리의 방망이','파워+7',1200);
----------------------------------------
insert into item(itemid,itemclass,itemname,summary,price) values(itemidseq.nextval,'신발','영광의 신발','스피드+3',300);
insert into item(itemid,itemclass,itemname,summary,price) values(itemidseq.nextval,'신발','명예의 신발','스피드+5',400);
insert into item(itemid,itemclass,itemname,summary,price) values(itemidseq.nextval,'신발','승리의 신발','스피드+7',500);
-----------------------------------------------------------
insert into itemequiphaveinfo(itemequipid,characterid,itemid,itemlevel) values(itemequiphaveseq.nextval,1,'104',3);

select * from item;
select * from usercharacter;


commit;


CREATE SEQUENCE trainingidseq INCREMENT BY 1 MINVALUE 201;

CREATE SEQUENCE coachidseq INCREMENT BY 1 MINVALUE 301;

CREATE SEQUENCE hallofframeidseq INCREMENT BY 1 MINVALUE 401;

CREATE SEQUENCE itemequiphaveseq INCREMENT BY 1 MINVALUE 501;




