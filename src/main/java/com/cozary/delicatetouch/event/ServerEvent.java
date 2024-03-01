/*
 *
 *  * Copyright (c) 2024 Cozary
 *  *
 *  * This file is part of Delicate Touch, a mod made for Minecraft.
 *  *
 *  * Delicate Touch is free software: you can redistribute it and/or modify it
 *  * under the terms of the GNU General Public License as published
 *  * by the Free Software Foundation, either version 3 of the License, or
 *  * (at your option) any later version.
 *  *
 *  * Delicate Touch is distributed in the hope that it will be useful, but
 *  * WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * License along with Delicate Touch.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package com.cozary.delicatetouch.event;

import com.cozary.delicatetouch.ConfigurationHandler;
import com.cozary.delicatetouch.DelicateTouch;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = DelicateTouch.MOD_ID)
public class ServerEvent {

    public static Set<UUID> playersWithButtonDown = new HashSet<>();
    private static boolean alreadyBreaking = false;

    @SubscribeEvent
    public static void veinMine(BlockEvent.BreakEvent event) {
        Level level = event.getPlayer().level;
        BlockPos pos = event.getPos();
        Player player = event.getPlayer();

        if (player.getAbilities().instabuild)
            return;

        if (alreadyBreaking)
            return;

        if (event.getPlayer() instanceof FakePlayer)
            return;

        if (ConfigurationHandler.SPEED.blockBlacklist.get().contains(Objects.requireNonNull(event.getState().getBlock().getName()).toString()))
            return;

        if (playersWithButtonDown.contains(player.getUUID())) {
            alreadyBreaking = true;
            event.setCanceled(true);
            level.removeBlock(pos, true);
            ItemEntity itementity = new ItemEntity(level, pos.getX(), pos.getY() + 0.5, pos.getZ(), event.getState().getBlock().asItem().getDefaultInstance());
            itementity.setDefaultPickUpDelay();
            level.addFreshEntity(itementity);
            setAlreadyBreaking(false);
        }
    }

    public static void setAlreadyBreaking(boolean breaking) {
        alreadyBreaking = breaking;
    }

    public static void addPlayer(UUID uuid) {
        playersWithButtonDown.add(uuid);
    }

    public static void removePlayer(UUID uuid) {
        playersWithButtonDown.removeIf(u -> u.equals(uuid));
    }

    @SubscribeEvent
    public static void SilkTouch(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();

        if (player.getAbilities().instabuild)
            return;

        if (alreadyBreaking)
            return;

        if (event.getEntity() instanceof FakePlayer)
            return;

        if (playersWithButtonDown.contains(player.getUUID())) {
            alreadyBreaking = true;
            event.setNewSpeed(event.getOriginalSpeed() / ConfigurationHandler.SPEED.miningSpeed.get());
            setAlreadyBreaking(false);
        }

    }
}
