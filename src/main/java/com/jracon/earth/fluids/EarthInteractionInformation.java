package com.jracon.earth.fluids;

import com.jracon.earth.setup.Registration;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fluids.FluidInteractionRegistry;

public class EarthInteractionInformation {
    public static void init() {
        FluidInteractionRegistry.addInteraction(Registration.MUD_FLUID_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
                ForgeMod.WATER_TYPE.get(),
                fluidState -> Blocks.AIR.defaultBlockState()
        ));
        FluidInteractionRegistry.addInteraction(Registration.MUD_FLUID_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
                ForgeMod.LAVA_TYPE.get(),
                fluidState -> Blocks.DIRT.defaultBlockState()
        ));
    }
}