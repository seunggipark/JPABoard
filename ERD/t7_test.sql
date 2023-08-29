SELECT TABLE_NAME FROM information_schema.TABLES
WHERE TABLE_SCHEMA = 'mydb2304'
AND TABLE_NAME LIKE 't7_%'
;

SELECT * FROM t7_authority;
SELECT * FROM t7_user;
SELECT * FROM t7_user_authorities;
SELECT * FROM t7_post;
SELECT * FROM t7_attachment ;
SELECT * FROM t7_comment;

# 페이징 테스트용 다량의 데이터
INSERT INTO t7_post(user_id, subject, content, reg_date)
SELECT user_id, subject, content, now() FROM t7_post;