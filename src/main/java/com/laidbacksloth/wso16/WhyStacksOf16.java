package com.laidbacksloth.wso16;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class WhyStacksOf16 implements ModInitializer {
	@Override
	public void onInitialize() {
		for (Item item : Registries.ITEM) {
			if (item.getMaxCount() == 16) {
				try {
					Field field;
					try {
						field = Item.class.getDeclaredField("maxCount");
					} catch (NoSuchFieldException e) {
						field = Item.class.getDeclaredField("field_8013");
					}
					field.setAccessible(true);
					field.set(item, 64);
				} catch (Exception e) {
					LoggerFactory.getLogger("wso16").error("Could not change the max stack-size of " + Registries.ITEM.getId(item) + ", exception: " + e);
				}
			}
		}
	}
}