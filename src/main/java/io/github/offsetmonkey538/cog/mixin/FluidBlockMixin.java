package io.github.offsetmonkey538.cog.mixin;

import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

@Mixin(FluidBlock.class)
public class FluidBlockMixin {

	private static final Tag<Block> ORES = TagFactory.BLOCK.create(new Identifier("c", "ores"));

	private static final Random rng = new Random();

	@Redirect(method = "receiveNeighborFluids", at = @At(value = "FIELD", target = "Lnet/minecraft/block/Blocks;COBBLESTONE:Lnet/minecraft/block/Block;", opcode = Opcodes.GETSTATIC))
	private Block cog$receiveNeighborFluids() {
		if (!ORES.values().isEmpty() && rng.nextInt(3) == 0)
			return ORES.getRandom(rng);
		return Blocks.COBBLESTONE;
	}
}
