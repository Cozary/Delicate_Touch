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

import com.cozary.delicatetouch.DelicateTouch;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ExcavationPacketHandler {
    public static SimpleChannel INSTANCE;
    private static int ID = 0;

    public static int nextID() {
        return ID++;
    }

    public static void registerMessages() {
        INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(DelicateTouch.MOD_ID, "delicate_touch"), () -> "1.0", s -> true, s -> true);

        INSTANCE.registerMessage(nextID(),
                PacketKeyIsDown.class,
                PacketKeyIsDown::toBytes,
                PacketKeyIsDown::new,
                PacketKeyIsDown::handle);

        INSTANCE.registerMessage(nextID(),
                PacketKeyIsUp.class,
                PacketKeyIsUp::toBytes,
                PacketKeyIsUp::new,
                PacketKeyIsUp::handle);
    }
}
