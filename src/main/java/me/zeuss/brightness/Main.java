package me.zeuss.brightness;

import me.zeuss.brightness.events.ToggleEvent;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.extensions.IForgeKeybinding;
import net.minecraftforge.client.settings.IKeyConflictContext;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("brightness")
public class Main {

    public static Main instance;
    public static KeyBinding key;

    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "brightness";
    public static final String NAME = "Brightness";
    public static final String VERSION = "1.0";

    public Main() {
        if (!FMLEnvironment.dist.equals(Dist.CLIENT)) return;
        instance = this;
        IEventBus event = FMLJavaModLoadingContext.get().getModEventBus();
        event.addListener(this::initClient);
        event.addListener(this::loadEvent);
    }

    private void initClient(FMLClientSetupEvent e) {
        key = new KeyBinding("key.brightness.toggle", KeyConflictContext.IN_GAME, KeyModifier.NONE, InputMappings.Type.KEYSYM, 71, "Brightness");
        ClientRegistry.registerKeyBinding(key.getKeyBinding());
    }

    private void loadEvent(FMLLoadCompleteEvent e) {
        MinecraftForge.EVENT_BUS.register(new ToggleEvent());
    }

}
