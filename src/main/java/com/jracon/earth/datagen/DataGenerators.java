package com.jracon.earth.datagen;

import com.jracon.earth.Earth;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Earth.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        generator.addProvider(event.includeClient(), new EarthBlockStates(generator, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new EarthItemModels(generator, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new EarthLanguageProvider(generator, "en_us"));
        generator.addProvider(event.includeServer(), new EarthLootTables(generator));
        generator.addProvider(event.includeClient(), new EarthFluidTags(generator, event.getExistingFileHelper()));
        generator.addProvider(event.includeServer(), new EarthRecipes(generator));
    }
}