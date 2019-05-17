package com.ruoxi.caronline.repositories;

import com.ruoxi.caronline.data.UrlEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcUrlRepo implements UrlRepo {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcUrlRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String findLongUrl(String shortUrlCode) {
        String query = "select long_url from urldata where short_url_code=?";
        try {
            return jdbcTemplate.queryForObject(query, String.class, shortUrlCode);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void saveUrlData(UrlEntity urlEntity) {
        String query = "insert into urldata (id, short_url_code, long_url) values (?,?,?)";
        jdbcTemplate.update(query, urlEntity.getId(), urlEntity.getShortUrlCode(), urlEntity.getLongUrl());
    }

    @Override
    public Long findMaxIndex() {
        String query = "select max(id) from urldata";
        try {
            Long maxId = jdbcTemplate.queryForObject(query, Long.class);
            return maxId != null ? maxId : 0;
        } catch (EmptyResultDataAccessException e) {
            return 0L;
        }
    }
}
