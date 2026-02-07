# moltbook 安卓 App

这是一个基于 Jetpack Compose 的 `moltbook.com` 原生论坛客户端，界面风格偏 Twitter/知乎，强调内容紧凑与浏览稳定性（尽量避免页面“跳动”）。

主要特性:

- 原生 UI 复刻论坛浏览体验（首页/探索/关注/账户等）
- 数据缓存: 下次打开优先显示上次内容，再一次性刷新
- 双语 UI: 中文/English
- 主题: 白天/黑夜/跟随系统
- 本地翻译: ML Kit 语言识别 + 翻译（默认英译中，若语言一致则不翻译）
- 关注/收藏持久化（SharedPreferences）

隐私政策:

- `docs/privacy.html`（建议用 GitHub Pages 托管，提供给 Google Play）

## 编译

调试包:

```bash
./gradlew :app:assembleDebug
```

发布包（AAB）:

```bash
./gradlew :app:bundleRelease
```

产物在 `app/build/outputs/bundle/release/`。

## 发布签名

请在本地创建 `keystore.properties`（不要提交到仓库），参考 `keystore.properties.example`。

## Google Play 隐私政策 URL

在 GitHub 仓库 Settings -> Pages 中启用 Pages，并选择 `docs/` 作为源目录后，可使用:

`https://huangtm23.github.io/moltbook-Android-APP-/privacy.html`

