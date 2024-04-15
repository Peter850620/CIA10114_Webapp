package com.food.model;

import static com.jdbc.common.Common.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodJDBCDAO implements FoodDAO_interface {

	private static final String INSERT_STMT = "INSERT INTO food (food_name,food_details,food_pic,food_price,food_status) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM food order by food_id";
	private static final String GET_ONE_STMT = "SELECT * FROM food where food_id = ?";
	private static final String DELETE = "DELETE FROM food where food_id = ?";
	private static final String UPDATE = "UPDATE food set food_name=?, food_details=?, food_pic=?, food_price=?, food_status=? where food_id = ?";

	@Override
	public void insert(FoodVO foodvo) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = conn.prepareStatement(INSERT_STMT);
			ps.setString(1, foodvo.getFood_name());
			ps.setString(2, foodvo.getFood_details());
			ps.setBytes(3, foodvo.getFood_pic());
			ps.setInt(4, foodvo.getFood_price());
			ps.setString(5, foodvo.getFood_status());

			ps.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(FoodVO foodvo) {
		Connection conn = null;
		PreparedStatement ps = null;
		FoodVO foodVO = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = conn.prepareStatement(UPDATE);
			ps.setString(1, foodvo.getFood_name());
			ps.setString(2, foodvo.getFood_details());
			ps.setBytes(3, foodvo.getFood_pic());
			ps.setInt(4, foodvo.getFood_price());
			ps.setString(5, foodvo.getFood_status());
			ps.setInt(6, foodvo.getFood_id());

			ps.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(Integer food_id) {
		Connection conn = null;
		PreparedStatement ps = null;
		FoodVO foodVO = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = conn.prepareStatement(DELETE);
			ps.setInt(1, food_id);

			ps.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public FoodVO findByPrimaryKey(Integer food_id) {

		Connection conn = null;
		PreparedStatement ps = null;
		FoodVO foodVO = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = conn.prepareStatement(GET_ONE_STMT);
			ps.setInt(1, food_id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				foodVO = new FoodVO();
				foodVO.setFood_id(rs.getInt("food_id"));
				foodVO.setFood_name(rs.getString("food_name"));
				foodVO.setFood_details(rs.getString("food_details"));
				foodVO.setFood_pic(rs.getBytes("food_pic"));
				foodVO.setFood_price(rs.getInt("food_price"));
				foodVO.setFood_status(rs.getString("food_status"));
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return foodVO;
	}

	@Override
	public List<FoodVO> getAll() {
		List<FoodVO> list = new ArrayList<FoodVO>();

		Connection conn = null;
		PreparedStatement ps = null;
		FoodVO foodVO = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = conn.prepareStatement(GET_ALL_STMT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				foodVO = new FoodVO();
				foodVO.setFood_id(rs.getInt("food_id"));
				foodVO.setFood_name(rs.getString("food_name"));
				foodVO.setFood_details(rs.getString("food_details"));
				foodVO.setFood_pic(rs.getBytes("food_pic"));
				foodVO.setFood_price(rs.getInt("food_price"));
				foodVO.setFood_status(rs.getString("food_status"));
				list.add(foodVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

//	測試SQL指令
//	public static void main(String[] args) {
//
//		FoodJDBCDAO dao = new FoodJDBCDAO();
//
//		// 新增
//		FoodVO foodVO1 = new FoodVO();
//		foodVO1.setFood_name("爆米花");
//		foodVO1.setFood_details("香香脆脆的焦糖爆米花");
//		foodVO1.setFood_price(120);
//		dao.insert(foodVO1);
//
//		// 修改
//		FoodVO foodVO2 = new FoodVO();
//		foodVO2.setFood_name("海鹽爆米花");
//		foodVO2.setFood_details("鹹鹹的爆米花");
//		foodVO2.setFood_price(200);
//		foodVO2.setFood_status("上架");
//		foodVO2.setFood_id(107);
//		dao.update(foodVO2);
//
//		// 刪除
//		dao.delete(108);
//
//		// 查詢
//		FoodVO foodVO3 = dao.findByPrimaryKey(105);
//		System.out.print(foodVO3.getFood_id() + ",");
//		System.out.print(foodVO3.getFood_name() + ",");
//		System.out.print(foodVO3.getFood_details() + ",");
//		System.out.print(foodVO3.getFood_price() + ",");
//		System.out.print(foodVO3.getFood_pic() + ",");
//		System.out.println(foodVO3.getFood_status());
//		System.out.println("---------------------");
//
//		// 查詢
//		List<FoodVO> list = dao.getAll();
//		for (FoodVO aFoodVO : list) {
//			System.out.print(aFoodVO.getFood_id() + ",");
//			System.out.print(aFoodVO.getFood_name() + ",");
//			System.out.print(aFoodVO.getFood_details() + ",");
//			System.out.print(aFoodVO.getFood_price() + ",");
//			System.out.print(aFoodVO.getFood_pic() + ",");
//			System.out.print(aFoodVO.getFood_status());
//			System.out.println();
//		}
//	}

}
