CREATE DATABASE db_imis2 

CREATE TABLE tb_user
(
  coluserid    INT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  colname      VARCHAR(30)         NOT NULL,
  colpassword  VARCHAR(255)        NOT NULL,
  colemail     VARCHAR(30)         NOT NULL,
  colstudentno VARCHAR(50)         NOT NULL,
  colrealname  VARCHAR(20)         NOT NULL
)
  ENGINE = InnoDB
  CHARSET = utf8
  COLLATE utf8_general_ci;
CREATE UNIQUE INDEX tb_user_colname_uindex ON tb_user (colname);
INSERT INTO tb_user (colname, colpassword, colemail, colstudentno, colrealname) VALUES ('root','-4e4hc3pvvu8n0rj1uena76948n37q23r','root@gmail.com','00001','FJY');
SELECT * FROM tb_user;


CREATE TABLE tb_file
(
    colfileid   INT(10) PRIMARY KEY AUTO_INCREMENT,
    coluserid   INT(10),
    coltime     VARCHAR(128) NOT NULL,
    colip       VARCHAR(128),
    colrealname VARCHAR(255),
    colfilename VARCHAR(255),
    colfilesize VARCHAR(32),
    colfilepath VARCHAR(128)
)ENGINE = InnoDB CHARSET=utf8 COLLATE utf8_general_ci;
ALTER TABLE tb_file COMMENT = '文件表';
ALTER TABLE tb_file ADD CONSTRAINT FK_fileuser FOREIGN KEY (coluserid) REFERENCES tb_user (coluserid);

CREATE TABLE tb_admin
(
  adminid   INT(10) PRIMARY KEY AUTO_INCREMENT,
  coluserid INT(10),
  coltime   VARCHAR(128) NOT NULL,
  CONSTRAINT FK_adminuser FOREIGN KEY (coluserid) REFERENCES tb_user (coluserid)
)
  ENGINE = InnoDB
  CHARSET = utf8
  COLLATE utf8_general_ci;
ALTER TABLE tb_admin COMMENT = '管理员表';

CREATE TABLE tb_student
(
    studentid   INT(10) PRIMARY KEY AUTO_INCREMENT,
    coluserid   INT(10),
    coltime     VARCHAR(128) NOT NULL,
  CONSTRAINT FK_sutdentuser FOREIGN KEY (coluserid) REFERENCES tb_user (coluserid)
)ENGINE = InnoDB CHARSET=utf8 COLLATE utf8_general_ci;
ALTER TABLE tb_student COMMENT = '学生表';

CREATE TABLE tb_teacher
(
    teacherid   INT(10) PRIMARY KEY AUTO_INCREMENT,
    coluserid   INT(10),
    coltime    VARCHAR(128) NOT NULL,
  CONSTRAINT FK_teacheruser FOREIGN KEY (coluserid) REFERENCES tb_user (coluserid)
)ENGINE = InnoDB CHARSET=utf8 COLLATE utf8_general_ci;
ALTER TABLE tb_teacher COMMENT = '老师表';

CREATE TABLE tb_log
(
    logid     INT(10) PRIMARY KEY AUTO_INCREMENT,
    coluserid INT(10),
    coltime   VARCHAR(128) NOT NULL,
    colip     VARCHAR(128),
    colheader VARCHAR(255),
  CONSTRAINT FK_loguser FOREIGN KEY (coluserid) REFERENCES tb_user (coluserid)
)
    ENGINE = InnoDB
    CHARSET = utf8
    COLLATE utf8_general_ci;
ALTER TABLE tb_log
    COMMENT = '日志表';

CREATE TABLE tb_course
(
    courseNo   INT(10) PRIMARY KEY    AUTO_INCREMENT,
    courseName VARCHAR(128) NOT NULL,
    courseTime VARCHAR(128)    NOT NULL,
    teacherid  INT(10),
  CONSTRAINT FK_teacherid FOREIGN KEY (teacherid) REFERENCES tb_teacher (teacherid)
)
    ENGINE = InnoDB
    CHARSET = utf8
    COLLATE utf8_general_ci;
ALTER TABLE tb_course COMMENT = '课程表';

CREATE TABLE tb_homework
(
  workId     INT(10) PRIMARY KEY    AUTO_INCREMENT,
  workName   VARCHAR(128) NOT NULL,
  workTime   VARCHAR(128) NOT NULL ,
  colfileid  INT(10),
  workFolder VARCHAR(255) NOT NULL,
  courseNo   INT(10),
  workRemark VARCHAR(255),
  CONSTRAINT FK_colfileid FOREIGN KEY (colfileid) REFERENCES tb_file (colfileid),
  CONSTRAINT FK_courseNo FOREIGN KEY (courseNo) REFERENCES tb_course (courseNo)
)
  ENGINE = InnoDB
  CHARSET = utf8
  COLLATE utf8_general_ci;
ALTER TABLE tb_homework COMMENT = '作业表';

CREATE TABLE tb_workstatus
(
  statusid  INT(10) PRIMARY KEY    AUTO_INCREMENT,
  coluserid INT(10),
  workId    INT(10),
  colstatus INT(10),
  CONSTRAINT FK_statususer FOREIGN KEY (coluserid) REFERENCES tb_user (coluserid),
  CONSTRAINT FK_workId FOREIGN KEY (workId) REFERENCES tb_homework (workId)
)
  ENGINE = InnoDB
  CHARSET = utf8
  COLLATE utf8_general_ci;
ALTER TABLE tb_workstatus COMMENT = '作业提交状态表';

CREATE TABLE tb_feedback
(
  feedbackid  INT(10) PRIMARY KEY    AUTO_INCREMENT,
  coluserid INT(10),
  feedbackContent    VARCHAR(255),
  issueTime VARCHAR(128),
  CONSTRAINT FK_feedbackuser FOREIGN KEY (coluserid) REFERENCES tb_user (coluserid)
)
  ENGINE = InnoDB
  CHARSET = utf8
  COLLATE utf8_general_ci;
ALTER TABLE tb_workstatus COMMENT = '反馈信息表';

CREATE TABLE tb_notice
(
  noticeid  INT(10) PRIMARY KEY    AUTO_INCREMENT,
  adminid INT(10),
  noticeContent    VARCHAR(255),
  issueTime VARCHAR(128),
  CONSTRAINT FK_noticeuser FOREIGN KEY (adminid) REFERENCES tb_admin (adminid)
)
  ENGINE = InnoDB
  CHARSET = utf8
  COLLATE utf8_general_ci;
ALTER TABLE tb_workstatus COMMENT = '公告表';


CREATE VIEW v_workdetail
  AS
    SELECT
      workId,
      workName,
      workTime,
      f.colfileid,
      colfilename,
      c.courseName,
      workRemark,
      h.workFolder
    FROM tb_homework h, tb_course c, tb_file f
    WHERE h.colfileid = f.colfileid AND h.courseNo = c.courseNo;

CREATE VIEW v_log
  AS
    SELECT
    logid,
    colname,
    coltime   ,
    colip     ,
    colheader
    FROM tb_log,tb_user
    WHERE tb_user.coluserid = tb_log.coluserid;

CREATE VIEW v_feedback
  AS
    SELECT
      feedbackid,
      colname,
      feedbackContent,
      issueTime
    FROM tb_feedback, tb_user
    WHERE tb_user.coluserid = tb_feedback.coluserid;


CREATE VIEW v_course
  AS
    SELECT
    courseNo,
    courseName,
    courseTime,
    colrealname,
    colname
    FROM tb_course, tb_teacher,tb_user
    WHERE tb_user.coluserid = tb_teacher.coluserid AND tb_teacher.teacherid=tb_course.teacherid;

CREATE VIEW v_homework
  AS
    SELECT
      workId,
      workName,
      workTime,
      colfileid,
      workFolder,
      courseName,
      workRemark
    FROM tb_homework, tb_course
    WHERE tb_homework.courseNo = tb_course.courseNo;


CREATE VIEW v_userfile
  AS
    SELECT
      colfileid,
      coltime,
      colip,
      colfilename,
      colfilesize,
      colfilepath,
      courseName,
      workFolder,
      tb_user.colrealname,
      colstudentno
    FROM tb_file, tb_user
    WHERE tb_user.coluserid = tb_file.coluserid;


CREATE VIEW v_userinfo
  AS
    SELECT
      coluserid,
      colname,
      colemail,
      colstudentno,
      colrealname
    FROM tb_user;


CREATE VIEW v_userque
  AS
    SELECT
      tb_user.coluserid,
      colname,
      colquestion,
      colanswer,
      colrealname
    FROM tb_userque,tb_user
    WHERE tb_user.coluserid=tb_userque.coluserid;

