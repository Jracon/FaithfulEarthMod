package com.jracon.earth.datagen;

import com.jracon.earth.Earth;
import com.jracon.earth.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

import static com.jracon.earth.setup.ModSetup.TAB_NAME;

public class EarthLanguageProvider extends LanguageProvider {

    public EarthLanguageProvider(DataGenerator gen, String locale) {
        super(gen, Earth.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup." + TAB_NAME, "Minecraft Earth");

        add(Registration.BUTTERCUP.get(), "Buttercup");
        add(Registration.BUTTERCUP_POT.get(), "Potted Buttercup");

        add(Registration.MUD_BUCKET.get(), "Bucket of Mud");

        // Chickens
        add(Registration.CLUCKSHROOM.get(), "Cluckshroom");
        //
    }
}