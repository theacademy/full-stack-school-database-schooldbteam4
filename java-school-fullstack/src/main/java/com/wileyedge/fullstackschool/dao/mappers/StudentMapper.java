package com.wileyedge.fullstackschool.dao.mappers;

import com.wileyedge.fullstackschool.model.Student;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        //YOUR CODE STARTS HERE

        Student st = new Student();
        st.setStudentId(rs.getInt("sid"));
        st.setStudentFirstName(rs.getString("fName"));
        st.setStudentLastName(rs.getString("lName"));
        return st;

        //YOUR CODE ENDS HERE
    }
}
