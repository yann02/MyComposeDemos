# MyComposeDemos
记录一些Compose实现的小demo。
| Demos | |
|:-----|---------|
| <h3>AnimationDrawable</h3>一个录音动画的Demo，功能类似于播放一个由多张图片连续播放的视图动画（帧动画） <br>传统View的动画有视图动画和属性动画，但是Compose的动画是通过状态驱动，其行为类似属性动画，没有办法直接实现像帧动画这种视图动画。<br>实现思路：<br> 使用Kotlin flow发送无限定时事件，在Compose副作用Api LaunchedEffect中收集该流，LaunchedEffect在Composable组件进入dispose时取消该流的收集。<br><br>**[> Browse](01_AnimationDrawable/)**<br><br> | <img src="screenshots/voice_anim.gif" width="100" alt="voice demo"> |
