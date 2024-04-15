package com.food.model;

import java.util.List;

public  interface FoodDAO_interface {
	public void insert(FoodVO foodvo);
	public void update(FoodVO foodvo);
	public void delete(Integer food_id);
    public FoodVO findByPrimaryKey(Integer food_id);
    public List<FoodVO> getAll();	
}
