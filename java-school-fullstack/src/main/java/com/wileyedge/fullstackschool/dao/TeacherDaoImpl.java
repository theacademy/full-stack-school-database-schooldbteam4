package com.wileyedge.fullstackschool.dao;

import com.wileyedge.fullstackschool.dao.mappers.TeacherMapper;
import com.wileyedge.fullstackschool.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class TeacherDaoImpl implements TeacherDao {

    private final JdbcTemplate jdbcTemplate;

    public TeacherDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Teacher createNewTeacher(Teacher teacher) {
        //query string, keyholder to grab the autoassigned id
        final String INSERT_TEACHER = "INSERT INTO Teacher(tFName, tLName, dept) VALUES(?, ?, ?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(
                    INSERT_TEACHER,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, teacher.getTeacherFName());
            statement.setString(2, teacher.getTeacherLName());
            statement.setString(3, teacher.getDept());
            return statement;

        }, keyHolder);

        teacher.setTeacherId(keyHolder.getKey().intValue());

        return teacher;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        //query string, query: uses the mapper to convert sql result -> object
        final String sql = "SELECT * FROM Teacher;";
        return jdbcTemplate.query(sql, new TeacherMapper());
    }

    @Override
    public Teacher findTeacherById(int id) {
        //return null if teacher not found
        try {
            final String SEL_TEACH_BY_ID = "SELECT * FROM Teacher WHERE tid = ?";
            return jdbcTemplate.queryForObject(SEL_TEACH_BY_ID, new TeacherMapper(), id);
        }
        catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public void updateTeacher(Teacher t) {
        //update statement
        final String UPDATE_TEACHER = "UPDATE Teacher SET "
                + "tFName = ?, "
                + "tLName = ?, "
                + "dept = ? "
                + "WHERE tid = ?;";

        //jdbc template update call
        jdbcTemplate.update(UPDATE_TEACHER,
                t.getTeacherFName(),
                t.getTeacherLName(),
                t.getDept(),
                t.getTeacherId());
    }

    @Override
    public void deleteTeacher(int id) {
        //query string, delete
        final String DELETE_TEACHER = "DELETE FROM Teacher WHERE tid = ?;";
        jdbcTemplate.update(DELETE_TEACHER, id);
    }
}
