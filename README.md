# Template

## 组成
- [ ] DataBinding
- [ ] Lifecycle
- [ ] LiveData
- [ ] ViewModel
- [ ] Retrofit
- [ ] Koroutine
- [ ] [依赖](https://developer.android.com/jetpack/androidx/releases/viewpager2) [官方文档](https://developer.android.com/training/animation/screen-slide-2)

## 细节
- [ ] Proguard
- [ ] Timber

## 即将加入
- [ ] Room (last time for refresh today api)
- [ ] Paging?
- [ ] Navigation?
- [ ] 多 Module

and so on

## 这个 repo 对我的意义
首先为了练习 `MVVM` 架构，或者说 `Clean` 架构，和各种新的库。还有一些开源的 CI。
作为脚手架，让我遇到新的项目的时候可以快速启动，只需要 clone 时换个名字即可。

## 下一步
这里有一些是结构性的，优先级较高，有一些只是优化，可以不做，或者说最好不做，因为脚手架不应该有太多细节的东西。

- [ ] 加入网络获取的状态。网络有成功有失败，需要添加状态。
- [ ] 加入数据库，将拉下来的数据先存入数据库，在没网的时候直接展示。或者在网络接口获取错误后，展示数据库里残存的数据（要告知用户这是之前的数据）
- [ ] 知乎日报的主要UI
- [ ] 保存用户之前阅读的位置
- [ ] StateView

## Thanks To
[知乎日报API](https://github.com/nonoroazoro/Zhihu-Daily-Reader/blob/master/Zhihu-Daily-API.md)
