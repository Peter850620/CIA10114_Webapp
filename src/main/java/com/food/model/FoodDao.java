package com.food.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import static com.jdbc.common.Common.*;

public class FoodDao implements FoodDAO_interface {

	@Override
	public void insert(FoodVO foodvo) {
		String sql = """
				INSERT INTO food (food_name, food_price, food_status)
				values(?, ?, ?);
				""";

		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ps.setString(1, foodvo.getFood_name());
				ps.setString(2, foodvo.getFood_details());
				ps.setInt(3, foodvo.getFood_price());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(FoodVO foodvo) {
		String sql = """
				update book
				set food_name = ?, food_details = ?, food_price = ?, food_status = ?
				where food_id = ?;
				""";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ps.setString(1, foodvo.getFood_name());
				ps.setString(2, foodvo.getFood_details());
				ps.setInt(3, foodvo.getFood_price());
				ps.setString(4, foodvo.getFood_status());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Integer foodvo) {
		String sql = "";

	}

	@Override
	public FoodVO findByPrimaryKey(Integer food_id) {
		String sql = "";
		return null;
	}

	@Override
	public List<FoodVO> getAll() {
		String sql = "";
		return null;
	}

}
