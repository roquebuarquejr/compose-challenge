/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.data

import androidx.annotation.DrawableRes
import com.example.androiddevchallenge.R

data class PuppyDto(
    val id: Long,
    val name: String,
    val breeds: String,
    val description: String,
    val instagram: String,
    val age: Int,
    @DrawableRes val image: Int,
    @DrawableRes val cover: Int
) {

    companion object {

        fun getPuppies(): List<PuppyDto> {
            return listOf(
                PuppyDto(
                    0L,
                    "Juca",
                    "French Bulldog",
                    "Juca is a french bulldog 4 years old very friendly",
                    "@jucafrenchbulldog",
                    4,
                    R.drawable.ic_juca,
                    R.drawable.ic_juca_cover
                ),
                PuppyDto(
                    1L,
                    "Pistolinha",
                    "Chiuaue",
                    "Pistolinha is a  who loves sleep",
                    "pistolinha_chiuaue",
                    3,
                    R.drawable.ic_pistolinha,
                    R.drawable.ic_pistolinha_cover
                ),
                PuppyDto(
                    2L,
                    "Luna",
                    "Bernese",
                    "Luna is a very friendly dog, she likes to be nearby",
                    "luna.bernese",
                    2,
                    R.drawable.ic_luna,
                    R.drawable.ic_luna_cover
                ),
                PuppyDto(
                    3L,
                    "Juca",
                    "French Bulldog",
                    "Juca is a french bulldog 4 years old very friendly",
                    "@jucafrenchbulldog",
                    4,
                    R.drawable.ic_juca,
                    R.drawable.ic_juca_cover
                ),
                PuppyDto(
                    4L,
                    "Pistolinha",
                    "Chiuaue",
                    "Pistolinha is a  who loves sleep",
                    "pistolinha_chiuaue",
                    3,
                    R.drawable.ic_pistolinha,
                    R.drawable.ic_pistolinha_cover
                ),
                PuppyDto(
                    5L,
                    "Luna",
                    "Bernese",
                    "Luna is a very friendly dog, she likes to be nearby",
                    "luna.bernese",
                    2,
                    R.drawable.ic_luna,
                    R.drawable.ic_luna_cover
                ),
                PuppyDto(
                    6L,
                    "Juca",
                    "French Bulldog",
                    "Juca is a french bulldog 4 years old very friendly",
                    "@jucafrenchbulldog",
                    4,
                    R.drawable.ic_juca,
                    R.drawable.ic_juca_cover
                ),
                PuppyDto(
                    7L,
                    "Pistolinha",
                    "Chiuaue",
                    "Pistolinha is a  who loves sleep",
                    "pistolinha_chiuaue",
                    3,
                    R.drawable.ic_pistolinha,
                    R.drawable.ic_pistolinha_cover
                ),
                PuppyDto(
                    8L,
                    "Luna",
                    "Bernese",
                    "Luna is a very friendly dog, she likes to be nearby",
                    "luna.bernese",
                    2,
                    R.drawable.ic_luna,
                    R.drawable.ic_luna_cover
                ),
                PuppyDto(
                    9L,
                    "Juca",
                    "French Bulldog",
                    "Juca is a french bulldog 4 years old very friendly",
                    "@jucafrenchbulldog",
                    4,
                    R.drawable.ic_juca,
                    R.drawable.ic_juca_cover
                ),
                PuppyDto(
                    10L,
                    "Pistolinha",
                    "Chiuaue",
                    "Pistolinha is a  who loves sleep",
                    "pistolinha_chiuaue",
                    3,
                    R.drawable.ic_pistolinha,
                    R.drawable.ic_pistolinha_cover
                ),
                PuppyDto(
                    11L,
                    "Luna",
                    "Bernese",
                    "Luna is a very friendly dog, she likes to be nearby",
                    "luna.bernese",
                    2,
                    R.drawable.ic_luna,
                    R.drawable.ic_luna_cover
                )

            )
        }
    }
}
