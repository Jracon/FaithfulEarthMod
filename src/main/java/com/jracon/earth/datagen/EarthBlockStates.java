package com.jracon.earth.datagen;

import com.jracon.earth.Earth;
import com.jracon.earth.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class EarthBlockStates extends BlockStateProvider {

    public EarthBlockStates(DataGenerator gen, ExistingFileHelper helper) {
        super(gen, Earth.MOD_ID, helper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(Registration.BUTTERCUP.get(), models().cross("buttercup", modLoc("block/buttercup")).renderType("cutout"));
    }
}