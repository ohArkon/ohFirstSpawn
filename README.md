# ohFirstSpawn

A lightweight and configurable plugin that automatically teleports new players to a designated spawn point on their first join. Perfect for servers that want to ensure all new players start at the same location with a welcoming experience.

## ✨ Features

- **Automatic Detection**: Identifies first-time players and teleports them after a configurable delay
- **One-Time Only**: Players are only teleported on their very first join, never again
- **Fully Configurable**: All messages, sounds, and timings can be customized
- **Multi-Language Support**: Comes with English and Italian configs
- **Sound Effects**: Optional teleport sounds for better user experience
- **Permission-Based**: Secure command system with proper permission checks
- **Lightweight**: Minimal performance impact with efficient player tracking
- **Data Persistence**: Remembers processed players across server restarts

## 🎮 Commands

| Command | Description | Permission |
|---------|-------------|------------|
| `/ofs set` | Set the first spawn location | `ohfirstspawn.set` |
| `/ofs info` | View current spawn configuration and statistics | `ohfirstspawn.set` |
| `/ofs reload` | Reload plugin configuration | `ohfirstspawn.set` |

## 🔧 Configuration

The plugin features a comprehensive `config.yml` where you can customize:

- Spawn coordinates (world, x, y, z, yaw, pitch)
- All plugin messages with color code support
- Teleport delay (in ticks)
- Sound effects on/off
- Custom teleport sounds

### Example Configuration

```yaml
[code=YAML]# ohFirstSpawn Configuration File

# Spawn configuration
spawn:
  world: world
  x: 0
  y: 0
  z: 0
  yaw: 0
  pitch: 0

# List of players who already spawned for the first time
players: []
# Customizable messages (supports color codes with &)
messages:
  # Command messages
  only-player: '&cThis command can only be executed by a player!'
  no-permission: '&cYou don''t have permission to use this command!'
  usage: '&cUsage: /ofs set | /ofs info'
 
  # Spawn set messages
  spawn-set: '&aSpawn for new players set successfully!'
  spawn-position: '&7Position: &f{x}, {y}, {z} &7in world &f{world}'
  spawn-rotation: '&7Yaw: &f{yaw} &7Pitch: &f{pitch}'
 
  # Info messages
  spawn-info: '&aSpawn configured:'
  spawn-world: '&7World: &f{world}'
  spawn-coordinates: '&7Coordinates: &f{x}, {y}, {z}'
  spawn-not-configured: '&cSpawn not configured yet!'
  spawn-load-error: '&cError loading spawn!'
  players-processed: '&7Players processed: &f{count}'


# Plugin settings
settings:
  # Delay in ticks before teleport (20 ticks = 1 second)
  teleport-delay: 20
 
  # Enable sounds on teleport
  play-sounds: true
 
  # Sound to play (only if play-sounds is true)
  teleport-sound: ENTITY_ENDERMAN_TELEPORT
[/code]
```

## 🔐 Permissions

| Permission | Description | Default |
|------------|-------------|---------|
| `ohfirstspawn.set` | Allow setting spawn locations and using commands | `op` |

## 📋 Compatibility

- **Minecraft Versions**: 1.17+
- **Server Software**: Spigot, Paper, Purpur
- **Java**: 17+

## 📦 Installation

1. Download the plugin JAR file from [SpigotMC](https://www.spigotmc.org/resources/ohfirstspawn.127596)
2. Place it in your server's `plugins` folder
3. Restart or reload your server
4. Configure messages in `config.yml` if desired
5. Set spawn location with `/ofs set`
6. Done! New players will automatically be teleported

## 🚀 Usage

1. **Set spawn location**: Stand where you want new players to spawn and run `/ofs set`
2. **Check configuration**: Use `/ofs info` to view current spawn settings
3. **Customize messages**: Edit the `config.yml` file to change messages and settings
4. **Reload configuration**: Use `/ofs reload` to apply config changes without restart

## 🌍 Multi-Language Support

The plugin comes with pre-configured language files:

- **English** (`config.yml`)

Simply replace the default config with your preferred language version.

## 🔨 Building

This project uses Maven for dependency management and building.

```bash
git clone https://github.com/yourusername/ohFirstSpawn.git
cd ohFirstSpawn
mvn clean package
```

The compiled JAR will be available in the `target/` directory.

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📞 Support

If you encounter any issues or have questions:

- Create an [Issue](../../issues) on GitHub
- Check the [Wiki](../../wiki) for documentation
- Join our [Discord](https://discord.gg/yourserver) (if applicable)

## ⭐ Star History

If you found this plugin helpful, please consider giving it a star!
