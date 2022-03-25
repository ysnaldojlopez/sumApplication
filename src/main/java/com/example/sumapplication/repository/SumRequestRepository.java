package com.example.sumapplication.repository;

import com.example.sumapplication.interfaces.ISumRequestRepository;
import com.example.sumapplication.models.SumRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class SumRequestRepository implements ISumRequestRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void saveRequestNumbers(int numberOne, int numberTwo) {
        String sql = "INSERT INTO requests (number_one, number_two) VALUES (:numberOne, :numberTwo)";
        SumRequestBody sumRequestBody = new SumRequestBody();
        sumRequestBody.setNumberOne(numberOne);
        sumRequestBody.setNumberTwo(numberTwo);
        BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(sumRequestBody);
        namedParameterJdbcTemplate.update(sql, paramSource);
    }

    @Override
    public SumRequestBody getDataOfTableRequests(int id_request) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id_request", id_request);
        return namedParameterJdbcTemplate.queryForObject(
                "SELECT * FROM REQUESTS WHERE ID_REQUEST = :id_request", namedParameters, new BeanPropertyRowMapper<>(SumRequestBody.class));
    }
}
