INSERT INTO hobby_tb (hobby_nm, hobby_cd)
VALUES
('등산', '1'),
('게임', '2'),
('야구', '3'),
('클라이밍', '4'),
('자전거', '5')
	ON CONFLICT (hobby_cd)
	DO NOTHING;

INSERT INTO dept_tb (dept_no, dept_nm)
VALUES
('1', '영업팀'),
('2', '인프라팀'),
('3', '서비스팀'),
('4', '클라우드팀')
	ON CONFLICT (dept_no)
	DO NOTHING;
