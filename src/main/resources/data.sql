INSERT INTO hobby_tb (hobby_nm, hobby_cd)
SELECT '등산', '1' WHERE NOT EXISTS (SELECT 1 FROM hobby_tb WHERE hobby_nm = '등산' AND hobby_cd = '1');
INSERT INTO hobby_tb (hobby_nm, hobby_cd)
SELECT '게임', '2' WHERE NOT EXISTS (SELECT 1 FROM hobby_tb WHERE hobby_nm = '게임' AND hobby_cd = '2');
INSERT INTO hobby_tb (hobby_nm, hobby_cd)
SELECT '야구', '3' WHERE NOT EXISTS (SELECT 1 FROM hobby_tb WHERE hobby_nm = '야구' AND hobby_cd = '3');
INSERT INTO hobby_tb (hobby_nm, hobby_cd)
SELECT '클라이밍', '4' WHERE NOT EXISTS (SELECT 1 FROM hobby_tb WHERE hobby_nm = '클라이밍' AND hobby_cd = '4');
INSERT INTO hobby_tb (hobby_nm, hobby_cd)
SELECT '자전거', '5' WHERE NOT EXISTS (SELECT 1 FROM hobby_tb WHERE hobby_nm = '자전거' AND hobby_cd = '5');

INSERT INTO dept_tb (dept_no, dept_nm)
SELECT '1', '영업팀' WHERE NOT EXISTS (SELECT 1 FROM dept_tb WHERE dept_no = '1' AND dept_nm = '영업팀');
INSERT INTO dept_tb (dept_no, dept_nm)
SELECT '2', '인프라팀' WHERE NOT EXISTS (SELECT 1 FROM dept_tb WHERE dept_no = '2' AND dept_nm = '인프라팀');
INSERT INTO dept_tb (dept_no, dept_nm)
SELECT '3', '서비스팀' WHERE NOT EXISTS (SELECT 1 FROM dept_tb WHERE dept_no = '3' AND dept_nm = '서비스팀');
INSERT INTO dept_tb (dept_no, dept_nm)
SELECT '4', '클라우드팀' WHERE NOT EXISTS (SELECT 1 FROM dept_tb WHERE dept_no = '4' AND dept_nm = '클라우드팀');