# Easy Client V2

A Fabric mod for Minecraft 1.21.1.  
Press **Left Ctrl** in-game to open a GUI and send a custom packet to the server.

## Features
- Left Ctrl opens a clean overlay screen
- Type any message, press **Enter** or click **Send**
- Sends a real `CustomPayload` (C2S) packet — not a command
- Server echoes the message back to confirm receipt
- Easily extend the server handler with your own logic

---

## Building (produces the .jar)

### Requirements
- Java 21 JDK ([Adoptium](https://adoptium.net/) recommended)
- Internet connection (Gradle downloads Fabric toolchain on first run)

### Steps

```bash
# 1. Clone / unzip this project, then enter the folder
cd EasyClientV2

# 2. (Linux/macOS) make the wrapper executable
chmod +x gradlew

# 3. Build
./gradlew build          # Linux/macOS
gradlew.bat build        # Windows
```

The compiled `.jar` will be at:
```
build/libs/easyclientv2-1.0.0.jar
```

---

## Installing via Modrinth App

1. Build the jar as above (or grab a release jar).
2. Open the **Modrinth App**.
3. Select your **1.21.1 Fabric** instance.
4. Click **"Add Content"** → **"From file"** (or drag the `.jar` into the mods list).
5. Make sure **Fabric API** is installed in the same instance.
6. Launch and press **Left Ctrl** in-game!

---

## Customising server-side logic

Edit the receiver lambda in:
```
src/main/java/com/example/easyclientv2/network/EasyPackets.java
```

Look for the comment:
```java
// ── Put your custom server logic here ──
```

---

## License
MIT
