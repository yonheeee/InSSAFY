USE ssafydb;

---------------------------------------------------------------
-- EQUI JOIN
---------------------------------------------------------------

# 사원이름과 부서이름 조회
# join ~ using
select ename, dname
from emp join dept using (deptno);
-- using 필드 이름이 같은 경우
-- on 은 같을 때 & 다를 때
-- 기본은 inner join
 
# join ~ on : 테이블이름 활용
select ename, dname
from emp join dept on (emp.deptno = dept.deptno);

# join ~ on : 테이블별칭 활용
select e.ename, e.deptno , d.dname
from emp e join dept d on (e.deptno = d.deptno);


# 함축형
select e.ename, e.deptno , d.dname
from emp e join dept d 
where e.deptno = d.deptno;
-- join ansi를 쓰지 않고 from절에 조회하고 싶은 조인 연결하고 싶은 걸 where절로

# 사원이름과 부서이름, 부서번호 조회
select e.ename, d.dname, d.deptno
from emp e join dept d on(e.deptno = d.DEPTNO);

# 직무가 CLERK인 사원의 이름, 직무, 부서이름 조회
select e.ename, e.job, d.dname
from emp e join dept d on (e.deptno = d.deptno)
where e.job = "CLERK";

---------------------------------------------------------------
-- NON-EQUI JOIN
---------------------------------------------------------------

# 사원의 이름, 월급여, 급여 등급 조회
select ename, sal, grade
from emp
join salgrade on sal between losal and hisal;


---------------------------------------------------------------
-- OUTER JOIN
---------------------------------------------------------------

# TEST DATA INSERT

  	INSERT INTO emp(empno, ename, sal, deptno, hiredate) 
	VALUES(9998,'TAEHEE',12000,20,now());
	INSERT INTO emp(empno, ename, sal, hiredate)
	VALUES(9999,'SSAFY',7000,now());
 


# 월급여 구간을 벗어나는 사원도 포함 하여 사원 이름, 월급여, 급여 등급 조회
select ename, sal, grade
from emp
left join salgrade on sal between losal and hisal;


# 부서 배치 받지 못한 사원까지 포함하여 모든 사원 이름, 부서 이름 조회
select ename, dname
from dept right join emp using (deptno);
-- using은 괄호 생략 X

# 부서 배치 받지 못한 사원까지 포함하여 모든 사원 이름, 부서 이름 조회(부서 이름 없을 경우 '해당부서없음')



# 소속 사원이 없는 모든 부서를 포함하여 사원이름, 부서이름 조회



# 월급여에 해당하는 급여등급구간이 존재하지 않는 사원도 부서배치 받지 못한 사원도 포함하여 사원이름, 월급여, 급여등급, 부서이름  조회(outer join)



---------------------------------------------------------------
-- NATURAL JOIN
---------------------------------------------------------------

# 모든 사원의 사원이름과 부서이름 조회
select emp.ename, dept.dname
from emp natural join dept;


---------------------------------------------------------------
-- CROSS JOIN
---------------------------------------------------------------

# 사원과 부서 CROSS JOIN
select *
from emp cross join dept;


---------------------------------------------------------------
-- SELF JOIN
---------------------------------------------------------------

# 모든 사원의 이름과 자신의 상사(관리자) 이름을 함께 조회
select e.ENAME, ifnull(m.ename,'상사없음')
from emp e left join emp m on (e.mgr = m.empno);

# 상사(관리자)인 사원의 사번과 이름 조회



# 모든 사원의 이름과 자신의 상사(관리자) 이름을 함께 조회(단, 상사(관리자)가 없는 직원까지 모두 조회)
# 상사가 없을경우 '없음'으로 나타낸다.
