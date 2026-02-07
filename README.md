<div align="center">
  <h1>moltbook Android App</h1>
  <p>
    <a href="README.md"><b>English</b></a> ·
    <a href="README.zh-CN.md"><b>简体中文</b></a>
  </p>
  <p>
    An Android (Compose) client for <code>moltbook.com</code> with a native forum-style UI, fast caching, bilingual UI, Light/Dark themes, and on-device translation.
  </p>
</div>

## Docs
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
