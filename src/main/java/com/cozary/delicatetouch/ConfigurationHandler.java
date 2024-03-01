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

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Arrays;
import java.util.List;

public class ConfigurationHandler {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final Speed SPEED = new Speed(BUILDER);
    public static final ForgeConfigSpec spec = BUILDER.build();

    public static class Speed {
        public final ForgeConfigSpec.IntValue miningSpeed;
        public final ForgeConfigSpec.ConfigValue<List<? extends String>> blockBlacklist;


        Speed(ForgeConfigSpec.Builder builder) {
            builder.comment("Indicates the number by which the mining speed divided (OriginalSpeed / \"value\"). If you type 1, slow mining is disabled.");

            miningSpeed = builder
                    .defineInRange("value", 4, 0, 100);

            builder.comment("Black List Blocks. List of Blocks that cause a problem when using the Mod, due to duplications or breaking the game. (Vanilla blocks added by default).");

            blockBlacklist = builder
                    .defineList("blockBlacklist",
                            Arrays.asList("minecraft:cake", "minecraft:big_dripleaf", "minecraft:lilac", "minecraft:rose_bush", "minecraft:peony"
                                    , "minecraft:white_bed", "minecraft:orange_bed", "minecraft:magenta_bed", "minecraft:light_blue_bed", "minecraft:yellow_bed", "minecraft:lime_bed", "minecraft:pink_bed", "minecraft:gray_bed", "minecraft:light_gray_bed", "minecraft:cyan_bed", "minecraft:purple_bed", "minecraft:blue_bed", "minecraft:brown_bed", "minecraft:green_bed", "minecraft:red_bed", "minecraft:black_bed"
                                    , "minecraft:sunflower")
                            , it -> it instanceof String);

        }
    }
}