package me.zeuss.brightness.events;

import me.zeuss.brightness.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.GameSettings;
import net.minecraft.client.audio.Sound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ToggleEvent {

    private static Minecraft mc;
    private static double init = 1D;
    private static double max = 15D;

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    void onPress(InputEvent.KeyInputEvent e) {
        if (e.getAction() != 1) return;
        if (mc == null)
            mc = Minecraft.getInstance();
        if (mc.currentScreen instanceof net.minecraft.client.gui.screen.ChatScreen) return;
        if (e.getKey() == Main.key.getKey().getKeyCode()) {
            GameSettings settings = mc.gameSettings;
            if (settings.gamma >= max) {
                settings.gamma = init;
                mc.player.sendStatusMessage(new TranslationTextComponent("message.brightness.disabled"), true);
            } else {
                settings.gamma = max;
                mc.player.sendStatusMessage(new TranslationTextComponent("message.brightness.enabled"), true);
            }
        }
    }

}
