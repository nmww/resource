package com.example.model;

/**
 * @author captain_miao
 */
public class SeatMo extends BaseMo {

	/**
	 * 座位名称：几排几�?
	 */
	public String seatName;
	/**
	 * 行名称：A�?
	 */
	public String rowName;
	/**
	 * 行坐�?
	 */
	public int row;
	/**
	 * 列坐�?
	 */
	public int column;

	/**
	 * 座位状�?:1：可售，0：已售，-1：删�?非法)
	 */
	public int status;

}
