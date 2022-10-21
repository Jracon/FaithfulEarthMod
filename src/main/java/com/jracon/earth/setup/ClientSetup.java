package com.jracon.earth.setup;

import com.jracon.earth.entities.chickens.CluckshroomModel;
import com.jracon.earth.entities.chickens.CluckshroomRenderer;
import com.jracon.earth.fluids.EarthInteractionInformation;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static com.jracon.earth.Earth.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void init(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(Registration.SOURCE_MUD_FLUID.get(), RenderType.solid());
        ItemBlockRenderTypes.setRenderLayer(Registration.FLOWING_MUD_FLUID.get(), RenderType.solid());
        EarthInteractionInformation.init();
    }

    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
    // Chickens
        event.registerLayerDefinition(CluckshroomModel.LAYER_LOCATION, CluckshroomModel::createBodyLayer);
    //
    }

    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
        // Chickens
        event.registerEntityRenderer(Registration.CLUCKSHROOM.get(), CluckshroomRenderer::new);
        //
    }
}
