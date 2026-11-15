package net.risobimods.risobimobs.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.risobimods.risobimobs.RisobiMobs;
import net.risobimods.risobimobs.entity.custom.ScaryEntity;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, RisobiMobs.MOD_ID);

    public static final Supplier<EntityType<ScaryEntity>> SCARY =
            ENTITY_TYPES.register("scary", () -> EntityType.Builder.of(ScaryEntity::new, MobCategory.MONSTER)
                    .sized(1, 4).build("scary"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
