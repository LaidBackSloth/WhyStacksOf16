package com.laidbacksloth.wso16;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;

@Mod("wso16")
public class WhyStacksOf16 {
    public WhyStacksOf16() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(this::commonSetup);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> ForgeRegistries.ITEMS.getValues().forEach((item) -> {
            if (new ItemStack(item).getMaxStackSize() == 16) {
                try {
                    ObfuscationReflectionHelper.setPrivateValue(Item.class, item, 64, "f_41370_");
                } catch (Exception e) {
                    LogManager.getLogger().error("Could not change the max stack-size of " + item.getRegistryName() + ", exception: " + e);
                }
            }
        }));
    }
}