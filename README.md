ohFirstSpawn - First Time Player Spawn Teleporter

A lightweight and configurable plugin that automatically teleports new players to a designated spawn point on their first join. Perfect for servers that want to ensure all new players start at the same location with a welcoming experience.

✨ Features
Automatic Detection: Identifies first-time players and teleports them after a configurable delay
One-Time Only: Players are only teleported on their very first join, never again
Fully Configurable: All messages, sounds, and timings can be customized
Multi-Language Support: Comes with English and Italian configs
Sound Effects: Optional teleport sounds for better user experience
Permission-Based: Secure command system with proper permission checks
Lightweight: Minimal performance impact with efficient player tracking
Data Persistence: Remembers processed players across server restarts
Commands
/ofs set - Set the first spawn location (Permission: ohfirstspawn.set)
/ofs info - View current spawn configuration and statistics
/ofs reload - Reload plugin configuration
Configuration
The plugin features a comprehensive config.yml where you can customize:

Spawn coordinates (world, x, y, z, yaw, pitch)
All plugin messages with color code support
Teleport delay (in ticks)
Sound effects on/off
Custom teleport sounds
Permissions
ohfirstspawn.set - Allow setting spawn locations (default: op)
Compatibility
Minecraft Versions: 1.17+
Server Software: Spigot, Paper, Purpur
Java: 17+
Installation
Download the plugin JAR file
Place it in your server's plugins folder
Restart or reload your server
Configure messages in config.yml if desired
Set spawn location with /ofs set
Done! New players will automatically be teleported
