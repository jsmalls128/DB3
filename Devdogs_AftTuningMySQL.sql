create index std_index on student(id);
-- Query 1 
SELECT sql_no_cache name FROM student where id = 653061;
-- Query 2
SELECT sql_no_cache name FROM student where id between 30000 and 50000;
-- Query 3
SELECT student.name FROM student JOIN transcript ON transcript.studId = student.id WHERE transcript.crsCode = 'crsCode800622';
-- Query 4
SELECT student.name FROM student, transcript, teaching, professor WHERE transcript.crsCode = teaching.crsCode AND studnt.id = transcript.studId AND
teaching.profId = professor.id AND professor.name = 'name297872';
-- Query 5
create index course_crsCode_index on course(crsCode);
create index transcript_crsCode_index on transcript(crsCode);
SELECT student name from course join transcript on course.crsCode = transcritp.crsCode join student on studId = id WHERE deptid = 
'deptId111660' and deptId != 'deptId181755' group by student.name;
-- Query 6
SELECT * FROM course JOIN transcript on course.crsCode = transcript.crsCode join student on sudId = id WHERE deptId =
'deptId181755' group by student.name;