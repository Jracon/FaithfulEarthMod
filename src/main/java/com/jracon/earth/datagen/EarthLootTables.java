package com.jracon.earth.datagen;

import com.jracon.earth.setup.Registration;
import net.minecraft.data.DataGenerator;

public class EarthLootTables extends BaseLootTableProvider {

    public EarthLootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables() {
        lootTables.put(Registration.BUTTERCUP.get(), createSimpleTable("buttercup", Registration.BUTTERCUP.get()));
    }
}