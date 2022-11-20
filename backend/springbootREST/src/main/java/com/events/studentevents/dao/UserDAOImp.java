package com.events.studentevents.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.events.studentevents.model.User;

@Repository
public class UserDAOImp implements UserDAO {
	@Autowired
	JdbcTemplate template;

	@Override
	public int save(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User getByid(int id) {
		// TODO Auto-generated method stub
		return template.queryForObject("SELECT * FROM Users", new BeanPropertyRowMapper<User>(User.class), id);
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		System.out.println("here!");
		List<User> res = template.query("SELECT * FROM Users", new BeanPropertyRowMapper<User>(User.class));
		for (User u : res) {
			System.out.println(u.getId());
		}
		return res;
	}
	
	
	

}
