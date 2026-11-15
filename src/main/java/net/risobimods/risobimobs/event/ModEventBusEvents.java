package net.risobimods.risobimobs.event;


import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.risobimods.risobimobs.RisobiMobs;
import net.risobimods.risobimobs.entity.ModEntities;
import net.risobimods.risobimobs.entity.client.ScaryModel;
import net.risobimods.risobimobs.entity.custom.ScaryEntity;

@EventBusSubscriber(modid = RisobiMobs.MOD_ID)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
    event.registerLayerDefinition(ScaryModel.LAYER_LOCATION, ScaryModel::createBodyLayer);
    }


    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.SCARY.get(), ScaryEntity.createAttributes().build());
    }
}

