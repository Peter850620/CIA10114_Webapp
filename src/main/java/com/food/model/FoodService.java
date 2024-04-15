package com.food.model;

import java.util.List;

public class FoodService {

	private FoodDAO_interface dao;

	public FoodService() {
		dao = new FoodJDBCDAO();
	}

	public FoodVO addFood(String food_name, String food_details, Integer food_price, byte[] food_pic,
			String food_status) {

		FoodVO foodVO = new FoodVO();

		foodVO.setFood_name(food_name);
		foodVO.setFood_details(food_details);
		foodVO.setFood_price(food_price);
		foodVO.setFood_pic(food_pic);
		foodVO.setFood_status(food_status);
		dao.insert(foodVO);

		return foodVO;
	}

	public FoodVO updateFood(Integer food_id, String food_name, String food_details, Integer food_price, byte[] food_pic,
			String food_status) {

		FoodVO foodVO = new FoodVO();

		foodVO.setFood_name(food_name);
		foodVO.setFood_details(food_details);
		foodVO.setFood_price(food_price);
		foodVO.setFood_pic(food_pic);
		foodVO.setFood_status(food_status);
		foodVO.setFood_id(food_id);
		dao.update(foodVO);

		return foodVO;
	}

	public void deleteFood(Integer food_id) {
		dao.delete(food_id);
	};

	public FoodVO getOneFood(Integer food_id) {
		return dao.findByPrimaryKey(food_id);
	};

	public List<FoodVO> getAll() {
		return dao.getAll();
	};

}
