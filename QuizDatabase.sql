SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";
CREATE DATABASE Quiz_project;
use Quiz_project;
CREATE TABLE `STUDENTS_Account` (
  `STU_ID` varchar(10) NOT NULL PRIMARY KEY,
  `STUD_PASS` varchar(7) DEFAULT NULL,
  `STUD_SCORE` int(2) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
INSERT INTO `STUDENTS_Account` (`STU_ID`, `STUD_PASS`,`STUD_SCORE`) VALUES
('STU1001','1001','0');

CREATE TABLE `QUIZ_ques` (
  `QUIZ_NO` INT(2) NOT NULL PRIMARY KEY,
  `QUIZ_QUES` varchar(50) DEFAULT NULL,
  `OPTION_A` varchar(50) DEFAULT NULL,
  `OPTION_B` varchar(50) DEFAULT NULL,
  `OPTION_C` varchar(50) DEFAULT NULL,
  `OPTION_D` varchar(50) DEFAULT NULL,
  `RIGHT_OPTION` varchar(50) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

INSERT INTO `QUIZ_ques` (`QUIZ_NO`, `QUIZ_QUES`,`OPTION_A`,`OPTION_B`,`OPTION_C`,`OPTION_D`,`RIGHT_OPTION`) VALUES
('01','Which of the following is not OOPS concept in Java?','Inheritance','Encapsulation','Polymorphism','Compilation','Compilation'),
('02','Which of the following is a type of polymorphism in Java?','Compile time polymorphism','Execution time polymorphism','Multiple polymorphism','Multilevel polymorphism','Compile time polymorphism'),
('03','When does method overloading is determined?','At run time','At compile time','At coding time','At execution time','At compile time'),
('04','Which concept of Java is a way of converting real world objects in terms of class?','Polymorphism','Encapsulation','Abstraction','Inheritance','Abstraction'),
('05','Which concept of Java is achieved by combining methods and attribute into a class?','Polymorphism','Encapsulation','Abstraction','Inheritance','Encapsulation'),
('06','What is it called if an object has its own lifecycle and there is no owner?','Aggregation','Composition','Encapsulation','Association','Association'),
('07','What is it called where child object gets killed if parent object is killed?','Aggregation','Composition','Encapsulation','Association','Composition'),
('08','What is it called where object has its own lifecycle and child object cannot belong to another parent object?','Aggregation','Composition','Encapsulation','Association','Aggregation'),
('09','Which component is used to compile, debug and execute java program?','JVM','JDK','JIT','JRE','JDK'),
('10','Which statement is true about java?','Platform independent programming language','Platform dependent programming language','Code dependent programming language','Sequence dependent programming language','Platform independent programming language'),
('11','Which of the below is invalid identifier with the main method?','public','static','private','final','private'),
('12','Which of the following is a valid declaration of an object of class Box?','Box obj = new Box();','obj = new Box();','new Box() obj;','Box obj = new Box;','Box obj = new Box();'),
('13','Which of these operators is used to allocate memory for an object?','malloc','alloc','new','give','new'),
('14','Which of these statement is incorrect?','Every class must contain a main() method','Applets do not require a main() method at all','There can be only one main() method in a program','main() method must be made public','Every class must contain a main() method'),
('15','Which of the following statements is correct?','Public method is accessible to all other classes in the hierarchy','Public method is accessible only to subclasses of its parent class','Public method can only be called by object of its class','Public method can be accessed by calling object of the public class','Public method is accessible to all other classes in the hierarchy'),
('16','What is the return type of a method that does not return any value?','int','float','void','double','void'),
('17','What is the process of defining more than one method in a class differentiated by method signature?','Function overriding','Function overloading','Function doubling','Function doubling','None of the mentioned'),
('18','Which of the following is a method having same name as that of it’s class?','finilize','delete','class','constructor','constructor'),
('19',' Which keyword is used by the method to refer to the object that invoked it?','import','catch','abstract','this','this'),
('20','Which function is used to perform some action when the object is to be destroyed?','finalize()','delete()','main()','none of the mentioned','finalize()');