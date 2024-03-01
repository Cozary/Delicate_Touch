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

package com.cozary.delicatetouch;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.glfw.GLFW;

import java.util.List;


public class KeyBindings {
    public static final KeyMapping excavate;
    private static final String categoryName = DelicateTouch.NAME;
    private static final List<KeyMapping> allBindings;

    static {
        allBindings = ImmutableList.of(
                excavate = new KeyMapping("key.delicate_touch", KeyConflictContext.IN_GAME, getKey(GLFW.GLFW_KEY_LEFT_SHIFT), categoryName)
        );
    }

    private KeyBindings() {

    }

    static InputConstants.Key getKey(int key) {
        return InputConstants.Type.KEYSYM.getOrCreate(key);
    }

    @SubscribeEvent
    public void registerKeys(RegisterKeyMappingsEvent event){
        for (KeyMapping binding : allBindings) {
            event.register(binding);
        }
    }

}
