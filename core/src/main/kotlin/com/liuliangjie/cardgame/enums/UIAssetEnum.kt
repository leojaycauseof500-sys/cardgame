package com.liuliangjie.cardgame.enums

// 资产枚举已废弃，改用基于JSON配置的资产管理系统
// 此枚举仅保留向后兼容性
enum class UIAssetEnum(
    val assetName: String,
    val url: String,
    val desc: String
) {
    DefaultButtonNinePatch("DefaultButton", "assets/ui/button/tile_0030.png", "默认的可伸缩按钮的九宫格图片"),
    SourceHanMedium("SourceHanMedium", "assets/fonts/zhs/SourceHanSerifSC-Medium.otf", "思源黑体"),
}