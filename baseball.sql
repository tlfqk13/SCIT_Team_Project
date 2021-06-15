 --����--

CREATE TABLE users (
    userid    VARCHAR2(20) PRIMARY KEY,
    password  VARCHAR2(40) NOT NULL
);
 
 update usercharacter set gold=500 where characterid=1;
 select * from usercharacter;
 

 
 --ĳ����-

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
 
 --�����ۼ�������--

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



--insert into item(itemid,itemclass,itemname,summary,price) values(itemidseq.nextval,'�۷���','������ �۷���','����+3',300);
 insert into itemhaveinfo(characterid, itemid, quantity) values(1,104,1);
 
 --����

 select uc.charactername, i.itemname, ihi.quantity from usercharacter uc, itemhaveinfo ihi, item i
 where uc.characterid=ihi.characterid and i.itemid=ihi.itemid;
 
 select ihi.characterid, ihi.itemid, ihi.quantity from item i, itemhaveinfo ihi
where i.itemid = ihi.itemid;
 
 --��������������--

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
 
 --������--

CREATE TABLE item (
    itemid       NUMBER PRIMARY KEY,
    itemclass    VARCHAR2(20) NOT NULL,
    itemname     VARCHAR2(40) NOT NULL,
    summary      VARCHAR2(100) NOT NULL,
    price        NUMBER NOT NULL
);


insert into board(num,id,title,content)values(boardseq.nextval,'aaa','������1','�۳��� 1111');

 
 --Ʈ���̴�--

CREATE TABLE training (
    trainingid     NUMBER PRIMARY KEY,
    trainingname   VARCHAR2(20) NOT NULL,
    trainingclass  VARCHAR2(20) NOT NULL
);

--Ʈ���̴�����

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

--��ġ--

CREATE TABLE coach (
    coachid        NUMBER PRIMARY KEY,
    coachname      VARCHAR2(20) NOT NULL,
    coacheffect1   NUMBER NOT NULL,
    coacheffect2   NUMBER DEFAULT 0,
    coachdecrease  NUMBER NOT NULL
);

--���� ����--

CREATE TABLE hallofframe (
    hallofframeid  NUMBER PRIMARY KEY,
    userid         VARCHAR2(20) NOT NULL,
    charactername  VARCHAR2(40) NOT NULL,
    allstat        NUMBER NOT NULL,
    CONSTRAINT hallofframefk FOREIGN KEY ( userid )
        REFERENCES users ( userid )
            ON DELETE CASCADE
);

--�����丮 ���̺�--

--�������� ���� ���� --
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

         
--������ ��ȭ--

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


--�ذ� ĳ���� --
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
delete from item where itemclass='�۷���';
select * from item;

insert into item(itemid,itemclass,itemname,summary,price) values(itemidseq.nextval,'�۷���','������ �۷���','����+3',300);
insert into item(itemid,itemclass,itemname,summary,price) values(itemidseq.nextval,'�۷���','���� �۷���','����+5',550);
insert into item(itemid,itemclass,itemname,summary,price) values(itemidseq.nextval,'�۷���','�¸��� �۷���','����+7',750);
----------------------------------------
insert into item(itemid,itemclass,itemname,summary,price) values(itemidseq.nextval,'��','������ ������','��Ż+3',300);
insert into item(itemid,itemclass,itemname,summary,price) values(itemidseq.nextval,'��','���� ������','��Ż+5',450);
insert into item(itemid,itemclass,itemname,summary,price) values(itemidseq.nextval,'��','�¸��� ������','��Ż+7',600);
----------------------------------------
insert into item(itemid,itemclass,itemname,summary,price) values(itemidseq.nextval,'���','������ ���','ü��+3',200);
insert into item(itemid,itemclass,itemname,summary,price) values(itemidseq.nextval,'���','���� ���','ü��+5',450);
insert into item(itemid,itemclass,itemname,summary,price) values(itemidseq.nextval,'���','�¸��� ���','ü��+7',650);
----------------------------------------
insert into item(itemid,itemclass,itemname,summary,price) values(itemidseq.nextval,'�����','������ �����','�Ŀ�+3',600);
insert into item(itemid,itemclass,itemname,summary,price) values(itemidseq.nextval,'�����','���� �����','�Ŀ�+5',900);
insert into item(itemid,itemclass,itemname,summary,price) values(itemidseq.nextval,'�����','�¸��� �����','�Ŀ�+7',1200);
----------------------------------------
insert into item(itemid,itemclass,itemname,summary,price) values(itemidseq.nextval,'�Ź�','������ �Ź�','���ǵ�+3',300);
insert into item(itemid,itemclass,itemname,summary,price) values(itemidseq.nextval,'�Ź�','���� �Ź�','���ǵ�+5',400);
insert into item(itemid,itemclass,itemname,summary,price) values(itemidseq.nextval,'�Ź�','�¸��� �Ź�','���ǵ�+7',500);
-----------------------------------------------------------
insert into itemequiphaveinfo(itemequipid,characterid,itemid,itemlevel) values(itemequiphaveseq.nextval,1,'104',3);

select * from item;
select * from usercharacter;


commit;


CREATE SEQUENCE trainingidseq INCREMENT BY 1 MINVALUE 201;

CREATE SEQUENCE coachidseq INCREMENT BY 1 MINVALUE 301;

CREATE SEQUENCE hallofframeidseq INCREMENT BY 1 MINVALUE 401;

CREATE SEQUENCE itemequiphaveseq INCREMENT BY 1 MINVALUE 501;




