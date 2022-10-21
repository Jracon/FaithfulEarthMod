package com.jracon.earth.datagen;

import com.jracon.earth.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class EarthRecipes extends RecipeProvider {
    public EarthRecipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(Registration.MUD_BUCKET.get())
                .requires(Blocks.DIRT, 8)
                .requires(Items.WATER_BUCKET, 1)
                .unlockedBy("has_item", has(Items.WATER_BUCKET))
                .group("earth")
                .save(consumer);
    }
}