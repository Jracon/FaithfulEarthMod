package com.jracon.earth.datagen;

import com.jracon.earth.Earth;
import com.jracon.earth.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class EarthFluidTags extends FluidTagsProvider {

    public EarthFluidTags(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, Earth.MOD_ID, helper);
    }

    @Override
    protected void addTags() {
        tag(Registration.MUD)
                .add(Registration.MUD_FLUID_BLOCK.get().getFluid());
    }

    @Override
    public String getName() {
        return "Minecraft Earth Tags";
    }
}
