package com.risobimods.tester.sound;

import com.risobimods.tester.Tester;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, Tester.MOD_ID);


    public static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Tester.MOD_ID)
    }


    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);





        public static final Supplier<SoundEvent> EXCHANGE = registerSoundEvent("exchange");
        public static final ResourceKey<JukeboxSong> EXCHANGE = createSong("exchange");

        private static ResourceKey<JukeboxSong> createSong (String name){
            return ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(Tester.MOD_ID, name));
        }
    }
}