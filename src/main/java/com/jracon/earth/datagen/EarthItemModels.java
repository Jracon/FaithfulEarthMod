package com.jracon.earth.datagen;

import com.jracon.earth.Earth;
import com.jracon.earth.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class EarthItemModels extends ItemModelProvider {

    public EarthItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Earth.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        singleTexture(Registration.BUTTERCUP.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("block/buttercup"));
    }
}
