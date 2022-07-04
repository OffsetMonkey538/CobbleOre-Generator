package io.github.offsetmonkey538.cog.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.block.FluidBlock;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FluidBlock.class)
public class FluidBlockMixin {

	@Redirect(method = "receiveNeighborFluids", at = @At(value = "FIELD", target = "Lnet/minecraft/block/Blocks;COBBLESTONE:Lnet/minecraft/block/Block;", opcode = Opcodes.GETSTATIC))
	private Block cog$receiveNeighborFluids() {
		//TODO: 50% chance for cobblestone, 50% chance for any ore
		return Blocks.CAKE;
	}
}
