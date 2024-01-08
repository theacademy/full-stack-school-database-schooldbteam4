package com.wileyedge.fullstackschool.service;

import com.wileyedge.fullstackschool.dao.TeacherDao;
import com.wileyedge.fullstackschool.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherServiceInterface {

    //YOUR CODE STARTS HERE
    //teacher does not need to directly interface with the other layers
    //it just works on its own entity table - no Foreign key constraints
    @Autowired
    TeacherDao teacherDao;

    public TeacherServiceImpl(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }


    //YOUR CODE ENDS HERE

    public List<Teacher> getAllTeachers() {
        //YOUR CODE STARTS HERE
        //pass through method

        return teacherDao.getAllTeachers();

        //YOUR CODE ENDS HERE
    }

    public Teacher getTeacherById(int id) {
        //YOUR CODE STARTS HERE
        //teacher dao will return null if the teacher is not found
        Teacher teach = teacherDao.findTeacherById(id);

        if (teach == null) {
            teach.setTeacherFName("Teacher Not Found");
            teach.setTeacherLName("Teacher Not Found");
        }

        return teach;

        //YOUR CODE ENDS HERE
    }

    public Teacher addNewTeacher(Teacher teacher) {
        //YOUR CODE STARTS HERE

        //program expects teacher to be directly overwritten
        //assumes non-null teacher

        //strings for setting f/l name if blank and boolean flag
        final String F_NAME_BLANK = "First Name blank, teacher NOT added";
        final String L_NAME_BLANK = "Last Name blank, teacher NOT added";
        boolean hasBlank = false;

        //check if either first name or last name is blank.
        //if blank, then set string and return without adding to db
        if (teacher.getTeacherFName() == null || teacher.getTeacherFName().isBlank()) {
            teacher.setTeacherFName(F_NAME_BLANK);
            hasBlank = true;
        }

        if (teacher.getTeacherLName() == null || teacher.getTeacherLName().isBlank()) {
            teacher.setTeacherLName(L_NAME_BLANK);
            hasBlank = true;
        }


        //if has blank, return temp, else add to db and return the teacher with id
        return (hasBlank) ? teacher : teacherDao.createNewTeacher(teacher);


        //YOUR CODE ENDS HERE
    }

    public Teacher updateTeacherData(int id, Teacher teacher) {
        //YOUR CODE STARTS HERE

        //not match string
        final String NOT_MATCH = "IDs do not match, teacher not updated";

        //check that id is same as teacher's id - if not match, directly
        //modify the teacher
        if (id != teacher.getTeacherId()) {
            teacher.setTeacherFName(NOT_MATCH);
            teacher.setTeacherLName(NOT_MATCH);
            return teacher;
        }

        //ids match, run the update, return updated teacher
        teacherDao.updateTeacher(teacher);
        return teacher;

        //YOUR CODE ENDS HERE
    }

    public void deleteTeacherById(int id) {
        //YOUR CODE STARTS HERE
        //pass through method: calls the dao
        teacherDao.deleteTeacher(id);

        //YOUR CODE ENDS HERE
    }
}
