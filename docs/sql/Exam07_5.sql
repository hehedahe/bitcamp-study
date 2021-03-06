/* 조인
=> 서로 관련된 테이블의 데이터를 연결하여 추출하는 방법
=> 기법
1) CROSS 조인(=Cartesian product)
2) NATURAL 조인
3) JOIN ~ ON
4) OUTER JOIN
*/

/* cross join : 두 테이블의 데이터를 1:1로 모두 연결한다.*/
select mno, name from memb;
select mno, work, bank from stnt;

/* => mno가 어떤 테이블의 컬럼인지 지정하지 않으면 실행 오류!*/
select mno, name, mno, work, bank
from memb cross join stnt;

/* => select  컬럼이 두 테이블 모두 있을 경우,
         컬럼명 앞에 테이블명을 명시하여 구분하라!*/
select memb.mno member_no, name, stnt.mno student_no, work, bank
from memb cross join stnt;

/* 예전 문법 */
select memb.mno member_no, name, stnt.mno student_no, work, bank
from memb, stnt;


/* => 컬럼명 앞에 테이블명을 붙이면 너무 길다.
         테이블에 별명을 부여하고
         그 별명을 사용하여 컬럼을 지정하라. */
select m.mno, name, s.mno, work, bank
from memb as m cross join stnt s;

/* 예전 문법 */
select m.mno, name, s.mno, work, bank
from memb m, stnt as s;


/* natural join: 같은 이름을 가진 컬럼 값을 기준으로 레코드를 연결한다. */
select m.mno, name, s.mno, work, bank
from memb m natural join stnt s;

/* 예전 문법 */
select m.mno, name, s.mno, work, bank
from memb m, stnt s
where m.mno=s.mno;

/* natural join 의 문제점
 * 1) 두 테이블의 조인 기준이 되는 컬럼 이름이 다를 때 연결되지 못한다.
   2) 상관 없는 컬럼과 이름이 같을 때 잘못 연결된다.
   3) 같은 이름의 컬럼이 여러 개 있을 경우 잘못 연결된다.
        모든 컬럼의 값이 일치할 경우에만 연결되기 때문이다. */

/* 만약에 두 테이블에 같은 이름을 가진 컬럼이 여러 개 있다면,
   join ~ using (기준컬럼) 을 사용하여
   두 테이블의 데이터를 연결할 때 기준이 될 컬럼을 지정한다.*/
select m.mno, name, s.mno, work, bank
from memb m join stnt s using (mno);

/* natural join 의 문제점 2
   => 두 테이블에 같은 이름의 컬럼이 없을 경우
        연결하지 못한다.*/

/* 만약 두 테이블에 같은 이름을 가진 컬럼이 없으면,
   natural join을 수행하지 못한다.
   또한 join using 으로도 해결할 수 없다.
   이럴 경우 join ~ on 컬럼a=컬럼b 문법을 사용하여
   각 테이블의 어떤 컬럼과 값을 비교할 것인지 지정하라!*/
select m.mno, name, s.mno, work, bank
from memb m inner join stnt s on m.mno=s.mno;

/* inner는 생략 가능하다 */
select m.mno, name, s.mno, work, bank
from memb m join stnt s on m.mno=s.mno;
/* 즉 inner join 은 기준 컬럼의 값이 일치할 때만 데이터를 연결한다. */

/* 예전의 조인 문법 = inner join */
select m.mno, name, s.mno, work, bank
from memb m, stnt s
where m.mno=s.mno;


/* [inner] join ~ on 의 문제점
   => 반드시 on 에서 지정한 컬럼의 값이 같을 경우에만
        두 테이블의 데이터가 연결된다.
   => 같은 값을 갖는 데이터가 없다면 연결되지 않고, 결과로 출력되지 않는다.
*/
/* 전체 강의 목록 */
select lno, titl, rno, mno from lect;

/* 전체 강의실 목록 */
select rno, loc, name from room;

/* 강의 테이블에서 강의명을 가져오고, 강의실 테이블에서 지점명과 강의실명을 가져오자. */
select
    l.lno,
    l.titl,
    l.rno,
    r.rno,
    r.loc,
    r.name
from lect l inner join room r on l.rno=r.rno;
/* inner join의 문제는 위의 경우처럼
   강의실이 아직 지정되지 않은 강의의 경우 강의실 테이블의 데이터와 연결하지 못해
   결과로 출력되지 않는 문제가 있다. */


/* inner join의 문제점 예2:
 * 모든 강의장 이름을 출력하라.
 * 단, 강의장에 강의가 배정된 경우 그 강의 이름도 출력하라.
 */
select
  r.rno,
  r.name,
  r.loc,
  l.titl
from room r inner join lect l on r.rno = l.rno;

/* => 만약 기준 컬럼의 값과 일치하는 데이터가 없어서
      다른 테이블의 데이터와 연결되지 않았다 하더라도
      결과로 뽑아내고 싶다면 outer join을 사용하라!*/
/* 즉 아직 강의실이 배정되지 않은 강의 데이터도 출력하고 싶을 때
   출력하고 싶은 테이블을 바깥쪽 테이블로 지정하라!
 */
select
    l.lno,
    l.titl,
    r.rno,
    r.loc,
    r.name
from lect l left outer join room r on l.rno=r.rno;
/* 왼쪽 테이블인 lect를 기준으로 room 데이터를 연결한다.
 * 만약 lect와 일치하는 데이터가 room에 없더라도
 * lect 데이터를 출력한다!
 */


/* 요구사항:
   모든 멤버의 번호와 이름을 출력하라!
   단, 학생의 경우 재직여부도 출력하라!*/

-- 1) 모든 멤버 데이터 출력하기
select mno, name
from memb;

-- 2) 학생 데이터를 가져와서 연결하기 => 모두 같은 문법!
select mno, name, work
from memb natural join stnt;

select mno, name, work
from memb join stnt using(mno);

select memb.mno, name, work
from memb, stnt
where memb.mno=stnt.mno;

select memb.mno, name, work
from memb inner join stnt on memb.mno=stnt.mno;

select memb.mno, name, work
from memb join stnt on memb.mno=stnt.mno;

select m.mno, name, work
from memb m join stnt s on m.mno=s.mno;

/* 안타깝게도 위의 SQL문은 학생 목록만 출력한다.
   => 왜? memb테이블의 데이터와 stnt 테이블의 데이터를
      연결할 때 mno가 같은 데이터만 연결하여 추출하기 때문이다.
   => 해결책!
      상대 테이블(stnt)에 연결할 대상(데이터)이 없더라도
      select에서 추출하는 방법 */
select m.mno, name, work
from memb m left outer join stnt s on m.mno=s.mno;



/* 여러 테이블의 데이터를 연결하기
    => 다음의 결과가 출력될 수 있도록 수강 신청 데이터를 출력하시오!
    수강신청번호, 강의명, 학생명, 재직여부, 수강신청일, 강의실명, 매니저명, 직위 */

/* 1단계: 수강신청 데이터를 출력 <- 별명 */
select la.lano, la.lno, la.mno, la.rdt
from lect_appl la;

/* 2단계: 수강신청한 학생의 번호 대신 이름을 출력 */
select la.lano, la.lno, m.name, la.rdt
from lect_appl la
     inner join memb m on la.mno=m.mno;

/* 3단계: 수강 신청한 학생의 재직 여부 출력
 * => inner join 에서 inner는 생략 가능
 */
select la.lano, la.lno, m.name, s.work, la.rdt
from lect_appl la
        join memb m on la.mno=m.mno
        join stnt s on la.mno=s.mno;

/* 4단계: 수상신청한 강의 번호 대신 강의명을 출력 */
select la.lano, l.titl, m.name, s.work, la.rdt, l.rno
from lect_appl la
        join memb m on la.mno=m.mno
        join stnt s on la.mno=s.mno
        join lect l on la.lno=l.lno;

/* 5단계: 강의실 이름을 출력한다.
 * => 강의실 번호는 lect 테이블 데이터에 있다.
 * => 강의실 이름은 room 테이블 데이터에 있다.
 */
select la.lano, l.titl, m.name, s.work, la.rdt, r.name, l.mno
from lect_appl la
        join memb m on la.mno=m.mno
        join stnt s on la.mno=s.mno
        join lect l on la.lno=l.lno
        left outer join room r on l.rno=r.rno;

/* 6단계: 매니저 이름을 출력
 * => 매니저 번호는 lect 테이블에 있다.
 * => 매니저 이름은 memb 테이블에 있다.
 */
select
  la.lano,
  l.titl,
  m.name member_name,
  s.work,
  la.rdt,
  r.name room_name,
  m2.name manager_name
from lect_appl la
        join memb m on la.mno=m.mno
        join stnt s on la.mno=s.mno
        join lect l on la.lno=l.lno
        left outer join room r on l.rno=r.rno
        left outer join memb m2 on l.mno=m2.mno;

/* 7단계: 매니저의 직위 출력
 * => 매니저 번호는 lect 테이블 있다.
 * => 매니저 직위는 mgr 테이블에 있다.
 */
select
  la.lano,
  l.titl,
  m.name snm,
  s.work,
  la.rdt,
  r.name rnm,
  m2.name mnm,
  mr.posi
from lect_appl la
        join memb m on la.mno=m.mno
        join stnt s on la.mno=s.mno
        join lect l on la.lno=l.lno
        left outer join room r on l.rno=r.rno
        left outer join memb m2 on l.mno=m2.mno
        left outer join mgr mr on l.mno=mr.mno;






--------------------------------------------------------
1단계)
select lano, lno, mno, rdt
from lect_appl;

+------+-----+-----+---------------------+
| lano | lno | mno | rdt                 |
+------+-----+-----+---------------------+
|    1 |   1 | 100 | 2017-11-02 00:00:00 |
|    2 |   1 | 101 | 2017-11-03 00:00:00 |
|    3 |   1 | 102 | 2017-11-04 00:00:00 |
|    4 |   2 | 104 | 2017-12-06 00:00:00 |
|    5 |   2 | 100 | 2017-12-07 00:00:00 |
|    6 |   3 | 101 | 2017-10-08 00:00:00 |
|    7 |   3 | 102 | 2017-11-09 00:00:00 |
|    8 |   3 | 104 | 2017-11-11 00:00:00 |
+------+-----+-----+---------------------+

2단계)
select
  lano,
  lno,
  mno,
  date_format(rdt, '%Y-%m-%d') reg_date
from lect_appl;

+------+-----+-----+------------+
| lano | lno | mno | reg_date   |
+------+-----+-----+------------+
|    1 |   1 | 100 | 2017-11-02 |
|    2 |   1 | 101 | 2017-11-03 |
|    3 |   1 | 102 | 2017-11-04 |
|    4 |   2 | 104 | 2017-12-06 |
|    5 |   2 | 100 | 2017-12-07 |
|    6 |   3 | 101 | 2017-10-08 |
|    7 |   3 | 102 | 2017-11-09 |
|    8 |   3 | 104 | 2017-11-11 |
+------+-----+-----+------------+

3단계)
select
  lano,
  lno,
  mno,
  date_format(rdt, '%Y-%m-%d') reg_date,
  titl
from lect_appl
  inner join lect on lno=lno;

ERROR 1052 (23000): Column 'lno' in field list is ambiguous

4단계)
select
 lano,
 lect_appl.lno,
 lect_appl.mno,
 date_format(rdt, '%Y-%m-%d') reg_date,
 titl
from lect_appl
  inner join lect on lect_appl.lno=lect.lno;

+------+-----+-----+------------+------------------+
| lano | lno | mno | reg_date   | titl             |
+------+-----+-----+------------+------------------+
|    1 |   1 | 100 | 2017-11-02 | 자바프로그래밍   |
|    2 |   1 | 101 | 2017-11-03 | 자바프로그래밍   |
|    3 |   1 | 102 | 2017-11-04 | 자바프로그래밍   |
|    4 |   2 | 104 | 2017-12-06 | IoT프로그래밍    |
|    5 |   2 | 100 | 2017-12-07 | IoT프로그래밍    |
|    6 |   3 | 101 | 2017-10-08 | 윈도우프로그래밍 |
|    7 |   3 | 102 | 2017-11-09 | 윈도우프로그래밍 |
|    8 |   3 | 104 | 2017-11-11 | 윈도우프로그래밍 |
+------+-----+-----+------------+------------------+

5단계)
select
  la.lano,
  la.lno,
  la.mno,
  date_format(la.rdt, '%Y-%m-%d') reg_date,
  l.titl
from lect_appl la
  inner join lect l on la.lno=l.lno;

+------+-----+-----+------------+------------------+
| lano | lno | mno | reg_date   | titl             |
+------+-----+-----+------------+------------------+
|    1 |   1 | 100 | 2017-11-02 | 자바프로그래밍   |
|    2 |   1 | 101 | 2017-11-03 | 자바프로그래밍   |
|    3 |   1 | 102 | 2017-11-04 | 자바프로그래밍   |
|    4 |   2 | 104 | 2017-12-06 | IoT프로그래밍    |
|    5 |   2 | 100 | 2017-12-07 | IoT프로그래밍    |
|    6 |   3 | 101 | 2017-10-08 | 윈도우프로그래밍 |
|    7 |   3 | 102 | 2017-11-09 | 윈도우프로그래밍 |
|    8 |   3 | 104 | 2017-11-11 | 윈도우프로그래밍 |
+------+-----+-----+------------+------------------+

6단계)
select
  la.lano,
  date_format(la.rdt, '%Y-%m-%d') reg_date,
  l.titl,
  m.name,
from lect_appl la
  inner join lect l on la.lno=l.lno
  inner join memb m on la.mno=m.mno;

+------+------------+------------------+------+
| lano | reg_date   | titl             | name |
+------+------------+------------------+------+
|    1 | 2017-11-02 | 자바프로그래밍   | s100 |
|    2 | 2017-11-03 | 자바프로그래밍   | s101 |
|    3 | 2017-11-04 | 자바프로그래밍   | s102 |
|    4 | 2017-12-06 | IoT프로그래밍    | s104 |
|    5 | 2017-12-07 | IoT프로그래밍    | s100 |
|    6 | 2017-10-08 | 윈도우프로그래밍 | s101 |
|    7 | 2017-11-09 | 윈도우프로그래밍 | s102 |
|    8 | 2017-11-11 | 윈도우프로그래밍 | s104 |
+------+------------+------------------+------+

7단계)
select
  la.lano,
  date_format(la.rdt, '%Y-%m-%d') reg_date,
  l.titl,
  m.name,
  s.work
from lect_appl la
  inner join lect l on la.lno=l.lno
  inner join memb m on la.mno=m.mno
  inner join stnt s on la.mno=s.mno;

+------+------------+------------------+------+------+
| lano | reg_date   | titl             | name | work |
+------+------------+------------------+------+------+
|    5 | 2017-12-07 | IoT프로그래밍    | s100 | N    |
|    4 | 2017-12-06 | IoT프로그래밍    | s104 | N    |
|    6 | 2017-10-08 | 윈도우프로그래밍 | s101 | Y    |
|    7 | 2017-11-09 | 윈도우프로그래밍 | s102 | N    |
|    8 | 2017-11-11 | 윈도우프로그래밍 | s104 | N    |
|    1 | 2017-11-02 | 자바프로그래밍   | s100 | N    |
|    2 | 2017-11-03 | 자바프로그래밍   | s101 | Y    |
|    3 | 2017-11-04 | 자바프로그래밍   | s102 | N    |
+------+------------+------------------+------+------+

8단계)
select
  la.lano,
  date_format(la.rdt, '%Y-%m-%d') reg_date,
  l.titl,
  m.name,
  s.work,
  r.name
from lect_appl la
  inner join lect l on la.lno=l.lno
  inner join memb m on la.mno=m.mno
  inner join stnt s on la.mno=s.mno
  left outer join room r on l.rno=r.rno;

+------+------------+------------------+------+------+------+
| lano | reg_date   | titl             | name | work | name |
+------+------------+------------------+------+------+------+
|    1 | 2017-11-02 | 자바프로그래밍   | s100 | N    | 501  |
|    5 | 2017-12-07 | IoT프로그래밍    | s100 | N    | 301  |
|    2 | 2017-11-03 | 자바프로그래밍   | s101 | Y    | 501  |
|    6 | 2017-10-08 | 윈도우프로그래밍 | s101 | Y    | NULL |
|    3 | 2017-11-04 | 자바프로그래밍   | s102 | N    | 501  |
|    7 | 2017-11-09 | 윈도우프로그래밍 | s102 | N    | NULL |
|    4 | 2017-12-06 | IoT프로그래밍    | s104 | N    | 301  |
|    8 | 2017-11-11 | 윈도우프로그래밍 | s104 | N    | NULL |
+------+------------+------------------+------+------+------+

select
  la.lano,
  /*date_format(la.rdt, '%Y-%m-%d') reg_date,*/
  to_char(la.rdt, 'YYYY-MM-DD') reg_date,
  l.titl,
  m.name,
  s.work,
  r.name
from lect_appl la
  inner join lect l on la.lno=l.lno
  inner join memb m on la.mno=m.mno
  inner join stnt s on la.mno=s.mno
  left outer join room r on l.rno=r.rno;

+------+------------+----------------+------+------+------+
| lano | reg_date   | titl           | name | work | name |
+------+------------+----------------+------+------+------+
|    1 | 2017-11-02 | 자바프로그래밍 | s100 | N    | 501  |
|    2 | 2017-11-03 | 자바프로그래밍 | s101 | Y    | 501  |
|    3 | 2017-11-04 | 자바프로그래밍 | s102 | N    | 501  |
| NULL | NULL       | NULL           | NULL | NULL | 502  |
| NULL | NULL       | NULL           | NULL | NULL | 503  |
| NULL | NULL       | NULL           | NULL | NULL | 301  |
| NULL | NULL       | NULL           | NULL | NULL | 302  |
| NULL | NULL       | NULL           | NULL | NULL | 501  |
| NULL | NULL       | NULL           | NULL | NULL | 601  |
|    4 | 2017-12-06 | IoT프로그래밍  | s104 | N    | 301  |
|    5 | 2017-12-07 | IoT프로그래밍  | s100 | N    | 301  |
| NULL | NULL       | NULL           | NULL | NULL | 302  |
| NULL | NULL       | NULL           | NULL | NULL | 303  |
+------+------------+----------------+------+------+------+
