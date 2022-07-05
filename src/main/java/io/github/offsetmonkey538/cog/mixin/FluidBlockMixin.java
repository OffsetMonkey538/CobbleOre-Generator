package io.github.offsetmonkey538.cog.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Mixin(FluidBlock.class)
public class FluidBlockMixin {

	private static final TagKey<Block> ORES = TagKey.of(Registry.BLOCK_KEY, new Identifier("c", "ores"));

	private static final Random rng = new Random();

	@Redirect(method = "receiveNeighborFluids", at = @At(value = "FIELD", target = "Lnet/minecraft/block/Blocks;COBBLESTONE:Lnet/minecraft/block/Block;", opcode = Opcodes.GETSTATIC))
	private Block cog$receiveNeighborFluids() {
		if (rng.nextInt(3) > 0)
			return Blocks.COBBLESTONE;
		return Registry.BLOCK.getEntryList(ORES).flatMap(t -> {

			List<RegistryEntry<Block>> list = t.stream().toList();

			if (list.isEmpty())
				return Optional.empty();
			return Optional.of(list.get(rng.nextInt(list.size())));

		}).orElse(RegistryEntry.of(Blocks.CAKE)).value();
	}
}
