-- Query number 1
SELECT sql_no_cache student.name FROM teaching join student join transcript where student.id = 653061 group by student.name limit 1;
-- Query number 2
SELECT sql_no_cache name FROM  teaching join student where id between 30000 and 50000 group by name;
-- Query number 3 
SELECT student.name FROM student, transcript WHERE student.id = transcript.studId AND transcript.crsCode='crsCode800622';
-- Query number 4
SELECT student.name FROM student, transcript, teaching, professor WHERE transcript.crsCode = teaching.crsCode AND student.id = transcript.studId AND
teaching.profId = professor.Id AND professor.name = 'name297872';
-- Query number 5
SELECT name FROM course join transcript on course.crsCode = transcript.crsCode join student on studId = id join teaching WHERE deptId = 'deptId111660' AND deptId != 'deptId181755'
group by student.name;
-- Query number 6
SELECT * FROM course join transcript on course.crsCode = transcript.crsCode join student on studId = id join teaching WHERE deptId  = 'deptId181755'
group by student.name;