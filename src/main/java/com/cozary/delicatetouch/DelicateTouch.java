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

import com.cozary.delicatetouch.network.ExcavationPacketHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("delicate_touch")
public class DelicateTouch {

    public static final String MOD_ID = "delicate_touch";
    public static final String NAME = "delicate_touch.name";
    public static final Logger LOGGER = LogManager.getLogger();

    public DelicateTouch() {
        final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> clientStart(eventBus));


        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigurationHandler.spec);
        ExcavationPacketHandler.registerMessages();

        MinecraftForge.EVENT_BUS.register(this);
    }

    private static void clientStart(IEventBus modEventBus) {

    }

}
