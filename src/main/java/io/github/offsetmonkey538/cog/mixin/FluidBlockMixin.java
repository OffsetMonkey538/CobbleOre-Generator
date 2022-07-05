package io.github.offsetmonkey538.cog.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.Registry;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FluidBlock.class)
public class FluidBlockMixin {

	private static final TagKey<Block> ORES = TagKey.of(Registry.BLOCK_KEY, new Identifier("c", "ores"));

	private static final Random rng = Random.create();

	@Redirect(method = "receiveNeighborFluids", at = @At(value = "FIELD", target = "Lnet/minecraft/block/Blocks;COBBLESTONE:Lnet/minecraft/block/Block;", opcode = Opcodes.GETSTATIC))
	private Block cog$receiveNeighborFluids() {
		//TODO: 50% chance for cobblestone, 50% chance for any ore
		return Blocks.CAKE;
	}
}
