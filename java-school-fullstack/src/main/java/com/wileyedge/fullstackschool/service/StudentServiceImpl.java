package com.wileyedge.fullstackschool.service;

import com.wileyedge.fullstackschool.dao.StudentDao;
import com.wileyedge.fullstackschool.model.Course;
import com.wileyedge.fullstackschool.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentServiceInterface {

    //YOUR CODE STARTS HERE
    @Autowired
    StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Autowired
    CourseServiceImpl courseService;



    //YOUR CODE ENDS HERE

    public List<Student> getAllStudents() {
        //YOUR CODE STARTS HERE

        return studentDao.getAllStudents();

        //YOUR CODE ENDS HERE
    }

    public Student getStudentById(int id) {
        //YOUR CODE STARTS HERE

        return studentDao.findStudentById(id);

        //YOUR CODE ENDS HERE
    }

    public Student addNewStudent(Student student) {
        //YOUR CODE STARTS HERE
        if(student.getStudentFirstName().isBlank()){
            student.setStudentFirstName("First Name blank, student NOT added");
        }
        if(student.getStudentLastName().isBlank()){
            student.setStudentLastName("Last Name blank, student NOT added");
        }
        return studentDao.createNewStudent(student);

        //YOUR CODE ENDS HERE
    }

    public Student updateStudentData(int id, Student student) {
        //YOUR CODE STARTS HERE
        if(student.getStudentId() == id){
            studentDao.updateStudent(student);
        }else {
            student.setStudentFirstName("IDs do not match, student not updated");
            student.setStudentLastName("IDs do not match, student not updated");
        }
        return student;
        //YOUR CODE ENDS HERE
    }

    public void deleteStudentById(int id) {
        //YOUR CODE STARTS HERE

        studentDao.deleteStudent(id);

        //YOUR CODE ENDS HERE
    }

    public void deleteStudentFromCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE
        Student checkStudent = getStudentById(studentId);
        Course checkCourse = courseService.getCourseById(courseId);
        if(checkStudent.getStudentFirstName() == "Student Not Found"){
            System.out.println("Student Not Found");
        } else if (checkCourse.getCourseName() == "Course Not Found") {
            System.out.println("Course not found");
        }else {
            studentDao.deleteStudentFromCourse(studentId, courseId);
            System.out.println("Student: <"+studentId+"> deleted from course: <"+courseId+">");
            //System.out.println("Student: <STUDENT ID> deleted from course: <COURSE ID>");
        }

        //YOUR CODE ENDS HERE
    }

    public void addStudentToCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE
        try{
            Student checkStudent = getStudentById(studentId);
            Course checkCourse = courseService.getCourseById(courseId);
            if(checkStudent.getStudentFirstName() == "Student Not Found"){
                System.out.println("Student Not Found");
            } else if (checkCourse.getCourseName() == "Course Not Found") {
                System.out.println("Course not found");
            }else {
                studentDao.addStudentToCourse(studentId, courseId);
                System.out.println("Student: <"+studentId+"> added to course: <"+courseId+">");
                //System.out.println("Student: <STUDENT ID> added to course: <COURSE ID>");
            }
        }catch (Exception e){
            System.out.println("Student: <STUDENT ID> already enrolled in course: <COURSE ID>");
        }

        //YOUR CODE ENDS HERE
    }
}
