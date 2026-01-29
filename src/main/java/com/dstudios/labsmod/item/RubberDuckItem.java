package com.dstudios.labsmod.item;

import java.util.List;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class RubberDuckItem extends Item {
  public RubberDuckItem(Settings settings) {
    super(settings);
  }

  @Override
  public ActionResult use(World world, PlayerEntity user, Hand hand) {
    ItemStack itemStack = user.getStackInHand(hand);

    // Toca o som de "quack" (usando som de galinha como exemplo)
    world.playSound(
        null,
        user.getX(),
        user.getY(),
        user.getZ(),
        SoundEvents.ENTITY_CHICKEN_AMBIENT,
        SoundCategory.PLAYERS,
        1.0f,
        1.5f // pitch mais alto para soar como um pato de borracha
    );

    // Adiciona uma pequena animação de "uso" do item
    user.incrementStat(Stats.USED.getOrCreateStat(this));
    user.getItemCooldownManager().set(itemStack, 20); // 1 segundo de cooldown

    return ActionResult.SUCCESS;
  }

}
