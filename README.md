# Permissions Viewer for Axycord

See which permissions a server role has, where a member gets their permissions, and what a channel explicitly allows or denies.

## What you can view

- **Server roles** show which permissions are granted or unset for each role.
- **Members** show server-level permissions and their granting roles, including `@everyone`, Administrator roles, and server ownership. Switch between the highest and lowest granting role. The plugin remembers your choice.
- **Channels and threads** show explicit allows and denies for individual roles and members. Threads display the overwrites from their parent channel.

## Download and install

1. Open the [latest release](https://github.com/mmaarij123/axycord-permissions-viewer/releases/latest) and download **Axycord-PermissionsViewer-debug.apk** under **Assets**, or use the [direct APK download](https://github.com/mmaarij123/axycord-permissions-viewer/releases/latest/download/Axycord-PermissionsViewer-debug.apk).
2. Install the APK on the Android device that runs Axycord.
3. Open **Axycord Settings → Plugins**, select **Permissions Viewer**, approve its signing certificate if prompted, and enable it.

## Open the viewer

- Long-press a server, channel, or thread and choose **View Permissions**.
- For a member, choose **View Permissions** from their actions menu or server profile.
- Use the dropdown to inspect an individual role or overwrite. The details screen also lets you copy the selected target's ID.

## Technical details

Written in Kotlin using Axycord's plugin SDK 1.0.6 and plugin API level 3. The plugin reads roles, members, and channel overwrites from the host's local stores. It displays IDs when target names are unavailable and uses the active Axycord theme's fonts and colors.

The Android package and Kotlin namespace follow the Axycord template's pattern, `mmaarij.axycord.permissionsviewer`. The plugin ID is `axycord.permissionsviewer`. Its discovery service uses the `mmaarij.axycord.PLUGIN` action.

Ported from [Permissions Viewer for Microcord](https://github.com/mmaarij123/microcord-permissions-viewer) by Maarij using the [Axycord Plugin Template](https://github.com/Axycord/Axycord-Plugin-Template). The feature was inspired by [Vencord’s Permissions Viewer](https://github.com/Vendicated/Vencord/tree/main/src/plugins/permissionsViewer) by Nuckyz and Vendicated.

### Build from source

Clone this repository and install JDK 21, Android SDK Platform 37.0, and Build Tools 36.0.0. Set `ANDROID_HOME` to your SDK directory or configure `sdk.dir` in `local.properties`.

**Windows**

```powershell
.\gradlew.bat lintDebug assembleDebug
```

**macOS or Linux**

```sh
./gradlew lintDebug assembleDebug
```

The APK is written to `build/outputs/apk/debug/`. GitHub Actions runs the same lint and build checks and saves the APK as an artifact.

Normal builds use the published Axycord SDK. To use a local host checkout, generate its SDK with `scripts/build.sh pluginSdkJar`, then pass `-PaxycordCheckout=/path/to/axycord` to Gradle.

For a signed release build, copy `keystore.properties.example` to `keystore.properties`, fill in your signing-key details, and run `assembleRelease`. Reuse the signing key for updates. The downloadable debug APK uses the maintainer's Android debug key.

## License

The Permissions Viewer implementation is available under the [MIT License](LICENSE). The Gradle Wrapper retains its [Apache License 2.0](gradle/wrapper/LICENSE). See [NOTICE.md](NOTICE.md) for template provenance and separately distributed SDK licensing.
