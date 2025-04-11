# Outpost Generator
A very simple repository for emulating Pillager Outpost piece placement. Loot is currently unsupported. The generator works best for large, flat surfaces; jagged or uneven terrain will often make it return incorrect results.

## How to use
Currently, the JitPack setup for this repo doesn't work.
For now you can use it by downloading the latest release (jar file),
and declaring it as a local dependency in your `build.gradle`:

```
dependencies {
    // library dependencies
    implementation('com.seedfinding:mc_math:1.171.0') 		{ transitive = false }
    implementation('com.seedfinding:mc_seed:1.171.1') 		{ transitive = false }
    implementation('com.seedfinding:mc_core:1.210.0') 		{ transitive = false }
    implementation('com.seedfinding:mc_noise:1.171.1') 		{ transitive = false }
    implementation('com.seedfinding:mc_reversal:1.171.1') 	{ transitive = false }
    implementation('com.seedfinding:mc_biome:1.171.1') 		{ transitive = false }
    implementation('com.seedfinding:mc_terrain:1.171.1') 	{ transitive = false }
    implementation('com.seedfinding:mc_feature:1.171.9') 	{ transitive = false }
    implementation group: 'it.unimi.dsi', name: 'fastutil-core', version: '8.5.13'

    implementation files('path/to/jar/file')
}
```
