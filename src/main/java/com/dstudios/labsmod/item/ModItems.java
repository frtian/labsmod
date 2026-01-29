package com.dstudios.labsmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

import java.util.List;
import java.util.function.Function;

import com.dstudios.labsmod.LabsMod;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItems {
  // Item personalizado com comportamento interativo
  public static final Item RUBBER_DUCK = registerItem("rubber_duck", RubberDuckItem::new, 
    new Item.Settings()
      .component(DataComponentTypes.LORE, new LoreComponent(List.of(
        Text.translatable("item.labsmod.rubber_duck.tooltip_1"),
        Text.translatable("item.labsmod.rubber_duck.tooltip_2")
      )))
  );
  
  // Exemplo de item genérico (descomente para adicionar mais itens simples)
  // public static final Item SIMPLE_ITEM = registerItem("simple_item", Item::new, new Item.Settings());

  private static Item registerItem(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
    // Cria o Identifier único baseado no MOD_ID e no nome fornecido
    Identifier id = Identifier.of(LabsMod.MOD_ID, name);

    // Define a chave de registro antes de instanciar o item
    settings.registryKey(RegistryKey.of(RegistryKeys.ITEM, id));

    // Cria o item usando a factory (Item::new) e o registra
    return Registry.register(Registries.ITEM, id, factory.apply(settings));
  }

  public static void registerModItems() {
    LabsMod.LOGGER.info("Registering Mod Itens for " + LabsMod.MOD_ID);

    ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
      entries.add(RUBBER_DUCK);
    });
  }
}
