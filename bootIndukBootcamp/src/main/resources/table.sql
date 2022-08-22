select * from board;

select * from user_info;

drop sequence board_seq;
create sequence board_seq increment by 1 start with 1;

drop table board;
create table board (
                       bno number(11) primary key not null,
                       title varchar2(50) not null,
                       contents varchar2(1000) not null,
                       writer varchar2(30) not null,
                       view_cnt number(11) default 0,
                       reply_cnt number(11) default 0,
                       reg_date date default sysdate,
                       up_date date default sysdate
);

drop sequence reply_seq;
create sequence reply_seq increment by 1 start with 1;

drop table reply;
create table reply (
                       rno number(11) primary key not null,
                       bno number(11) not null,
                       prno number(11) not null,
                       reply varchar2(1000),
                       replier varchar2(30),
                       reg_date date default sysdate,
                       up_date date default sysdate
);


drop table user_info;
create table user_info(
                          id varchar2(30) primary key not null,
                          pwd varchar2(50),
                          name varchar2(30),
                          email varchar2(30),
                          birth date,
                          reg_date date default sysdate
);

commit;