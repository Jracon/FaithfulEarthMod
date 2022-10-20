package com.jracon.earth;

import com.jracon.earth.setup.ClientSetup;
import com.jracon.earth.setup.ModSetup;
import com.jracon.earth.setup.Registration;
import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Earth.MOD_ID)
public class Earth {

    public static final String MOD_ID = "earth";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Earth() {

        ModSetup.setup();
        Registration.init();

        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
        modbus.addListener(ModSetup::init);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> modbus.addListener(ClientSetup::init));
    }
}
