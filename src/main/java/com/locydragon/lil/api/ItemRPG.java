package com.locydragon.lil.api;


import com.locydragon.lil.Main;
import com.locydragon.locyitem.api.LocyItem;
import com.locydragon.locyitem.util.Control;
import com.locydragon.locyitem.util.LItem;
import org.bukkit.inventory.ItemStack;

public class ItemRPG {
	private LItem item;
	private DataSet data;

	private ItemRPG(LItem item) {
		if (item == null) {
			throw new NullPointerException("Cannot find this item.");
		}
		this.item = item;
		this.data = DataSet.deSerialize(Main.instance.getConfig().getString(item.getId(), ""));
	}

	/**
	 * 通过物品的显示名称获取ItemRPG对象
	 * 可能返回null
	 *
	 * @param disPlayerName 物品的显示名称
	 * @return 实例对象
	 */
	public static ItemRPG fromDisplayName(String disPlayerName) {
		if (Control.getByItemName(disPlayerName) == null) {
			return null;
		}
		return new ItemRPG(Control.getByItemName(disPlayerName));
	}

	/**
	 * 通过物品的ID来获取ItemRPG实例对象
	 * 可能返回null
	 *
	 * @param id 物品的ID
	 * @return 实例对象
	 */
	public static ItemRPG fromItemID(String id) {
		if (Control.search(id) == null) {
			return null;
		}
		return new ItemRPG(Control.search(id));
	}

	/**
	 * 获取该物品的ItemStack
	 *
	 * @return 该物品的ItemStack
	 */
	public ItemStack getItemStack() {
		return this.item.getItemStack();
	}

	/**
	 * 获取该物品的物品ID
	 *
	 * @return 物品ID
	 */
	public String getID() {
		return this.item.getId();
	}

	/**
	 * 获取该物品的LocyItem RPG信息
	 *
	 * @return LocyItem中类LocyItem的实例对象
	 */
	public LocyItem getItem() {
		return this.item;
	}

	/**
	 * 设置并保存该物品的数据
	 * 所有RPG技能的实现都是通过DataSet类的key值(技能名字),获取value值(技能的描述,如技能的等级等)
	 * 之后再在事件等地方进行判断
	 *
	 * @param data 数据
	 * @return 该类实例对象
	 */
	public ItemRPG setData(DataSet data) {
		this.data = data;
		updateAndSaveData();
		return this;
	}

	/**
	 * 刷新并保存信息
	 *
	 * @return 该类实例对象
	 */
	public ItemRPG updateAndSaveData() {
		Main.instance.getConfig().set(item.getId(), data.toString());
		Main.reloadSettings();
		this.data = DataSet.deSerialize(Main.instance.getConfig().getString(item.getId(), ""));
		return this;
	}

	/**
	 * 获取数据
	 *
	 * @return 数据对象
	 */
	public DataSet getData() {
		return this.data;
	}
}
