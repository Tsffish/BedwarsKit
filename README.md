# Introduction

[点击查看中文版介绍](README_zh.md)
A Addon for BedwarsRel, Added some features to BedwarsRel

### Depend:

- BedwarsRel
- ProtocolLib(5.1.0 or above)

## Functions:

- Floating text
- Resource fragmentation
- Open Shop at any entity (Can be quickly set up)
- Creative Flight Fix
- Seize resources on kill
- Custom Game ScoreBoard
- Leave Team on waiting
- Select Kit & ForceKit
- Disable villager AI
- Auto Clean Bottle
- Kill Information
- Break bed title
- No Death Gui
- Start Message
- No Armor Drop
- FoodLevel Lock
- No Swords break
- Damage feedback
- No EnderPearl Damage
- Auto Clean hostile on start
- Anti EnderMan Moving Blocks
- Protect map outside of the game
- Team Enchant & Team Effect Shop
- Armor LevelUp & Keep Armor Level
- Respawn Delay & Respawn Message
- No BlockPlace at TeamSpawn & ResSpawn
- Game Task (ResSpawner SpeedUp & Death Mode)

- (Most functions can be customized)

## Download

You can download the latest version [here](https://www.spigotmc.org/resources/bedwarskit.105616/)

## Noticed:

- By default, plugins allow you to open any store through all entities, but you need to first set the name ,enter/bwk
  edit as a player to enter editing mode
- In editing mode, entities can be named and set as item stores or upgrade stores. After editing, enter/bwk edit again
  to exit editing mode

## Installation:

- 1.Stop your server
- 2.Place the plugin jar file in the plugins folder of the root directory
- 3.Start the server, enter the plugins/BedwarsKit folder, and modify the content under "Basic Config"
- 4.The plugin is enabled, and you can use/bwk to view command help

## Usage:

- 1.Make sure your world name includes the "gameWorld" entry in config to make the plugin recognize it.
- On this basis, match "gameWorld2v2" and "gameWorld4v4" to distinguish between two different patterns
- Finally, make sure that your lobby name includes the items in "lobbyWorld" (The standard for determining a world name
  is to include but not equal to (except for the lobby))

- 2.Ensure relTeamColorName_ Xxx Eight teams correspond to your BedwarsRel team name (displayed name not color name)

## Commands:

- /bwk - display help message
- /bwk reload - reload the config
- /bwk edit - toggle edit mode