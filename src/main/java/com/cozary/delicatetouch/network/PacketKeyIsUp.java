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

package com.cozary.delicatetouch.network;

import com.cozary.delicatetouch.event.ServerEvent;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketKeyIsUp {

    public PacketKeyIsUp(FriendlyByteBuf buf) {
    }

    public PacketKeyIsUp() {
    }

    public void toBytes(FriendlyByteBuf buf) {
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            ServerEvent.removePlayer(player.getUUID());
        });
    }
}
