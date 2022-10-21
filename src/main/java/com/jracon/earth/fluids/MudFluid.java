package com.jracon.earth.fluids;

import com.jracon.earth.setup.Registration;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.shorts.Short2BooleanMap;
import it.unimi.dsi.fastutil.shorts.Short2ObjectMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.WaterFluid;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;

public abstract class MudFluid extends WaterFluid {

    protected MudFluid() {
        super();
    }

    public @NotNull Fluid getFlowing() {
        return Registration.FLOWING_MUD_FLUID.get();
    }

    public @NotNull Fluid getSource() {
        return Registration.SOURCE_MUD_FLUID.get();
    }

    public @NotNull FluidType getFluidType() {
        return Registration.MUD_FLUID_TYPE.get();
    }

    public @NotNull Item getBucket() {
        return Registration.MUD_BUCKET.get();
    }

    public int getTickDelay(@NotNull LevelReader level) {
        return 20;
    }

    protected boolean isRandomlyTicking() {
        return true;
    }

    public boolean isSame(@NotNull Fluid fluid) {
        return fluid == Registration.SOURCE_MUD_FLUID.get() || fluid == Registration.FLOWING_MUD_FLUID.get();
    }

    protected float getExplosionResistance() {
        return 50.0F;
    }

    protected boolean canConvertToSource() {
        return false;
    }

    public @NotNull BlockState createLegacyBlock(@NotNull FluidState fluidState) {
        return Registration.MUD_FLUID_BLOCK.get().defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(fluidState));
    }

    public int getDropOff(LevelReader levelReader) {
        return 4;
    }

    protected int getSlopeDistance(@NotNull LevelReader levelReader, @NotNull BlockPos blockPos, int i, @NotNull Direction direction, @NotNull BlockState blockState, @NotNull BlockPos blockPos1, @NotNull Short2ObjectMap<Pair<BlockState, FluidState>> pairShort2ObjectMap, @NotNull Short2BooleanMap short2BooleanMap) {
        return 1;
    }

    public boolean canBeReplacedWith(@NotNull FluidState fluidState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos, @NotNull Fluid fluid, @NotNull Direction direction) {
        return !this.isSame(fluid);
    }

    public static class Flowing extends MudFluid {
        public Flowing() {
            super();
            registerDefaultState(getStateDefinition().any().setValue(LEVEL, 7));
        }

        protected void createFluidStateDefinition(StateDefinition.@NotNull Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        public int getAmount(FluidState state) {
            return state.getValue(LEVEL);
        }

        public boolean isSource(@NotNull FluidState state) {
            return false;
        }
    }

    public static class Source extends MudFluid {
        public Source() {
            super();
        }

        public int getAmount(@NotNull FluidState state) {
            return 8;
        }

        public boolean isSource(@NotNull FluidState state) {
            return true;
        }
    }
}