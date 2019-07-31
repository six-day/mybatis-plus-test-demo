package com.example.demo.utils;

import com.example.demo.enums.CodeEnum;
import com.example.demo.vo.OptionVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author TangLingYun
 * @describe 枚举工具类
 */
public class EnumUtil {

	/** 通过code获取枚举 */
	public static <T extends CodeEnum<?>> T getEnumByCode(Serializable code, Class<T> enumClass) {
		for (T each : enumClass.getEnumConstants()) {
			if (code.equals(each.getCode())) {
				return each;
			}
		}
		return null;
	}
	
	/** 枚举转下拉列表 */
	public static <T extends CodeEnum<?>> List<OptionVO> toOptions(Class<T> enumClass) {
		List<OptionVO> list = new ArrayList<OptionVO>();
		for (T each : enumClass.getEnumConstants()) {
			OptionVO optionVO = new OptionVO();
			optionVO.setValue(each.getCode());
			optionVO.setMessage(each.getMessage());
			list.add(optionVO);
		}
		return list;
	}
	
}