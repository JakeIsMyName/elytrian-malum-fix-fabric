package jakeismyname.ermf.mixin;

import com.sammy.malum.core.ElytraLayerExtensions;
import io.github.apace100.apoli.component.PowerHolderComponent;
import io.github.apace100.apoli.power.ElytraFlightPower;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ElytraLayerExtensions.class)
public interface ElytraLayerExtensionsMixin {
    @Inject(method = "shouldRender", at = @At("HEAD"), cancellable = true)
    private <T> void ermf$renderPowerElytra(ItemStack stack, T entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof Entity entity1) {
            if (PowerHolderComponent.getPowers(entity1, ElytraFlightPower.class).stream().anyMatch(ElytraFlightPower::shouldRenderElytra)) {
                cir.setReturnValue(true);
            }
        }
    }
}