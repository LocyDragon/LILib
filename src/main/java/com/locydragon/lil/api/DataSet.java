package com.locydragon.lil.api;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;


import java.util.*;

/**
 * @author LocyDragon
 */
public class DataSet {
	private HashMap<String, String[]> dataMap = new HashMap<>();

	public DataSet() {
	}

	private DataSet(HashMap<String, String[]> entry) {
		this.dataMap = entry;
	}

	/**
	 * 往信息Set里添加一行记录
	 *
	 * @param flagData 记录的Flag(该插件用作技能名)
	 * @param info     记录的Flag数据(该插件用于对技能的描述,如技能的等级,攻击力药水效果ID等等);
	 * @return 该类实例对象
	 */
	public DataSet addSet(String flagData, String... info) {
		dataMap.put(flagData, info);
		return this;
	}

	/**
	 * 清除所有以技能名为Flag的记录
	 *
	 * @param flagData 记录的Flag(技能名)
	 * @return 该类实例对象
	 */
	public DataSet removeFlagData(String flagData) {
		dataMap.remove(flagData);
		return this;
	}

	/**
	 * 序列化
	 * 我们采用FastJson对DataSet类进行序列化
	 *
	 * @return JSON
	 */
	@Override
	public String toString() {
		return JSON.toJSONString(this.dataMap);
	}

	private boolean arrayContains(String[] array, String contains) {
		for (String obj : array) {
			if (obj.contains(contains)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 反序列化
	 *
	 * @param json JSON
	 * @return 实例对象
	 */
	public static DataSet deSerialize(String json) {
		if (StringUtils.isBlank(json)) {
			return new DataSet(new HashMap());
		}
		HashMap<String, String[]> map = JSON.parseObject(json, HashMap.class);
		return new DataSet(map);
	}

	/**
	 * 以Map的形式反馈该类对象
	 *
	 * @return 该对象行程的Map
	 */
	public HashMap<String, String[]> entryMap() {
		return this.dataMap;
	}

	/**
	 * 通过技能名获取该类对象中所包含的所有对这个技能的描述
	 *
	 * @param flagName 技能名
	 * @return 对该技能的描述
	 */
	public List<String[]> getValuesByFlag(String flagName) {
		List<String[]> values = new ArrayList<>();
		for (Map.Entry<String, String[]> entry : this.dataMap.entrySet()) {
			if (entry.getKey().equals(flagName)) {
				values.add(entry.getValue());
			}
		}
		return values;
	}

	/**
	 * 判断该类是否存储了一个技能
	 *
	 * @param flagName 技能名
	 * @return 布尔值
	 */
	public boolean hasFlag(String flagName) {
		return this.dataMap.keySet().contains(flagName);
	}
}
