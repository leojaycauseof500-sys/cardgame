package com.liuliangjie.cardgame.enums

import java.net.URL

//todo 后续需要改造，用json来保持资源的元数据，过渡一下
enum class UIAssetEnum(
    val assetName: String,
    val url: String ,
    val desc : String
) {
    DefaultButtonNinePatch("DefaultButton","assets/ui/button/tile_0030.png","默认的可伸缩按钮的九宫格图片")
}