package com.wyy.animationdrawable

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wyy.animationdrawable.ui.theme.AnimationDrawableTheme
import com.wyy.animationdrawable.ui.theme.Black900
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationDrawableTheme {
                RecordingView()
            }
        }
    }
}


@SuppressLint("RememberReturnType")
@Composable
fun RecordingView() {
    var resIntForVoice by remember {
        mutableStateOf(R.mipmap.voice_recognize_1)
    }
    LaunchedEffect(Unit) {
        infiniteWithPeriod(300).collect {
            resIntForVoice = getVoiceResIntByRemainder(it % 3)  //  因为只有3张图片，所有这里求3的余数，当余数为0时，加载最后一张图片。循环类型类似于restart，每次都是重新开始。Value is on 012 by 012 etc.
        }
    }
    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(color = Black900), contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(id = resIntForVoice), contentDescription = "正在录音提示")
    }
}

fun getVoiceResIntByRemainder(remainder: Int) = when (remainder) {
    1 -> R.mipmap.voice_recognize_1
    2 -> R.mipmap.voice_recognize_2
    else -> R.mipmap.voice_recognize_3
}

/**
 * 无限并且周期性发送一条消息的流
 * @param periodInTimeMillis 周期时长
 * 第一个发送的值为1
 */
fun infiniteWithPeriod(periodInTimeMillis: Long) = flow {
    var counter = 0
    while (true) {
        emit(++counter)
        delay(periodInTimeMillis)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AnimationDrawableTheme {
        RecordingView()
    }
}