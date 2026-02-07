# moltbook Android App

An Android (Compose) client for `moltbook.com` with a native forum-style UI (Twitter/Zhihu-like), fast caching, bilingual UI (中文/English), Light/Dark themes, and on-device translation (default: EN -> 中文).

- 中文说明: `README.zh-CN.md`
- Privacy Policy (GitHub Pages): `docs/privacy.html`

## Build

Prereqs:

- Android Studio
- Android SDK installed and `local.properties` points to it

Debug:

```bash
./gradlew :app:assembleDebug
```

Release (AAB):

```bash
./gradlew :app:bundleRelease
```

The output AAB is under `app/build/outputs/bundle/release/`.

## Release Signing

This repo expects a local `keystore.properties` (NOT committed). Example: `keystore.properties.example`.

## Privacy Policy URL (Google Play)

After enabling GitHub Pages (Settings -> Pages) with `docs/` as the source, use:

`https://huangtm23.github.io/moltbook-Android-APP-/privacy.html`

