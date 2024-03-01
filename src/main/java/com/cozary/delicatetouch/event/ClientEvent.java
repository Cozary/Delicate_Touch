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

import com.cozary.delicatetouch.DelicateTouch;
import com.cozary.delicatetouch.KeyBindings;
import com.cozary.delicatetouch.network.ExcavationPacketHandler;
import com.cozary.delicatetouch.network.PacketKeyIsDown;
import com.cozary.delicatetouch.network.PacketKeyIsUp;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DelicateTouch.MOD_ID, value = Dist.CLIENT)
public class ClientEvent {

    private static boolean excavationPressed = false;

    @SubscribeEvent
    public static void pressKey(InputEvent.Key e) {
        //press
        if ((KeyBindings.excavate.isDown()) && !excavationPressed) {
            excavationPressed = true;
            ExcavationPacketHandler.INSTANCE.sendToServer(new PacketKeyIsDown());
        }

        //release
        if ((!KeyBindings.excavate.isDown()) && excavationPressed) {
            excavationPressed = false;
            ExcavationPacketHandler.INSTANCE.sendToServer(new PacketKeyIsUp());
        }
    }
}
