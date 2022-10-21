package com.jracon.earth.setup;

import com.jracon.earth.Earth;
import com.jracon.earth.entities.chickens.CluckshroomEntity;
import com.jracon.earth.fluids.BaseFluidType;
import com.jracon.earth.fluids.MudFluid;
import com.jracon.earth.world.biomemods.EarthVegetalBiomeModifier;
import com.jracon.earth.world.feature.EarthConfiguredFeatures;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

import static com.jracon.earth.Earth.MOD_ID;

public class Registration {

    // Deferred Registers
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, MOD_ID);
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, Earth.MOD_ID);
    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, Earth.MOD_ID);
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Earth.MOD_ID);
    //

    // Block Registry Objects
    public static final RegistryObject<FlowerBlock> BUTTERCUP = registerBlock("buttercup", () ->
            new FlowerBlock(MobEffects.LUCK, 0, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)));
    public static final RegistryObject<FlowerPotBlock> BUTTERCUP_POT = BLOCKS.register("buttercup_pot", () ->
            new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, Registration.BUTTERCUP, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));

    public static final RegistryObject<LiquidBlock> MUD_FLUID_BLOCK = BLOCKS.register("mud_fluid_block",
            () -> new LiquidBlock(Registration.SOURCE_MUD_FLUID, BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100F).noLootTable()));
    //

    // Resource Locations
    public static final ResourceLocation MUD_STILL_RL = new ResourceLocation(Earth.MOD_ID, "block/mud_still");
    public static final ResourceLocation MUD_FLOWING_RL = new ResourceLocation(Earth.MOD_ID, "block/mud_flow");
    public static final ResourceLocation MUD_OVERLAY_RL = new ResourceLocation(MOD_ID, "misc/mud_fluid_overlay");
    //

    // Fluid Registry Objects
    public static final RegistryObject<FluidType> MUD_FLUID_TYPE = FLUID_TYPES.register("mud",
            () -> new BaseFluidType(MUD_STILL_RL, MUD_FLOWING_RL, MUD_OVERLAY_RL, FluidType.Properties.create()
                    .canExtinguish(true).motionScale(0.0075F).viscosity(2000).density(2000)
                    .fallDistanceModifier(0F).supportsBoating(true)));
    public static final RegistryObject<FlowingFluid> FLOWING_MUD_FLUID = FLUIDS.register("flowing_mud_fluid",
            MudFluid.Flowing::new);
    public static final RegistryObject<FlowingFluid> SOURCE_MUD_FLUID = FLUIDS.register("source_mud_fluid",
            MudFluid.Source::new);
    //

    // Item Registry Objects
    public static final RegistryObject<Item> MUD_BUCKET = ITEMS.register("mud_bucket",
            () -> new BucketItem(Registration.SOURCE_MUD_FLUID, new Item.Properties().tab(ModSetup.ITEM_GROUP).rarity(Rarity.RARE).stacksTo(1)));
    //

    // Tags
    public static final TagKey<Fluid> MUD = TagKey.create(Registry.FLUID_REGISTRY, new ResourceLocation(MOD_ID, "mud"));
    //

    // Biome Modifier Registry Objects
    public static RegistryObject<Codec<EarthVegetalBiomeModifier>> VEGETAL_MODIFIER = BIOME_MODIFIERS.register(
            "vegetal", () ->
                    RecordCodecBuilder.create(builder -> builder.group(
                            Biome.LIST_CODEC.fieldOf("biomes").forGetter(EarthVegetalBiomeModifier::biomes),
                            PlacedFeature.CODEC.fieldOf("feature").forGetter(EarthVegetalBiomeModifier::feature)
                    ).apply(builder, EarthVegetalBiomeModifier::new)));
    //

    // Placed Feature Registry Objects
    public static final RegistryObject<PlacedFeature> BUTTERCUP_PLACED = PLACED_FEATURES.register("buttercup_placed",
            () -> new PlacedFeature((Holder<ConfiguredFeature<?, ?>>)(Holder<? extends ConfiguredFeature<?, ?>>)
                    EarthConfiguredFeatures.BUTTERCUP, List.of(RarityFilter.onAverageOnceEvery(16),
                    InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));
    //

    // Entities
        // Chicken Registry Objects
        public static final RegistryObject<EntityType<CluckshroomEntity>> CLUCKSHROOM = ENTITIES.register("cluckshroom", () -> EntityType.Builder.of(CluckshroomEntity::new, MobCategory.CREATURE)
                .sized(0.4f, 0.7f)
                .setShouldReceiveVelocityUpdates(false)
                .clientTrackingRange(10)
                .build("cluckshroom"));
        //
    //

    public static void registerAdditionalEntityInformation() {
        registerEntitySpawnRestrictions();
    }

    private static void registerEntitySpawnRestrictions() {

        // Chickens
        SpawnPlacements.register(CLUCKSHROOM.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        //
    }

    public static void init() {

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        ITEMS.register(bus);
        ENTITIES.register(bus);
        FLUIDS.register(bus);
        FLUID_TYPES.register(bus);
        BIOME_MODIFIERS.register(bus);
        PLACED_FEATURES.register(bus);
    }

    // Convenience Methods
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        Registration.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    }
    //
}