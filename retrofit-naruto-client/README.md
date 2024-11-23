# Retrofit Naruto Client

This is a simple type-safe HTTP client for a Naruto API called [Dattebayo](https://api-dattebayo.vercel.app/), this demonstrates how to use [Retrofit](https://square.github.io/retrofit/) to make network calls to an API.

## Features

-   [x] Get all characters
-   [x] Get character by ID

## Running the project

You can run the project using the following command from the root directory:

```bash
./gradlew run
```

> [!NOTE]
> Alternatively, you can only dowload the executable JAR file from the [releases](https://github.com/N3VERS4YDIE/mobile-dev-eam/releases/tag/retrofit-naruto-client) page and run it using the following command:
>
> ```bash
> java -jar retrofit-naruto-client.jar
> ```

## Output Example

```
- GET /characters/1293

Id: 1293
Name: Itachi Uchiha
Images:
    https://static.wikia.nocookie.net/naruto/images/b/bb/Itachi.png
Jutsu:
    Amaterasu
    Chakra Suppression Technique (Novel only)
    Clone Great Explosion
    Crow Clone Technique
    Demonic Illusion: Mirage Crow
    Demonic Illusion: Mirror Heaven and Earth Change
    Demonic Illusion: Shackling Stakes Technique
    Early Sacrifice
    Ephemeral
    Fire Release: Great Fireball Technique
    ... (Showing 10 Jutsu of 26)
Tools:
    Military Rations Pill (Anime only)
    Sword
    Sword of Totsuka
    Tantō (Anime only)
    Yata Mirror

- GET /characters?limit=5

Id: 1344
Name: Naruto Uzumaki
Images:
    https://static.wikia.nocookie.net/naruto/images/d/d6/Naruto_Part_I.png
    https://static.wikia.nocookie.net/naruto/images/7/7d/Naruto_Part_II.png
Jutsu:
    All Directions Shuriken
    Baryon Mode
    Big Ball Rasengan
    Big Ball Rasenshuriken
    Big Ball Spiralling Serial Zone Spheres
    Boil Release: Unrivalled Strength
    Chakra Transfer Technique
    Clone Body Blow
    Clone Flying Arrow
    Clone Spinning Heel Drop (Anime only)
    ... (Showing 10 Jutsu of 94)
Tools:
    Absorbing Hand
    Bō (Anime only)
    Chakra Blade (Anime only)
    Flying Thunder God Kunai
    Fūma Shuriken
    Hidden Kunai Mechanism
    Konoha Chakra Blade
    Sand
    Stone of Gelel

Id: 1307
Name: Sasuke Uchiha
Images:
    https://static.wikia.nocookie.net/naruto/images/2/21/Sasuke_Part_1.png
    https://static.wikia.nocookie.net/naruto/images/1/13/Sasuke_Part_2.png
Jutsu:
    Afterglow
    Amaterasu
    Amaterasu: Flame Wrapping Fire
    Amenotejikara
    Animal Path
    Area Scanning Technique
    Asura Path
    Banshō Ten'in (Anime only)
    Binding Snake Glare Spell
    Blaze Release: Honoikazuchi (Anime only)
    ... (Showing 10 Jutsu of 75)
Tools:
    Bow & Arrow
    Fūma Shuriken
    Katar
    Kusari (Anime only)
    Mind Awakening Pill
    Scalpel (Anime only)
    Sword
    Sword of Kusanagi
    Wire Strings

Id: 1299
Name: Madara Uchiha
Images:
    https://static.wikia.nocookie.net/naruto/images/f/fd/Madara.png
Jutsu:
    Animal Path
    Asura Path
    Black Receiver
    Blocking Technique Absorption Seal
    Chibaku Tensei
    Demonic Statue Chains
    Deva Path
    Evil Disturbance Waltz
    Fire Release: Dragon Flame Release Song Technique
    Fire Release: Great Fire Annihilation
    ... (Showing 10 Jutsu of 51)
Tools:
    Fūma Shuriken
    Gunbai
    Kama
    Spear (Anime only)
    Sword
    Tantō (Anime only)

Id: 376
Name: Kakashi Hatake
Images:
    https://static.wikia.nocookie.net/naruto/images/2/27/Kakashi_Hatake.png
    https://static.wikia.nocookie.net/naruto/images/2/25/Kakashi_Part_III.png
Jutsu:
    Area Scanning Technique
    Chidori
    Demonic Illusion: Hell Viewing Technique
    Earth Release: Double Suicide Decapitation Technique
    Earth Release: Earth Wave Technique (Anime only)
    Earth Release: Earth-Style Wall
    Earth Release: Hiding Like a Mole Technique
    Earth Release: Multiple Earth-Style Wall (Anime only)
    Earth Release: Rending Drill Fang
    Eight Gates
    ... (Showing 10 Jutsu of 47)
Tools:
    Bow & Arrow (Anime only)
    Chakra-Suppressing Seal
    Flying Thunder God Kunai
    Kubikiribōchō
    Kusarigama (Anime only)
    Lightning Barrel (Anime only)
    Makibishi
    Sword
    Tantō
    White Light Chakra Sabre
    ... (Showing 10 Tools of 11)

Id: 928
Name: Orochimaru
Images:
    https://static.wikia.nocookie.net/naruto/images/1/14/Orochimaru_Infobox.png
    https://static.wikia.nocookie.net/naruto/images/b/be/Orochimaru_Part_III.png
Jutsu:
    Binding Snake Glare Spell
    Casualty Puppet (Anime only)
    Chakra Scalpel (Anime only)
    Earth Release: Hiding Like a Mole Technique
    Earth Release: Shadow Clone (Anime only)
    Eight Branches Technique
    Eight Trigrams Sealing Style (Anime only)
    Five Elements Seal
    Five Elements Unseal (Anime only)
    Formation of Ten Thousand Snakes
    ... (Showing 10 Jutsu of 47)
Tools:
    Genjutsu Pill (Anime only)
    Mind Awakening Pill
    Poison
    Shinigami Mask
    Sword
    Sword of Kusanagi
```
