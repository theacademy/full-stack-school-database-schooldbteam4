package com.wileyedge.fullstackschool.dao.mappers;

import com.wileyedge.fullstackschool.model.Teacher;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherMapper implements RowMapper<Teacher> {
    @Override
    public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
        //YOUR CODE STARTS HERE
        Teacher teach = new Teacher();
        teach.setTeacherId(rs.getInt("tid"));
        teach.setTeacherFName(rs.getString("tFName"));
        teach.setTeacherLName(rs.getString("tLName"));
        teach.setDept(rs.getString("dept"));

        return teach;

        //YOUR CODE ENDS HERE
    }
}